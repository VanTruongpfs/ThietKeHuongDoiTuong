package model;

import java.util.List;

public class CuaHang implements Subject{
	private String maCH;
	private String tenCH;
	private List<NhanVien> dsNV;
	private List<SanPham> dsSP;
	private List<KhachHang> dsKH;
	private List<HoaDon> dsHD;
	public CuaHang(String maCH, String tenCH, List<NhanVien> dsNV, List<SanPham> dsSP, List<KhachHang> dsKH,
			List<HoaDon> dsHD) {
		super();
		this.maCH = maCH;
		this.tenCH = tenCH;
		this.dsNV = dsNV;
		this.dsSP = dsSP;
		this.dsKH = dsKH;
		this.dsHD = dsHD;
	}
	@Override
	public void themOB(Observer ob) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void xoaOB(Observer ob) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void thongBaoSPM() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void thongBaoKM() {
		// TODO Auto-generated method stub
		
	}
	
	
}
