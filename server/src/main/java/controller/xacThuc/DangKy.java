package controller.xacThuc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import lop.Loi;
import model.dao.NguoiDungDao;
import model.object.NguoiDung;
import tienIch.TienIch;

@WebServlet("/xac-thuc/dang-ky")
public class DangKy extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NguoiDung nguoiDung = (NguoiDung) TienIch.layObject(req, NguoiDung.class);
        if(nguoiDung.getUsername()==  null ||nguoiDung.getUsername().length() == 0){
            TienIch.guiJson(resp, new Loi(-1,"Thiếu username"));
            return;
        }
        if(nguoiDung.getEmail()==  null ||nguoiDung.getEmail().length() == 0){
            TienIch.guiJson(resp, new Loi(-1,"Thiếu email"));
            return;
        }
        if(nguoiDung.getMatKhau()==  null ||nguoiDung.getMatKhau().length() == 0){
            TienIch.guiJson(resp, new Loi(-1,"Thiếu password"));
            return;
        }
        NguoiDung user_exist = NguoiDungDao.layUserTheoEmail(nguoiDung.getEmail());
        if(user_exist.getId() != 0){
            System.out.println(user_exist);
            TienIch.guiJson(resp, new Loi(-1,"Email đã tồn tại!"));
            return;
        }
        int status = NguoiDungDao.luu(nguoiDung);
        if(status > 0){
            TienIch.guiJson(resp, new Token("OK"));
        }
    }   

}


class Token{
    private String token;

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Token(String token) {
        this.token = token;
    }

}