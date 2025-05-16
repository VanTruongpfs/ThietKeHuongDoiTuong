package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel; 
public class QLKHView {
    JPanel pnQuanLyKhachHang = new JPanel();
    JPanel pnTrai, pnPhai, pnDuoi, formPanel, pnTimKiem;
    JButton btnThem, btnXoa, btnSua, btnTim;
    JTextField txtMaKH, txtTenKH, txtSDT, txtDiaChi, txtDiem, tfTimKiem;
    JComboBox<String> cboGioiTinh;
    JTable tblKH;
    DefaultTableModel modelKH;
    JScrollPane scrollKH;
    JSplitPane splitPane;

    public QLKHView() {
        pnQuanLyKhachHang.setLayout(new BorderLayout());
		// Panel bên trái
		pnTrai = new JPanel();
		pnTrai.setLayout(new BoxLayout(pnTrai, BoxLayout.Y_AXIS));
		pnTrai.setBorder(BorderFactory.createTitledBorder("QUẢN LÍ KHÁCH HÀNG"));

		btnThem = new JButton("Thêm khách hàng");
		btnXoa = new JButton("Xóa khách hàng");
		btnSua = new JButton("Sửa khách hàng");

		// Tạo panel chứa các ô nhập liệu theo dạng bảng
		formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
		formPanel.setBorder(BorderFactory.createTitledBorder("Thêm khách hàng"));
		// Các ô nhập liệu
		txtMaKH = new JTextField();
		txtTenKH = new JTextField();
		cboGioiTinh = new JComboBox<>(new String[] { "Nam", "Nữ" });
		txtDiaChi = new JTextField();
		txtSDT = new JTextField();
		txtDiem = new JTextField();

		// Thêm vào form
		formPanel.add(new JLabel("Mã khách hàng:"));
		formPanel.add(txtMaKH);

		formPanel.add(new JLabel("Tên khách hàng:"));
		formPanel.add(txtTenKH);

		formPanel.add(new JLabel("Số điện thoại:"));
		formPanel.add(txtSDT);

		formPanel.add(new JLabel("Giới tính:"));
		formPanel.add(cboGioiTinh);

		formPanel.add(new JLabel("Địa chỉ:"));
		formPanel.add(txtDiaChi);

		formPanel.add(new JLabel("Điểm"));
		formPanel.add(txtDiem);
		pnTrai.add(formPanel, BorderLayout.NORTH);
		pnTrai.add(Box.createVerticalStrut(30));

		pnDuoi = new JPanel();
		pnDuoi.setLayout(new FlowLayout());
		pnDuoi.add(btnThem);
		pnDuoi.add(btnXoa);
		pnDuoi.add(btnSua);

		pnTrai.add(pnDuoi, BorderLayout.CENTER);

		// Panel bên phải (tìm kiếm + bảng)
		pnPhai = new JPanel(new BorderLayout());

		pnTimKiem = new JPanel(new BorderLayout());
		tfTimKiem = new JTextField();
		btnTim = new JButton("Tìm");
		pnTimKiem.add(new JLabel("Nhập thông tin cần tìm: "), BorderLayout.WEST);
		pnTimKiem.add(tfTimKiem, BorderLayout.CENTER);
		pnTimKiem.add(btnTim, BorderLayout.EAST);

		String[] cols = { "Mã KH", "Tên khách hàng", "Số điện thoại", "Giới tính", "Địa chỉ", "Điểm tích lũy" };
		modelKH = new DefaultTableModel(cols, 0);
		tblKH = new JTable(modelKH);
		scrollKH = new JScrollPane(tblKH);

		pnPhai.add(pnTimKiem, BorderLayout.NORTH);
		pnPhai.add(scrollKH, BorderLayout.CENTER);

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnTrai, pnPhai);
		splitPane.setDividerLocation(450);
		splitPane.setResizeWeight(0.1);
		pnQuanLyKhachHang.removeAll();
		pnQuanLyKhachHang.add(splitPane, BorderLayout.CENTER);
		pnQuanLyKhachHang.revalidate();
		pnQuanLyKhachHang.repaint();
	}
    public JPanel QLKH(){
        return  pnQuanLyKhachHang;
    }
    
}
