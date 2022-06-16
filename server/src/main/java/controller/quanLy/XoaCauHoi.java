package controller.quanLy;

import java.io.IOException;

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

@WebServlet("/quan-ly/xoa/cau-hoi")
public class XoaCauHoi extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CauHoi cauHoi = (CauHoi) TienIch.layObject(req, CauHoi.class);
        NguoiDung nguoiDung = (NguoiDung)req.getAttribute("nguoiDung");
        if(nguoiDung == null || !nguoiDung.la(HangSo.QUAN_LY)){
            TienIch.guiJson(resp, new Loi(-1,"Bạn không có quyền!"));
            return;
        }
        CauHoiDao.xoaTheoId(cauHoi.getId());
        TienIch.guiJson(resp, cauHoi);
    }
}
