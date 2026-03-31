/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ConnectionData;

import java.sql.ResultSet;
 
public interface ICONNECTIONSQLSERVER {
    void Open();
    void Close();
    ResultSet GetResultSetSQL(String SQL);
    int ExecuteUpdateSQL(String SQL);
    int ExecuteStoredProcedures(String NameStoredProcedures, Object[] param);
    ResultSet GetResultSetStoredProcedurce(String procedureName, Object[] param);
}
 