package controller.troChoi;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import lop.GiaiMa;
import lop.Lenh;
import lop.Loi;
import lop.MaHoa;
import model.dao.PhongDao;
import model.object.NguoiDung;
import model.object.Phong;
import socket.CauHinh;
import tienIch.TienIch;



@ServerEndpoint(value="/tro-choi", configurator = CauHinh.class ,
                decoders = GiaiMa.class, 
                encoders = MaHoa.class
            )
public class Choi {
    static Map<Integer,ArrayList<Session>> danhSachPhong =  Collections.synchronizedMap(new HashMap<Integer,ArrayList<Session>>());
    // static Set<Session> chatroom_users = Collections.synchronizedSet(new HashSet<Session>());
    final ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();

    @OnOpen
    public void handleOpen(EndpointConfig endpoint_config, Session userSession){
        if(endpoint_config.getUserProperties().get("phongId") == null 
            || endpoint_config.getUserProperties().get("ten") ==  null){
                System.out.println("Không tồn tại endpoint");
                try {
                    userSession.close();
                } catch (Exception e) {
                   e.printStackTrace();
                }
                return;
        }

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
    public void handleMessage(Lenh lenh, Session user_session) throws IOException{
        System.out.println("lenh "+ lenh);
        String ten = (String)user_session.getUserProperties().get("ten");
        final int phongId = (int) user_session.getUserProperties().get("phongId");

        System.out.println("-----------Username"+ten);

        Lenh lenhMoi = new Lenh();
        final ArrayList<Session> phongHienTai = danhSachPhong.get(phongId);

        switch(lenh.getLenh()){
            case "batDau":
                // if(lenh.getPhongId() != 0 ){
                //    Phong phong = PhongDao.layRoomTheoId(lenh.getPhongId());
                //    if(phong == null){
                //         lenhMoi.setLenh("Loi");
                //         lenhMoi.setLoi(new Loi(-1, "Phòng không tồn tại!"));
                //         break;
                //     }
                timer.scheduleWithFixedDelay(new Runnable() {
                    @Override
                    public void run() {
                        Lenh lenhMoi_1 = new Lenh();
                        lenhMoi_1.setLenh("TEST");
                        for (Iterator<Session> i = phongHienTai.stream().iterator(); i.hasNext();) {
                            Session x = i.next();
                            try {
                                x.getAsyncRemote().sendObject(lenhMoi_1);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, 1, 2, TimeUnit.SECONDS);
                    

                // }else{
                //     lenhMoi.setLenh("Loi");
                //     lenhMoi.setLoi(new Loi(-1, "Phòng không tồn tại!"));
                // }
        }
        

        for (Iterator<Session> i = phongHienTai.stream().iterator(); i.hasNext();) {
            Session x = i.next();
            try {
                x.getAsyncRemote().sendObject(lenhMoi);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @OnClose
    public void handleClose(Session user_session){
        System.out.println("-----------Close"+user_session);
        // chatroom_users.remove(user_session);
    }

    @OnError
    public void handleError(Throwable t){}

}
