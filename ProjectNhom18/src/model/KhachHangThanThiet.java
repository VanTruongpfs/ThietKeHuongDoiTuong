
package model;
public class KhachHangThanThiet extends KhachHang{
    private int diemTichLuy;

	

	public KhachHangThanThiet(String maKH, String tenKH, String gt, String sdt, String diachi, int diemTichLuy) {
		super(maKH, tenKH, gt, sdt, diachi);
		this.diemTichLuy = diemTichLuy;
	}

	@Override
	public void hienThiTTSP(String nd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hienThiKM(String nd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void themSP(SanPham sp) {
		// TODO Auto-generated method stub
		
	}

	public int getDiemTichLuy() {
		return diemTichLuy;
	}

	public void setDiemTichLuy(int diemTichLuy) {
		this.diemTichLuy = diemTichLuy;
	}
    
    
}
