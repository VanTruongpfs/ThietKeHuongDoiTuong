package view;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.lang.ModuleLayer.Controller;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.NhanVienController;
import model.CuaHang;

public class QLNVView {
     JPanel pnQuanLyNhanVien = new JPanel();

 public  JPanel panelQLNV_Main, panelQLNV_TimKiem, panelQLNV_Trai, panelQLNV_FormThem,
           panelQLNV_BtnThem, panelQLNV_Xoa, panelQLNV_Phai, panelQLNV_tinhLuong;
 public   JTextField tfQLNV_MaTK, tfQLNV_MaNV, tfQLNV_TenNV, tfQLNV_NgaySinh, tfQLNV_NBDL,
          tfQLNV_Luong, tfQLNV_XoaMaNV, tfQLNV_TinhLuong, tfQLNV_soGioLam;
 public  JButton btnQLNV_Tim, btnQLNV_Them, btnQLNV_Xoa, btnQLNV_TinhLuong, btnQLNV_Cancel, btnQLNV_Sua;
 public JLabel lblQLNV_MaTK, lblQLNV_TinhLuong;

 public JLabel lblQLNV_raLuong;
 public JTable tblQLNV;
 public DefaultTableModel modelQLNV;
 public JScrollPane scrollQLNV;   
 public JSplitPane splitPaneQLNV;
 public NhanVienController NVcontroller;
 public CuaHang ch;
 public JFrame FramSua;
    
	public QLNVView() {
	build();
	NVcontroller = new NhanVienController(this, ch);
}
	public void build() {
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
		btnQLNV_Cancel = new JButton("Quay lại");
		
		panelQLNV_TimKiem.add(lblQLNV_MaTK);
		panelQLNV_TimKiem.add(tfQLNV_MaTK);
		panelQLNV_TimKiem.add(btnQLNV_Tim);
		panelQLNV_TimKiem.add(btnQLNV_Cancel);

		panelQLNV_Main.add(panelQLNV_TimKiem, BorderLayout.NORTH);

		// ========== KHU VỰC TRÁI ==========
		panelQLNV_Trai = new JPanel(new BorderLayout());

		// ---- FORM THÊM NHÂN VIÊN ----
		panelQLNV_FormThem = new JPanel(new GridLayout(8, 2));
		panelQLNV_FormThem.setBorder(BorderFactory.createTitledBorder("Thêm nhân viên"));

		tfQLNV_MaNV = new JTextField();
		tfQLNV_TenNV = new JTextField();
		tfQLNV_NgaySinh = new JTextField();
		tfQLNV_NBDL = new JTextField();;
		tfQLNV_Luong = new JTextField();
		tfQLNV_soGioLam = new JTextField();

		panelQLNV_FormThem.add(new JLabel("Mã nhân viên:"));
		panelQLNV_FormThem.add(tfQLNV_MaNV);
		panelQLNV_FormThem.add(new JLabel("Tên nhân viên:"));
		panelQLNV_FormThem.add(tfQLNV_TenNV);
		panelQLNV_FormThem.add(new JLabel("Ngày sinh:"));
		panelQLNV_FormThem.add(tfQLNV_NgaySinh);
panelQLNV_FormThem.add(new JLabel("Ngày BD Làm:"));
		panelQLNV_FormThem.add(tfQLNV_NBDL);
		panelQLNV_FormThem.add(new JLabel("Lương CB:"));
		panelQLNV_FormThem.add(tfQLNV_Luong);
		panelQLNV_FormThem.add(new JLabel("Số giờ làm:"));
		panelQLNV_FormThem.add(tfQLNV_soGioLam);

		btnQLNV_Them = new JButton("Thêm nhân viên");
		btnQLNV_Sua = new JButton("Sửa");
		panelQLNV_BtnThem = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelQLNV_BtnThem.add(btnQLNV_Them);
		panelQLNV_BtnThem.add(btnQLNV_Sua);
		
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

		String[] cols = { "Mã NV", "Tên NV", "Ngày sinh", "Ngày BD Làm", "Lương cơ bản", "Số giờ làm"};
		modelQLNV = new DefaultTableModel(cols, 0);
		tblQLNV = new JTable(modelQLNV);
		scrollQLNV = new JScrollPane(tblQLNV);

		panelQLNV_Phai.add(scrollQLNV, BorderLayout.CENTER);
		
		// Dưới 
		panelQLNV_tinhLuong = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelQLNV_tinhLuong.setBorder(BorderFactory.createTitledBorder("Tính Lương"));
		
		btnQLNV_TinhLuong = new JButton("Tính Lương");
		JLabel lbl_mnv = new JLabel("Mã nhân viên: ");
		tfQLNV_TinhLuong = new JTextField(15);
		lblQLNV_raLuong = new JLabel("0");
		lblQLNV_TinhLuong = new JLabel("Lương: ");
		
		
		panelQLNV_tinhLuong.add(lbl_mnv);
		panelQLNV_tinhLuong.add(tfQLNV_TinhLuong);
		panelQLNV_tinhLuong.add(btnQLNV_TinhLuong);
		panelQLNV_tinhLuong.add(lblQLNV_TinhLuong);
		panelQLNV_tinhLuong.add(lblQLNV_raLuong);
		
		panelQLNV_Phai.add(panelQLNV_tinhLuong, BorderLayout.SOUTH);
		
		
		// ========== GỘP TRÁI - PHẢI ==========
		splitPaneQLNV = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelQLNV_Trai, panelQLNV_Phai);
		splitPaneQLNV.setResizeWeight(0.4);

		panelQLNV_Main.add(splitPaneQLNV, BorderLayout.CENTER);
		pnQuanLyNhanVien.add(panelQLNV_Main);

		pnQuanLyNhanVien.revalidate();
		pnQuanLyNhanVien.repaint();
	}
	public JFrame getFramSua() {
		return FramSua;
	}
	public void setFramSua(JFrame framSua) {
		FramSua = framSua;
	}
    public JPanel QLNV() {
        return pnQuanLyNhanVien;
    }
   
}