package GUI;

import ProcessData.KHUVUCS;
import SCHEMA.KHUVUC;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class frm_KHUVUC extends JFrame {

    JTable table;
    DefaultTableModel model;

    public frm_KHUVUC() {
        setTitle("Quản lý khu vực");
        setSize(500, 400);
        setLayout(new BorderLayout());

        model = new DefaultTableModel();
        model.addColumn("Mã");
        model.addColumn("Tên");
        model.addColumn("Mô tả");

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        LoadData();
    }

    public void LoadData() {
        Vector<KHUVUC> ds = new KHUVUCS().GetAllKhuVuc();

        for (KHUVUC kv : ds) {
            model.addRow(new Object[]{
                kv.getMaKhuVuc_ID(),
                kv.getTenKhuVuc(),
                kv.getMoTa()
            });
        }
    }
}