package GUI;

import BusinessLogic.blTINHTRANGBAN_THANHTOAN;
import SCHEMA.BAN;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class frmTINHTRANGBAN_THANHTOAN extends JInternalFrame
        implements MouseListener, ActionListener {

    JPopupMenu popup;
    blTINHTRANGBAN_THANHTOAN blquanlyban;

    JMenuItem menuitemOrderMenu;
    JMenuItem menuitemInHoaDon;
    JMenuItem menuitemhuyHoaDon;
    JMenuItem menuXemChiTiet;

    private int selectedBanID = -1;

    private JPanel panelBan;
    private JScrollPane scrollPane;

    public frmTINHTRANGBAN_THANHTOAN() {
        super("Quản lý tình trạng bàn và thanh toán",
                true, true, true, true);
        setSize(820, 550);
        this.setName("frmOrder");
        blquanlyban = new blTINHTRANGBAN_THANHTOAN();

        popup = new JPopupMenu();
        menuitemOrderMenu = new JMenuItem("Menu Order");
        menuitemInHoaDon = new JMenuItem("In hóa đơn");
        menuitemhuyHoaDon = new JMenuItem("Hủy hóa đơn");
        JMenuItem menuXemChiTiet = new JMenuItem("Xem chi tiết");
        menuXemChiTiet.addActionListener(this);

        menuitemOrderMenu.addActionListener(this);
        menuitemInHoaDon.addActionListener(this);
        menuitemhuyHoaDon.addActionListener(this);

        popup.add(menuitemOrderMenu);
        popup.add(menuitemInHoaDon);
        popup.addSeparator();
        popup.add(menuXemChiTiet);
        popup.add(menuitemhuyHoaDon);

        // Panel dùng GridLayout 3 cột giống ảnh
        panelBan = new JPanel(new GridLayout(0, 3, 10, 10));
        panelBan.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        scrollPane = new JScrollPane(panelBan);
        scrollPane.setBorder(null);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        SetTable();
    }

    public void SetTable() {
        panelBan.removeAll();
        Vector<BAN> listTable = blquanlyban.getListTable();

        for (BAN ban : listTable) {
            JButton btn = new JButton();
            btn.setName(String.valueOf(ban.getMaBan_ID()));
            btn.setPreferredSize(new Dimension(220, 200));
            btn.setHorizontalTextPosition(SwingConstants.CENTER);
            btn.setVerticalTextPosition(SwingConstants.BOTTOM);
            btn.setToolTipText(ban.getTenBan());

            if (ban.isTrangThai()) {
                Image img = new ImageIcon(getClass().getResource("/images/BAN2.jpg"))
                        .getImage().getScaledInstance(160, 150, Image.SCALE_SMOOTH);
                btn.setIcon(new ImageIcon(img));
                btn.setText(ban.getTenBan());
            } else {
                Image img = new ImageIcon(getClass().getResource("/images/BAN1.jpg"))
                        .getImage().getScaledInstance(160, 150, Image.SCALE_SMOOTH);
                btn.setIcon(new ImageIcon(img));
                btn.setText(ban.getTenBan());
            }
            btn.setText("<html><center>"
                    + ban.getTenBan()
                    + "<br>"
                    + (ban.isTrangThai() ? "[Có khách]" : "[Trống]")
                    + "</center></html>");
            btn.setFont(new Font("Arial", Font.BOLD, 13));
            btn.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                    BorderFactory.createEmptyBorder(8, 8, 8, 8)
            ));
            btn.setFocusPainted(false);
            btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btn.setOpaque(true);
            btn.setBackground(Color.WHITE);

            btn.addMouseListener(this);
            btn.addActionListener(this);
            panelBan.add(btn);
        }

        panelBan.revalidate();
        panelBan.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger()) {
            showPopup(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()) {
            showPopup(e);
        }
    }

    private void showPopup(MouseEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton btn = (JButton) e.getSource();
            try {
                selectedBanID = Integer.parseInt(btn.getName());
            } catch (NumberFormatException ex) {
                selectedBanID = -1;
            }
        }
        popup.show(e.getComponent(), e.getX(), e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() instanceof JButton) {
            ((JButton) e.getSource()).setBackground(new Color(240, 240, 240));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof JButton) {
            ((JButton) e.getSource()).setBackground(Color.WHITE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton btn = (JButton) e.getSource();
            try {
                selectedBanID = Integer.parseInt(btn.getName());
            } catch (NumberFormatException ex) {
            }
            return;
        }

        if (selectedBanID < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một bàn!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (e.getSource() == menuitemOrderMenu) {

            frmOrder f = new frmOrder(selectedBanID);

            JDesktopPane desktop = getDesktopPane();

            if (desktop != null) {
                desktop.add(f);
                f.setVisible(true);
                try {
                    f.setSelected(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                // fallback nếu lỗi
                JFrame frame = new JFrame("Order");
                frame.setSize(500, 400);
                frame.add(f);
                frame.setVisible(true);
            }

        } else if (e.getSource() == menuXemChiTiet) {

            BAN ban = blquanlyban.GetBanByID(selectedBanID);

            if (ban != null) {
                JOptionPane.showMessageDialog(this,
                        "Tên bàn: " + ban.getTenBan()
                        + "\nTrạng thái: " + (ban.isTrangThai() ? "Có khách" : "Trống"),
                        "Thông tin bàn",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } else if (e.getSource() == menuitemInHoaDon) {

            ConnectionData.CONNECTIONSQLSERVER cnn = new ConnectionData.CONNECTIONSQLSERVER();

            String result = "===== HÓA ĐƠN =====\n";

            try {
                var rsHD = cnn.GetResultSetSQL(
                        "SELECT TOP 1 MaHD FROM HOADON WHERE MaBan=" + selectedBanID + " ORDER BY MaHD DESC");

                int maHD = 0;
                if (rsHD.next()) {
                    maHD = rsHD.getInt("MaHD");
                }

                var rs = cnn.GetResultSetSQL(
                        "SELECT m.TenMon, c.SoLuong, m.Gia, (c.SoLuong*m.Gia) AS ThanhTien "
                        + "FROM CHITIETHOADON c JOIN MON m ON c.MaMon = m.MaMon "
                        + "WHERE c.MaHD=" + maHD);

                double tong = 0;

                while (rs.next()) {
                    String ten = rs.getString("TenMon");
                    int sl = rs.getInt("SoLuong");
                    double gia = rs.getDouble("Gia");
                    double tt = rs.getDouble("ThanhTien");

                    tong += tt;

                    result += ten + " | SL: " + sl + " | " + tt + "\n";
                }

                result += "\nTỔNG: " + tong;

                JTextArea txt = new JTextArea(result);
                txt.print(); // 👉 in thật

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }
}
