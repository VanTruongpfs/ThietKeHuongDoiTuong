package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.ChiTietHoaDon;
import model.CuaHang;
import model.HoaDon;
import view.QLHDView;

public class HoaDonController {
	private QLHDView view;
	private CuaHang model;
	public HoaDonController(QLHDView view, CuaHang model) {
		super();
		this.view = view;
		this.model = model;
		initController();
		
	}

	public void initController() {
		reset();
		loc();
		hienthiDS();
		thongke();
		tim();
		chiTiet();
	}
	public List<HoaDon> hienthiDS() {
		 List<HoaDon> dshd = model.laydsHD();
		 view.getTable().setRowCount(0);
		 for (HoaDon hoadon : dshd ) {
			 String[] tt = { hoadon.getMaHD(),hoadon.getNgayLapHD()+ "", hoadon.getMaKH(), hoadon.getMaNV(),
					 hoadon.tongTien()+ "",hoadon.getPT()+""};
			view.getTable().addRow(tt);
		 }
		 return dshd;
	}
	public void reset() {
		view.getBtnReset().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hienthiDS();
			}
		});
	}
	public void thongke() {
		view.getBtnThongKe().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				double sum = 0;
				DefaultTableModel table = view.getTable();
				int rowCount = table.getRowCount();
				for (int i = 0; i < rowCount; i++) {
				    Object data = table.getValueAt(i, 4);
				    Double value = Double.parseDouble(data.toString());
				    sum += value;
				}
				view.getTxtDoanhThu().setText(sum+"");
			}
		});
	}
	public void tim() {
		view.getSearchButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String maHD = view.getSearchField().getText();
				List<HoaDon> dshd = model.laydsHD();
				HoaDon rs = null;
				for (HoaDon hd : dshd) {
					view.getTable().setRowCount(0);
		            if (hd.getMaHD().equalsIgnoreCase(maHD)) {
		            	rs = new HoaDon(hd.getMaHD(), hd.getMaNV(), hd.getMaKH(), hd.getNgayLapHD(), hd.tongTien(), hd.getPT());
		            	break;
		            }
				}
		
	                String[] row = {
	                		rs.getMaHD(),
	                		rs.getNgayLapHD().toString(),
	                		rs.getMaKH(),
	                		rs.getMaNV(),
	                    String.valueOf(rs.tongTien()),
	                    rs.getPT()
	                };
	                view.getTable().addRow(row);
	            
			}
		});
	}
	public void chiTiet() {
		view.getStatsButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int selected = view.getTB().getSelectedRow();
				String maHD = (String) view.getTB().getValueAt(selected, 0);
				if (selected == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
					return;
				}
				List<ChiTietHoaDon> dsCTHD = model.layDsChiTietHoaDon(maHD);
				try {
					
					StringBuilder message = new StringBuilder("Chi tiết hóa đơn " + maHD + ":\n\n");

		            for (ChiTietHoaDon ct : dsCTHD) {
		                message.append("Mã SP: ").append(ct.getMaSP())
		                       .append("\nTên SP: ").append(ct.getTenSP())
		                       .append("\nĐơn giá: ").append(ct.getDongia())
		                       .append("\nSố lượng: ").append(ct.getSoLuong())
		                       .append("\n-----------------------------\n");
		            }


					JOptionPane.showMessageDialog(null, message, "Chi tiết sản phẩm", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Lỗi khi lấy dữ liệu sản phẩm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	public void loc() {
		view.getBtnLocTheoNgay().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String ngayStr = view.getTfNgay().getText();
				String thangStr = view.getTfThang().getText();
				String namStr = view.getTfNam().getText();
				 List<HoaDon> dshd = model.laydsHD();
		         List<HoaDon> locKetQua = new ArrayList<>();
		         for (HoaDon hd : dshd) {
		               
		                Date ngayLap = hd.getNgayLapHD();
		                Calendar cal = Calendar.getInstance();
		                cal.setTime(ngayLap);

		                int ngay = cal.get(Calendar.DAY_OF_MONTH);
		                int thang = cal.get(Calendar.MONTH) + 1;
		                int nam = cal.get(Calendar.YEAR);
		                boolean match = true;
		                try {
		                    if (!ngayStr.isEmpty()) {
		                        match &= (ngay == Integer.parseInt(ngayStr));
		                    }
		                    if (!thangStr.isEmpty()) {
		                        match &= (thang == Integer.parseInt(thangStr));
		                    }
		                    if (!namStr.isEmpty()) {
		                        match &= (nam == Integer.parseInt(namStr));
		                    }
		                } catch (NumberFormatException ex) {
		                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		                    return;
		                }

		                if (match) {
		                    locKetQua.add(hd);
		                }
		            
		        	 
		         }
		         view.getTable().setRowCount(0);
		            for (HoaDon hd : locKetQua) {
		                String[] row = {
		                    hd.getMaHD(),
		                    hd.getNgayLapHD().toString(),
		                    hd.getMaKH(),
		                    hd.getMaNV(),
		                    String.valueOf(hd.tongTien()),
		                    hd.getPT()
		                };
		                view.getTable().addRow(row);
		            }
			}
		});
		
	}
}
