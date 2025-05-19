package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
import model.SanPham;

public class QLSanPhamView {
    JPanel pnQuanLySanPham = new JPanel();
    JPanel quanLiSanPHam, panelSearch, panelBangThongTinSP, panelBangThongTinSP1,
           panelBangThongTinSP2, panelQlFunction, pnADDRe1, pnADDRe11, pnADDRe12,
           pnADDRe2, pnADDRe21, pnADDRe22;
    JButton bSave, add, remove, nutSerach;
    JTextField textMaSP, textMaSP_Xoa, textTenSP, textSLSP, textGiaSP, textAnhSP, boxSearch;
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
		String[] thongtinCacCot = { "Mã Sản Phẩm", "Tên Sản Phẩm", "Số lượng Tồn Kho", "Giá Niêm Yết", "Xuất xứ" };
	
		// không cho chỉnh sửa cột mã
		DefaultTableModel ttCOT = new DefaultTableModel(thongtinCacCot,0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column != 0;
			}
		};
		 SanPhamController controller = new SanPhamController();
	     
//		List<SanPham> data = controller.hienThiTatCaSanPham();
//		for (SanPham sp : data) {
//		    Object[] row = {
//		        sp.getMaSP(),
//		        sp.getTenSP(),
//		        sp.getTonKho(),
//		        sp.getDonGia(),
//		        sp.getXuatXu()
//		    };
//		    ttCOT.addRow(row);
//		}
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
}
