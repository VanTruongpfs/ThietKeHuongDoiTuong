package model;

import java.sql.Date;

public class NhanVien {
	private String maNV;
	private String tenNV;
	private Date ngaySinh;
	private Date ngayBDLam;
	private double luongCB;
	private int soGioLam;
	
	public NhanVien(String maNV, String tenNV, Date ngaySinh, Date ngayBDLam, double luongCB, int soGioLam) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.ngaySinh = ngaySinh;
		this.ngayBDLam = ngayBDLam;
		this.luongCB = luongCB;
		this.soGioLam = soGioLam;
	}
	
	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public Date getNgayBDLam() {
		return ngayBDLam;
	}

	public void setNgayBDLam(Date ngayBDLam) {
		this.ngayBDLam = ngayBDLam;
	}

	public double getLuongCB() {
		return luongCB;
	}

	public void setLuongCB(double luongCB) {
		this.luongCB = luongCB;
	}

	public int getSoGioLam() {
		return soGioLam;
	}

	public void setSoGioLam(int soGioLam) {
		this.soGioLam = soGioLam;
	}

	@Override
	public String toString() {
	    return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV +
	           ", ngaySinh=" + ngaySinh + ", ngayBDLam=" + ngayBDLam +
	           ", luongCB=" + luongCB + ", soGioLam=" + soGioLam + "]";
	}
	public double TinhLuong() {
		return luongCB * soGioLam;
	}
}
