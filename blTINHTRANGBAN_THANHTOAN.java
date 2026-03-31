package BusinessLogic;

import ProcessData.BANS;
import SCHEMA.BAN;

import java.util.Vector;

public class blTINHTRANGBAN_THANHTOAN {

    BANS pdBan;

    public blTINHTRANGBAN_THANHTOAN() {
        pdBan = new BANS();
    }

    public Vector<BAN> getListTable() {
        return pdBan.GetAllBan();
    }

    // Cập nhật trạng thái bàn (true = đang có khách, false = trống)
    public int CapNhatTrangThai(int maBan, boolean trangThai) {
        return pdBan.CapNhatTrangThai(maBan, trangThai);
    }

    // Lấy bàn theo ID
    public BAN GetBanByID(int maBan) {
        return pdBan.GetBanByID(maBan);
    }
}
