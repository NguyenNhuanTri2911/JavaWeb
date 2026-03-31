/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProcessData;

import ConnectionData.CONNECTIONSQLSERVER;
import SCHEMA.KHUVUC;
 
import java.sql.ResultSet;
import java.util.Vector;
 
public class KHUVUCS {
 
    private CONNECTIONSQLSERVER CNN;
 
    public KHUVUCS() {
        CNN = new CONNECTIONSQLSERVER();
    }
 
    // Lấy tất cả khu vực
    public Vector<KHUVUC> GetAllKhuVuc() {
        Vector<KHUVUC> vector = new Vector<>();
        try {
            String SQL = "SELECT * FROM KHUVUC";
            ResultSet rs = CNN.GetResultSetSQL(SQL);
            while (rs.next()) {
                KHUVUC kv = new KHUVUC();
                kv.setMaKhuVuc_ID(rs.getInt("MaKhuVuc_ID"));
                kv.setTenKhuVuc(rs.getString("TenKhuVuc"));
                kv.setMoTa(rs.getString("MoTa"));
                vector.add(kv);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return vector;
    }
 
    // Lấy khu vực theo ID
    public KHUVUC GetKhuVucByID(int MaKhuVuc_ID) {
        try {
            String SQL = "SELECT * FROM KHUVUC WHERE MaKhuVuc_ID=" + MaKhuVuc_ID;
            ResultSet rs = CNN.GetResultSetSQL(SQL);
            if (rs.next()) {
                KHUVUC kv = new KHUVUC();
                kv.setMaKhuVuc_ID(rs.getInt("MaKhuVuc_ID"));
                kv.setTenKhuVuc(rs.getString("TenKhuVuc"));
                kv.setMoTa(rs.getString("MoTa"));
                return kv;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
