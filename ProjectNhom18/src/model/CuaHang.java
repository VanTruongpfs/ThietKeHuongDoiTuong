
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import utils.DBConnection;

public class CuaHang implements Subject {
	private String maCH;
	private String tenCH;
	private List<NhanVien> dsNV= new ArrayList<NhanVien>();
	private List<SanPham> dsSP = new ArrayList<SanPham>();
	private List<Observer> dsKH = new ArrayList<Observer>();
	private List<HoaDon> dsHD= new ArrayList<HoaDon>();

	public CuaHang(String maCH, String tenCH, List<NhanVien> dsNV, List<SanPham> dsSP, List<Observer> dsKH,
			List<HoaDon> dsHD) {
		super();
		this.maCH = maCH;
		this.tenCH = tenCH;
		this.dsNV = dsNV;
		this.dsSP = dsSP;
		this.dsKH = dsKH;
		this.dsHD = dsHD;
	}

	public CuaHang() {
		laySPTuDB();
		layKHTuDB();
		insertNhanVienFromDB();
	}

	public void laySPTuDB() {
		try {
			Connection cnn = DBConnection.getConnection();
			String sql = "SELECT * FROM SANPHAM";
			PreparedStatement ps = cnn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				dsSP.add(new SanPham(rs.getString("maSP"), rs.getString("tenSP"), rs.getDouble("donGia"),
						rs.getInt("tonKho"), rs.getString("xuatXu")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void layKHTuDB() {
		try {
			Connection cnn = DBConnection.getConnection();
			String sql = "SELECT * FROM KHACHHANG";
			PreparedStatement ps = cnn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Observer kh = new KhachHangThanThiet(rs.getString("maKH"), rs.getString("tenKH"), rs.getString("gt"),
						rs.getString("sdt"), rs.getString("diaChi"), rs.getInt("diemTichLuy"));
				dsKH.add(kh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void insertNhanVienFromDB() {
	    try {
	    	Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement("SELECT * FROM NHANVIEN");
	         ResultSet rs = ps.executeQuery();
	         dsNV.clear();
		        while (rs.next()) {
		            dsNV.add(new NhanVien(
		                rs.getString("maNV"),
		                rs.getString("tenNV"),
		                rs.getDate("ngaySinh"),
		                rs.getDate("ngayBDLam"),
		                rs.getDouble("luongCB"),
		                rs.getInt("soGioLam")
		            ));
		        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Lỗi tải dữ liệu từ sql: " + e.getMessage());
	    }
	}
	@Override
	public void themOB(Observer ob) {

	}

	@Override
	public void xoaOB(Observer ob) {
		if (dsKH.contains(ob)) {
			dsKH.remove(ob);
		}
	}

	@Override
	public void thongBaoSPM() {
		// TODO Auto-generated method stub

	}

	@Override
	public void thongBaoKM() {
		// TODO Auto-generated method stub

	}

	public void themSP(SanPham sp) {
		if (dsSP.contains(sp)) {
			for (SanPham s : dsSP) {
				if (s.equals(sp)) {
					s.capNhatSL(sp.getTonKho());
				}
			}
		} else {
			dsSP.add(sp);
		}
	}

	public List<SanPham> getDsSP() {
		dsSP.clear();
		laySPTuDB();
		return dsSP;
	}

	public boolean updateSLSP(String maSP, int soLuong) {
		try {
			Connection cnn = DBConnection.getConnection();
			String sql = "UPDATE SANPHAM SET tonKho = tonKho + ? WHERE maSP =?";
			PreparedStatement ps = cnn.prepareStatement(sql);
			ps.setInt(1, soLuong);
			ps.setString(2, maSP);
			int rs = ps.executeUpdate();
			dsSP = new ArrayList<SanPham>();
			laySPTuDB();
			return rs != 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean insertHoaDon(HoaDon hd) {
		try {
			Connection cnn = DBConnection.getConnection();
			String sql = "INSERT INTO HOADON (MaHD, MaKH, MaNV, ngayLap, TongTien) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement ps = cnn.prepareStatement(sql);

			ps.setNString(1, hd.getMaHD());
			ps.setString(2, hd.getMaKH());
			ps.setString(3, hd.getMaNV());

			// Giả sử chuỗi định dạng yyyy-MM-dd
			String dateStr = hd.getNgayLapHD();
			java.sql.Date sqlDate = java.sql.Date.valueOf(dateStr);
			ps.setDate(4, sqlDate);
			ps.setDouble(5, hd.tongTien());

			int rs = ps.executeUpdate();
			dsSP = new ArrayList<SanPham>();
			return rs != 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean insertChiTietHoaDon(ChiTietHoaDon ct) {
		try {
			Connection cnn = DBConnection.getConnection();
			String sql = "INSERT INTO CHITIETHOADON (maHD, maSP, tenSP, soLuong, donGia) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement ps = cnn.prepareStatement(sql);
			ps.setString(1, ct.getMaHD());
			ps.setString(2, ct.getMaSP());
			ps.setString(3, ct.getTenSP());
			ps.setInt(4, ct.getSoLuong());
			ps.setDouble(5, ct.getDongia());
			int rs = ps.executeUpdate();
			dsSP = new ArrayList<SanPham>();
			return rs != 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean checkTrung(String maSP, String tenSP) {
		for (SanPham sp : dsSP) {
			if (sp.getMaSP().equalsIgnoreCase(maSP) || sp.getTenSP().equalsIgnoreCase(tenSP)) {
				return true;
			}
		}
		return false;
	}

	public void themKH(String maKH, String tenKH, String gt, String sdt, String diachi, int diem) {
		try {
			Connection conn = DBConnection.getConnection();
			String sql = "INSERT INTO KHACHHANG(maKH, tenKH, gt, sdt, diaChi, diemTichLuy) VALUES (?,?,?,?,?,?) ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, maKH);
			ps.setString(2, tenKH);
			ps.setString(3, gt);
			ps.setString(4, sdt);
			ps.setString(5, diachi);
			ps.setInt(6, diem);
			int rs = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<SanPham> dsSP() {
		List<SanPham> dsSP1 = new ArrayList<SanPham>();
		try {
			Connection conn = DBConnection.getConnection();
			String bangSP = "select * from SanPham";
			PreparedStatement ps = conn.prepareStatement(bangSP);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SanPham sp = new SanPham(rs.getString("maSP"), rs.getString("tenSP"), rs.getDouble("donGia"),
						rs.getInt("tonKho"), rs.getString("xuatXu"));
				dsSP1.add(sp);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsSP1;
	}

	public SanPham searchSP(String maSP, String tenSP) {
		for (SanPham p : dsSP) {
			if (p.getMaSP().equalsIgnoreCase(maSP) || p.getTenSP().equalsIgnoreCase(tenSP)) {
				return p;
			}
		}
		return null;
	}

	public void xoaSP(String maSP) {
		try {
			Connection conn = DBConnection.getConnection();
			String bangSP = "delete from SanPham where SanPham.maSP=?";
			PreparedStatement ps = conn.prepareStatement(bangSP);
			ps.setString(1, maSP);
			int rs = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateSP(String maSP, String tenSP, double donGia, int tonKho, String xuatXu) {
		try {
			Connection conn = DBConnection.getConnection();
			String bangSP = "update SanPham set tenSP = ? , donGia= ?, tonKho= ? ,xuatXu= ? where maSP= ?";
			PreparedStatement ps = conn.prepareStatement(bangSP);
			ps.setString(1, tenSP);
			ps.setDouble(2, donGia);
			ps.setInt(3, tonKho);
			ps.setString(4, xuatXu);
			ps.setString(5, maSP);
			int rs = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void themSP(String maSP, String tenSP, double donGia, int tonKho, String xuatXu) {
		try {
			Connection conn = DBConnection.getConnection();
			String bangSP = "insert SanPham values (?,?,?,?,?) ";
			PreparedStatement ps = conn.prepareStatement(bangSP);
			ps.setString(1, maSP);
			ps.setString(2, tenSP);
			ps.setDouble(3, donGia);
			ps.setInt(4, tonKho);
			ps.setString(5, xuatXu);
			int rs = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int totalSP() {
		int total = 0;
		List<SanPham> dsSPMoi = getDsSP();
		for (SanPham sp : dsSPMoi) {
			total += sp.getTonKho();
		}
		return total;
	}

	// khách hàng
	public KhachHang searchKH(String maKH, String tenKH) {
		for (Observer observer : dsKH) {
			if (observer instanceof KhachHangThanThiet) {
				KhachHangThanThiet kh = (KhachHangThanThiet) observer;
				if (kh.getMaKH().equalsIgnoreCase(maKH) || kh.getTenKH().equalsIgnoreCase(tenKH)) {
					return kh;
				}
			}
		}
		return null;
	}

	public boolean xoaKH(String maKH) {
		String sql = "DELETE FROM KHACHHANG WHERE maKH = ?";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, maKH.trim());
			return stmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void setDsSP(List<SanPham> dsSP) {
		this.dsSP = dsSP;
	}

	public String getMaCH() {
		return maCH;
	}

	public void setMaCH(String maCH) {
		this.maCH = maCH;
	}

	public String getTenCH() {
		return tenCH;
	}

	public void setTenCH(String tenCH) {
		this.tenCH = tenCH;
	}

	public List<NhanVien> getDsNV() {
		dsNV.clear();
		insertNhanVienFromDB();
		return dsNV;
	}

	public void setDsNV(List<NhanVien> dsNV) {
		this.dsNV = dsNV;
	}

	public List<Observer> getDsKH() {
		dsKH.clear();
		layKHTuDB();
		return dsKH;
	}

	public void setDsKH(List<Observer> dsKH) {
		this.dsKH = dsKH;
	}

	public List<HoaDon> getDsHD() {
		return dsHD;
	}

	public void setDsHD(List<HoaDon> dsHD) {
		this.dsHD = dsHD;
	}

	public static void main(String[] args) {
		CuaHang c = new CuaHang();
		System.out.println(c.getDsNV());
	}

}
