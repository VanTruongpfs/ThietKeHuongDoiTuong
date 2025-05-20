package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.SanPhamController;
import model.CuaHang;
import model.SanPham;

public class QLSanPhamView {
    JPanel pnQuanLySanPham = new JPanel();
    JPanel quanLiSanPHam, panelSearch, panelBangThongTinSP, panelBangThongTinSP1,
           panelBangThongTinSP2, panelQlFunction, pnADDRe1, pnADDRe11, pnADDRe12,
           pnADDRe2, pnADDRe21, pnADDRe22;
    JButton bSave, add, remove, nutSerach;
    JTextField textMaSP, textMaSP_Xoa, textTenSP, textSLSP, textGiaSP, textAnhSP, boxSearch;
    DefaultTableModel ttCOT;
    JTable tableBangThongTinSP;
    JScrollPane scrollThongTinSP;
    public QLSanPhamView() {
        pnQuanLySanPham.setLayout(new BorderLayout()); 
        bSave = new JButton("Lưu");
		add = new JButton("Thêm");
		remove = new JButton("Xóa");
		textMaSP = new JTextField(10);
		textMaSP_Xoa = new JTextField(10);
		textTenSP = new JTextField(10);
		textSLSP = new JTextField(10);
		textGiaSP = new JTextField(10);
		textAnhSP = new JTextField(10);
		nutSerach = new JButton("Tìm kiếm");
		//
		//
		quanLiSanPHam = new JPanel();
		quanLiSanPHam.setPreferredSize(new Dimension(975, 300));
		quanLiSanPHam.setLayout(new BorderLayout());
		quanLiSanPHam.setBorder(BorderFactory.createTitledBorder("Quản lí sản phẩm"));
		// thanh tiềm kiếm sản phẩm
		panelSearch = new JPanel();
		panelSearch.setLayout(new FlowLayout());
		panelSearch.setBorder(BorderFactory.createTitledBorder("Tìm kiếm sản phẩm"));
		boxSearch = new JTextField(20);
		panelSearch.add(boxSearch);
		panelSearch.add(nutSerach);

		quanLiSanPHam.add(panelSearch, BorderLayout.NORTH);

		// bảng thông tin các loại sản phẩm toàn kho;
		panelBangThongTinSP = new JPanel();
		panelBangThongTinSP.setLayout(new BorderLayout());
		quanLiSanPHam.add(panelBangThongTinSP, BorderLayout.CENTER);

		panelBangThongTinSP1 = new JPanel();
		panelBangThongTinSP.setLayout(new BorderLayout());
		panelBangThongTinSP.add(panelBangThongTinSP1, BorderLayout.CENTER);
		panelBangThongTinSP.setBorder(BorderFactory.createTitledBorder("Thông tin các loại sản phẩm"));
		String[] thongtinCacCot = { "Mã Sản Phẩm", "Tên Sản Phẩm","Giá", "Số lượng", "Xuất xứ" };
		
		// không cho chỉnh sửa cột mã
		ttCOT = new DefaultTableModel(thongtinCacCot,0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column != 0;
			}
		};
	     
		tableBangThongTinSP = new JTable(ttCOT);
		scrollThongTinSP = new JScrollPane(tableBangThongTinSP);
		panelBangThongTinSP1.add(scrollThongTinSP);
		//
		panelBangThongTinSP2 = new JPanel();
		panelBangThongTinSP2.setLayout(new FlowLayout());
		panelBangThongTinSP.add(panelBangThongTinSP2, BorderLayout.SOUTH);
		panelBangThongTinSP2.add(bSave);

		// bảng quản lí chức năng sản phẩm
		panelQlFunction = new JPanel();
		panelQlFunction.setLayout(new BoxLayout(panelQlFunction, BoxLayout.Y_AXIS));
		quanLiSanPHam.add(panelQlFunction, BorderLayout.WEST);

		//
		pnADDRe1 = new JPanel();
		pnADDRe1.setLayout(new GridLayout(2, 1));
		pnADDRe1.setBorder(BorderFactory.createTitledBorder("Thêm Sản Phẩm"));
		panelQlFunction.add(pnADDRe1);

		pnADDRe11 = new JPanel();
		pnADDRe11.setLayout(new GridLayout(5, 2));
		pnADDRe1.add(pnADDRe11);
		pnADDRe11.add(new JLabel("Mã Sản Phẩm:"));
		pnADDRe11.add(textMaSP);
		pnADDRe11.add(new JLabel("Tên Sản Phẩm:"));
		pnADDRe11.add(textTenSP);
		pnADDRe11.add(new JLabel("Số Lượng:"));
		pnADDRe11.add(textSLSP);
		pnADDRe11.add(new JLabel("Giá Sản Phẩm:"));
		pnADDRe11.add(textGiaSP);
		pnADDRe11.add(new JLabel("Ảnh Sản Phẩm:"));
		pnADDRe11.add(textAnhSP);

		pnADDRe12 = new JPanel();
		pnADDRe12.setLayout(new FlowLayout());
		pnADDRe1.add(pnADDRe12);
		pnADDRe12.add(add);
		//
		pnADDRe2 = new JPanel();
		pnADDRe2.setLayout(new GridLayout(2, 1));
		pnADDRe2.setBorder(BorderFactory.createTitledBorder("Xóa Sản Phẩm"));
		panelQlFunction.add(pnADDRe2);

		pnADDRe21 = new JPanel();
		pnADDRe21.setLayout(new GridLayout(2, 1));
		pnADDRe2.add(pnADDRe21);
		pnADDRe21.add(new JLabel("Mã Sản Phẩm:"));
		pnADDRe21.add(textMaSP_Xoa);

		pnADDRe22 = new JPanel();
		pnADDRe22.setLayout(new FlowLayout());
		pnADDRe2.add(pnADDRe22);
		pnADDRe22.add(remove);
		//
		pnQuanLySanPham.removeAll();
		pnQuanLySanPham.add(quanLiSanPHam, BorderLayout.CENTER);
		pnQuanLySanPham.revalidate();
		pnQuanLySanPham.repaint();
		//
    }
    public JPanel QLSP(){
        return pnQuanLySanPham;
    }
	public DefaultTableModel getTtCOT() {
		return ttCOT;
	}
	public void setTtCOT(DefaultTableModel ttCOT) {
		this.ttCOT = ttCOT;
	}
	public JButton getbSave() {
		return bSave;
	}
	public void setbSave(JButton bSave) {
		this.bSave = bSave;
	}
	public JPanel getPnQuanLySanPham() {
		return pnQuanLySanPham;
	}
	public JPanel getQuanLiSanPHam() {
		return quanLiSanPHam;
	}
	public JPanel getPanelSearch() {
		return panelSearch;
	}
	public JPanel getPanelBangThongTinSP() {
		return panelBangThongTinSP;
	}
	public JPanel getPanelBangThongTinSP1() {
		return panelBangThongTinSP1;
	}
	public JPanel getPanelBangThongTinSP2() {
		return panelBangThongTinSP2;
	}
	public JPanel getPanelQlFunction() {
		return panelQlFunction;
	}
	public JPanel getPnADDRe1() {
		return pnADDRe1;
	}
	public JPanel getPnADDRe11() {
		return pnADDRe11;
	}
	public JPanel getPnADDRe12() {
		return pnADDRe12;
	}
	public JPanel getPnADDRe2() {
		return pnADDRe2;
	}
	public JPanel getPnADDRe21() {
		return pnADDRe21;
	}
	public JPanel getPnADDRe22() {
		return pnADDRe22;
	}
	public JButton getAdd() {
		return add;
	}
	public JButton getRemove() {
		return remove;
	}
	public JButton getNutSerach() {
		return nutSerach;
	}
	public JTextField getTextMaSP() {
		return textMaSP;
	}
	public JTextField getTextMaSP_Xoa() {
		return textMaSP_Xoa;
	}
	public JTextField getTextTenSP() {
		return textTenSP;
	}
	public JTextField getTextSLSP() {
		return textSLSP;
	}
	public JTextField getTextGiaSP() {
		return textGiaSP;
	}
	public JTextField getTextAnhSP() {
		return textAnhSP;
	}
	public JTextField getBoxSearch() {
		return boxSearch;
	}
	public JTable getTableBangThongTinSP() {
		return tableBangThongTinSP;
	}
	public JScrollPane getScrollThongTinSP() {
		return scrollThongTinSP;
	}
	
    
}
