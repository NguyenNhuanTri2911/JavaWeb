/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessLogic;

import SCHEMA.BAN;
 
import javax.swing.table.AbstractTableModel;
import java.util.Vector;
 
public class ModelTable extends AbstractTableModel {
 
    Vector<BAN> list;
    String[] colNames = {"Mã bàn", "Tên bàn", "Khu vực", "Mô tả", "Trạng thái"};
    Class<?>[] colClasses = {int.class, String.class, int.class, String.class, Boolean.class};
 
    public ModelTable(Vector<BAN> list) {
        this.list = list;
    }
 
    @Override
    public int getRowCount() {
        return list.size();
    }
 
    @Override
    public int getColumnCount() {
        return colNames.length;
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        BAN ban = list.get(rowIndex);
        switch (columnIndex) {
            case 0: return ban.getMaBan_ID();
            case 1: return ban.getTenBan();
            case 2: return ban.getMaKhuVuc_ID();
            case 3: return ban.getMoTa();
            case 4: return ban.isTrangThai();
            default: return null;
        }
    }
 
    @Override
    public String getColumnName(int columnIndex) {
        return colNames[columnIndex];
    }
 
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return colClasses[columnIndex];
    }
 
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
 
    // Lấy BAN object theo dòng
    public BAN getBanAt(int rowIndex) {
        return list.get(rowIndex);
    }
}
 