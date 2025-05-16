package model;

import java.util.Date;

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
	
}
