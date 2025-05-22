package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.CuaHang;
import model.NhanVien;
public class TaoHoaDonView {
    JPanel pnTaoDon = new JPanel();
    JSplitPane splitPaneTD;
    JPanel pnTraiTD, pnPhaiTD, pnThanhToan, pnThongTin, pnThaoTac, ptPanel, pnNut, pnDuoiTrai, pnTimKiem;
    JTextField tfMaHD, tfNgayLap, tfSDT, tfTongTien, tfTienKhach, tfTimKiem;
    JTable tblChiTiet, tblMenu;
    DefaultTableModel modelChiTiet, modelKho;
    JRadioButton rdTienMat, rdChuyenKhoan, rdTheNganHang;
    ButtonGroup bgPTTT;
    JButton btnHuy, btnXuat, btnTim, btnThemSP, btnXoaSP;
    JLabel lblMaHD, lblNgayLap, lblTongTien, lblPTTT, lblTenNV, lblTienKhach, lblSDT;
    JComboBox<String> cbTenNV;
    JScrollPane scrollMenu, scrollChiTiet;

    public TaoHoaDonView(){
        pnTaoDon.setLayout(new BorderLayout());
        // Panel chính chứa bên trái và phải
		splitPaneTD = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPaneTD.setResizeWeight(0.4);

		// === Panel trái: Form hóa đơn ===
		pnTraiTD = new JPanel(new BorderLayout());
		pnThongTin = new JPanel(new GridLayout(4, 2, 10, 10));
		pnThongTin.setBorder(BorderFactory.createTitledBorder("HÓA ĐƠN BÁN LẺ"));

		// Các trường nhập liệu (tạo trước)
//		lblMaHD = new JLabel("Mã hóa đơn");
//		tfMaHD = new JTextField("HD002");

		lblNgayLap = new JLabel("Ngày lập hóa đơn");
		LocalDate date = LocalDate.now();
		tfNgayLap = new JTextField( date.getYear() + "-"+""+ date.getMonthValue()+ "-" +date.getDayOfMonth() );

		lblTenNV = new JLabel("Tên nhân viên");
		
		CuaHang ch = new CuaHang();
		List<NhanVien> dsNV = ch.getDsNV();
		String[] tenNV = new String[dsNV.size()];
		for (int i = 0; i < dsNV.size(); i++) {
		    tenNV[i] = dsNV.get(i).getTenNV();
		}
		cbTenNV = new JComboBox<>(tenNV);

		lblSDT = new JLabel("SDT khách hàng");
		tfSDT = new JTextField();

		// Thêm vào panel thông tin
//		pnThongTin.add(lblMaHD);
//		pnThongTin.add(tfMaHD);
		pnThongTin.add(lblNgayLap);
		pnThongTin.add(tfNgayLap);
		pnThongTin.add(lblTenNV);
		pnThongTin.add(cbTenNV);
		pnThongTin.add(lblSDT);
		pnThongTin.add(tfSDT);

		// Bảng chi tiết món
		String[] khoSP = { "Mã Sản Phẩm","TÊN MÓN", "SỐ LƯỢNG", "ĐƠN GIÁ", "THÀNH TIỀN" };
		modelChiTiet = new DefaultTableModel(khoSP, 0);
		tblChiTiet = new JTable(modelChiTiet);
		scrollChiTiet = new JScrollPane(tblChiTiet);
        // Panel thanh toán
		pnThanhToan = new JPanel(new GridLayout(3, 2));
		pnThanhToan.setBorder(BorderFactory.createTitledBorder("Thanh toán"));

		lblTongTien = new JLabel("Tổng tiền");
		tfTongTien = new JTextField();

		lblPTTT = new JLabel("PTTT");
		rdTienMat = new JRadioButton("Tiền mặt");
		rdChuyenKhoan = new JRadioButton("Chuyển khoản");
		rdTheNganHang = new JRadioButton("Thẻ NH");
		bgPTTT = new ButtonGroup();
		bgPTTT.add(rdTienMat);
		bgPTTT.add(rdChuyenKhoan);
		bgPTTT.add(rdTheNganHang);
		ptPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		ptPanel.add(rdTienMat);
		ptPanel.add(rdChuyenKhoan);
		ptPanel.add(rdTheNganHang);

		lblTienKhach = new JLabel("Tiền khách đưa");
		tfTienKhach = new JTextField();

		pnThanhToan.add(lblTongTien);
		pnThanhToan.add(tfTongTien);
		pnThanhToan.add(lblPTTT);
		pnThanhToan.add(ptPanel);
		pnThanhToan.add(lblTienKhach);
		pnThanhToan.add(tfTienKhach);

		// Nút thao tác
		btnHuy = new JButton("Hủy hóa đơn");
		btnXuat = new JButton("Xuất hóa đơn");
		pnNut = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnNut.add(btnHuy);
		pnNut.add(btnXuat);

		// Gắn vào trái
		pnTraiTD.add(pnThongTin, BorderLayout.NORTH);
		pnTraiTD.add(scrollChiTiet, BorderLayout.CENTER);

		pnDuoiTrai = new JPanel(new BorderLayout());
		pnDuoiTrai.add(pnThanhToan, BorderLayout.CENTER);
		pnDuoiTrai.add(pnNut, BorderLayout.SOUTH);
		pnTraiTD.add(pnDuoiTrai, BorderLayout.SOUTH);

		// === Panel phải: MENU BAR ===
		pnPhaiTD = new JPanel(new BorderLayout());
		pnPhaiTD.setBorder(BorderFactory.createTitledBorder("MENU BAR"));

		String[] cols = { "Tên món", "Ảnh", "Giá", "Số lượng còn lại"};
		modelKho = new QLSanPhamView().getTtCOT();
//		modelKho = new DefaultTableModel(cols,0); 
		tblMenu = new JTable(modelKho);
		scrollMenu = new JScrollPane(tblMenu);
		
		tfTimKiem = new JTextField();
		btnTim = new JButton("Tìm Kiếm");
		pnTimKiem = new JPanel(new BorderLayout());
		pnTimKiem.add(tfTimKiem, BorderLayout.CENTER);
		pnTimKiem.add(btnTim, BorderLayout.EAST);

		btnThemSP = new JButton("Thêm sản phẩm");
		btnXoaSP = new JButton("Xóa sản phẩm");
		pnThaoTac = new JPanel();
		pnThaoTac.add(btnThemSP);
		pnThaoTac.add(btnXoaSP);

		pnPhaiTD.add(pnTimKiem, BorderLayout.NORTH);
		pnPhaiTD.add(scrollMenu, BorderLayout.CENTER);
		pnPhaiTD.add(pnThaoTac, BorderLayout.SOUTH);

		// Gắn vào SplitPane
		splitPaneTD.setLeftComponent(pnTraiTD);
		splitPaneTD.setRightComponent(pnPhaiTD);
        pnTraiTD.setMinimumSize(new Dimension(400, 400));
        pnPhaiTD.setMinimumSize(new Dimension(400, 400));
		pnTaoDon.removeAll();
		pnTaoDon.add(splitPaneTD, BorderLayout.CENTER);
		pnTaoDon.revalidate();
		pnTaoDon.repaint();
    }
    public JPanel TaoHoaDon(){
        return pnTaoDon;
    }
	public JButton getBtnHuy() {
		return btnHuy;
	}
	public void setBtnHuy(JButton btnHuy) {
		this.btnHuy = btnHuy;
	}
	public DefaultTableModel getModelKho() {
		return modelKho;
	}
	public void setModelKho(DefaultTableModel modelKho) {
		this.modelKho = modelKho;
	}
	public JPanel getPnTaoDon() {
		return pnTaoDon;
	}
	public JSplitPane getSplitPaneTD() {
		return splitPaneTD;
	}
	public JPanel getPnTraiTD() {
		return pnTraiTD;
	}
	public JPanel getPnPhaiTD() {
		return pnPhaiTD;
	}
	public JPanel getPnThanhToan() {
		return pnThanhToan;
	}
	public JPanel getPnThongTin() {
		return pnThongTin;
	}
	public JPanel getPnThaoTac() {
		return pnThaoTac;
	}
	public JPanel getPtPanel() {
		return ptPanel;
	}
	public JPanel getPnNut() {
		return pnNut;
	}
	public JPanel getPnDuoiTrai() {
		return pnDuoiTrai;
	}
	public JPanel getPnTimKiem() {
		return pnTimKiem;
	}
	public JTextField getTfMaHD() {
		return tfMaHD;
	}
	public JTextField getTfNgayLap() {
		return tfNgayLap;
	}
	public JTextField getTfSDT() {
		return tfSDT;
	}
	public JTextField getTfTongTien() {
		return tfTongTien;
	}
	public JTextField getTfTienKhach() {
		return tfTienKhach;
	}
	public JTextField getTfTimKiem() {
		return tfTimKiem;
	}
	public JTable getTblChiTiet() {
		return tblChiTiet;
	}
	public JTable getTblMenu() {
		return tblMenu;
	}
	public DefaultTableModel getModelChiTiet() {
		return modelChiTiet;
	}
	public JRadioButton getRdTienMat() {
		return rdTienMat;
	}
	public JRadioButton getRdChuyenKhoan() {
		return rdChuyenKhoan;
	}
	public JRadioButton getRdTheNganHang() {
		return rdTheNganHang;
	}
	public ButtonGroup getBgPTTT() {
		return bgPTTT;
	}
	public JButton getBtnXuat() {
		return btnXuat;
	}
	public JButton getBtnTim() {
		return btnTim;
	}
	public JButton getBtnThemSP() {
		return btnThemSP;
	}
	public JButton getBtnXoaSP() {
		return btnXoaSP;
	}
	public JLabel getLblMaHD() {
		return lblMaHD;
	}
	public JLabel getLblNgayLap() {
		return lblNgayLap;
	}
	public JLabel getLblTongTien() {
		return lblTongTien;
	}
	public JLabel getLblPTTT() {
		return lblPTTT;
	}
	public JLabel getLblTenNV() {
		return lblTenNV;
	}
	public JLabel getLblTienKhach() {
		return lblTienKhach;
	}
	public JLabel getLblSDT() {
		return lblSDT;
	}
	public JComboBox<String> getCbTenNV() {
		return cbTenNV;
	}
	public JScrollPane getScrollMenu() {
		return scrollMenu;
	}
	public JScrollPane getScrollChiTiet() {
		return scrollChiTiet;
	}
	
}