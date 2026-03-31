package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import ProcessData.MONS;
import ConnectionData.CONNECTIONSQLSERVER;

public class frmOrder extends JInternalFrame {

    private int maBan;

    JComboBox<String> cbMon;
    JTextField txtSoLuong;
    JButton btnThem, btnXoa;
    JTable table;
    DefaultTableModel model;
    JLabel lblTongTien;

    public frmOrder(int maBan) {
        super("Order - Bàn " + maBan, true, true, true, true);
        this.maBan = maBan;

        setSize(500, 400);
        setLayout(new BorderLayout());

        // ===== TOP PANEL =====
        JPanel top = new JPanel();

        MONS mons = new MONS();
        cbMon = new JComboBox<>(mons.GetAllTenMon());
        txtSoLuong = new JTextField(5);

        btnThem = new JButton("Thêm");
        btnXoa = new JButton("Xóa");

        top.add(new JLabel("Món:"));
        top.add(cbMon);
        top.add(new JLabel("SL:"));
        top.add(txtSoLuong);
        top.add(btnThem);
        top.add(btnXoa);

        add(top, BorderLayout.NORTH);

        // ===== TABLE =====
        model = new DefaultTableModel();
        model.addColumn("Tên món");
        model.addColumn("Số lượng");
        model.addColumn("Giá");
        model.addColumn("Thành tiền");

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ===== BOTTOM =====
        JPanel bottom = new JPanel();
        JButton btnThanhToan = new JButton("Thanh toán");
        bottom.add(btnThanhToan);
        JButton btnLuu = new JButton("Lưu Order");
        bottom.add(btnLuu);

        btnLuu.addActionListener(e -> LuuOrder());
        btnThanhToan.addActionListener(e -> {
            LuuHoaDon();
            JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
        });

        lblTongTien = new JLabel("Tổng: 0");

        bottom.add(lblTongTien);
        add(bottom, BorderLayout.SOUTH);

        // ===== EVENT =====
        btnThem.addActionListener(e -> ThemMon());
        btnXoa.addActionListener(e -> XoaMon());
    }

    // ===== THÊM MÓN =====
    private void ThemMon() {
        String tenMon = cbMon.getSelectedItem().toString();
        int soLuong = Integer.parseInt(txtSoLuong.getText());

        double gia = new MONS().GetGiaByTen(tenMon);
        double thanhTien = gia * soLuong;

        model.addRow(new Object[]{tenMon, soLuong, gia, thanhTien});

        TinhTongTien();
    }

    // ===== XÓA =====
    private void XoaMon() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            model.removeRow(row);
            TinhTongTien();
        }
    }

    // ===== TÍNH TIỀN =====
    private void TinhTongTien() {
        double tong = 0;

        for (int i = 0; i < model.getRowCount(); i++) {
            tong += (double) model.getValueAt(i, 3);
        }

        lblTongTien.setText("Tổng: " + tong);
    }

    public void LuuHoaDon() {
        CONNECTIONSQLSERVER cnn = new CONNECTIONSQLSERVER();

        // tạo hóa đơn
        cnn.ExecuteUpdateSQL(
                "INSERT INTO HOADON(MaBan, TrangThai) VALUES (" + maBan + ",1)");

        try {
            var rs = cnn.GetResultSetSQL("SELECT MAX(MaHD) AS MaHD FROM HOADON");
            int maHD = 0;
            if (rs.next()) {
                maHD = rs.getInt("MaHD");
            }

            for (int i = 0; i < model.getRowCount(); i++) {
                String tenMon = model.getValueAt(i, 0).toString();
                int soLuong = (int) model.getValueAt(i, 1);

                var rsMon = cnn.GetResultSetSQL(
                        "SELECT MaMon FROM MON WHERE TenMon = N'" + tenMon + "'");
                int maMon = 0;
                if (rsMon.next()) {
                    maMon = rsMon.getInt("MaMon");
                }

                cnn.ExecuteUpdateSQL(
                        "INSERT INTO CHITIETHOADON VALUES("
                        + maHD + "," + maMon + "," + soLuong + ")");
            }

        } catch (Exception e) {
        }
    }

    public void LuuOrder() {

        ConnectionData.CONNECTIONSQLSERVER cnn = new ConnectionData.CONNECTIONSQLSERVER();

        // tạo hóa đơn
        cnn.ExecuteUpdateSQL(
                "INSERT INTO HOADON(MaBan, TrangThai) VALUES (" + maBan + ",1)");

        try {
            var rs = cnn.GetResultSetSQL("SELECT MAX(MaHD) AS MaHD FROM HOADON");
            int maHD = 0;
            if (rs.next()) {
                maHD = rs.getInt("MaHD");
            }

            for (int i = 0; i < model.getRowCount(); i++) {

                String tenMon = model.getValueAt(i, 0).toString();
                int soLuong = (int) model.getValueAt(i, 1);

                var rsMon = cnn.GetResultSetSQL(
                        "SELECT MaMon FROM MON WHERE TenMon = N'" + tenMon + "'");

                int maMon = 0;
                if (rsMon.next()) {
                    maMon = rsMon.getInt("MaMon");
                }

                cnn.ExecuteUpdateSQL(
                        "INSERT INTO CHITIETHOADON VALUES("
                        + maHD + "," + maMon + "," + soLuong + ")");
            }

            JOptionPane.showMessageDialog(this, "Lưu Order thành công!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
