package controller.quanLy;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hangSo.HangSo;
import lop.Game;
import lop.Loi;
import model.dao.CauHoiDao;
import model.object.CauHoi;
import model.object.NguoiDung;
import tienIch.TienIch;

@WebServlet("/quan-ly/danh-sach/cau-hoi")
public class DanhSachCauHoi extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NguoiDung nguoiDung = (NguoiDung)req.getAttribute("nguoiDung");
        if(nguoiDung == null || !nguoiDung.la(HangSo.QUAN_LY)){
            TienIch.guiJson(resp, new Loi(-1,"Bạn không có quyền!"));
            return;
        }
        ArrayList<CauHoi> danhSachCauHoi = CauHoiDao.phanTrang(1,10);
        for(CauHoi cauHoi: danhSachCauHoi){
            System.out.println(cauHoi.getNoiDung());
        }
        TienIch.guiJson(resp, new Game(danhSachCauHoi));
    }
}
