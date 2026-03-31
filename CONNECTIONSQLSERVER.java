/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConnectionData;

import java.sql.*;
 
public class CONNECTIONSQLSERVER implements ICONNECTIONSQLSERVER {
 
    private String UserName;
    private String PassWord;
    private String DataBaseName;
    private String ServerName;
    private String DriverClass;
    private Connection cnn = null;
    private Statement stm = null;
    private String DriverURL;
 
    public CONNECTIONSQLSERVER() {
        try {
            this.UserName = "sa";
            this.PassWord = "291106";
            this.DataBaseName = "dbQuanLyShopCaPhe";
            this.ServerName = "NhuanTri";
            this.DriverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            this.DriverURL = "jdbc:sqlserver://" + ServerName + ":1433;databaseName=" + DataBaseName
                    + ";user=" + UserName + ";password=" + PassWord
                    + ";encrypt=false;IntegratedSecurity=false";
            Class.forName(DriverClass);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
 
    @Override
    public void Open() {
        try {
            cnn = DriverManager.getConnection(DriverURL);
            stm = cnn.createStatement();
        } catch (Exception ex) {
            System.out.println("LOI KET NOI SQL:");
            ex.printStackTrace();
        }
    }
 
    @Override
    public void Close() {
        try {
            if (stm != null) stm.close();
            if (cnn != null) cnn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
 
    @Override
    public ResultSet GetResultSetSQL(String SQL) {
        try {
            this.Open();
            return stm.executeQuery(SQL);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
 
    @Override
    public int ExecuteUpdateSQL(String SQL) {
        try {
            this.Open();
            return stm.executeUpdate(SQL);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
 
    @Override
    public int ExecuteStoredProcedures(String NameStoredProcedures, Object[] param) {
        try {
            CallableStatement ps = cnn.prepareCall("{call " + NameStoredProcedures + "}");
            for (int i = 0; i < param.length; i++) {
                ps.setObject(i + 1, param[i]);
            }
            int k = ps.executeUpdate();
            ps.close();
            return k;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
 
    @Override
    public ResultSet GetResultSetStoredProcedurce(String procedureName, Object[] param) {
        try {
            this.Open();
            CallableStatement ps = cnn.prepareCall("{call " + procedureName + "}");
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    ps.setObject(i + 1, param[i]);
                }
            }
            ResultSet rs = ps.executeQuery();
            ps.close();
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}