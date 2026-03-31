/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessLogic;

import ProcessData.BANS;
import ProcessData.KHUVUCS;
import SCHEMA.BAN;
import SCHEMA.KHUVUC;
 
import java.util.Vector;
 
public class blBAN {
 
    private BANS dsBan;
    private KHUVUCS dsKhuVuc;
 
    public blBAN() {
        dsBan = new BANS();
        dsKhuVuc = new KHUVUCS();
    }
 
    // Lấy tất cả bàn
    public Vector<BAN> LoadBan() {
        return dsBan.GetAllBan();
    }
 
    // Lấy tất cả khu vực
    public Vector<KHUVUC> LoadKhuVuc() {
        return dsKhuVuc.GetAllKhuVuc();
    }
 
    // Thêm bàn - có kiểm tra dữ liệu
    public int ThemBan(String tenBan, String moTa, int maKhuVuc) {
        if (tenBan == null || tenBan.trim().isEmpty()) {
            return -1; // Tên bàn không được rỗng
        }
        BAN ban = new BAN();
        ban.setTenBan(tenBan.trim());
        ban.setMoTa(moTa);
        ban.setMaKhuVuc_ID(maKhuVuc);
        ban.setTrangThai(true);
        return dsBan.ThemBan(ban);
    }
 
    // Sửa bàn
    public int SuaBan(int maBan, String tenBan, String moTa, int maKhuVuc) {
        if (tenBan == null || tenBan.trim().isEmpty()) {
            return -1;
        }
        BAN ban = new BAN();
        ban.setMaBan_ID(maBan);
        ban.setTenBan(tenBan.trim());
        ban.setMoTa(moTa);
        ban.setMaKhuVuc_ID(maKhuVuc);
        return dsBan.SuaBan(ban);
    }
 
    // Xóa bàn
    public int XoaBan(int maBan) {
        if (maBan <= 0) return -1;
        return dsBan.XoaBan(maBan);
    }
 
    // Lấy bàn theo ID
    public BAN GetBanByID(int maBan) {
        return dsBan.GetBanByID(maBan);
    }
}
