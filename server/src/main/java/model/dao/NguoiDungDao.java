package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DatabaseKetNoi;
import model.object.NguoiDung;

public class NguoiDungDao {
    public static int luu(NguoiDung nguoiDung){
        int  status = 0;
        try {
            Connection con  = DatabaseKetNoi.init();
            String query = "insert into nguoiDung(ten,matKhau,quyen) values (?,?,?)";
            PreparedStatement ps=con.prepareStatement(query); 
            ps.setString(1, nguoiDung.getTen());
            ps.setString(2, nguoiDung.getMatKhau());
            ps.setString(3, nguoiDung.getQuyen());
            status = ps.executeUpdate();
            System.out.println(status);

        } catch (Exception e) {
           e.printStackTrace();
        }
        return status;
    }

    public static NguoiDung layNguoiDung(ResultSet ketQua) throws SQLException{
        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.setId(ketQua.getInt("id"));
        nguoiDung.setAvatar(ketQua.getString("avatar"));
        nguoiDung.setMatKhau(ketQua.getString("matKhau"));
        nguoiDung.setTen(ketQua.getString("ten"));
        nguoiDung.setKinhNghiem(ketQua.getLong("kinhNghiem"));
        nguoiDung.setVang(ketQua.getLong("vang"));
        nguoiDung.setQuyen(ketQua.getString("quyen"));
        return nguoiDung;
    }
    public static NguoiDung layUserTheoId(int id){
        try {
            Connection con  = DatabaseKetNoi.init();
            String query = "select * from nguoiDung where nguoiDung.id = ? limit 1";
            
            PreparedStatement ps=con.prepareStatement(query); 
            ps.setInt(1, id);
            ResultSet tapKetQua = ps.executeQuery();

            NguoiDung nguoiDung = new NguoiDung();
            while(tapKetQua.next()){
                nguoiDung = layNguoiDung(tapKetQua);
            }
            return nguoiDung;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }

    public static NguoiDung layNguoiDungTheoTen(String ten){
        try {
            Connection con  = DatabaseKetNoi.init();
            String query = "select * from nguoiDung where nguoiDung.ten = ? limit 1";
            PreparedStatement ps=con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE); 
            ps.setString(1, ten);
            ResultSet tapKetQua = ps.executeQuery();

            NguoiDung nguoiDung = new NguoiDung();
            while(tapKetQua.next()){
                nguoiDung = layNguoiDung(tapKetQua);
            }
            return nguoiDung;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }

}
