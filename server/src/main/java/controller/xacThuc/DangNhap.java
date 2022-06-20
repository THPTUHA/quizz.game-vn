package controller.xacThuc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hangSo.HangSo;
import jwt.JwtTokenCungCap;
import lop.Loi;
import model.dao.NguoiDungDao;
import model.object.NguoiDung;
import tienIch.TienIch;

@WebServlet("/xac-thuc/dang-nhap")
public class DangNhap extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NguoiDung nguoiDung = (NguoiDung) TienIch.layObject(req, NguoiDung.class);
        NguoiDung nguoiDungTonTai = NguoiDungDao.layNguoiDungTheoTen(nguoiDung.getTen(), true);
        if(nguoiDungTonTai == null || nguoiDungTonTai.getId() == 0 || !nguoiDungTonTai.getTen().equals(nguoiDung.getTen())|| !nguoiDungTonTai.getMatKhau().equals(nguoiDung.getMatKhau())){
            TienIch.guiJson(resp, new Loi(-1,"Sai tên hoặc mật khẩu!"));
            return;
        }

        if(nguoiDungTonTai.getTrangThai() == HangSo.CAM){
            TienIch.guiJson(resp, new Loi(-1,"Tài khoản đã bị cấm!"));
            return;
        }

        String token = JwtTokenCungCap.generateToken(nguoiDungTonTai);
        TienIch.guiJson(resp, new Token(token));
    }
}

