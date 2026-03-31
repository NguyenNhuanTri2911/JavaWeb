/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SCHEMA;

public class KHUVUC {
    private int MaKhuVuc_ID;
    private String TenKhuVuc;
    private String MoTa;
 
    public KHUVUC() {}
 
    public KHUVUC(int MaKhuVuc_ID, String TenKhuVuc, String MoTa) {
        this.MaKhuVuc_ID = MaKhuVuc_ID;
        this.TenKhuVuc = TenKhuVuc;
        this.MoTa = MoTa;
    }
 
    public int getMaKhuVuc_ID() { return MaKhuVuc_ID; }
    public void setMaKhuVuc_ID(int MaKhuVuc_ID) { this.MaKhuVuc_ID = MaKhuVuc_ID; }
 
    public String getTenKhuVuc() { return TenKhuVuc; }
    public void setTenKhuVuc(String TenKhuVuc) { this.TenKhuVuc = TenKhuVuc; }
 
    public String getMoTa() { return MoTa; }
    public void setMoTa(String MoTa) { this.MoTa = MoTa; }
 
    @Override
    public String toString() { return this.TenKhuVuc; }
}
 
