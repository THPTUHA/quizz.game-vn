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

@WebServlet("/quan-ly/cap-nhat/cau-hoi")
public class CapNhatCauHoi extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CauHoi cauHoi = (CauHoi) TienIch.layObject(req, CauHoi.class);
        NguoiDung user = (NguoiDung)req.getAttribute("nguoiDung");
        if(user == null || !user.la(HangSo.QUAN_LY)){
            TienIch.guiJson(resp, new Loi(-1,"Bạn không có quyền!"));
            return;
        }
        CauHoiDao.capNhat(cauHoi);
        TienIch.guiJson(resp, cauHoi);
    }
}
