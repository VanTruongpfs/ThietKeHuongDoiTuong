package Controller;

import model.CuaHang;
import view.MainView;
import view.QLSanPhamView;

public class MainController {
	private MainView view;
	private CuaHang model;
	private TaoHoaDonController tao;
	private SanPhamController sp;
	public MainController(MainView view, CuaHang model) {
		super();
		this.view = view;
		this.model = model;
		tao = new TaoHoaDonController(view.getTaoHoaDonView(), model);
		sp = new SanPhamController(view.getQlspView(), model);
	}
	
	public static void main(String[] args) {
		MainView view = new MainView();
		CuaHang model = new CuaHang();
		new MainController(view, model);
	}
}
