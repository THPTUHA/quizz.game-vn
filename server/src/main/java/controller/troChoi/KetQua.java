package controller.troChoi;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lop.Loi;
import model.dao.GhiChepNguoiDungDao;
import model.object.GhiChepNguoiDung;
import model.object.NguoiDung;
import model.object.Phong;
import tienIch.TienIch;

@WebServlet("/tro-choi/ket-qua")
public class KetQua extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Phong phong = (Phong) TienIch.layObject(req, Phong.class);
            NguoiDung nguoiDung = (NguoiDung)req.getAttribute("nguoiDung");
            if(nguoiDung == null){
                TienIch.guiJson(resp, new Loi(-1,"Bạn không có quyền!"));
                return;
            }

            if(phong == null){
                TienIch.guiJson(resp, new Loi(-1,"Phòng không tồn tại!"));
                return;
            }
            ArrayList<GhiChepNguoiDung>  danhSachGhiChepNguoiDung = GhiChepNguoiDungDao.layGhiChepNguoiDungTheoPhongId(phong.getId());
            
            if(danhSachGhiChepNguoiDung.size() == 0){
                TienIch.guiJson(resp, new Loi(-1,"Phòng trống!"));
                return;
            }
            

            GhiChepNguoiDung ghiChepNguoiDung = danhSachGhiChepNguoiDung.get(0);
            for(int i = 1 ;i < danhSachGhiChepNguoiDung.size() ; ++i){
                if(danhSachGhiChepNguoiDung.get(i).getDiem() > ghiChepNguoiDung.getDiem()){
                    ghiChepNguoiDung = danhSachGhiChepNguoiDung.get(i);
                }
            }
            System.out.println("Danh Sach Ghi chep"+ghiChepNguoiDung);

            TienIch.guiJson(resp, ghiChepNguoiDung);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
