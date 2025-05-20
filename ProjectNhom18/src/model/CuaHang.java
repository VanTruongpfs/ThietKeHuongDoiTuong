package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import utils.DBConnection;

public class CuaHang implements Subject{
	private String maCH;
	private String tenCH;
	private List<NhanVien> dsNV;
	private List<SanPham> dsSP = new ArrayList<SanPham>();
	private List<Observer> dsKH = new ArrayList<Observer>();
	private List<HoaDon> dsHD;
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
	}
	public void laySPTuDB() {
		 try {
				Connection cnn = DBConnection.getConnection();
				String sql ="SELECT * FROM SANPHAM";
				PreparedStatement ps = cnn.prepareStatement(sql);
				ResultSet rs =  ps.executeQuery();
				while(rs.next()) {
					dsSP.add( 
					new SanPham(rs.getString("maSP"), rs.getString("tenSP"), rs.getDouble("donGia"), rs.getInt("tonKho"), rs.getString("xuatXu")));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	 }
	
	
	public void layKHTuDB() {
			try {
				Connection cnn = DBConnection.getConnection();
				String sql ="SELECT * FROM KHACHHANG";
				PreparedStatement ps = cnn.prepareStatement(sql);
				ResultSet rs =  ps.executeQuery();
				while(rs.next()) {
					Observer kh = new KhachHangThanThiet(rs.getString("maKH"), rs.getString("tenKH"), rs.getString("sdt"), rs.getString("diaChi"), rs.getBoolean("loaiKH"), rs.getInt("diemTichLuy")); 
					dsKH.add(kh);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	@Override
	public void themOB(Observer ob) {
		
	}
	@Override
	public void xoaOB(Observer ob) {
		if(dsKH.contains(ob)) {
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
	
	public void themSP(SanPham sp ) {
		if(dsSP.contains(sp)) {
			for (SanPham s : dsSP) {
				if(s.equals(sp)) {
					s.capNhatSL(sp.getTonKho());
				}
			}
		}else {
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
				String sql ="UPDATE SANPHAM SET tonKho = tonKho + ? WHERE maSP =?";
				PreparedStatement ps = cnn.prepareStatement(sql);
				ps.setInt(1, soLuong);
				ps.setString(2, maSP);
				int rs =  ps.executeUpdate();
				dsSP = new ArrayList<SanPham>();
				laySPTuDB();
				return rs!=0;
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
		        ps.setDate(4,sqlDate);
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
		return dsNV;
	}
	public void setDsNV(List<NhanVien> dsNV) {
		this.dsNV = dsNV;
	}
	public List<Observer> getDsKH() {
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
	
}
