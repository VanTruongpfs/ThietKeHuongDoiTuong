package Controller;

import view.MainView;
import view.TaoHoaDonView;

public class TaoHoaDonController {
	private TaoHoaDonView view;
	public TaoHoaDonController(TaoHoaDonView view) {
		this.view = view;
		initController();
	}
	 private void initController() {
	        view.getBtnHuy().addActionListener(e -> themSanPhamVaoGio());
	    
	    }
	 private void themSanPhamVaoGio() {
	        // Logic thêm sản phẩm vào JTable giỏ hàng
	        System.out.println("Đã thêm sản phẩm vào giỏ");
	    }
}
