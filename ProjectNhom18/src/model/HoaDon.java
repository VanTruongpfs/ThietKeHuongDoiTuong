package model;

import java.sql.Date;
import java.util.List;

public class HoaDon {
	private String maHD;
	private String maKH;
	private String maNV;
	private List<SanPham> dsSP;
	private Date ngayLapHD;
	private ThanhToan phuongthucPay;
	private boolean tinhTrangHD;
	private double tongTien;
	private String PT;

	public HoaDon(String maHD, String maNV, String maKH, Date date, double tongTien,String PT) {
		this.maHD = maHD;
		this.maNV = maNV;
		this.maKH = maKH;
		this.ngayLapHD = date;
		this.tongTien = tongTien;
		this.PT=PT;
	}
	public String getMaHD() {
		return maHD;
	}
	public String getMaKH() {
		return maKH;
	}
	public String getMaNV() {
		return maNV;
	}
	public List<SanPham> getDsSP() {
		return dsSP;
	}
	public Date getNgayLapHD() {
		return ngayLapHD;
	}
	public ThanhToan getPhuongthucPay() {
		return phuongthucPay;
	}
	public boolean isTinhTrangHD() {
		return tinhTrangHD;
	}
	
	public double tongTien() {
		return tongTien;
	}
	public String getPT() {
		return PT;
	}
	public void setPT(String pT) {
		PT = pT;
	}
	
	
}
