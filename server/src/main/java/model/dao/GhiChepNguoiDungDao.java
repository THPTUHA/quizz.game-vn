package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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


    public static int capNhatDiem(GhiChepNguoiDung ghiChepNguoiDung){
        int trangThai = 0;
        try {
            Connection con  = DatabaseKetNoi.init();
            String query = "update ghiChepNguoiDung set diem = ? where nguoiDungId = ? and phongId = ?";
            PreparedStatement ps=con.prepareStatement(query); 
            ps.setInt(1, ghiChepNguoiDung.getDiem());
            ps.setInt(2, ghiChepNguoiDung.getNguoiDung().getId());
            ps.setInt(3, ghiChepNguoiDung.getPhong().getId());
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
        nguoiDung.setAnhDaiDien(tapKetQua.getString("anhDaiDien"));
        return ghiChepNguoiDung;
    }

    public static GhiChepNguoiDung layGhiChepNguoiDung(ResultSet tapKetQua) throws SQLException{
        GhiChepNguoiDung ghiChepNguoiDung = new GhiChepNguoiDung();
        NguoiDung nguoiDung = NguoiDungDao.layUserTheoId(tapKetQua.getInt("nguoiDungId"), false);
        Phong phong = PhongDao.layPhongTheoId(tapKetQua.getInt("phongId"));
        ghiChepNguoiDung.setNguoiDung(nguoiDung);
        ghiChepNguoiDung.setTrangThai(tapKetQua.getInt("trangThai"));
        ghiChepNguoiDung.setDiem(tapKetQua.getInt("diem"));
        ghiChepNguoiDung.setPhong(phong);
        nguoiDung.setAnhDaiDien(tapKetQua.getString("anhDaiDien"));
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

    public static ArrayList<GhiChepNguoiDung> layGhiChepNguoiDungTheoPhongId(int phongId){
        ArrayList<GhiChepNguoiDung> danhSachGhiChepNguoiDung = new ArrayList<>();

        try {
            Connection con  = DatabaseKetNoi.init();
            String query = "select * from ghiChepNguoiDung where phongId = ? ";
            PreparedStatement ps=con.prepareStatement(query); 
            ps.setInt(1, phongId);

            ResultSet tapKetQua = ps.executeQuery();
            System.out.println("PhongId "+phongId);
            while(tapKetQua.next()){
                System.out.println("Next---- ");
                GhiChepNguoiDung ghiChepNguoiDung = layGhiChepNguoiDung(tapKetQua);
                danhSachGhiChepNguoiDung.add(ghiChepNguoiDung);
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return danhSachGhiChepNguoiDung;
    }
}
