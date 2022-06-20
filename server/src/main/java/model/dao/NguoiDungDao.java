package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public static NguoiDung layNguoiDung(ResultSet ketQua, boolean coBaoMat) throws SQLException{
        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.setId(ketQua.getInt("id"));
        nguoiDung.setAnhDaiDien(ketQua.getString("avatar"));
        if(coBaoMat){
            nguoiDung.setMatKhau(ketQua.getString("matKhau"));
        }
        nguoiDung.setTen(ketQua.getString("ten"));
        nguoiDung.setKinhNghiem(ketQua.getLong("kinhNghiem"));
        nguoiDung.setVang(ketQua.getLong("vang"));
        nguoiDung.setQuyen(ketQua.getString("quyen"));
        nguoiDung.setTrangThai(ketQua.getInt("trangThai"));
        return nguoiDung;
    }
    
    public static NguoiDung layUserTheoId(int id, boolean coBaoMat){
        try {
            Connection con  = DatabaseKetNoi.init();
            String query = "select * from nguoiDung where nguoiDung.id = ? limit 1";
            
            PreparedStatement ps=con.prepareStatement(query); 
            ps.setInt(1, id);
            ResultSet tapKetQua = ps.executeQuery();

            NguoiDung nguoiDung = new NguoiDung();
            while(tapKetQua.next()){
                nguoiDung = layNguoiDung(tapKetQua, coBaoMat);
            }
            return nguoiDung;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }

    public static NguoiDung layNguoiDungTheoTen(String ten, boolean coBaoMat){
        try {
            Connection con  = DatabaseKetNoi.init();
            String query = "select * from nguoiDung where nguoiDung.ten = ? limit 1";
            PreparedStatement ps=con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE); 
            ps.setString(1, ten);
            ResultSet tapKetQua = ps.executeQuery();

            NguoiDung nguoiDung = new NguoiDung();
            while(tapKetQua.next()){
                nguoiDung = layNguoiDung(tapKetQua,coBaoMat);
            }
            return nguoiDung;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<NguoiDung> phanTrang(int soTrang, int kichThuocTrang){
        ArrayList<NguoiDung> danhSachNguoiDung = new ArrayList<>();
        try {
            Connection con  = DatabaseKetNoi.init();
            String query = "select * from nguoiDung limit ? offset ?";
            PreparedStatement ps=con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE); 
            ps.setInt(1, kichThuocTrang);
            ps.setInt(2, kichThuocTrang*(soTrang-1));
            ResultSet tapKetQua = ps.executeQuery();
            while(tapKetQua.next()){
                NguoiDung nguoiDung = layNguoiDung(tapKetQua, false);
                danhSachNguoiDung.add(nguoiDung);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSachNguoiDung;
    }

    public static int capNhat(NguoiDung nguoiDung){
        int  status = 0;
        try {
            Connection con  = DatabaseKetNoi.init();
            String query = "update nguoiDung set trangThai = ? where id = ?";
            PreparedStatement ps=con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE); 
            ps.setInt(1, nguoiDung.getTrangThai());
            ps.setInt(2, nguoiDung.getId());
            status = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

}
