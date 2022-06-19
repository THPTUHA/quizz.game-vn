package model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import lop.DapAn;
import lop.GoiY;
import model.DatabaseKetNoi;
import model.object.CauHoi;
import tienIch.TienIch;

public class CauHoiDao {
    public static int luu(CauHoi cauHoi){
        int  trangThai = 0;
        try {
            Connection con  = DatabaseKetNoi.init();
            String query = "insert into cauHoi(noiDung,goiY,dapAn,nguoiDungId,capDo,diem,loai) values (?,?,?,?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(query,  Statement.RETURN_GENERATED_KEYS); 
            ps.setString(1, cauHoi.getNoiDung());
            ps.setString(2, TienIch.bienDoiThanhJson(cauHoi.getGoiY()));
            ps.setString(3, TienIch.bienDoiThanhJson(cauHoi.getDapAn()));
            ps.setInt(4, cauHoi.getNguoiDung().getId());
            ps.setInt(5, cauHoi.getCapDo());
            ps.setInt(6, cauHoi.getDiem());
            ps.setInt(7, cauHoi.getLoai());
            trangThai = ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cauHoi.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Tao cau hoi that bau, ID cau hoi ko the tao");
                }
            }

        } catch (Exception e) {
           e.printStackTrace();
        }
        return trangThai;
    }

    public static int xoaTheoId(int id){
        int trangThai = 0;
        try {
            Connection con  = DatabaseKetNoi.init();
            String query = "delete from cauHoi where id = ?";
            PreparedStatement ps = con.prepareStatement(query); 
            ps.setInt(1, id);
            trangThai = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trangThai;
    }

    public static int capNhat(CauHoi cauHoi){
        int trangThai = 0;
        try {
            Connection con  = DatabaseKetNoi.init();
            String query = "update cauHoi set noiDung = ?,goiY = ?, dapAn = ?, capDo = ?, diem = ?, loai = ? where id = ?  ";
            PreparedStatement ps=con.prepareStatement(query); 
            ps.setString(1, cauHoi.getNoiDung());
            ps.setString(2, TienIch.bienDoiThanhJson(cauHoi.getGoiY()));
            ps.setString(3, TienIch.bienDoiThanhJson(cauHoi.getDapAn()));
            ps.setInt(4, cauHoi.getCapDo());
            ps.setInt(5, cauHoi.getDiem());
            ps.setInt(6, cauHoi.getLoai());
            ps.setInt(7, cauHoi.getId());
            trangThai = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trangThai;
    }

    public static CauHoi LayCauHoi(ResultSet ketQua) throws SQLException , IOException{
        CauHoi cauHoi = new CauHoi();
        cauHoi.setId(ketQua.getInt("id"));
        cauHoi.setGoiY((GoiY)TienIch.layObject(ketQua.getString("goiY"),GoiY.class));
        cauHoi.setDapAn((DapAn)TienIch.layObject(ketQua.getString("dapAn"),DapAn.class));
        cauHoi.setCapDo(ketQua.getInt("capDo"));
        cauHoi.setDiem(ketQua.getInt("diem"));
        cauHoi.setLoai(ketQua.getInt("loai"));
        cauHoi.setNoiDung(ketQua.getString("noiDung"));
        return cauHoi;
    }


    public static ArrayList<CauHoi> phanTrang(int soTrang, int kichThuocTrang){
        ArrayList<CauHoi> danhSachCauHoi = new ArrayList<>();
        try {
            Connection con  = DatabaseKetNoi.init();
            String query = "select * from cauHoi limit ? offset ?";
            PreparedStatement ps=con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE); 
            ps.setInt(1, kichThuocTrang);
            ps.setInt(2, kichThuocTrang*(soTrang-1));
            ResultSet tapKetQua = ps.executeQuery();
            while(tapKetQua.next()){
                CauHoi cauHoi = LayCauHoi(tapKetQua);
                danhSachCauHoi.add(cauHoi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSachCauHoi;
    }

    public static ArrayList<CauHoi> layCauHoiTheoCapDo(int soLuong, int capDo){
        ArrayList<CauHoi> danhSachCauHoi = new ArrayList<>();
        try {
            Connection con  = DatabaseKetNoi.init();
            String query = "select * from cauHoi where capDo = ? order by rand() limit ?";
            PreparedStatement ps=con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE); 
            ps.setInt(1, capDo);
            ps.setInt(2, soLuong);
            ResultSet tapKetQua = ps.executeQuery();
            while(tapKetQua.next()){
                CauHoi cauHoi = LayCauHoi(tapKetQua);
                danhSachCauHoi.add(cauHoi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSachCauHoi;
    }
}
