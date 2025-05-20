package model;

public class TheNganHang implements PTPAY {
	
	
	public TheNganHang() {
		super();
	}

	@Override
	public boolean thanhToan() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double pay(double tien) {
		return tien*= 1.02 ;
	}

}
