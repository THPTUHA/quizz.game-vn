package socket;

import java.util.List;
import java.util.Map;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

import jwt.JwtTokenCungCap;
import model.dao.GhiChepNguoiDungDao;
import model.dao.NguoiDungDao;
import model.dao.PhongDao;
import model.object.GhiChepNguoiDung;
import model.object.NguoiDung;
import model.object.Phong;

public class CauHinh extends ServerEndpointConfig.Configurator{
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest req, HandshakeResponse resp){
        Map<String,List<String>> params = req.getParameterMap();
        System.out.println("TOKEN"+params.get("token"));
        System.out.println("ROOM_IDS"+params.get("phongId"));


        String ten = JwtTokenCungCap.layTenTuJWT(params.get("token").get(0));
        int phongId = Integer.parseInt(params.get("phongId").get(0));
        // String room_id = params.get("room_id").get(0);
        // System.out.println("ROOM_ID "+ room_id);
        Phong phong = PhongDao.layPhongTheoId(phongId);
        NguoiDung nguoiDung = NguoiDungDao.layNguoiDungTheoTen(ten, false); 
        GhiChepNguoiDung  ghiChepNguoiDung = GhiChepNguoiDungDao.layGhiChepNguoiDung(nguoiDung, phong);
        
        System.out.println("NguoiChoi"+ghiChepNguoiDung);
        sec.getUserProperties().put("nguoiChoi",ghiChepNguoiDung);
    }
}
