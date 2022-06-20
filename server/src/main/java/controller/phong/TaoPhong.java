package controller.phong;

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

@WebServlet("/phong/tao")
public class TaoPhong extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            NguoiDung nguoiDung = (NguoiDung)req.getAttribute("nguoiDung");
            Phong phong = (Phong) TienIch.layObject(req, Phong.class);

            if(nguoiDung == null){
                TienIch.guiJson(resp, new Loi(-1,"Bạn không có quyền!"));
                return;
            }

            if(phong == null || phong.getAnhDaiDien()  == null){
                TienIch.guiJson(resp, new Loi(-1,"Ảnh đại diện không tồn tại!"));
                return;
            }
            int anhDaiDienId = Integer.parseInt(phong.getAnhDaiDien());
            if(anhDaiDienId <1 || anhDaiDienId > 8){
                TienIch.guiJson(resp, new Loi(-1,"Ảnh đại diện không tồn tại!"));
                return;
            }

            nguoiDung.setAnhDaiDien(phong.getAnhDaiDien());
            phong.setTrangThai(HangSo.HOAT_DONG);
            phong.setNguoiDung(nguoiDung);
            phong.setSoLuongNguoiDung(HangSo.SO_LUONG_NGUOI_THAM_GIA_MAC_DINH);
            PhongDao.luu(phong);
            
            GhiChepNguoiDung ghiChepNguoiDung = new GhiChepNguoiDung(nguoiDung, phong, 0, HangSo.HOAT_DONG);
            System.out.println("Anh Dai Dien "+ghiChepNguoiDung.getNguoiDung().getAnhDaiDien());
            GhiChepNguoiDungDao.luu(ghiChepNguoiDung);
            TienIch.guiJson(resp, phong);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
