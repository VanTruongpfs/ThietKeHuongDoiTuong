package model;

import java.util.List;

public abstract class KhachHang implements Observer{
    protected String maKH;
    protected String tenKH;
    protected String sdt;
    protected String diachi;
    private Boolean loaiKH;
    protected HoaDon hoadon;
     public KhachHang(String maKH, String tenKH, String sdt, String diachi, Boolean loaiKH) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.sdt = sdt;
        this.diachi = diachi;
        this.loaiKH = loaiKH;
    }
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	public Boolean getLoaiKH() {
		return loaiKH;
	}
	public void setLoaiKH(Boolean loaiKH) {
		this.loaiKH = loaiKH;
	}
	public HoaDon getHoadon() {
		return hoadon;
	}
	public void setHoadon(HoaDon hoadon) {
		this.hoadon = hoadon;
	}
     
}
