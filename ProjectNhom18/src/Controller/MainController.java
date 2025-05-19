package Controller;

import model.CuaHang;
import view.MainView;

public class MainController {
	private MainView view;
	private CuaHang model;
	private TaoHoaDonController tao;
	public MainController(MainView view, CuaHang model) {
		super();
		this.view = view;
		this.model = model;
		tao = new TaoHoaDonController(view.getTaoHoaDonView());
	}
	
	public static void main(String[] args) {
		MainView view = new MainView();
		CuaHang model = new CuaHang(null, null, null, null, null, null);
		new MainController(view, model);
		
		System.out.println("1");
	
	}
}
