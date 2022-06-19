package controller.troChoi;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import model.object.NguoiDung;
import socket.CauHinh;
import tienIch.TienIch;

@ServerEndpoint(value="/tro-choi", configurator = CauHinh.class)
public class Choi {
    static Map<Integer,ArrayList<Session>> danhSachPhong =  Collections.synchronizedMap(new HashMap<Integer,ArrayList<Session>>());
    // static Set<Session> chatroom_users = Collections.synchronizedSet(new HashSet<Session>());
    @OnOpen
    public void handleOpen(EndpointConfig endpoint_config, Session userSession){
        int phongId = (int) endpoint_config.getUserProperties().get("phongId");
        String ten = (String) endpoint_config.getUserProperties().get("ten");

        System.out.println("PHONG_ID"+phongId);
        if(danhSachPhong.containsKey(phongId)){
            ArrayList<Session> phongHienTai =  danhSachPhong.get(phongId);
            phongHienTai.add(userSession);
        }else{
            System.out.println("PHONG_MOI"+phongId);
            ArrayList<Session> phongMoi = new ArrayList<>();
            phongMoi.add(userSession);
            danhSachPhong.put(phongId, phongMoi);
        }
        // chatroom_users.add(userSession);
    }

    @OnMessage
    public void handleMessage(String data, Session user_session) throws IOException{
        System.out.println("message "+ data);
        NguoiDung  nguoiDung = (NguoiDung)TienIch.layObject(data, NguoiDung.class);
        String ten = (String)user_session.getUserProperties().get("ten");
        int phongId = (int) user_session.getUserProperties().get("phongId");

        System.out.println("-----------Username"+ten);
        ArrayList<Session> phongHienTai = danhSachPhong.get(phongId);
        System.out.println("------Phong Hien tai ----"+phongHienTai);
        for (Iterator<Session> i = phongHienTai.stream().iterator(); i.hasNext();) {
            Session x = i.next();
            try {
                x.getAsyncRemote().sendText(TienIch.bienDoiThanhJson(nguoiDung));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // chatroom_users.stream().forEach(x ->{
        //     try {
                
        //     } catch (Exception e) {
        //         //TODO: handle exception
        //     }
        // });
    }

    @OnClose
    public void handleClose(Session user_session){
        System.out.println("-----------Close"+user_session);
        // chatroom_users.remove(user_session);
    }

    @OnError
    public void handleError(Throwable t){}

}
