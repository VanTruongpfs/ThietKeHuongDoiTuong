package Controller;
import model.CuaHang;
import model.SanPham;
import utils.DBConnection;
import view.QLSanPhamView;
import view.TaoHoaDonView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SanPhamController {
   private QLSanPhamView view;
   private TaoHoaDonView tao = new TaoHoaDonView();
   private CuaHang model;
public SanPhamController(QLSanPhamView view, CuaHang model) {
	super();
	this.view = view;
	this.model = model;
	hienThiSP();
	initController();
}

   private void initController() {
	   save();
   }
	
	
	public void save() {
		view.getbSave().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 view.getTtCOT().setRowCount(0);
				 for (SanPham sanPham : model.getDsSP()) {
						String[] tt = { sanPham.getMaSP(),sanPham.getTenSP(), sanPham.getDonGia()+"", sanPham.getTonKho()+"",
								 sanPham.getXuatXu()};
						view.getTtCOT().addRow(tt);
				}				
			}
		});
	}
	public void hienThiSP() {
		view.getTtCOT().setRowCount(0);
		 for (SanPham sanPham : model.getDsSP()) {
				String[] tt = { sanPham.getMaSP(),sanPham.getTenSP(), sanPham.getDonGia()+"", sanPham.getTonKho()+"",
						 sanPham.getXuatXu()};
				view.getTtCOT().addRow(tt);
		}	
	}
	
}
