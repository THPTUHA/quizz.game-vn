package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.DatabaseKetNoi;
import model.object.Phong;

public class PhongDao {
    public static int luu(Phong phong){
        int  trangThai = 0;
        try {
            Connection con  = DatabaseKetNoi.init();
            String query = "insert into phong(nguoiDungId,trangThai,soLuongNguoiDung) values (?,?,?)";
            PreparedStatement ps = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS); 
            ps.setInt(1, phong.getNguoiDung().getId());
            ps.setInt(2, phong.getTrangThai());
            ps.setInt(3, phong.getSoLuongNguoiDung());
            trangThai = ps.executeUpdate();
            System.out.println(trangThai);
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    phong.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Tao Phong that bai, ko the tao ID");
                }
            }

        } catch (Exception e) {
           e.printStackTrace();
        }
        return trangThai;
    }

    public static Phong layPhongTheoId(int id){
        try {
            Connection con  = DatabaseKetNoi.init();
            String query = "select * from phong where phong.id = ? limit 1";
            
            PreparedStatement ps=con.prepareStatement(query); 
            ps.setInt(1, id);
            ResultSet tapKetQua = ps.executeQuery();

            Phong phong = new Phong();
            while(tapKetQua.next()){
                phong.setId(tapKetQua.getInt("id"));
                phong.setTrangThai(tapKetQua.getInt("trangThai"));
                phong.setSoLuongNguoiDung(tapKetQua.getInt("soLuongNguoiDung"));
            }
            return phong;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }

    public static Phong layPhongTheoId(Phong phongCanTim){
        try {
            Connection con  = DatabaseKetNoi.init();
            String query = "select * from phong where phong.id = ? and phong.trangThai = ? limit 1";
            
            PreparedStatement ps=con.prepareStatement(query); 
            ps.setInt(1, phongCanTim.getId());
            ps.setInt(2, phongCanTim.getTrangThai());
            ResultSet tapKetQua = ps.executeQuery();

            Phong phong = new Phong();
            while(tapKetQua.next()){
                phong.setId(tapKetQua.getInt("id"));
                phong.setTrangThai(tapKetQua.getInt("trangThai"));
                phong.setSoLuongNguoiDung(tapKetQua.getInt("soLuongNguoiDung"));
            }
            return phong.getId() > 0 ? phong : null;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }
}
