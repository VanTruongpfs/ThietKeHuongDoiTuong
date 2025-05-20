package Controller;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mysql.cj.x.protobuf.MysqlxCrud.Update;

import model.ChiTietHoaDon;
import model.ChuyenKhoan;
import model.CuaHang;
import model.HoaDon;
import model.SanPham;
import model.ThanhToan;
import model.TheNganHang;
import model.TienMat;
import utils.DBConnection;
import view.QLSanPhamView;
import view.TaoHoaDonView;

public class TaoHoaDonController {
	private TaoHoaDonView view;
	private CuaHang model ;
	private double tongTien = 0;
	private double tienKhachDua;
	private QLSanPhamView sp;
	public TaoHoaDonController(TaoHoaDonView view, CuaHang model) {
		this.view = view;
		this.model = model;
		model = new CuaHang();
		hienThiSP();
		initController();
		
	}
	public void hienThiSP() {
		 view.getModelKho().setRowCount(0);
		 view.getTblMenu().removeAll();
		 for (SanPham sanPham : model.getDsSP()) {
				String[] tt = { sanPham.getMaSP(),sanPham.getTenSP(), sanPham.getDonGia()+"", sanPham.getTonKho()+"",
						 sanPham.getXuatXu()};
				view.getModelKho().addRow(tt);
		}
	 }
	 private void initController() {
	      view.getBtnHuy().addActionListener(e -> huyHoaDon());
	      themSP();
	      xoaSP();
	      PTTT();
	      timKiem();
	      xuatHoaDon();
	 }
	
	 // thêm sản phẩm
	 public void themSP () {
			view.getTblMenu().clearSelection();	
		 view.getBtnThemSP().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selected = view.getTblMenu().getSelectedRow();
				String maSP;
				String tenSP;
				double gia;
				int soLuongConLai;
				String xuatXu;
				int soLuongThem = 0;
				if(selected==-1) {
					JOptionPane.showConfirmDialog(null, "Vui lòng chọn sản phẩm muốn thêm");
				}else {
					maSP = (String) view.getTblMenu().getValueAt(selected, 0);
					tenSP = (String) view.getTblMenu().getValueAt(selected, 1);
					gia = Double.parseDouble((String) view.getTblMenu().getValueAt(selected, 2));
					soLuongConLai = Integer.parseInt((String )view.getTblMenu().getValueAt(selected, 3)) ;
					xuatXu = (String)  view.getTblMenu().getValueAt(selected, 4);
					JPanel panel = new JPanel();
					panel.setLayout(new GridLayout(2, 2));
					panel.add(new JLabel("Số lượng:"));
					JTextField txtSoLuong = new JTextField();
					panel.add(txtSoLuong);
					int result = JOptionPane.showConfirmDialog(
				            null, panel, "Thêm sản phẩm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
				        );
					soLuongThem = Integer.parseInt(txtSoLuong.getText());
					if(result==0) {
						if(soLuongThem>soLuongConLai) {
							JOptionPane.showConfirmDialog(null,"Số lượng còn lại không đủ");
						}else {
							SanPham sp = new SanPham(maSP, tenSP, gia, soLuongThem, xuatXu);
							String[] row = {sp.getMaSP(),sp.getTenSP(), soLuongThem+"",sp.getDonGia()+"", soLuongThem*sp.getDonGia()+""};
							view.getModelChiTiet().addRow(row);	
							tongTien+= soLuongThem*sp.getDonGia();
							view.getTfTongTien().setText(tongTien+"");
							view.getModelKho().setValueAt(soLuongConLai-soLuongThem, selected, 3);
							model.updateSLSP(maSP, -soLuongThem);
						}
					}
				}
			}
		});
	 }
	 // sự kiện xóa sản phẩm
	 public void xoaSP() {
		 view.getBtnXoaSP().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selected = view.getTblChiTiet().getSelectedRow();
				String maSP;
				int soLuong;
				int soLuongXoa;
				if(selected==-1) {
					JOptionPane.showConfirmDialog(null, "Vui lòng chọn sản phẩm muốn xóa");
				}else {
					JPanel panel = new JPanel();
					panel.setLayout(new GridLayout(2, 2));
					panel.add(new JLabel("Số lượng:"));
					JTextField txtSoLuong = new JTextField();
					panel.add(txtSoLuong);
					int result = JOptionPane.showConfirmDialog(
				            null, panel, "Xóa sản phẩm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
				        );
					if(result==JOptionPane.OK_OPTION) {
						soLuongXoa = Integer.parseInt(txtSoLuong.getText());
						maSP = (String) view.getTblChiTiet().getValueAt(selected, 0);
						soLuong = Integer.parseInt((String) view.getTblChiTiet().getValueAt(selected, 2));
						if(soLuong<=soLuongXoa) {
							double gia  = Double.parseDouble((String) view.getTblChiTiet().getValueAt(selected, 3));
							tongTien-=soLuong*gia;
							view.getTfTongTien().setText(tongTien+"");
							view.getModelChiTiet().removeRow(selected);
							view.getTblChiTiet().clearSelection();
							model.updateSLSP(maSP, soLuong);
							hienThiSP();
						}else {
							double gia  = Double.parseDouble((String) view.getTblChiTiet().getValueAt(selected, 3));
							tongTien-=soLuongXoa*gia;
							view.getTfTongTien().setText(tongTien+"");
							view.getModelChiTiet().setValueAt(soLuong-soLuongXoa+"", selected, 2);
							view.getTblChiTiet().clearSelection();
							model.updateSLSP(maSP, soLuongXoa);
							hienThiSP();
						}
					}
					
					}
				}
		});
	 }
	// hủy hóa đơn
	 public void huyHoaDon() {
		view.getBtnHuy().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String maSP;
				int soLuong;
				for (int i = 0; i < view.getTblChiTiet().getRowCount(); i++) {
					maSP = (String) view.getTblChiTiet().getValueAt(i, 0);
					soLuong = Integer.parseInt((String) view.getTblChiTiet().getValueAt(i, 2)); 
					model.updateSLSP(maSP, soLuong);
					hienThiSP();
				}
				view.getModelChiTiet().setRowCount(0);
			}
		});
	 }
	 //Tìm không thấy sản phẩm
	 public void PTTT() {
		 ThanhToan pttt = new ThanhToan();
		 view.getRdTienMat().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(view.getRdTienMat().isSelected()) {
					 pttt.setPTTT(new TienMat());
					 tienKhachDua = pttt.getPhuongThucPay().pay(tongTien);
					 view.getTfTienKhach().setText(tienKhachDua+"");
				 }
			}
		});
		 
		 view.getRdChuyenKhoan().addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 if(view.getRdChuyenKhoan().isSelected()) {
					 pttt.setPTTT(new ChuyenKhoan());
					 tienKhachDua = pttt.getPhuongThucPay().pay(tongTien);
					 view.getTfTienKhach().setText(tienKhachDua+"");
				 }
			 }
		 });
		 view.getRdTheNganHang().addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 if(view.getRdTheNganHang().isSelected()) {
					 pttt.setPTTT(new TheNganHang());
					 tienKhachDua = pttt.getPhuongThucPay().pay(tongTien);
					 view.getTfTienKhach().setText(tienKhachDua+"");
				 }
			 }
		 });
	 }
	 //thanh tìm kiếm
	 public void timKiem() {
		 int selected;
			 view.getBtnTim().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String noidung = view.getTfTimKiem().getText();
		            boolean found = false;
					for (int i = 0; i < view.getTblMenu().getRowCount(); i++) {
						String maSP= (String) view.getTblMenu().getValueAt(i, 0);
						String tenSP= (String) view.getTblMenu().getValueAt(i, 1);
						if(noidung.equals(maSP) || noidung.equals(tenSP)) {
							view.getTblMenu().setRowSelectionInterval(i, i); // chọn dòng
							view.getTblMenu().scrollRectToVisible(view.getTblMenu().getCellRect(i, 0, true)); // cuộn tới dòng đó
		                    found = true;
		                    break;
						}
					}
					if(!found) {
						JOptionPane.showConfirmDialog(null, "tìm không thấy sản phẩm");
					}
				}
			});
	 }
	 // xuất hóa đơn
	 public void xuatHoaDon() {
		 view.getBtnXuat().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String maHD = String.valueOf(view.getTfMaHD().getText());
				String maKH=  String.valueOf(view.getTfSDT().getText());
				String maNV= String.valueOf(view.getCbTenNV().getSelectedItem());
				String ngayLap= String.valueOf(view.getTfNgayLap().getText());
				double tongTien= Double.parseDouble(String.valueOf(view.getTfTienKhach().getText()));
				HoaDon hd = new HoaDon(maHD, "NV001", "KH001", ngayLap, tongTien);
				model.insertHoaDon(hd);
				tongTien=0;
				tienKhachDua=0;
				for (int i = 0; i < view.getTblChiTiet().getRowCount(); i++) {
					String maSP = String.valueOf(view.getModelChiTiet().getValueAt(i, 0));
					String tenSP = String.valueOf(view.getModelChiTiet().getValueAt(i, 1));
					int soLuong = Integer.parseInt(String.valueOf(view.getModelChiTiet().getValueAt(i, 2)));
					double thanhTien = Double.parseDouble(String.valueOf(view.getModelChiTiet().getValueAt(i, 4)));
					model.insertChiTietHoaDon(new ChiTietHoaDon(maHD, maSP, tenSP, soLuong, thanhTien));
				}
				view.getModelChiTiet().setRowCount(0);
				
			}
		});
	 }
}
