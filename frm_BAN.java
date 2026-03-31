    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package GUI;

    import BusinessLogic.ModelTable;
    import BusinessLogic.blBAN;
    import SCHEMA.BAN;
    import SCHEMA.KHUVUC;

    import javax.swing.*;
    import java.awt.event.*;
    import java.util.Vector;

    public class frm_BAN extends JFrame implements ActionListener {

        private JLabel lbe_MaBan, lbe_TenBan, lbe_KhuVuc, lbe_MoTa;
        public JTextField txtMaBan, txtTenBan, txtMoTa;
        public JComboBox<KHUVUC> cobKhuVuc;
        public JButton btnThem, btnXoa, btnSua, btnDong;
        public JTable tbleBan;
        public JScrollPane jScrollPane;

        private blBAN bl;

        public frm_BAN() {
            bl = new blBAN();
            setTitle("Quản Lý Bàn - Shop Cà Phê");
            setLayout(null);
            setSize(720, 560);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            initComponents();
            LoadKhuVuc();
            LoadBan();
            setLocationRelativeTo(null);
        }

        private void initComponents() {
            lbe_MaBan = new JLabel("Mã bàn:");
            lbe_MaBan.setBounds(30, 20, 100, 30);
            add(lbe_MaBan);
            txtMaBan = new JTextField();
            txtMaBan.setBounds(140, 20, 150, 30);
            txtMaBan.setEditable(false);
            txtMaBan.setBackground(new java.awt.Color(220, 220, 220));
            add(txtMaBan);

            lbe_TenBan = new JLabel("Tên bàn:");
            lbe_TenBan.setBounds(320, 20, 100, 30);
            add(lbe_TenBan);
            txtTenBan = new JTextField();
            txtTenBan.setBounds(420, 20, 200, 30);
            add(txtTenBan);

            lbe_KhuVuc = new JLabel("Khu vực:");
            lbe_KhuVuc.setBounds(30, 65, 100, 30);
            add(lbe_KhuVuc);
            cobKhuVuc = new JComboBox<>();
            cobKhuVuc.setBounds(140, 65, 150, 30);
            add(cobKhuVuc);

            lbe_MoTa = new JLabel("Mô tả:");
            lbe_MoTa.setBounds(320, 65, 100, 30);
            add(lbe_MoTa);
            txtMoTa = new JTextField();
            txtMoTa.setBounds(420, 65, 200, 30);
            add(txtMoTa);

            btnThem = new JButton("Thêm mới");
            btnThem.setBounds(50, 115, 110, 35);
            btnThem.addActionListener(this);
            add(btnThem);

            btnSua = new JButton("Sửa");
            btnSua.setBounds(180, 115, 110, 35);
            btnSua.addActionListener(this);
            add(btnSua);

            btnXoa = new JButton("Xóa");
            btnXoa.setBounds(310, 115, 110, 35);
            btnXoa.addActionListener(this);
            add(btnXoa);

            btnDong = new JButton("Đóng");
            btnDong.setBounds(440, 115, 110, 35);
            btnDong.addActionListener(this);
            add(btnDong);

            tbleBan = new JTable();
            tbleBan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tbleBan.getSelectionModel().addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    int row = tbleBan.getSelectedRow();
                    if (row >= 0) {
                        ModelTable model = (ModelTable) tbleBan.getModel();
                        BAN ban = model.getBanAt(row);
                        txtMaBan.setText(String.valueOf(ban.getMaBan_ID()));
                        txtTenBan.setText(ban.getTenBan());
                        txtMoTa.setText(ban.getMoTa() != null ? ban.getMoTa() : "");
                        for (int i = 0; i < cobKhuVuc.getItemCount(); i++) {
                            if (cobKhuVuc.getItemAt(i).getMaKhuVuc_ID() == ban.getMaKhuVuc_ID()) {
                                cobKhuVuc.setSelectedIndex(i);
                                break;
                            }
                        }
                    }
                }
            });

            jScrollPane = new JScrollPane(tbleBan);
            jScrollPane.setBounds(30, 165, 640, 320);
            add(jScrollPane);
        }

        public void LoadKhuVuc() {
            Vector<KHUVUC> ds = bl.LoadKhuVuc();
            DefaultComboBoxModel<KHUVUC> model = new DefaultComboBoxModel<>(ds);
            cobKhuVuc.setModel(model);
        }

        public void LoadBan() {
            Vector<BAN> ds = bl.LoadBan();
            ModelTable model = new ModelTable(ds);
            tbleBan.setModel(model);
        }

        private void ClearForm() {
            txtMaBan.setText("");
            txtTenBan.setText("");
            txtMoTa.setText("");
            if (cobKhuVuc.getItemCount() > 0)
                cobKhuVuc.setSelectedIndex(0);
            tbleBan.clearSelection();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnThem) {
                Them();
            } else if (e.getSource() == btnSua) {
                Sua();
            } else if (e.getSource() == btnXoa) {
                Xoa();
            } else if (e.getSource() == btnDong) {
                this.dispose();
            }
        }

        private void Them() {
            String tenBan = txtTenBan.getText().trim();
            String moTa = txtMoTa.getText().trim();
            KHUVUC kv = (KHUVUC) cobKhuVuc.getSelectedItem();
            if (tenBan.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên bàn!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int k = bl.ThemBan(tenBan, moTa, kv.getMaKhuVuc_ID());
            if (k > 0) {
                JOptionPane.showMessageDialog(this, "Thêm bàn thành công!");
                LoadBan();
                ClearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm bàn thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void Sua() {
            if (txtMaBan.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn bàn từ bảng!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String tenBan = txtTenBan.getText().trim();
            String moTa = txtMoTa.getText().trim();
            KHUVUC kv = (KHUVUC) cobKhuVuc.getSelectedItem();
            if (tenBan.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên bàn!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận sửa bàn này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) return;
            int maBan = Integer.parseInt(txtMaBan.getText().trim());
            int k = bl.SuaBan(maBan, tenBan, moTa, kv.getMaKhuVuc_ID());
            if (k > 0) {
                JOptionPane.showMessageDialog(this, "Sửa bàn thành công!");
                LoadBan();
                ClearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Sửa bàn thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void Xoa() {
            if (txtMaBan.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn bàn từ bảng!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận xóa bàn này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) return;
            int maBan = Integer.parseInt(txtMaBan.getText().trim());
            int k = bl.XoaBan(maBan);
            if (k > 0) {
                JOptionPane.showMessageDialog(this, "Xóa bàn thành công!");
                LoadBan();
                ClearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại! Bàn đang được sử dụng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }