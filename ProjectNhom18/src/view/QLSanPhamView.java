package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Controller.SanPhamController;
import model.CuaHang;
import model.SanPham;

public class QLSanPhamView {
	JPanel pnQuanLySanPham = new JPanel();
	JPanel quanLiSanPHam, panelSearch, panelBangThongTinSP, panelBangThongTinSP1, panelBangThongTinSP2, panelQlFunction,
			pnADDRe1, pnADDRe11, pnADDRe12, pnADDRe2, pnADDRe21, pnADDRe22, panelSeacrchAndTK, panelTK;
	JButton sua, add, remove, nutSerach, chapNhan, huy;
	JTextField textMaSP, textMaSP_Xoa, textTenSP, textSLSP, textGiaSP, textAnhSP, boxSearch, totalSP, textMASP_Sua,
			textTenSP_Sua, textSLSP_Sua, textGiaSP_Sua, textXXSP_SUa;
	DefaultTableModel ttCOT;
	JTable tableBangThongTinSP;
	JScrollPane scrollThongTinSP;
	JFrame framSua;

	public QLSanPhamView() {
		pnQuanLySanPham.setLayout(new BorderLayout());
		sua = new JButton("Sửa");
		add = new JButton("Thêm");
		remove = new JButton("Xóa");
		textMaSP = new JTextField(10);
		textMaSP_Xoa = new JTextField(10);
		textTenSP = new JTextField(10);
		textSLSP = new JTextField(10);
		textGiaSP = new JTextField(10);
		textAnhSP = new JTextField(10);
		totalSP = new JTextField(10);
		totalSP.setEditable(false);
		nutSerach = new JButton("Tìm kiếm");
		//
		textMASP_Sua = new JTextField(10);
		textTenSP_Sua = new JTextField(10);
		textGiaSP_Sua = new JTextField(10);
		textSLSP_Sua = new JTextField(10);
		textXXSP_SUa = new JTextField(10);
		chapNhan = new JButton("Chấp nhận");
		huy = new JButton("Hủy");
		//
		quanLiSanPHam = new JPanel();
		quanLiSanPHam.setPreferredSize(new Dimension(975, 300));
		quanLiSanPHam.setLayout(new BorderLayout());
		quanLiSanPHam.setBorder(BorderFactory.createTitledBorder("Quản lí sản phẩm"));
		// thanh tiềm kiếm sản phẩm và thống kê sản phẩm
		panelSeacrchAndTK = new JPanel();
		quanLiSanPHam.add(panelSeacrchAndTK, BorderLayout.NORTH);
		panelSeacrchAndTK.setLayout(new FlowLayout());

		panelSearch = new JPanel();
		panelSearch.setLayout(new FlowLayout());
		panelSearch.setBorder(BorderFactory.createTitledBorder("Tìm kiếm sản phẩm"));
		boxSearch = new JTextField(20);
		panelSearch.add(boxSearch);
		panelSearch.add(nutSerach);
		panelSeacrchAndTK.add(panelSearch);

		panelTK = new JPanel();
		panelTK.setLayout(new FlowLayout());
		panelTK.setBorder(BorderFactory.createTitledBorder("Thống Kê sản phẩm"));
		panelTK.add(new JLabel("Tổng Sản Phẩm"));
		panelTK.add(totalSP);
		panelSeacrchAndTK.add(panelTK);

		// bảng thông tin các loại sản phẩm toàn kho;
		panelBangThongTinSP = new JPanel();
		panelBangThongTinSP.setLayout(new BorderLayout());
		quanLiSanPHam.add(panelBangThongTinSP, BorderLayout.CENTER);

		panelBangThongTinSP1 = new JPanel();
		panelBangThongTinSP.setLayout(new BorderLayout());
		panelBangThongTinSP.add(panelBangThongTinSP1, BorderLayout.CENTER);
		panelBangThongTinSP.setBorder(BorderFactory.createTitledBorder("Thông tin các loại sản phẩm"));
		String[] thongtinCacCot = { "Mã Sản Phẩm", "Tên Sản Phẩm", "Giá", "Số Lượng", "Xuất xứ" };

		// không cho chỉnh sửa cột mã
		ttCOT = new DefaultTableModel(thongtinCacCot, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column != 0;
			}
		};

		List<SanPham> data = new ArrayList<SanPham>();
		data.add(new SanPham("SP001", "Sữa tươi Vinamilk", 25000, 100, "Việt Nam"));
		data.add(new SanPham("SP002", "Mì tôm Hảo Hảo", 4500, 300, "Việt Nam"));
		for (SanPham sp : data) {
			String[] row = { sp.getMaSP(), sp.getTenSP(), sp.getDonGia() + "", sp.getTonKho() + "", sp.getXuatXu() };
			ttCOT.addRow(row);
		}
		tableBangThongTinSP = new JTable(ttCOT);
		scrollThongTinSP = new JScrollPane(tableBangThongTinSP);
		panelBangThongTinSP1.add(scrollThongTinSP);
		//
		panelBangThongTinSP2 = new JPanel();
		panelBangThongTinSP2.setLayout(new FlowLayout());
		panelBangThongTinSP.add(panelBangThongTinSP2, BorderLayout.SOUTH);
		panelBangThongTinSP2.add(sua);

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
		pnADDRe11.add(new JLabel("Xuất xứ"));
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
		sua.addActionListener(e -> cuaSoSua());
		huy.addActionListener(e -> framSua.dispose());
	}

	public JPanel getPanelSeacrchAndTK() {
		return panelSeacrchAndTK;
	}

	public void setPanelSeacrchAndTK(JPanel panelSeacrchAndTK) {
		this.panelSeacrchAndTK = panelSeacrchAndTK;
	}

	public JPanel getPanelTK() {
		return panelTK;
	}

	public void setPanelTK(JPanel panelTK) {
		this.panelTK = panelTK;
	}

	public JButton getSua() {
		return sua;
	}

	public void setSua(JButton sua) {
		this.sua = sua;
	}

	public JButton getChapNhan() {
		return chapNhan;
	}

	public void setChapNhan(JButton chapNhan) {
		this.chapNhan = chapNhan;
	}

	public JButton getHuy() {
		return huy;
	}

	public void setHuy(JButton huy) {
		this.huy = huy;
	}

	public JTextField getTotalSP() {
		return totalSP;
	}

	public void setTotalSP(JTextField totalSP) {
		this.totalSP = totalSP;
	}

	public JTextField getTextMASP_Sua() {
		return textMASP_Sua;
	}

	public void setTextMASP_Sua(JTextField textMASP_Sua) {
		this.textMASP_Sua = textMASP_Sua;
	}

	public JTextField getTextTenSP_Sua() {
		return textTenSP_Sua;
	}

	public void setTextTenSP_Sua(JTextField textTenSP_Sua) {
		this.textTenSP_Sua = textTenSP_Sua;
	}

	public JTextField getTextSLSP_Sua() {
		return textSLSP_Sua;
	}

	public void setTextSLSP_Sua(JTextField textSLSP_Sua) {
		this.textSLSP_Sua = textSLSP_Sua;
	}

	public JTextField getTextGiaSP_Sua() {
		return textGiaSP_Sua;
	}

	public void setTextGiaSP_Sua(JTextField textGiaSP_Sua) {
		this.textGiaSP_Sua = textGiaSP_Sua;
	}

	public JTextField getTextXXSP_SUa() {
		return textXXSP_SUa;
	}

	public void setTextXXSP_SUa(JTextField textXXSP_SUa) {
		this.textXXSP_SUa = textXXSP_SUa;
	}

	public JFrame getFramSua() {
		return framSua;
	}

	public void setFramSua(JFrame framSua) {
		this.framSua = framSua;
	}

	public JPanel QLSP() {
		return pnQuanLySanPham;
	}

	public DefaultTableModel getTtCOT() {
		return ttCOT;
	}

	public void setTtCOT(DefaultTableModel ttCOT) {
		this.ttCOT = ttCOT;
	}

	public JPanel getPnQuanLySanPham() {
		return pnQuanLySanPham;
	}

	public void setPnQuanLySanPham(JPanel pnQuanLySanPham) {
		this.pnQuanLySanPham = pnQuanLySanPham;
	}

	public JPanel getQuanLiSanPHam() {
		return quanLiSanPHam;
	}

	public void setQuanLiSanPHam(JPanel quanLiSanPHam) {
		this.quanLiSanPHam = quanLiSanPHam;
	}

	public JPanel getPanelSearch() {
		return panelSearch;
	}

	public void setPanelSearch(JPanel panelSearch) {
		this.panelSearch = panelSearch;
	}

	public JPanel getPanelBangThongTinSP() {
		return panelBangThongTinSP;
	}

	public void setPanelBangThongTinSP(JPanel panelBangThongTinSP) {
		this.panelBangThongTinSP = panelBangThongTinSP;
	}

	public JPanel getPanelBangThongTinSP1() {
		return panelBangThongTinSP1;
	}

	public void setPanelBangThongTinSP1(JPanel panelBangThongTinSP1) {
		this.panelBangThongTinSP1 = panelBangThongTinSP1;
	}

	public JPanel getPanelBangThongTinSP2() {
		return panelBangThongTinSP2;
	}

	public void setPanelBangThongTinSP2(JPanel panelBangThongTinSP2) {
		this.panelBangThongTinSP2 = panelBangThongTinSP2;
	}

	public JPanel getPanelQlFunction() {
		return panelQlFunction;
	}

	public void setPanelQlFunction(JPanel panelQlFunction) {
		this.panelQlFunction = panelQlFunction;
	}

	public JPanel getPnADDRe1() {
		return pnADDRe1;
	}

	public void setPnADDRe1(JPanel pnADDRe1) {
		this.pnADDRe1 = pnADDRe1;
	}

	public JPanel getPnADDRe11() {
		return pnADDRe11;
	}

	public void setPnADDRe11(JPanel pnADDRe11) {
		this.pnADDRe11 = pnADDRe11;
	}

	public JPanel getPnADDRe12() {
		return pnADDRe12;
	}

	public void setPnADDRe12(JPanel pnADDRe12) {
		this.pnADDRe12 = pnADDRe12;
	}

	public JPanel getPnADDRe2() {
		return pnADDRe2;
	}

	public void setPnADDRe2(JPanel pnADDRe2) {
		this.pnADDRe2 = pnADDRe2;
	}

	public JPanel getPnADDRe21() {
		return pnADDRe21;
	}

	public void setPnADDRe21(JPanel pnADDRe21) {
		this.pnADDRe21 = pnADDRe21;
	}

	public JPanel getPnADDRe22() {
		return pnADDRe22;
	}

	public void setPnADDRe22(JPanel pnADDRe22) {
		this.pnADDRe22 = pnADDRe22;
	}

	public JButton getbSave() {
		return sua;
	}

	public void setbSave(JButton bSave) {
		this.sua = bSave;
	}

	public JButton getAdd() {
		return add;
	}

	public void setAdd(JButton add) {
		this.add = add;
	}

	public JButton getRemove() {
		return remove;
	}

	public void setRemove(JButton remove) {
		this.remove = remove;
	}

	public JButton getNutSerach() {
		return nutSerach;
	}

	public void setNutSerach(JButton nutSerach) {
		this.nutSerach = nutSerach;
	}

	public JTextField getTextMaSP() {
		return textMaSP;
	}

	public void setTextMaSP(JTextField textMaSP) {
		this.textMaSP = textMaSP;
	}

	public JTextField getTextMaSP_Xoa() {
		return textMaSP_Xoa;
	}

	public void setTextMaSP_Xoa(JTextField textMaSP_Xoa) {
		this.textMaSP_Xoa = textMaSP_Xoa;
	}

	public JTextField getTextTenSP() {
		return textTenSP;
	}

	public void setTextTenSP(JTextField textTenSP) {
		this.textTenSP = textTenSP;
	}

	public JTextField getTextSLSP() {
		return textSLSP;
	}

	public void setTextSLSP(JTextField textSLSP) {
		this.textSLSP = textSLSP;
	}

	public JTextField getTextGiaSP() {
		return textGiaSP;
	}

	public void setTextGiaSP(JTextField textGiaSP) {
		this.textGiaSP = textGiaSP;
	}

	public JTextField getTextAnhSP() {
		return textAnhSP;
	}

	public void setTextAnhSP(JTextField textAnhSP) {
		this.textAnhSP = textAnhSP;
	}

	public JTextField getBoxSearch() {
		return boxSearch;
	}

	public void setBoxSearch(JTextField boxSearch) {
		this.boxSearch = boxSearch;
	}

	public JTable getTableBangThongTinSP() {
		return tableBangThongTinSP;
	}

	public void setTableBangThongTinSP(JTable tableBangThongTinSP) {
		this.tableBangThongTinSP = tableBangThongTinSP;
	}

	public JScrollPane getScrollThongTinSP() {
		return scrollThongTinSP;
	}

	public void setScrollThongTinSP(JScrollPane scrollThongTinSP) {
		this.scrollThongTinSP = scrollThongTinSP;
	}

	public static void showValidDialog() { // hop le
		JOptionPane.showMessageDialog(null, "thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void showInvalidDialog() {
		JOptionPane.showMessageDialog(null, "Thông tin không hợp lệ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	}
	public void cuaSoSua() {
		int select =tableBangThongTinSP.getSelectedRow();
		if(select!=-1) {
			String maSP=tableBangThongTinSP.getValueAt(select, 0).toString();
			String tenSP=tableBangThongTinSP.getValueAt(select, 1).toString();
			String gia=tableBangThongTinSP.getValueAt(select, 2).toString();
			String sl=tableBangThongTinSP.getValueAt(select, 3).toString();
			String xx=tableBangThongTinSP.getValueAt(select, 4).toString();
		framSua = new JFrame("Sửa thông tin sp");
		framSua.setSize(400, 250);
		framSua.setLocationRelativeTo(null);
		framSua.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 1));
		framSua.add(panel);

		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1,2));
		panel1.add(new JLabel("Mã Sản Phẩm:"));
		panel1.add(textMASP_Sua);
		textMASP_Sua.setText(maSP);
		textMASP_Sua.setEditable(false);
		
		panel.add(panel1);

		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1,2));
		panel2.add(new JLabel("Tên Sản Phẩm:"));
		panel2.add(textTenSP_Sua);
		textTenSP_Sua.setText(tenSP);
		panel.add(panel2);

		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(1,2));
		panel3.add(new JLabel("Số Lượng:"));
		panel3.add(textSLSP_Sua);
		textSLSP_Sua.setText(sl);
		panel.add(panel3);

		JPanel panel4 = new JPanel();
		panel4.setLayout(new GridLayout(1,2));
		panel4.add(new JLabel("Giá Sản Phẩm:"));
		panel4.add(textGiaSP_Sua);
		textGiaSP_Sua.setText(gia);
		panel.add(panel4);

		JPanel panel5 = new JPanel();
		panel5.setLayout(new GridLayout(1,2));
		panel5.add(new JLabel("Xuất xứ"));
		panel5.add(textXXSP_SUa);
		textXXSP_SUa.setText(xx);
		panel.add(panel5);

		JPanel panel6 = new JPanel();
		panel6.setLayout(new FlowLayout());
		panel6.add(chapNhan);
		panel6.add(huy);
		panel.add(panel6);

		//
		framSua.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng !", "Thông báo", JOptionPane.WARNING_MESSAGE, null);
		}
		
	}

}
