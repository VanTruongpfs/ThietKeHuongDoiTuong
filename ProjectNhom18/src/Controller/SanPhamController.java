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

import javax.swing.table.DefaultTableModel;

public class SanPhamController {
	private QLSanPhamView view;
	private CuaHang model;
	private TaoHoaDonController taoHoaDonController;

	public SanPhamController(QLSanPhamView view, CuaHang model, TaoHoaDonController taoHoaDonController) {
		super();
		this.view = view;
		this.model = model;
		this.taoHoaDonController = taoHoaDonController;
		initController();
		capNhatBang();
	}

	private void initController() {
		view.getAdd().addActionListener(e -> themSP());
		view.getNutSerach().addActionListener(e -> search());
		view.getRemove().addActionListener(e -> xoaSP());
		view.getChapNhan().addActionListener(e -> updateSP());
		reset();
	}

	public void xoaSP() {
		String maSP = view.getTextMaSP_Xoa().getText().trim();
		if (maSP.isEmpty()) {
			view.showInvalidDialog();
		} else {
			if (model.checkTrung(maSP, maSP)) {
				model.xoaSP(maSP);
				capNhatBang();
				view.getTextMaSP_Xoa().setText("");
				view.showValidDialog();
			} else {
				view.showInvalidDialog();
			}
		}
	}

	public void save() {
		System.out.println("Đã save");
	}

	public void themSP() {
		String maSP = view.getTextMaSP().getText().trim();
		String tenSP = view.getTextTenSP().getText().trim();
		String soLuongStr = view.getTextSLSP().getText().trim();
		String giaStr = view.getTextGiaSP().getText().trim();
		String xuatXu = view.getTextAnhSP().getText().trim();
		try {
			if (maSP.isEmpty() || tenSP.isEmpty() || soLuongStr.isEmpty() || giaStr.isEmpty() || xuatXu.isEmpty()) {
				view.showInvalidDialog();
			} else {
				if (model.checkTrung(maSP, tenSP)) {
					view.showInvalidDialog();
				} else {
					int soLuong = Integer.parseInt(soLuongStr);
					double gia = Double.parseDouble(giaStr);
					model.themSP(maSP, tenSP, gia, soLuong, xuatXu);
					capNhatBang();
					view.showValidDialog();
					view.getTextAnhSP().setText("");
					view.getTextGiaSP().setText("");
					view.getTextTenSP().setText("");
					view.getTextSLSP().setText("");
					view.getTextMaSP().setText("");
				}
			}
		} catch (Exception e) {
			view.showInvalidDialog();
		}
	}

	public void capNhatBang() {
		DefaultTableModel modelTable = view.getTtCOT();
		modelTable.setRowCount(0);
		List<SanPham> danhSachMoi = model.getDsSP();
		for (SanPham sp : danhSachMoi) {
			String[] row = { sp.getMaSP(), sp.getTenSP(), String.valueOf(sp.getDonGia()),
					String.valueOf(sp.getTonKho()), sp.getXuatXu() };
			modelTable.addRow(row);
		}
		view.getTotalSP().setText(model.totalSP() + "");
		taoHoaDonController.hienThiSP();;
	}

	public void search() {
		String ttSearch = view.getBoxSearch().getText();
		DefaultTableModel modelTable = view.getTtCOT();
		SanPham sp = model.searchSP(ttSearch, ttSearch);
		modelTable.setRowCount(0);
		if (sp != null) {
			String[] row = { sp.getMaSP(), sp.getTenSP(),
					String.valueOf(sp.getDonGia()), String.valueOf(sp.getTonKho()), sp.getXuatXu() };
			modelTable.addRow(row);
		} else {
			capNhatBang();
		}
	}

	public void updateSP() {
		String maSP = view.getTextMASP_Sua().getText().trim();
		String tenSP = view.getTextTenSP_Sua().getText().trim();
		String soLuongStr = view.getTextSLSP_Sua().getText().trim();
		String giaStr = view.getTextGiaSP_Sua().getText().trim();
		String xuatXu = view.getTextXXSP_SUa().getText().trim();
		int soLuong = Integer.parseInt(soLuongStr);
		double gia = Double.parseDouble(giaStr);
		model.updateSP(maSP, tenSP, gia, soLuong, xuatXu);
		capNhatBang();
		view.getFramSua().dispose();
		view.showValidDialog();
	}
	public void reset() {
		view.getReset().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				capNhatBang();
			}
		});
	}

}
