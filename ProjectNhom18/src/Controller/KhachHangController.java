
package Controller;

import model.CuaHang;
import model.KhachHang;
import model.KhachHangThanThiet;
import model.Observer;
import model.SanPham;
import utils.DBConnection;
import view.QLKHView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class KhachHangController {
    private QLKHView view;
    private CuaHang model;

    public KhachHangController(QLKHView view, CuaHang model) {
        this.view = view;
        this.model = model;
        capNhatBang();
        initController();

    }

    private void initController() {
        view.getBtnThem().addActionListener(e -> themKH());
        view.getBtnXoa().addActionListener(e -> xoaKH());
        view.getBtnTim().addActionListener(e -> search());
        
        view.getBtnThongBao().addActionListener(e ->{
        	String tb = view.getTfThongBao().getText();
        	if (!tb.isEmpty()) {
        		 JOptionPane.showMessageDialog(null, "Đã gửi thông báo đến tất cả khách hàng");
        	}
        });
        

        capNhatBang();
        
        view.getBtnSua().addActionListener(e -> {
	        int row = view.getTblKH().getSelectedRow();
	        if (row != -1) {
	            String maKH = (String) view.getModelKH().getValueAt(row, 0);
	            String tenKH = JOptionPane.showInputDialog(this, "Nhập tên KH mới:");
	            String sdt = JOptionPane.showInputDialog(this, "Nhập số điện thoại mới:");
	            String dc = JOptionPane.showInputDialog(this, "Nhập địa chỉ mới:");
	            String diem = JOptionPane.showInputDialog(this, "Nhập số điểm cập nhật");

	            if (tenKH != null && sdt != null && !tenKH.isEmpty() && !sdt.isEmpty()&& !diem.isEmpty()) {
	            	view.getModelKH().setValueAt(tenKH, row, 1);
	            	view.getModelKH().setValueAt(sdt, row, 2);
	            	view.getModelKH().setValueAt(dc, row, 4);
	            	view.getModelKH().setValueAt(diem, row, 6);
	            	
	            } else {
	                JOptionPane.showMessageDialog(null, "Thông tin không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            }
	        } else {
	            JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng để sửa!");
	        }
	    });
       
    }
    
    
    
    public void themKH() {
		String maKH = view.getTxtMaKH().getText().trim();
		String tenKH = view.getTxtTenKH().getText().trim();
		String gt = (String)view.getCboGioiTinh().getSelectedItem();
		String sdt = view.getTxtSDT().getText().trim();
		String diachi = view.getTxtDiaChi().getText().trim();
		int diem = Integer.parseInt((String)view.getTxtDiem().getText().trim());
		
		try {
			if (maKH.isEmpty() || tenKH.isEmpty() || gt.isEmpty() || diachi.isEmpty() || sdt.isEmpty() || diem==0) {
				view.showInvalidDialog();
			} else {
				if (model.checkTrung(maKH, tenKH)) {
					view.showInvalidDialog();
				} else {
					model.themKH(maKH, tenKH, gt, sdt, diachi, diem);
					view.showValidDialog();
					view.getTxtMaKH().setText("");
					view.getTxtTenKH().setText("");
					view.getTxtSDT().setText("");
					view.getTxtDiaChi().setText("");
					view.getTxtDiem().setText("");
					capNhatBang();

				}
			}
		} catch (Exception e) {
			view.showInvalidDialog();
		}
	}
    public void capNhatBang() {
		view.getModelKH().setRowCount(0);
		for (Observer observer : model.getDsKH()) {
			if (observer instanceof KhachHangThanThiet) {
			KhachHangThanThiet kh = (KhachHangThanThiet) observer;
			String[] row = { kh.getMaKH(), kh.getTenKH(), kh.getGt(),kh.getSdt(), kh.getDiachi(), kh.getDiemTichLuy()+"" };
			view.getModelKH().addRow(row);
		}
		
	}
    }
    public void xoaKH() {
        int selectedRow = view.getTblKH().getSelectedRow(); 
        if (selectedRow == -1) {
        	JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng để xóa!");
            return;
        }

        String maKH = (String )view.getTblKH().getValueAt(selectedRow, 0);

        int confirm = JOptionPane.showConfirmDialog(null,
            "Bạn có chắc muốn xóa khách hàng có mã: " + maKH + "?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
        	int count =0;
        	for (Observer observer : model.getDsKH()) {
        		KhachHangThanThiet kh = (KhachHangThanThiet) observer;
				if(kh.getMaKH().equals(maKH)) {
					 model.xoaKH(maKH);
		              JOptionPane.showMessageDialog(null, "Đã xóa thành công");
		               count++;
		               capNhatBang();
		               break;
				}
			} 
        	if(count==0) {
	            	JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng!");
            }
            

        }
    }

    private boolean kiemTraThongTin(String ma, String ten, String dc, String sdt, String diem) {
        if (ma.isEmpty() || ten.isEmpty() || dc.isEmpty() || sdt.isEmpty() || diem.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
            return false;
        }

        try {
            Integer.parseInt(diem);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Điểm phải là số!");
            return false;
        }

        return true;
    }

    private void clearForm() {
        view.getTxtMaKH().setText("");
        view.getTxtTenKH().setText("");
        view.getCboGioiTinh().setSelectedIndex(0);
        view.getTxtDiaChi().setText("");
        view.getTxtSDT().setText("");
        view.getTxtDiem().setText("");
    }
    
    public void search() {
		String ttSearch = view.getTfTimKiem().getText();
		DefaultTableModel modelTable = view.getModelKH();
		KhachHangThanThiet kh = (KhachHangThanThiet) model.searchKH(ttSearch, ttSearch);
		modelTable.setRowCount(0);
		if (kh != null) {
			String[] row = { kh.getMaKH(), kh.getTenKH(), kh.getGt(),kh.getSdt(), kh.getDiachi(), kh.getDiemTichLuy()+""};
			modelTable.addRow(row);
		}
		else {
			capNhatBang();
		}
	}

    public QLKHView getView() {
        return view;
    }

    public void setView(QLKHView view) {
        this.view = view;
    }

    public CuaHang getModel() {
        return model;
    }

    public void setModel(CuaHang model) {
        this.model = model;
    }
}
