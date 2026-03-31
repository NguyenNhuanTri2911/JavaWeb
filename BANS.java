package ProcessData;

import ConnectionData.CONNECTIONSQLSERVER;
import SCHEMA.BAN;

import java.sql.ResultSet;
import java.util.Vector;

public class BANS {

    private CONNECTIONSQLSERVER CNN;

    public BANS() {
        CNN = new CONNECTIONSQLSERVER();
    }

    // Lấy tất cả bàn
    public Vector<BAN> GetAllBan() {
        Vector<BAN> vector = new Vector<>();
        try {
            String SQL = "SELECT * FROM BAN";
            ResultSet rs = CNN.GetResultSetSQL(SQL);
            while (rs.next()) {
                BAN ban = new BAN();
                ban.setMaBan_ID(rs.getInt("MaBan_ID"));
                ban.setTenBan(rs.getString("TenBan"));
                ban.setTrangThai(rs.getBoolean("TrangThai"));
                ban.setMoTa(rs.getString("MoTa"));
                ban.setMaKhuVuc_ID(rs.getInt("MaKhuVuc_ID"));
                vector.add(ban);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    // Alias dùng cho blTINHTRANGBAN_THANHTOAN
    public Vector<BAN> getListBan() {
        return GetAllBan();
    }

    // Thêm bàn mới
    public int ThemBan(BAN ban) {
        String SQL = "INSERT INTO BAN(TenBan, TrangThai, MoTa, MaKhuVuc_ID) "
                + "VALUES(N'" + ban.getTenBan() + "', 1, N'" + ban.getMoTa() + "', "
                + ban.getMaKhuVuc_ID() + ")";
        return CNN.ExecuteUpdateSQL(SQL);
    }

    // Sửa bàn
    public int SuaBan(BAN ban) {
        String SQL = "UPDATE BAN SET TenBan=N'" + ban.getTenBan() + "', "
                + "MoTa=N'" + ban.getMoTa() + "', "
                + "MaKhuVuc_ID=" + ban.getMaKhuVuc_ID() + " "
                + "WHERE MaBan_ID=" + ban.getMaBan_ID();
        return CNN.ExecuteUpdateSQL(SQL);
    }

    // Xóa bàn
    public int XoaBan(int MaBan_ID) {
        String SQL = "DELETE FROM BAN WHERE MaBan_ID=" + MaBan_ID;
        return CNN.ExecuteUpdateSQL(SQL);
    }

    // Lấy bàn theo ID
    public BAN GetBanByID(int MaBan_ID) {
        try {
            String SQL = "SELECT * FROM BAN WHERE MaBan_ID=" + MaBan_ID;
            ResultSet rs = CNN.GetResultSetSQL(SQL);
            if (rs.next()) {
                BAN ban = new BAN();
                ban.setMaBan_ID(rs.getInt("MaBan_ID"));
                ban.setTenBan(rs.getString("TenBan"));
                ban.setTrangThai(rs.getBoolean("TrangThai"));
                ban.setMoTa(rs.getString("MoTa"));
                ban.setMaKhuVuc_ID(rs.getInt("MaKhuVuc_ID"));
                return ban;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // Cập nhật trạng thái bàn
    public int CapNhatTrangThai(int MaBan_ID, boolean trangThai) {
        String SQL = "UPDATE BAN SET TrangThai=" + (trangThai ? 1 : 0)
                + " WHERE MaBan_ID=" + MaBan_ID;
        return CNN.ExecuteUpdateSQL(SQL);
    }
}