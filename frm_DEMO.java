/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
public class frm_DEMO extends JFrame implements ActionListener {
 
    private JButton btnMoBan;
    private JLabel lbeTitle;
 
    public frm_DEMO() {
        setTitle("Demo - Shop Cà Phê");
        setSize(400, 200);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 
        lbeTitle = new JLabel("Chào mừng đến Shop Cà Phê!", SwingConstants.CENTER);
        lbeTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lbeTitle.setBounds(30, 30, 340, 40);
        add(lbeTitle);
 
        btnMoBan = new JButton("Mở quản lý bàn");
        btnMoBan.setBounds(130, 100, 160, 35);
        btnMoBan.addActionListener(this);
        add(btnMoBan);
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnMoBan) {
            frm_BAN f = new frm_BAN();
            f.setVisible(true);
        }
    }
}
