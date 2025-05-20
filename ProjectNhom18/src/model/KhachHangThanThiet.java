package model;
public class KhachHangThanThiet extends KhachHang{
    private int diemTichLuy;

	public KhachHangThanThiet(String maKH, String tenKH, String sdt, String diachi, Boolean loaiKH, int diemTichLuy) {
		super(maKH, tenKH, sdt, diachi, loaiKH);
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
    
    
}