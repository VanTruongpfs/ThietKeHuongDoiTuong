package Controller;

import model.CuaHang;
import view.MainView;
import view.QLSanPhamView;

public class MainController {
	private MainView view;
	private CuaHang model;
	private TaoHoaDonController tao;
	private SanPhamController sp;
	private KhachHangController kh;
	private NhanVienController nv;
	private HoaDonController hd;
	public MainController(MainView view, CuaHang model) {
		super();
		this.view = view;
		this.model = model;
		tao = new TaoHoaDonController(view.getTaoHoaDonView(), model);
		sp = new SanPhamController(view.getQlspView(), model,tao);
		kh = new KhachHangController(view.getQlkhView(), model);
		nv = new NhanVienController(view.getQlnvView(), model);
		hd = new HoaDonController(view.getQlhdView(), model);
		
	}
	
	public static void main(String[] args) {
		MainView view = new MainView();
		CuaHang model = new CuaHang();
		new MainController(view, model);
	}
}
