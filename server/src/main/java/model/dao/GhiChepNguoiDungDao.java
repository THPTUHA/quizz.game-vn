package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DatabaseKetNoi;
import model.object.GhiChepNguoiDung;
import model.object.NguoiDung;
import model.object.Phong;

public class GhiChepNguoiDungDao {
    public static int luu(GhiChepNguoiDung ghiChepNguoiDung){
        int trangThai = 0;
        try {
            Connection con  = DatabaseKetNoi.init();
            String query = "insert into ghiChepNguoiDung(nguoiDungId,phongId,diem,trangThai,anhDaiDien) values (?,?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(query); 
            ps.setInt(1, ghiChepNguoiDung.getNguoiDung().getId());
            ps.setInt(2, ghiChepNguoiDung.getPhong().getId());
            ps.setInt(3, ghiChepNguoiDung.getDiem());
            ps.setInt(4, ghiChepNguoiDung.getTrangThai());
            ps.setString(5, ghiChepNguoiDung.getNguoiDung().getAnhDaiDien());
            trangThai = ps.executeUpdate();
            System.out.println(trangThai);

        } catch (Exception e) {
           e.printStackTrace();
        }
        return trangThai;
    }

    public static GhiChepNguoiDung layGhiChepNguoiDung(ResultSet tapKetQua, NguoiDung  nguoiDung, Phong phong) throws SQLException{
        GhiChepNguoiDung ghiChepNguoiDung = new GhiChepNguoiDung();
        ghiChepNguoiDung.setNguoiDung(nguoiDung);
        ghiChepNguoiDung.setTrangThai(tapKetQua.getInt("trangThai"));
        ghiChepNguoiDung.setDiem(tapKetQua.getInt("diem"));
        ghiChepNguoiDung.setPhong(phong);
        return ghiChepNguoiDung;
    }
    public static GhiChepNguoiDung layGhiChepNguoiDung(NguoiDung nguoiDung, Phong phong){
        try {
            Connection con  = DatabaseKetNoi.init();
            String query = "select * from ghiChepNguoiDung where nguoiDungId = ? and phongId = ? ";
            PreparedStatement ps=con.prepareStatement(query); 
            ps.setInt(1, nguoiDung.getId());
            ps.setInt(2, phong.getId());

            ResultSet tapKetQua = ps.executeQuery();

            GhiChepNguoiDung ghiChepNguoiDung = new GhiChepNguoiDung();
            while(tapKetQua.next()){
                ghiChepNguoiDung = layGhiChepNguoiDung(tapKetQua, nguoiDung, phong);
            }
            System.out.println(ghiChepNguoiDung.getNguoiDung());

            return ghiChepNguoiDung.getNguoiDung() != null ? ghiChepNguoiDung : null;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }
}
