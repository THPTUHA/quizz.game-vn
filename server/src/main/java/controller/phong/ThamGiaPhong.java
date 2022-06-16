package controller.phong;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hangSo.HangSo;
import lop.Loi;
import model.dao.GhiChepNguoiDungDao;
import model.dao.PhongDao;
import model.object.GhiChepNguoiDung;
import model.object.NguoiDung;
import model.object.Phong;
import tienIch.TienIch;

@WebServlet("/phong/tham-gia")
public class ThamGiaPhong extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Phong phong = (Phong) TienIch.layObject(req, Phong.class);
        NguoiDung nguoiDung = (NguoiDung)req.getAttribute("nguoiDung");
        if(nguoiDung == null){
            TienIch.guiJson(resp, new Loi(-1,"Bạn không có quyền!"));
            return;
        }
        
        phong.setTrangThai(HangSo.HOAT_DONG);
        Phong phongThamGia = PhongDao.layRoomTheoId(phong);
        if(phongThamGia == null){
            TienIch.guiJson(resp, new Loi(-1,"Phòng không tồn tại!"));
            return;
        }

        GhiChepNguoiDung ghiChepNguoiDungTonTai =  GhiChepNguoiDungDao.layGhiChepNguoiDung(nguoiDung, phong);
        if(ghiChepNguoiDungTonTai!=null){
            TienIch.guiJson(resp, new Loi(-1,"Bạn đã tham gia phòng này!"));
            return;
        }

        GhiChepNguoiDung ghiChepNguoiDung = new GhiChepNguoiDung(nguoiDung, phong, 0, HangSo.HOAT_DONG);
        GhiChepNguoiDungDao.luu(ghiChepNguoiDung);
        TienIch.guiJson(resp, phong);
    }
}
