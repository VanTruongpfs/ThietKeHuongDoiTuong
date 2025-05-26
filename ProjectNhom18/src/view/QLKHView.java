
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel; 
public class QLKHView {
    JPanel pnQuanLyKhachHang = new JPanel();
    JPanel pnTrai, pnPhai, pnDuoi, formPanel, pnTimKiem, pnThongBao;
    JButton btnThem, btnXoa, btnSua, btnTim, btnThongBao,btnHuy,btnChapNhan;
    JTextField txtMaKH, txtTenKH, txtSDT, txtDiaChi, txtDiem, tfTimKiem, tfThongBao, tfMaSua, tfTenSua, tfGTSua, tfSDTSua, tfDiaChiSua, tfDiemSua;
    JComboBox<String> cboGioiTinh;
    JTable tblKH;
    DefaultTableModel modelKH;
    JScrollPane scrollKH;
    JSplitPane splitPane;
    JFrame frameSua;

    public QLKHView() {

    	tfMaSua = new JTextField(10);
		tfTenSua = new JTextField(10);
		tfGTSua = new JTextField(10);
		tfSDTSua = new JTextField(10);
		tfDiaChiSua = new JTextField(10);
		tfDiemSua = new JTextField(10);
        btnChapNhan= new JButton("Chấp nhận");
		btnHuy = new JButton("Hủy");
	
    	pnQuanLyKhachHang.setLayout(new BorderLayout());

	    // Panel bên trái
	    pnTrai = new JPanel();
	    pnTrai.setLayout(new BoxLayout(pnTrai, BoxLayout.Y_AXIS));
	    pnTrai.setBorder(BorderFactory.createTitledBorder("QUẢN LÍ KHÁCH HÀNG"));
	    
	    
	    
	    
	    
	    pnThongBao = new JPanel(new GridLayout(1,2));
	    pnThongBao.setPreferredSize(new Dimension(2, 2));
	    pnThongBao.setBorder(BorderFactory.createTitledBorder("Gửi thông báo"));
	    tfThongBao = new JTextField();

	    btnThongBao = new JButton("Gửi");

	    pnThongBao.add(tfThongBao);
	    pnThongBao.add(btnThongBao, BorderLayout.EAST);
	     	    
	    pnTrai.add(pnThongBao, BorderLayout.NORTH);
	    


	    btnThem = new JButton("Thêm khách hàng");
	    btnXoa = new JButton("Xóa khách hàng");
	    btnSua = new JButton("Sửa khách hàng");
	    
	   
        // Tạo panel chứa các ô nhập liệu theo dạng bảng
       formPanel = new JPanel(new GridLayout(6, 2,10,10));
       formPanel.setBorder(BorderFactory.createTitledBorder("Thêm khách hàng"));
        // Các ô nhập liệu
        txtMaKH = new JTextField();
        txtTenKH = new JTextField();
        cboGioiTinh = new JComboBox<>(new String[]{"Nam", "Nữ"});
        txtDiaChi = new JTextField();
        txtSDT = new JTextField();
        txtDiem = new JTextField();

        // Thêm vào form
        formPanel.add(new JLabel("Mã khách hàng:"));
        formPanel.add(txtMaKH);

        formPanel.add(new JLabel("Tên khách hàng:"));
        formPanel.add(txtTenKH);
        
        formPanel.add(new JLabel("Số điện thoại:"));
        formPanel.add(txtSDT);

        formPanel.add(new JLabel("Giới tính:"));
        formPanel.add(cboGioiTinh);

        formPanel.add(new JLabel("Địa chỉ:"));
        formPanel.add(txtDiaChi);


        formPanel.add(new JLabel("Điểm"));
        formPanel.add(txtDiem);
        pnTrai.add(formPanel, BorderLayout.CENTER);
        pnTrai.add(Box.createVerticalStrut(30));

        
	   
	    pnDuoi = new JPanel(new GridLayout(1,2));
	    pnDuoi.setLayout(new FlowLayout());
	    pnDuoi.add(btnThem);
	    pnDuoi.add(btnXoa);
	    pnDuoi.add(btnSua);
	    
	  
	    pnTrai.add(pnDuoi, BorderLayout.SOUTH);

		// Panel bên phải (tìm kiếm + bảng)
		pnPhai = new JPanel(new BorderLayout());

		pnTimKiem = new JPanel(new BorderLayout());
		tfTimKiem = new JTextField();
		btnTim = new JButton("Tìm");
		pnTimKiem.add(new JLabel("Nhập thông tin cần tìm: "), BorderLayout.WEST);
		pnTimKiem.add(tfTimKiem, BorderLayout.CENTER);
		pnTimKiem.add(btnTim, BorderLayout.EAST);

		String[] cols = { "Mã KH", "Tên khách hàng", "Số điện thoại", "Giới tính", "Địa chỉ", "Điểm tích lũy" };
		modelKH = new DefaultTableModel(cols, 0);
		tblKH = new JTable(modelKH);
		tblKH.setFillsViewportHeight(true);
		scrollKH = new JScrollPane(tblKH);

		pnPhai.add(pnTimKiem, BorderLayout.NORTH);
		pnPhai.add(scrollKH, BorderLayout.CENTER);

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnTrai, pnPhai);
		splitPane.setDividerLocation(450);
		splitPane.setResizeWeight(0.1);
		pnQuanLyKhachHang.removeAll();
		pnQuanLyKhachHang.add(splitPane, BorderLayout.CENTER);
		pnQuanLyKhachHang.revalidate();
		pnQuanLyKhachHang.repaint();
	btnSua.addActionListener(e -> cuaSoSua());
	btnHuy.addActionListener(e -> frameSua.dispose());
	}
    public JPanel QLKH(){
        return  pnQuanLyKhachHang;
    }
	public JPanel getPnQuanLyKhachHang() {
		return pnQuanLyKhachHang;
	}
	public void setPnQuanLyKhachHang(JPanel pnQuanLyKhachHang) {
		this.pnQuanLyKhachHang = pnQuanLyKhachHang;
	}
	public JPanel getPnTrai() {
		return pnTrai;
	}
	public void setPnTrai(JPanel pnTrai) {
		this.pnTrai = pnTrai;
	}
	public JPanel getPnPhai() {
		return pnPhai;
	}
	public void setPnPhai(JPanel pnPhai) {
		this.pnPhai = pnPhai;
	}
	public JPanel getPnDuoi() {
		return pnDuoi;
	}
	public void setPnDuoi(JPanel pnDuoi) {
		this.pnDuoi = pnDuoi;
	}
	public JPanel getFormPanel() {
		return formPanel;
	}
	public void setFormPanel(JPanel formPanel) {
		this.formPanel = formPanel;
	}
	public JPanel getPnTimKiem() {
		return pnTimKiem;
	}
	public void setPnTimKiem(JPanel pnTimKiem) {
		this.pnTimKiem = pnTimKiem;
	}
	public JButton getBtnThem() {
		return btnThem;
	}
	public void setBtnThem(JButton btnThem) {
		this.btnThem = btnThem;
	}
	public JButton getBtnXoa() {
		return btnXoa;
	}
	public void setBtnXoa(JButton btnXoa) {
		this.btnXoa = btnXoa;
	}
	public JButton getBtnSua() {
		return btnSua;
	}
	public void setBtnSua(JButton btnSua) {
		this.btnSua = btnSua;
	}
	public JButton getBtnTim() {
		return btnTim;
	}
	public void setBtnTim(JButton btnTim) {
		this.btnTim = btnTim;
	}
	public JTextField getTxtMaKH() {
		return txtMaKH;
	}
	public void setTxtMaKH(JTextField txtMaKH) {
		this.txtMaKH = txtMaKH;
	}
	public JTextField getTxtTenKH() {
		return txtTenKH;
	}
	public void setTxtTenKH(JTextField txtTenKH) {
		this.txtTenKH = txtTenKH;
	}
	public JTextField getTxtSDT() {
		return txtSDT;
	}
	public void setTxtSDT(JTextField txtSDT) {
		this.txtSDT = txtSDT;
	}
	public JTextField getTxtDiaChi() {
		return txtDiaChi;
	}
	public void setTxtDiaChi(JTextField txtDiaChi) {
		this.txtDiaChi = txtDiaChi;
	}
	public JTextField getTxtDiem() {
		return txtDiem;
	}
	public void setTxtDiem(JTextField txtDiem) {
		this.txtDiem = txtDiem;
	}
	public JTextField getTfTimKiem() {
		return tfTimKiem;
	}
	public void setTfTimKiem(JTextField tfTimKiem) {
		this.tfTimKiem = tfTimKiem;
	}
	public JComboBox<String> getCboGioiTinh() {
		return cboGioiTinh;
	}
	public void setCboGioiTinh(JComboBox<String> cboGioiTinh) {
		this.cboGioiTinh = cboGioiTinh;
	}
	public JTable getTblKH() {
		return tblKH;
	}
	public void setTblKH(JTable tblKH) {
		this.tblKH = tblKH;
	}
	public DefaultTableModel getModelKH() {
		return modelKH;
	}
	public void setModelKH(DefaultTableModel modelKH) {
		this.modelKH = modelKH;
	}
	public JScrollPane getScrollKH() {
		return scrollKH;
	}
	public void setScrollKH(JScrollPane scrollKH) {
		this.scrollKH = scrollKH;
	}
	public JSplitPane getSplitPane() {
		return splitPane;
	}
	public void setSplitPane(JSplitPane splitPane) {
		this.splitPane = splitPane;
	}
	
	public JPanel getPnThongBao() {
		return pnThongBao;
	}
	public void setPnThongBao(JPanel pnThongBao) {
		this.pnThongBao = pnThongBao;
	}
	public JButton getBtnThongBao() {
		return btnThongBao;
	}
	public void setBtnThongBao(JButton btnThongBao) {
		this.btnThongBao = btnThongBao;
	}
	public JTextField getTfThongBao() {
		return tfThongBao;
	}
	public void setTfThongBao(JTextField tfThongBao) {
		this.tfThongBao = tfThongBao;
	}
	public static void showValidDialog() { // hop le
		JOptionPane.showMessageDialog(null, "thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void showInvalidDialog() {
		JOptionPane.showMessageDialog(null, "Thông tin không hợp lệ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	}
public void cuaSoSua() {
		int select =tblKH.getSelectedRow();
		if(select!=-1) {
			String maKH= tblKH.getValueAt(select, 0).toString();
			String tenKH= tblKH.getValueAt(select, 1).toString();
			String gt= tblKH.getValueAt(select, 2).toString();
			String sdt = tblKH.getValueAt(select, 3).toString();
			String diachi= tblKH.getValueAt(select, 4).toString();
			String diem = tblKH.getValueAt(select, 5).toString();
		frameSua = new JFrame("Sửa thông tin khách hàng");
		frameSua.setSize(400, 250);
		frameSua.setLocationRelativeTo(null);
		frameSua.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(7, 1));
		frameSua.add(panel);

		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1,2));
		panel1.add(new JLabel("Mã khách hàng:"));
		panel1.add(tfMaSua);
		tfMaSua.setText(maKH);
		tfMaSua.setEditable(false);
		
		panel.add(panel1);

		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1,2));
		panel2.add(new JLabel("Tên khách hàng:"));
		panel2.add(tfTenSua);
		tfTenSua.setText(tenKH);
		panel.add(panel2);

		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(1,2));
		panel3.add(new JLabel("Giới tính"));
		panel3.add(tfGTSua);
		tfGTSua.setText(gt);
		panel.add(panel3);

		JPanel panel4 = new JPanel();
		panel4.setLayout(new GridLayout(1,2));
		panel4.add(new JLabel("Số điện thoại"));
		panel4.add(tfSDTSua);
		tfSDTSua.setText(sdt);
		panel.add(panel4);

		JPanel panel5 = new JPanel();
		panel5.setLayout(new GridLayout(1,2));
		panel5.add(new JLabel("Địa chỉ"));
		panel5.add(tfDiaChiSua);
		tfDiaChiSua.setText(diachi);
		panel.add(panel5);

		JPanel panel6 = new JPanel();
		panel6.setLayout(new GridLayout(1,2));
		panel6.add(new JLabel("Điểm"));
		panel6.add(tfDiemSua);
		tfDiemSua.setText(diem);
		panel.add(panel6);


		JPanel panel7 = new JPanel();
		panel7.setLayout(new FlowLayout());
		panel7.add(btnChapNhan);
		panel7.add(btnHuy);
		panel.add(panel7);

		//
		frameSua.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng !", "Thông báo", JOptionPane.WARNING_MESSAGE, null);
		}
		
	}
public JButton getBtnHuy() {
	return btnHuy;
}
public void setBtnHuy(JButton btnHuy) {
	this.btnHuy = btnHuy;
}
public JButton getBtnChapNhan() {
	return btnChapNhan;
}
public void setBtnChapNhan(JButton btnChapNhan) {
	this.btnChapNhan = btnChapNhan;
}
public JTextField getTfMaSua() {
	return tfMaSua;
}
public void setTfMaSua(JTextField tfMaSua) {
	this.tfMaSua = tfMaSua;
}
public JTextField getTfTenSua() {
	return tfTenSua;
}
public void setTfTenSua(JTextField tfTenSua) {
	this.tfTenSua = tfTenSua;
}
public JTextField getTfGTSua() {
	return tfGTSua;
}
public void setTfGTSua(JTextField tfGTSua) {
	this.tfGTSua = tfGTSua;
}
public JTextField getTfSDTSua() {
	return tfSDTSua;
}
public void setTfSDTSua(JTextField tfSDTSua) {
	this.tfSDTSua = tfSDTSua;
}
public JTextField getTfDiaChiSua() {
	return tfDiaChiSua;
}
public void setTfDiaChiSua(JTextField tfDiaChiSua) {
	this.tfDiaChiSua = tfDiaChiSua;
}
public JTextField getTfDiemSua() {
	return tfDiemSua;
}
public void setTfDiemSua(JTextField tfDiemSua) {
	this.tfDiemSua = tfDiemSua;
}
public JFrame getFrameSua() {
	return frameSua;
}
public void setFrameSua(JFrame frameSua) {
	this.frameSua = frameSua;
}

	
}
