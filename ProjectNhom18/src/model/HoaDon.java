package model;

import java.util.List;

public class HoaDon {
	private String maHD;
	private String maKH;
	private List<SanPham> dsSP;
	private ThanhToan phuongthucPay;
	private boolean tinhTrangHD;
	public HoaDon(String maHD, String maKH, List<SanPham> dsSP, ThanhToan phuongthucPay, boolean tinhTrangHD) {
		super();
		this.maHD = maHD;
		this.maKH = maKH;
		this.dsSP = dsSP;
		this.phuongthucPay = phuongthucPay;
		this.tinhTrangHD = tinhTrangHD;
	}
	
}
