package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class QLNVView {
     JPanel pnQuanLyNhanVien = new JPanel();

    JPanel panelQLNV_Main, panelQLNV_TimKiem, panelQLNV_Trai, panelQLNV_FormThem,
           panelQLNV_BtnThem, panelQLNV_Xoa, panelQLNV_Phai;
    JTextField tfQLNV_MaTK, tfQLNV_MaNV, tfQLNV_TenNV, tfQLNV_NgaySinh, tfQLNV_GioiTinh,
               tfQLNV_DiaChi, tfQLNV_SDT, tfQLNV_ChucVu, tfQLNV_Luong, tfQLNV_XoaMaNV;
    JButton btnQLNV_Tim, btnQLNV_Them, btnQLNV_Xoa;
    JLabel lblQLNV_MaTK;
    JTable tblQLNV;
    DefaultTableModel modelQLNV;
    JScrollPane scrollQLNV;
    JSplitPane splitPaneQLNV;
    public QLNVView() {
        pnQuanLyNhanVien.removeAll();
		pnQuanLyNhanVien.setLayout(new BorderLayout());

		panelQLNV_Main = new JPanel(new BorderLayout());
		panelQLNV_Main.setBorder(BorderFactory.createTitledBorder("Quản Lí Nhân Viên"));

		// ========== KHU VỰC TÌM KIẾM NHÂN VIÊN ==========
		panelQLNV_TimKiem = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelQLNV_TimKiem.setBorder(BorderFactory.createTitledBorder("Tìm kiếm nhân viên"));

		lblQLNV_MaTK = new JLabel("Mã nhân viên: ");
		tfQLNV_MaTK = new JTextField(15);
		btnQLNV_Tim = new JButton("Tìm");

		panelQLNV_TimKiem.add(lblQLNV_MaTK);
		panelQLNV_TimKiem.add(tfQLNV_MaTK);
		panelQLNV_TimKiem.add(btnQLNV_Tim);

		panelQLNV_Main.add(panelQLNV_TimKiem, BorderLayout.NORTH);

		// ========== KHU VỰC TRÁI ==========
		panelQLNV_Trai = new JPanel(new BorderLayout());

		// ---- FORM THÊM NHÂN VIÊN ----
		panelQLNV_FormThem = new JPanel(new GridLayout(8, 2));
		panelQLNV_FormThem.setBorder(BorderFactory.createTitledBorder("Thêm nhân viên"));

		tfQLNV_MaNV = new JTextField();
		tfQLNV_TenNV = new JTextField();
		tfQLNV_NgaySinh = new JTextField();
		tfQLNV_GioiTinh = new JTextField();
		tfQLNV_DiaChi = new JTextField();
		tfQLNV_SDT = new JTextField();
		tfQLNV_ChucVu = new JTextField();
		tfQLNV_Luong = new JTextField();

		panelQLNV_FormThem.add(new JLabel("Mã nhân viên:"));
		panelQLNV_FormThem.add(tfQLNV_MaNV);
		panelQLNV_FormThem.add(new JLabel("Tên nhân viên:"));
		panelQLNV_FormThem.add(tfQLNV_TenNV);
		panelQLNV_FormThem.add(new JLabel("Ngày sinh:"));
		panelQLNV_FormThem.add(tfQLNV_NgaySinh);
		panelQLNV_FormThem.add(new JLabel("Giới tính:"));
		panelQLNV_FormThem.add(tfQLNV_GioiTinh);
		panelQLNV_FormThem.add(new JLabel("Địa chỉ:"));
		panelQLNV_FormThem.add(tfQLNV_DiaChi);
		panelQLNV_FormThem.add(new JLabel("Số điện thoại:"));
		panelQLNV_FormThem.add(tfQLNV_SDT);
		panelQLNV_FormThem.add(new JLabel("Chức vụ:"));
		panelQLNV_FormThem.add(tfQLNV_ChucVu);
		panelQLNV_FormThem.add(new JLabel("Lương:"));
		panelQLNV_FormThem.add(tfQLNV_Luong);

		btnQLNV_Them = new JButton("Thêm nhân viên");
		panelQLNV_BtnThem = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelQLNV_BtnThem.add(btnQLNV_Them);

		JPanel panelQLNV_ThemNV = new JPanel(new BorderLayout());
		panelQLNV_ThemNV.add(panelQLNV_FormThem, BorderLayout.NORTH);
		panelQLNV_ThemNV.add(panelQLNV_BtnThem, BorderLayout.CENTER);

		panelQLNV_Trai.add(panelQLNV_ThemNV, BorderLayout.NORTH);

		// ---- FORM XÓA NHÂN VIÊN ----
		panelQLNV_Xoa = new JPanel(new BorderLayout());
		panelQLNV_Xoa.setBorder(BorderFactory.createTitledBorder("Xóa nhân viên"));

		tfQLNV_XoaMaNV = new JTextField();
		btnQLNV_Xoa = new JButton("Xóa");

		panelQLNV_Xoa.add(new JLabel("Mã nhân viên:"), BorderLayout.NORTH);
		panelQLNV_Xoa.add(tfQLNV_XoaMaNV, BorderLayout.CENTER);
		panelQLNV_Xoa.add(btnQLNV_Xoa, BorderLayout.SOUTH);

		panelQLNV_Trai.add(panelQLNV_Xoa, BorderLayout.SOUTH);

		// ========== KHU VỰC PHẢI ==========
		panelQLNV_Phai = new JPanel(new BorderLayout());
		panelQLNV_Phai.setBorder(BorderFactory.createTitledBorder("Danh sách nhân viên"));

		String[] cols = { "Mã NV", "Tên NV", "Ngày sinh", "Giới tính", "Địa chỉ", "SĐT", "Chức vụ", "Lương" };
		modelQLNV = new DefaultTableModel(cols, 0);
		tblQLNV = new JTable(modelQLNV);
		scrollQLNV = new JScrollPane(tblQLNV);

		panelQLNV_Phai.add(scrollQLNV, BorderLayout.CENTER);

		// ========== GỘP TRÁI - PHẢI ==========
		splitPaneQLNV = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelQLNV_Trai, panelQLNV_Phai);
		splitPaneQLNV.setResizeWeight(0.4);

		panelQLNV_Main.add(splitPaneQLNV, BorderLayout.CENTER);
		pnQuanLyNhanVien.add(panelQLNV_Main);

		pnQuanLyNhanVien.revalidate();
		pnQuanLyNhanVien.repaint();
	}
    public JPanel QLNV() {
        return pnQuanLyNhanVien;
    }
}
