package model;

public class ChuyenKhoan implements PTPAY {
	
	public ChuyenKhoan() {
		super();
	}
	@Override
	public double pay(double tien) {
		// TODO Auto-generated method stub
		return tien;
	}

	@Override
	public String getPT() {
		// TODO Auto-generated method stub
		return "Chuyển Khoản";
	}

}
