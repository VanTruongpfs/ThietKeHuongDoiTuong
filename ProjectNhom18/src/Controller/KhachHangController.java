package Controller;

import model.CuaHang;
import model.KhachHang;
import view.QLKHView;

public class KhachHangController {
	private QLKHView view;
	private CuaHang model;
	
	public KhachHangController(QLKHView view, CuaHang model) {
		super();
		this.view = view;
		this.model = model;
		
	}
	public QLKHView getView() {
		return view;
	}
	public void setView(QLKHView view) {
		this.view = view;
	}
	public CuaHang getModel() {
		return model;
	}
	public void setModel(CuaHang model) {
		this.model = model;
	}

	
}
