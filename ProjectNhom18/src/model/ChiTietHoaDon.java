package model;

public class ChiTietHoaDon {
	private String maHD;
	private String maSP;
	private String tenSP;
	private int soLuong;
	private double dongia;
	public ChiTietHoaDon(String maHD, String maSP, String tenSP, int soLuong, double dongia) {
		super();
		this.maHD = maHD;
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.soLuong = soLuong;
		this.dongia = dongia;
	}
	public String getMaHD() {
		return maHD;
	}
	public String getMaSP() {
		return maSP;
	}
	public String getTenSP() {
		return tenSP;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public double getDongia() {
		return dongia;
	}
	
}
