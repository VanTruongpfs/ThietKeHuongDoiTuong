package model;

public class TienMat implements PTPAY {
	@Override
	public double pay(double tien) {
		return Math.round(tien);
	}

	@Override
	public String getPT() {
		// TODO Auto-generated method stub
		return "Tiền mặt";
	}

}
