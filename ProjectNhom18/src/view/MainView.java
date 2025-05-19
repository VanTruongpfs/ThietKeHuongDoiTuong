package view;

import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import utils.DBConnection;

public class MainView extends JFrame {
    private JPanel pnContainer;
    private CardLayout cardLayout;
    private JMenuBar menubar;
    private JMenu mnHoaDon, mnQuanLiHeThong;
    private JMenuItem mniQuanLyHoaDon, mniQuanLySanPham, mniQuanLyNhanVien, mniQuanLiKhachHang;
    private QLHDView qlhdView;
    private QLKHView qlkhView;
    private QLNVView qlnvView;
    private QLSanPhamView qlspView;
    private TaoHoaDonView taoHoaDonView;

    public MainView() {
        setTitle("Quản lý cửa hàng");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pnContainer = new JPanel();
        cardLayout = new CardLayout();
        setContentPane(pnContainer);
        pnContainer.setLayout(cardLayout);
        menubar = new JMenuBar();
        mnHoaDon = new JMenu("Tạo hóa đơn");
        mnQuanLiHeThong = new JMenu("Chức năng quản lý");
        mniQuanLyHoaDon = new JMenuItem("Quản lý hóa đơn");
        mniQuanLyNhanVien = new JMenuItem("Quản lý nhân viên");
        mniQuanLySanPham = new JMenuItem("Quản lý sản phẩm");
        mniQuanLiKhachHang = new JMenuItem("Quản lý khách hàng");
        mnQuanLiHeThong.add(mniQuanLyHoaDon);
        mnQuanLiHeThong.add(mniQuanLySanPham);
        mnQuanLiHeThong.add(mniQuanLyNhanVien);
        mnQuanLiHeThong.add(mniQuanLiKhachHang);
        menubar.add(mnHoaDon);
        menubar.add(mnQuanLiHeThong);
        setJMenuBar(menubar);

        qlhdView = new QLHDView();
        qlkhView = new QLKHView();
        qlnvView = new QLNVView();
        qlspView = new QLSanPhamView();
        taoHoaDonView = new TaoHoaDonView();

        pnContainer.add(qlhdView.QLHD(), "QLHoaDon");
        pnContainer.add(qlnvView.QLNV(), "QLNhanVien");
        pnContainer.add(qlspView.QLSP(), "QLSanPham");
        pnContainer.add(qlkhView.QLKH(), "QLKhachHang");
        pnContainer.add(taoHoaDonView.TaoHoaDon(), "TaoDon");
        
        mnHoaDon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(pnContainer, "TaoDon");
            }
        });
        mniQuanLyHoaDon.addActionListener(e -> cardLayout.show(getContentPane(), "QLHoaDon"));
        mniQuanLyNhanVien.addActionListener(e -> cardLayout.show(getContentPane(), "QLNhanVien"));
        mniQuanLySanPham.addActionListener(e -> cardLayout.show(getContentPane(), "QLSanPham"));
        mniQuanLiKhachHang.addActionListener(e -> cardLayout.show(getContentPane(), "QLKhachHang"));

        cardLayout.show(getContentPane(), "TaoDon");
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public void showCard(String name) {
        cardLayout.show(getContentPane(), name);
    }
    
    public JPanel getPnContainer() {
		return pnContainer;
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public JMenuBar getMenubar() {
		return menubar;
	}

	public JMenu getMnHoaDon() {
		return mnHoaDon;
	}

	public JMenu getMnQuanLiHeThong() {
		return mnQuanLiHeThong;
	}

	public JMenuItem getMniQuanLyHoaDon() {
		return mniQuanLyHoaDon;
	}

	public JMenuItem getMniQuanLySanPham() {
		return mniQuanLySanPham;
	}

	public JMenuItem getMniQuanLyNhanVien() {
		return mniQuanLyNhanVien;
	}

	public JMenuItem getMniQuanLiKhachHang() {
		return mniQuanLiKhachHang;
	}

	public QLHDView getQlhdView() {
		return qlhdView;
	}

	public QLKHView getQlkhView() {
		return qlkhView;
	}

	public QLNVView getQlnvView() {
		return qlnvView;
	}

	public QLSanPhamView getQlspView() {
		return qlspView;
	}

	public TaoHoaDonView getTaoHoaDonView() {
		return taoHoaDonView;
	}

	public static void main(String[] args) {
        new MainView();
    }
}