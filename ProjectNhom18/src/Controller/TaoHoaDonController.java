package Controller;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.ChiTietHoaDon;
import model.ChuyenKhoan;
import model.CuaHang;
import model.HoaDon;
import model.KhachHang;
import model.KhachHangThanThiet;
import model.NhanVien;
import model.SanPham;
import model.ThanhToan;
import model.TheNganHang;
import model.TienMat;
import view.TaoHoaDonView;

public class TaoHoaDonController {
	private TaoHoaDonView view;
	private CuaHang model;
	private double tongTien = 0;
	private double tienKhachDua;

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
			String[] tt = { sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getDonGia() + "", sanPham.getTonKho() + "",
					sanPham.getXuatXu() };
			view.getModelKho().addRow(tt);
		}
	}

	private void initController() {
		huyHoaDon();
		themSP();
		xoaSP();
		PTTT();
		timKiem();
		xuatHoaDon();
		tienKhachPhaiThanhToan();
	}

	// thêm sản phẩm
	public void themSP() {
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
				try {
					if (selected == -1) {
						JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm muốn thêm", "Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						maSP = (String) view.getTblMenu().getValueAt(selected, 0);
						tenSP = (String) view.getTblMenu().getValueAt(selected, 1);
						gia = Double.parseDouble((String) view.getTblMenu().getValueAt(selected, 2));
						soLuongConLai = Integer.parseInt((String) view.getTblMenu().getValueAt(selected, 3));
						xuatXu = (String) view.getTblMenu().getValueAt(selected, 4);
						JPanel panel = new JPanel();
						panel.setLayout(new GridLayout(2, 2));
						panel.add(new JLabel("Số lượng:"));
						JTextField txtSoLuong = new JTextField();
						panel.add(txtSoLuong);
						int result = JOptionPane.showConfirmDialog(null, panel, "Thêm sản phẩm",
								JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
						soLuongThem = Integer.parseInt(txtSoLuong.getText());
						if (result == 0) {
							if (soLuongThem > soLuongConLai) {
								JOptionPane.showMessageDialog(null, "Số lượng sản phẩm còn lại không đủ", "Thông báo",
										JOptionPane.INFORMATION_MESSAGE);
							} else {
								SanPham sp = new SanPham(maSP, tenSP, gia, soLuongThem, xuatXu);
								boolean flag = false;
								for (int i = 0; i < view.getTblChiTiet().getRowCount(); i++) {
									if (String.valueOf(view.getModelChiTiet().getValueAt(i, 0)).equals(sp.getMaSP())) {
										int soLuongTrongBill = Integer
												.parseInt((String) view.getModelChiTiet().getValueAt(i, 2));
										view.getModelChiTiet().setValueAt(soLuongThem + soLuongTrongBill + "", i, 2);
										view.getModelChiTiet()
												.setValueAt((soLuongThem + soLuongTrongBill) * sp.getDonGia(), i, 4);
										view.getModelKho().setValueAt(soLuongConLai - soLuongThem, selected, 3);
										model.updateSLSP(maSP, -soLuongThem);
										tongTien += soLuongThem * sp.getDonGia();
										view.getTfTongTien().setText(tongTien + "");
										flag = true;
										hienThiSP();
										view.getBgPTTT().clearSelection();
										break;
									}
								}
								if (!flag) {
									String[] row = { sp.getMaSP(), sp.getTenSP(), soLuongThem + "", sp.getDonGia() + "",
											soLuongThem * sp.getDonGia() + "" };
									view.getModelChiTiet().addRow(row);
									tongTien += soLuongThem * sp.getDonGia();
									view.getTfTongTien().setText(tongTien + "");
									view.getModelKho().setValueAt(soLuongConLai - soLuongThem, selected, 3);
									model.updateSLSP(maSP, -soLuongThem);
									hienThiSP();
									view.getBgPTTT().clearSelection();
								}

							}
						}
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng muốn thêm", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
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
				try {
					if (selected == -1) {
						JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm muốn xóa", "Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JPanel panel = new JPanel();
						panel.setLayout(new GridLayout(2, 2));
						panel.add(new JLabel("Số lượng:"));
						JTextField txtSoLuong = new JTextField();
						panel.add(txtSoLuong);
						int result = JOptionPane.showConfirmDialog(null, panel, "Xóa sản phẩm",
								JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
						if (result == JOptionPane.OK_OPTION) {
							soLuongXoa = Integer.parseInt(txtSoLuong.getText());
							maSP = (String) view.getTblChiTiet().getValueAt(selected, 0);
							soLuong = Integer.parseInt((String) view.getTblChiTiet().getValueAt(selected, 2));
							if (soLuong <= soLuongXoa) {
								double gia = Double.parseDouble((String) view.getTblChiTiet().getValueAt(selected, 3));
								tongTien -= soLuong * gia;
								view.getTfTongTien().setText(tongTien + "");
								view.getModelChiTiet().removeRow(selected);
								view.getTblChiTiet().clearSelection();
								model.updateSLSP(maSP, soLuong);
								hienThiSP();
							} else {
								double gia = Double.parseDouble((String) view.getTblChiTiet().getValueAt(selected, 3));
								tongTien -= soLuongXoa * gia;
								view.getTfTongTien().setText(tongTien + "");
								view.getModelChiTiet().setValueAt(soLuong - soLuongXoa + "", selected, 2);
								view.getModelChiTiet().setValueAt((soLuong - soLuongXoa) * gia, selected, 4);

								view.getTblChiTiet().clearSelection();
								model.updateSLSP(maSP, soLuongXoa);
								hienThiSP();
							}
						}

					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Vui lòng số lượng muốn xóa", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
				}
				view.getBgPTTT().clearSelection();
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
					tongTien = 0;
					tienKhachDua = 0;
					view.getTfTongTien().setText(tongTien + "");
					view.getTfTienKhach().setText(tienKhachDua + "");
				}
				view.getModelChiTiet().setRowCount(0);
				view.getBgPTTT().clearSelection();
			}
		});
	}

	// chọn phương thức thanh toán
	public void PTTT() {
		ThanhToan pttt = new ThanhToan();
		view.getRdTienMat().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (view.getRdTienMat().isSelected()) {
					pttt.setPTTT(new TienMat());
					tienKhachDua = pttt.getPhuongThucPay().pay(tongTien);
				}
			}
		});

		view.getRdChuyenKhoan().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (view.getRdChuyenKhoan().isSelected()) {
					pttt.setPTTT(new ChuyenKhoan());
					tienKhachDua = pttt.getPhuongThucPay().pay(tongTien);
				}
			}
		});
		view.getRdTheNganHang().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (view.getRdTheNganHang().isSelected()) {
					pttt.setPTTT(new TheNganHang());
					tienKhachDua = pttt.getPhuongThucPay().pay(tongTien);
				}
			}
		});
	}

	// thanh tìm kiếm
	public void timKiem() {
		int selected;
		view.getBtnTim().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String noidung = view.getTfTimKiem().getText();
				boolean found = false;
				for (int i = 0; i < view.getTblMenu().getRowCount(); i++) {
					String maSP = (String) view.getTblMenu().getValueAt(i, 0);
					String tenSP = (String) view.getTblMenu().getValueAt(i, 1);
					if (noidung.equalsIgnoreCase(maSP) || noidung.equalsIgnoreCase(tenSP)) {
						view.getTblMenu().setRowSelectionInterval(i, i); // chọn dòng
						view.getTblMenu().scrollRectToVisible(view.getTblMenu().getCellRect(i, 0, true)); // cuộn tới
																											// dòng đó
						found = true;
						break;
					}
				}
				if (!found) {
					JOptionPane.showMessageDialog(null, "Tìm không thấy sản phẩm", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}

	public static String randomCode(int length) {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder sb = new StringBuilder();
		Random rnd = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		}
		return sb.toString();
	}

	public static String taoMaHoaDon() {
		// Lấy ngày hiện tại theo định dạng ddMMyy
		String ngay = new SimpleDateFormat("ddMMyy").format(new java.util.Date());

		// Tạo chuỗi random 4 ký tự gồm số và chữ in hoa
		String randomStr = randomCode(4);

		// Kết hợp thành mã hoá đơn
		return "HD" + ngay + "_" + randomStr;
	}

	// xuất hóa đơn
	public void xuatHoaDon() {
		view.getBtnXuat().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String maHD = taoMaHoaDon();
				String sdtKH = String.valueOf(view.getTfSDT().getText());
				String tenNV = String.valueOf(view.getCbTenNV().getSelectedItem());
				Date ngayLap = java.sql.Date.valueOf(view.getTfNgayLap().getText());
				String pt = "";
				if (view.getRdTienMat().isSelected()) {
					pt = view.getRdTienMat().getText();
				} else if (view.getRdChuyenKhoan().isSelected()) {
					pt = view.getRdChuyenKhoan().getText();
				} else if (view.getRdTheNganHang().isSelected()) {
					pt = view.getRdTheNganHang().getText();
				}
				double tienThanhToan = Double.parseDouble(String.valueOf(view.getTfTienKhach().getText()));
				NhanVien nv = model.timNhanVienTheoTen(tenNV);
				KhachHang kh = model.timKhachHangTheoSDT(sdtKH);
				if (kh == null) {
					JOptionPane.showMessageDialog(view.TaoHoaDon(),
							"Không tìm thấy khách hàng có số điện thoại: " + sdtKH);
					return;
				}
				HoaDon hd = new HoaDon(maHD, nv.getMaNV(), kh.getMaKH(), ngayLap, tienThanhToan, pt);
				model.insertHoaDon(hd);

				for (int i = 0; i < view.getTblChiTiet().getRowCount(); i++) {
					String maSP = String.valueOf(view.getModelChiTiet().getValueAt(i, 0));
					String tenSP = String.valueOf(view.getModelChiTiet().getValueAt(i, 1));
					int soLuong = Integer.parseInt(String.valueOf(view.getModelChiTiet().getValueAt(i, 2)));
					double thanhTien = Double.parseDouble(String.valueOf(view.getModelChiTiet().getValueAt(i, 4)));
					model.insertChiTietHoaDon(new ChiTietHoaDon(maHD, maSP, tenSP, soLuong, thanhTien));
				}

				int tongSoLuong = 0;
				for (int i = 0; i < view.getTblChiTiet().getRowCount(); i++) {
					int soLuong = Integer.parseInt(String.valueOf(view.getModelChiTiet().getValueAt(i, 2)));
					tongSoLuong += soLuong;
				}

				if (!kh.getMaKH().equals("KHVangLai")) {
					KhachHangThanThiet khTT = (KhachHangThanThiet) kh;
					int diemCongThem = tongSoLuong * 2;
					khTT.setDiemTichLuy(khTT.getDiemTichLuy() + diemCongThem);
					model.capNhatDiemTichLuy(khTT);
				}

				tongTien = 0;
				tienKhachDua = 0;
				view.getModelChiTiet().setRowCount(0);
				view.getTfTongTien().setText(tongTien + "");
				view.getTfTienKhach().setText(tienKhachDua + "");
				view.getBgPTTT().clearSelection();
			}
		});
	}

	public void tienKhachPhaiThanhToan() {
		view.getBtnTamTinh().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				double tienGiamGia = 0;
				tienGiamGia = tienKhachDua;
				if (view.getTfSDT().getText() == "") {
					view.getTfTienKhach().setText(tienGiamGia + "");
				} else {
					String sdt = (String) view.getTfSDT().getText().trim();
					int diemTichLuy = model.getDiemtichLuy(sdt);
					if (diemTichLuy > 200) {
						tienGiamGia = tienGiamGia * 0.9;
						view.getTfTienKhach().setText(tienGiamGia + "");
					} else if (diemTichLuy > 40) {
						tienGiamGia = tienGiamGia * 0.95;
						view.getTfTienKhach().setText(tienGiamGia + "");
					} else {
						view.getTfTienKhach().setText(tienGiamGia + "");
					}
				}
				view.getTfTongTien().setText(tongTien + "");
			}
		});
	}
	
	public void tenNV() {
		view.getCbTenNV().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				List<NhanVien> dsNV = model.getDsNV();
				String[] tenNV = new String[dsNV.size()];
				for (int i = 0; i < dsNV.size(); i++) {
				    tenNV[i] = dsNV.get(i).getTenNV();
				}
				DefaultComboBoxModel<String> modelMoi = new DefaultComboBoxModel<String>(tenNV);
				view.getCbTenNV().setModel(modelMoi);
			}
		});
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
