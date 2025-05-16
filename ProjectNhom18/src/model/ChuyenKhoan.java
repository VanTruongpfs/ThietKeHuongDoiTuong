package model;

public class ChuyenKhoan implements PTPAY {
	private String thongTinTaiKhoan;
	
	public ChuyenKhoan(String thongTinTaiKhoan) {
		super();
		this.thongTinTaiKhoan = thongTinTaiKhoan;
	}

	@Override
	public boolean thanhToan() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double pay() {
		// TODO Auto-generated method stub
		return 0;
	}

}
