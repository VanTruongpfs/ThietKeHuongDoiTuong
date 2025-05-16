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
    private JPanel contentPane;
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
        // contentPane = new JPanel();
        cardLayout = new CardLayout();
        getContentPane().setLayout(cardLayout);
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

        getContentPane().add(qlhdView.QLHD(), "QLHoaDon");
        getContentPane().add(qlnvView.QLNV(), "QLNhanVien");
        getContentPane().add(qlspView.QLSP(), "QLSanPham");
        getContentPane().add(qlkhView.QLKH(), "QLKhachHang");
        getContentPane().add(taoHoaDonView.TaoHoaDon(), "TaoDon");
        
        mnHoaDon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(getContentPane(), "TaoDon");
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

    public static void main(String[] args) {
        new MainView();
    }
}