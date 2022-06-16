package controller.troChoi;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hangSo.HangSo;
import lop.Loi;
import model.dao.CauHoiDao;
import model.object.CauHoi;
import model.object.NguoiDung;
import tienIch.TienIch;

@WebServlet("/tro-choi/bat-dau")
public class BatDau extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NguoiDung nguoiDung = (NguoiDung)req.getAttribute("nguoiDung");
        if(nguoiDung == null || (!nguoiDung.la(HangSo.QUAN_LY) && !nguoiDung.la(HangSo.NGUOI_DUNG)) ){
            TienIch.guiJson(resp, new Loi(-1,"Bạn không có quyền!"));
            return;
        }
        DanhSachLoaiCauHoi danhSachLoaiCauHoi = (DanhSachLoaiCauHoi) TienIch.layObject(req, DanhSachLoaiCauHoi.class);
        System.out.println(danhSachLoaiCauHoi);

        ArrayList<CauHoi> danhSachCauHoi = new ArrayList<>();
        for(LoaiCauHoi loaiCauHoi : danhSachLoaiCauHoi.getDanhSachLoaiCauHoi()){
            ArrayList<CauHoi> ques = CauHoiDao.layCauHoiTheoCapDo(loaiCauHoi.getSoLuong(), loaiCauHoi.getCapDo());
            for(CauHoi q: ques){
                danhSachCauHoi.add(q);
            }
        }
        TienIch.guiJson(resp, danhSachCauHoi);
    }
}

class LoaiCauHoi{
    private int soLuong;
    private int loai;
    private int capDo;
    
    public LoaiCauHoi(int soLuong, int loai, int capDo) {
        this.soLuong = soLuong;
        this.loai = loai;
        this.capDo = capDo;
    }



    public int getSoLuong() {
        return this.soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getLoai() {
        return this.loai;
    }

    public void setLoai(int loai) {
        this.loai = loai;
    }

    public int getCapDo() {
        return this.capDo;
    }

    public void setCapDo(int capDo) {
        this.capDo = capDo;
    }
    


    @Override
    public String toString() {
        return "{" +
            " soLuong='" + getSoLuong() + "'" +
            ", loai='" + getLoai() + "'" +
            ", capDo='" + getCapDo() + "'" +
            "}";
    }

}

class DanhSachLoaiCauHoi{
    ArrayList<LoaiCauHoi>danhSachLoaiCauHoi ;


    public DanhSachLoaiCauHoi(ArrayList<LoaiCauHoi> danhSachLoaiCauHoi) {
        this.danhSachLoaiCauHoi = danhSachLoaiCauHoi;
    }


    public ArrayList<LoaiCauHoi> getDanhSachLoaiCauHoi() {
        return this.danhSachLoaiCauHoi;
    }

    public void setDanhSachLoaiCauHoi(ArrayList<LoaiCauHoi> danhSachLoaiCauHoi) {
        this.danhSachLoaiCauHoi = danhSachLoaiCauHoi;
    }
   

    @Override
    public String toString() {
        return "{" +
            " danhSachLoaiCauHoi='" + getDanhSachLoaiCauHoi() + "'" +
            "}";
    }

}