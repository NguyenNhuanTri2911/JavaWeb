package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import GUI.frm_KHUVUC;
public class frm_MAIN extends JFrame implements ActionListener {

    private JMenuBar menuBar;

    // Menu Hệ thống
    private JMenu menuHeThong;
    private JMenuItem menuItemDangNhap, menuItemDoiMatKhau, menuItemThoat;

    // Menu Quản lý Order
    private JMenu menuQuanLyOrder;
    private JMenuItem menuItemTinhTrangBan, menuItemOrder;

    // Menu Quản lý nhập
    private JMenu menuQuanLyNhap;
    private JMenuItem menuItemQuanLyBan, menuItemQuanLyKhuVuc;

    private JLabel lbeWelcome;

    public frm_MAIN() {
        setTitle("Quản Lý Shop Cà Phê - 3 Layer");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== MENU BAR =====
        menuBar = new JMenuBar();

        // --- Menu: Hệ thống ---
        menuHeThong = new JMenu("Hệ thống");
        menuItemDangNhap = new JMenuItem("Đăng nhập");
        menuItemDangNhap.addActionListener(this);
        menuItemDoiMatKhau = new JMenuItem("Đổi mật khẩu");
        menuItemDoiMatKhau.addActionListener(this);
        menuItemThoat = new JMenuItem("Thoát");
        menuItemThoat.addActionListener(this);
        menuHeThong.add(menuItemDangNhap);
        menuHeThong.add(menuItemDoiMatKhau);
        menuHeThong.addSeparator();
        menuHeThong.add(menuItemThoat);

        // --- Menu: Quản lý Order ---
        menuQuanLyOrder = new JMenu("Quản lý Order");
        menuItemTinhTrangBan = new JMenuItem("Tình trạng bàn & Thanh toán");
        menuItemTinhTrangBan.addActionListener(this);
        menuItemOrder = new JMenuItem("Lập order");
        menuItemOrder.addActionListener(this);
        menuQuanLyOrder.add(menuItemTinhTrangBan);
        menuQuanLyOrder.add(menuItemOrder);

        // --- Menu: Quản lý nhập ---
        menuQuanLyNhap = new JMenu("Quản lý nhập");
        menuItemQuanLyBan = new JMenuItem("Quản lý Bàn");
        menuItemQuanLyBan.addActionListener(this);
        menuItemQuanLyKhuVuc = new JMenuItem("Quản lý Khu vực");
        menuItemQuanLyKhuVuc.addActionListener(this);
        menuQuanLyNhap.add(menuItemQuanLyBan);
        menuQuanLyNhap.add(menuItemQuanLyKhuVuc);

        menuBar.add(menuHeThong);
        menuBar.add(menuQuanLyOrder);
        menuBar.add(menuQuanLyNhap);
        setJMenuBar(menuBar);

        // ===== WELCOME LABEL =====
        lbeWelcome = new JLabel("Chào mừng đến hệ thống Quản Lý Shop Cà Phê", SwingConstants.CENTER);
        lbeWelcome.setFont(new Font("Arial", Font.BOLD, 18));
        lbeWelcome.setForeground(new Color(101, 67, 33));
        add(lbeWelcome, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuItemQuanLyBan) {
            frm_BAN f = new frm_BAN();
            f.setVisible(true);

        } else if (e.getSource() == menuItemTinhTrangBan) {
            // Mở form tình trạng bàn trong JDesktopPane
            JFrame wrapper = new JFrame("Quản lý tình trạng bàn và thanh toán");
            wrapper.setSize(870, 600);
            wrapper.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JDesktopPane desktop = new JDesktopPane();
            wrapper.setContentPane(desktop);
            frmTINHTRANGBAN_THANHTOAN f = new frmTINHTRANGBAN_THANHTOAN();
            f.setVisible(true);
            desktop.add(f);
            try {
                f.setMaximum(true);
            } catch (Exception ex) {
            }
            wrapper.setLocationRelativeTo(null);
            wrapper.setVisible(true);

        } else if (e.getSource() == menuItemOrder) {
            JOptionPane.showMessageDialog(this, "Chức năng lập order đang phát triển.");
        } else if (e.getSource() == menuItemDangNhap) {
            JOptionPane.showMessageDialog(this, "Chức năng đăng nhập đang phát triển.");
        } else if (e.getSource() == menuItemDoiMatKhau) {
            JOptionPane.showMessageDialog(this, "Chức năng đổi mật khẩu đang phát triển.");
        } else if (e.getSource() == menuItemQuanLyKhuVuc) {
            frm_KHUVUC f = new frm_KHUVUC();
            f.setVisible(true);
        } else if (e.getSource()
                == menuItemThoat) {
            System.exit(0);
        }
    }
}
