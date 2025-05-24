package Controller;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.CuaHang;
import model.NhanVien;
import utils.DBConnection;
import view.QLNVView;

public class NhanVienController {
    private QLNVView view;
    private CuaHang model;

    public NhanVienController(QLNVView view, CuaHang model) {
        this.view = view;
        this.model = model;
        hienThiNV(); 
        initController();
    }

    private void initController() {
        view.btnQLNV_Them.addActionListener(e -> themNhanVien());
        view.btnQLNV_Them.addActionListener(e -> System.out.println("Nút Thêm Nhân Viên đã được nhấn!"));
        view.btnQLNV_Xoa.addActionListener(e -> xoaNhanVien());
        view.btnQLNV_Tim.addActionListener(e -> timNhanVien());
        view.btnQLNV_Cancel.addActionListener(e -> CancelTK());
        view.btnQLNV_TinhLuong.addActionListener(e -> tinhLuong());
        view.btnQLNV_Sua.addActionListener(e-> hienThiFormSua());
       
    }
    public void hienThiNV() {
    	CuaHang model = new CuaHang();
    	List<NhanVien> list = model.getDsNV();
	      view.modelQLNV.setRowCount(0);
	      for (NhanVien nv : list) {
	    	  Object[] row = {nv.getMaNV(), nv.getTenNV(), nv.getNgaySinh(), nv.getNgayBDLam(), nv.getLuongCB(), nv.getSoGioLam()};
              view.modelQLNV.addRow(row);
		}
    }

   
    public void themNhanVien() {

        String maNV = view.tfQLNV_MaNV.getText();
        String tenNV = view.tfQLNV_TenNV.getText();
        String ngaySinhStr = view.tfQLNV_NgaySinh.getText();
        String ngayBDLStr = view.tfQLNV_NBDL.getText();
        String luongStr = view.tfQLNV_Luong.getText();
        String gioLamStr = view.tfQLNV_soGioLam.getText();
        
       
        if (maNV.isEmpty() || tenNV.isEmpty() || ngaySinhStr.isEmpty() || ngayBDLStr.isEmpty() || luongStr.isEmpty() || gioLamStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        
        try {
            Date ngaySinh = Date.valueOf(ngaySinhStr);
            Date ngayBDL = Date.valueOf(ngayBDLStr);
           
            double luongCB = Double.parseDouble(luongStr);
            int soGioLam = Integer.parseInt(gioLamStr);

            NhanVien nv = new NhanVien(maNV, tenNV, ngaySinh, ngayBDL, luongCB, soGioLam);

            try  {
            	Connection con = DBConnection.getConnection();
                String sql = "INSERT INTO NHANVIEN (maNV, tenNV, ngaySinh, ngayBDLam, luongCB, soGioLam) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, maNV);
                ps.setString(2, tenNV);
                ps.setDate(3, ngaySinh);
                ps.setDate(4, ngayBDL);
                ps.setDouble(5, luongCB);
                ps.setInt(6, soGioLam);
         
                int result = ps.executeUpdate();
                if (result > 0) {
                    JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công!");
                    hienThiNV(); 
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm nhân viên thất bại.");
                }
            

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Lỗi nhập liệu, kiểm tra ngày và số!");
        }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm nhân viên: " + e.getMessage());
        }
        view.tfQLNV_MaNV.setText("");
        view.tfQLNV_TenNV.setText("");
        view.tfQLNV_NgaySinh.setText("");
        view.tfQLNV_NBDL.setText("");
        view.tfQLNV_Luong.setText("");
        view.tfQLNV_soGioLam.setText("");
    }


    public void xoaNhanVien() {
        String maNV = view.tfQLNV_XoaMaNV.getText().trim();
        if (maNV.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập mã nhân viên để xóa!");
            return;
        }

        try {
        	Connection con = DBConnection.getConnection();
            String sql = "DELETE FROM NHANVIEN WHERE maNV = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maNV);

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Xóa nhân viên thành công!");
                hienThiNV();
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa nhân viên: " + e.getMessage());
        }
        view.tfQLNV_XoaMaNV.setText("");
    }
    
    public void hienThiFormSua() {
    	JDialog log = new JDialog();
    	log.setTitle("Nhập mã nhân viên cần sửa");
    	log.setSize(300,150);
    	log.setLocationRelativeTo(null);
    	
    	JPanel panel = new JPanel(new FlowLayout());
        JLabel lblMaNV = new JLabel("Mã nhân viên:");
        JTextField txtMaNV = new JTextField(10);
        JButton btnOK = new JButton("OK");
        
        panel.add(lblMaNV);
        panel.add(txtMaNV);
        panel.add(btnOK);
        log.add(panel);
        
        btnOK.addActionListener(e -> {
            String maNV = txtMaNV.getText().trim();
            log.dispose();
            hienThiChiTietSua(maNV);
        });

        log.setVisible(true);
    	
    }
    public void hienThiChiTietSua(String maNV) {
    	CuaHang model = new CuaHang();
        NhanVien nv = model.timNhanVien(maNV);
        if (nv == null) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên!");
            return;
        }

        JDialog suaDialog = new JDialog();
        suaDialog.setTitle("Sửa thông tin nhân viên");
        suaDialog.setSize(400, 300);
        suaDialog.setLocationRelativeTo(null);
        suaDialog.setLayout(new GridLayout(7, 2));

        JTextField tfTen = new JTextField(nv.getTenNV());
        JTextField tfNgaySinh = new JTextField(nv.getNgaySinh().toString());
        JTextField tfNgayBDL = new JTextField(nv.getNgayBDLam().toString());
        JTextField tfLuongCB = new JTextField(String.valueOf(nv.getLuongCB()));
        JTextField tfSoGioLam = new JTextField(String.valueOf(nv.getSoGioLam()));

        JButton btnCapNhat = new JButton("Cập nhật");

        suaDialog.add(new JLabel("Tên NV:"));
        suaDialog.add(tfTen);
        suaDialog.add(new JLabel("Ngày sinh:"));
        suaDialog.add(tfNgaySinh);
        suaDialog.add(new JLabel("Ngày bắt đầu làm:"));
        suaDialog.add(tfNgayBDL);
        suaDialog.add(new JLabel("Lương CB:"));
        suaDialog.add(tfLuongCB);
        suaDialog.add(new JLabel("Số giờ làm:"));
        suaDialog.add(tfSoGioLam);
        suaDialog.add(new JLabel());
        suaDialog.add(btnCapNhat);

        btnCapNhat.addActionListener(e -> {
            capNhatNhanVien(maNV, tfTen.getText(), tfNgaySinh.getText(), tfNgayBDL.getText(), tfLuongCB.getText(), tfSoGioLam.getText());
            suaDialog.dispose();
        });

        suaDialog.setVisible(true);
    }
    

    public void capNhatNhanVien(String maNV, String tenNV, String ngaySinhStr, String ngayBDLStr, String luongStr, String gioLamStr) {
        try {
            Date ngaySinh = Date.valueOf(ngaySinhStr);
            Date ngayBDL = Date.valueOf(ngayBDLStr);
            double luongCB = Double.parseDouble(luongStr);
            int soGioLam = Integer.parseInt(gioLamStr);

            try
                 {

            	
            	Connection con = DBConnection.getConnection();
String sql = "UPDATE NHANVIEN SET tenNV = ?, ngaySinh = ?, ngayBDLam = ?, luongCB = ?, soGioLam = ? WHERE maNV = ?";
            	 PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, tenNV);
                ps.setDate(2, ngaySinh);
                ps.setDate(3, ngayBDL);
                ps.setDouble(4, luongCB);
                ps.setInt(5, soGioLam);
                ps.setString(6, maNV);

                int result = ps.executeUpdate();
                if (result > 0) {
                    JOptionPane.showMessageDialog(null, "Cập nhật nhân viên thành công!");
                    hienThiNV();
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật thất bại.");
                }
            

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Lỗi nhập liệu, kiểm tra ngày và số!");}
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật nhân viên: " + e.getMessage());
        }
    }
    
    public void timNhanVien() {
        String maNV = view.tfQLNV_MaTK.getText().trim();
        if (maNV.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập mã nhân viên để tìm!");
            return;
        }

        try  {
        	Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM NHANVIEN WHERE maNV = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maNV);
            ResultSet rs = ps.executeQuery();

            DefaultTableModel model = (DefaultTableModel) view.tblQLNV.getModel();
            model.setRowCount(0);

            if (rs.next()) {
                Object[] row = {
                    rs.getString("maNV"),
                    rs.getString("tenNV"),
                    rs.getDate("ngaySinh"),
                    rs.getDate("ngayBDLam"),
                    rs.getDouble("luongCB"),
                    rs.getInt("soGioLam")
                };
                model.addRow(row);
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi tìm nhân viên: " + e.getMessage());
        }
    }
    
    
    public void CancelTK() {
    	view.tfQLNV_MaTK.setText("");
    	hienThiNV();
    }
    
    public void tinhLuong() {
        String maNV = view.tfQLNV_TinhLuong.getText().trim();

        if (maNV.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập mã nhân viên!");
            return;
        }


        try {
        	Connection con = DBConnection.getConnection();
            String sql = "SELECT luongCB, soGioLam FROM NHANVIEN WHERE TRIM(maNV) = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maNV);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                double luongCB = rs.getDouble("luongCB");
                int soGioLam = rs.getInt("soGioLam");

                if (luongCB == 0 || soGioLam == 0) {
                    JOptionPane.showMessageDialog(null, "Lương cơ bản hoặc số giờ làm không hợp lệ!");
                    return;
                }

                double tongLuong = luongCB * soGioLam;
                view.lblQLNV_raLuong.setText(String.valueOf(tongLuong));
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên với mã: " + maNV);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi tính lương: " + e.getMessage());
        }
        view.tfQLNV_TinhLuong.setText("");
    }
   
}
