
package model;

import java.util.List;

public abstract class KhachHang implements Observer{
    protected String maKH;
    protected String tenKH;
    protected String gt;
    protected String sdt;
    protected String diachi;
     
	public KhachHang(String maKH, String tenKH, String gt, String sdt, String diachi) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.gt = gt;
		this.sdt = sdt;
		this.diachi = diachi;

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

	public String getGt() {
		return gt;
	}

	public void setGt(String gt) {
		this.gt = gt;
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

	public abstract int getDiemTichLuy();
	
     
}
