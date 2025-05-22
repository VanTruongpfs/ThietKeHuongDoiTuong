package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
public class QLHDView {
    JPanel pnHD, leftPanel, rightPanel, searchPanel, dateFilterPanel, pnQuanLyHoaDon;
    JLabel titleLabel, searchLabel, lblNgay, lblThang, lblNam;
    JComboBox<String> filterBox;
    JButton statsButton, searchButton, btnReset, btnLocTheoNgay, btnThongKe;
    JTextField searchField;
    DefaultTableModel model;
    JScrollPane tableScrollPane;
    JTable table;
    JSplitPane splitPaneHD;
    JTextField tfNgay, tfThang, tfNam,txtDoanhThu;
    public JTextField getTxtDoanhThu() {
		return txtDoanhThu;
	}

	public QLHDView(){
        pnHD = new JPanel(new BorderLayout());
    // ===== Panel bên trái =====
    leftPanel = new JPanel();
    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
    leftPanel.setPreferredSize(new Dimension(200, 0)); // Chiều rộng cố định
    leftPanel.setMaximumSize(new Dimension(200, Integer.MAX_VALUE));
    leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    titleLabel = new JLabel("QUẢN LÝ HÓA ĐƠN");
    titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
    titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


    statsButton = new JButton("Chi tiết hóa đơn");
    statsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    leftPanel.add(Box.createVerticalStrut(20));
    leftPanel.add(titleLabel);
    leftPanel.add(Box.createVerticalStrut(15));

    leftPanel.add(Box.createVerticalStrut(15));
    leftPanel.add(statsButton);

    // ===== Panel bên phải =====
    rightPanel = new JPanel(new BorderLayout());

    searchPanel = new JPanel(new BorderLayout());
    searchLabel = new JLabel("Nhập thông tin cần tìm: ");
    searchField = new JTextField();
    searchButton = new JButton("Tìm");

    searchPanel.add(searchLabel, BorderLayout.WEST);
    searchPanel.add(searchField, BorderLayout.CENTER);
    searchPanel.add(searchButton, BorderLayout.EAST);
    searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
    btnReset = new JButton("Reload");
    buttonPanel.add(searchButton);
    buttonPanel.add(btnReset);
   
    searchPanel.add(buttonPanel, BorderLayout.EAST);
    String[] columns = { "Mã HD", "Thời gian lập", "Mã KH", "Tên NV", "Tổng tiền", "PT Thanh toán" };
    model = new DefaultTableModel(columns, 0);
    table = new JTable(model);
    table.setFillsViewportHeight(true);
    tableScrollPane = new JScrollPane(table);

    rightPanel.add(searchPanel, BorderLayout.NORTH);
    rightPanel.add(tableScrollPane, BorderLayout.CENTER);

    // ===== JSplitPane cho layout trái - phải =====
    splitPaneHD = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
    splitPaneHD.setDividerLocation(200);           
    splitPaneHD.setResizeWeight(0);              
    splitPaneHD.setOneTouchExpandable(true);        

    pnHD.add(splitPaneHD, BorderLayout.CENTER);

 // ===== Panel thống kê doanh thu (góc dưới bên trái) =====
    JPanel bottomLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    btnThongKe = new JButton("Thống kê doanh thu");
    txtDoanhThu = new JTextField(15);
    txtDoanhThu.setEditable(false);
    txtDoanhThu.setText("0đ");

    bottomLeftPanel.add(btnThongKe);
    bottomLeftPanel.add(txtDoanhThu);

    // Thêm panel này vào phía dưới cùng của pnHD
    pnHD.add(bottomLeftPanel, BorderLayout.SOUTH);
 // ===== Thanh tìm kiếm hóa đơn theo ngày/tháng/năm =====
    dateFilterPanel = new JPanel();
    dateFilterPanel.setLayout(new GridLayout(4, 1, 5, 5)); // 2 hàng, 4 cột
    dateFilterPanel.setMaximumSize(new Dimension(180, 120));

     lblNgay = new JLabel("Ngày:");
     lblThang = new JLabel("Tháng:");
     lblNam = new JLabel("Năm:");

    tfNgay = new JTextField();
    tfThang = new JTextField();
    tfNam = new JTextField();
    btnLocTheoNgay = new JButton("Lọc");
    dateFilterPanel.add(lblNgay);
    dateFilterPanel.add(tfNgay);
    dateFilterPanel.add(lblThang);
    dateFilterPanel.add(tfThang);
    dateFilterPanel.add(lblNam);
    dateFilterPanel.add(tfNam);
    dateFilterPanel.add(new JLabel()); 
    dateFilterPanel.add(btnLocTheoNgay);

    leftPanel.add(Box.createVerticalStrut(20));
    leftPanel.add(dateFilterPanel);
    // ===== Gắn vào panel chính =====
    pnQuanLyHoaDon = new JPanel();
    pnQuanLyHoaDon.removeAll();
    pnQuanLyHoaDon.setLayout(new BorderLayout());
    pnQuanLyHoaDon.add(pnHD, BorderLayout.CENTER);
    pnQuanLyHoaDon.revalidate();
    pnQuanLyHoaDon.repaint();
    }

    public JButton getBtnLocTheoNgay() {
		return btnLocTheoNgay;
	}

	public JPanel QLHD(){
        return pnHD;
    }

	public DefaultTableModel getTable() {
		// TODO Auto-generated method stub
		return model;
	}

	public JPanel getPnHD() {
		return pnHD;
	}

	public JPanel getLeftPanel() {
		return leftPanel;
	}

	public JPanel getRightPanel() {
		return rightPanel;
	}

	public JPanel getSearchPanel() {
		return searchPanel;
	}

	public JPanel getDateFilterPanel() {
		return dateFilterPanel;
	}

	public JPanel getPnQuanLyHoaDon() {
		return pnQuanLyHoaDon;
	}

	public JLabel getTitleLabel() {
		return titleLabel;
	}

	public JLabel getSearchLabel() {
		return searchLabel;
	}

	public JLabel getLblNgay() {
		return lblNgay;
	}

	public JLabel getLblThang() {
		return lblThang;
	}

	public JLabel getLblNam() {
		return lblNam;
	}
	public JTable getTB() {
		return table;
	}
	public JComboBox<String> getFilterBox() {
		return filterBox;
	}

	public JButton getStatsButton() {
		return statsButton;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public JButton getBtnReset() {
		return btnReset;
	}

	public JTextField getSearchField() {
		return searchField;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public JButton getBtnThongKe() {
		return btnThongKe;
	}

	public JScrollPane getTableScrollPane() {
		return tableScrollPane;
	}

	public JSplitPane getSplitPaneHD() {
		return splitPaneHD;
	}

	public JTextField getTfNgay() {
		return tfNgay;
	}

	public JTextField getTfThang() {
		return tfThang;
	}

	public JTextField getTfNam() {
		return tfNam;
	}
	
}
