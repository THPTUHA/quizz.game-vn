package controller.quanLy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hangSo.HangSo;
import lop.Loi;
import model.object.NguoiDung;
import tienIch.TienIch;

@WebServlet("/quan-ly/cam/nguoi-dung")
public class CamNguoiDung  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NguoiDung nguoiDungXuLy = (NguoiDung) TienIch.layObject(req, NguoiDung.class);
        NguoiDung nguoiDung =(NguoiDung)req.getAttribute("nguoiDung");

        if(nguoiDung == null || !nguoiDung.la(HangSo.QUAN_LY)){
            TienIch.guiJson(resp, new Loi(-1,"Bạn không có quyền!"));
            return;
        }
        
        if(nguoiDungXuLy == null){
            TienIch.guiJson(resp, new Loi(-1,"Người dùng không tông tại!"));
            return;
        }
        
        if(nguoiDung.getTrangThai() == HangSo.CAM){
            nguoiDung.setTrangThai(HangSo.HOAT_DONG);
        }else{
            nguoiDung.setTrangThai(HangSo.CAM);
        }

        TienIch.guiJson(resp, nguoiDung);
    }
}
