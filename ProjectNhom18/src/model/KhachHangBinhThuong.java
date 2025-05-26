
package model;

public class KhachHangBinhThuong extends KhachHang {

	
	public KhachHangBinhThuong(String maKH, String tenKH, String gt, String sdt, String diachi) {
		super(maKH, tenKH, gt, sdt, diachi);
	}

	@Override
	public void hienThiTB(String nd) {
		System.out.println(tenKH+" đã nhận được thông báo: "+nd);
	}
	@Override
	public void themSP(SanPham sp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDiemTichLuy() {
		// TODO Auto-generated method stub
		return 0;
	}

}
