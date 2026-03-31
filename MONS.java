package ProcessData;

import ConnectionData.CONNECTIONSQLSERVER;
import java.sql.ResultSet;
import java.util.Vector;

public class MONS {

    CONNECTIONSQLSERVER cnn = new CONNECTIONSQLSERVER();

    public Vector<String> GetAllTenMon() {
        Vector<String> ds = new Vector<>();
        try {
            ResultSet rs = cnn.GetResultSetSQL("SELECT TenMon FROM MON");
            while (rs.next()) {
                ds.add(rs.getString("TenMon"));
            }
        } catch (Exception e) {}
        return ds;
    }

    public double GetGiaByTen(String tenMon) {
        try {
            ResultSet rs = cnn.GetResultSetSQL(
                "SELECT Gia FROM MON WHERE TenMon = N'" + tenMon + "'");
            if (rs.next()) {
                return rs.getDouble("Gia");
            }
        } catch (Exception e) {}
        return 0;
    }
}