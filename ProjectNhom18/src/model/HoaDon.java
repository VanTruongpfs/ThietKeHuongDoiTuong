package model;

import java.util.List;

public class HoaDon {
	private String maHD;
	private String maKH;
	private String maNV;
	private List<SanPham> dsSP;
	private String ngayLapHD;
	private ThanhToan phuongthucPay;
	private boolean tinhTrangHD;
	private double tongTien;
	public HoaDon(String maHD, String maKH, List<SanPham> dsSP,String ngayLapHD, ThanhToan phuongthucPay, boolean tinhTrangHD) {
		super();
		this.maHD = maHD;
		this.maKH = maKH;
		this.dsSP = dsSP;
		this.ngayLapHD = ngayLapHD;
		this.phuongthucPay = phuongthucPay;
		this.tinhTrangHD = tinhTrangHD;
	}
	public HoaDon(String maHD, String maNV, String maKH, String ngayLap, double tongTien) {
		this.maHD = maHD;
		this.maNV = maNV;
		this.maKH = maKH;
		this.ngayLapHD = ngayLap;
		this.tongTien = tongTien;
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
	public String getNgayLapHD() {
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
	
	
}
