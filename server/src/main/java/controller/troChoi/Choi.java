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
import model.dao.CauHoiDao;
import model.dao.PhongDao;
import model.object.CauHoi;
import model.object.NguoiDung;
import model.object.Phong;
import socket.CauHinh;
import tienIch.TienIch;

import static model.dao.CauHoiDao.layCauHoiTheoCapDo;


// TODO: them so luong cau hoi khi config

@ServerEndpoint(value="/tro-choi", configurator = CauHinh.class ,
                decoders = GiaiMa.class, 
                encoders = MaHoa.class
            )
public class Choi {
    static Map<Integer,ArrayList<Session>> danhSachPhong =  Collections.synchronizedMap(new HashMap<Integer,ArrayList<Session>>());
    // static Set<Session> chatroom_users = Collections.synchronizedSet(new HashSet<Session>());
    private ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();
    private int phongId;
    private boolean chuPhong = false;

    private void setChuPhong(boolean chuPhong) {
        this.chuPhong = chuPhong;
    }

    private boolean getChuPhong() {
        return this.chuPhong;
    }


    @OnOpen
    public void handleOpen(EndpointConfig endpoint_config, Session phien) throws IOException {
        if(endpoint_config.getUserProperties().get("phongId") == null 
            || endpoint_config.getUserProperties().get("ten") ==  null){
                System.out.println("Không tồn tại endpoint");
                try {
                    phien.close();
                } catch (Exception e) {
                   e.printStackTrace();
                }
                return;
        }

        phongId = (int) endpoint_config.getUserProperties().get("phongId");
        String ten = (String) endpoint_config.getUserProperties().get("ten");
        System.out.println("PHONG_ID"+phongId);
        if(danhSachPhong.containsKey(phongId)){
            // Da ton tai phong
            ArrayList<Session> phongHienTai =  danhSachPhong.get(phongId);
            phongHienTai.add(phien);
            ArrayList<Session> phong = danhSachPhong.get(phongId);
            if (phong.size() == 1) {
                // Gui lenh bat dau cho chu phong khi nguoi choi da vao du
                Lenh lenhBatDau = new Lenh();
                lenhBatDau.setLenh("batDau");
                lenhBatDau.setPhongId(phongId);
                phong.get(0).getAsyncRemote().sendObject(lenhBatDau);
            }
            else {
                System.out.println("Loi so luong phien trong phong");
                phien.close();
                return;
            }
        }
        else {
            // Tao phong moi -> set chu phong
            setChuPhong(true);
            System.out.println("PHONG_MOI"+phongId);
            ArrayList<Session> phongMoi = new ArrayList<>();
            phongMoi.add(phien);
            danhSachPhong.put(phongId, phongMoi);
        }
    }

    @OnMessage
    public void handleMessage(Lenh lenh, Session phien) throws IOException{
        System.out.println("lenh "+ lenh);
        String ten = (String)phien.getUserProperties().get("ten");
        phongId = (int) phien.getUserProperties().get("phongId");

        System.out.println("-----------Username"+ten);

        Lenh lenhMoi = new Lenh();
        final ArrayList<Session> phongHienTai = danhSachPhong.get(phongId);

        switch(lenh.getLenh()){
            // Lenh batDau duoc gui tu client chu phong den may chu de tien hay tao bo cau hoi va bat dau dong ho dem nguoc
            case "batDau":

                // kiem tra neu khong phai chu phong -> break
                if (!getChuPhong()) break;
                // Sinh bo cau hoi ngau nhien
                ArrayList<CauHoi> danhSachCauHoi = new ArrayList<>();
                danhSachCauHoi.addAll(CauHoiDao.layCauHoiTheoCapDo(4,1));
                danhSachCauHoi.addAll(CauHoiDao.layCauHoiTheoCapDo(3,2));
                danhSachCauHoi.addAll(CauHoiDao.layCauHoiTheoCapDo(3,3));

                // Set cau hoi hien tai
                ArrayList<Session> phienTrongPhong = danhSachPhong.get(phongId);
                for (Session phienNguoiDung: phienTrongPhong) {
                    phienNguoiDung.getUserProperties().put("cauHienTai", 0);
                }

                    timer.scheduleWithFixedDelay(new Runnable() {
                    @Override
                    public void run() {
                        guiCauHoiChoClient(phien, danhSachCauHoi);
                    }
                }, 3, 5, TimeUnit.SECONDS);
                    

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
    public void handleClose(Session phien){
        System.out.println("-----------Close"+phien);
        phongId = (int)phien.getUserProperties().get("phongId");
        // xoa phong khoi map danh sach phong
        if (danhSachPhong.containsKey(phongId)) danhSachPhong.remove(phongId);
    }

    @OnError
    public void handleError(Throwable t){}

    private void guiCauHoiChoClient(Session phien, ArrayList<CauHoi> danhSachCauHoi) {
        // kiem tra cau hoi hien tai
        ArrayList<Session> phienTrongPhong = danhSachPhong.get(phongId);
        int cauHienTai;
        if ((cauHienTai = (int)phien.getUserProperties().get("cauHienTai")) >= 10) {
            Lenh lenhKetThuc = new Lenh("ketThuc");
            for (Session phienNguoiDung: phienTrongPhong) {
                phienNguoiDung.getAsyncRemote().sendObject(lenhKetThuc);
            }

            // Huy dong ho dem nguoc
            timer.shutdown();
        }
        else {
            cauHienTai += 1;
            Lenh lenhGuiCauHoi = new Lenh("", danhSachCauHoi.get(cauHienTai));
            // Bo dap an khoi lenh
            lenhGuiCauHoi.getCauHoi().setDapAn(null);

            // Gui lenhGuiCauHoi cho cac phien trong phong
            for (Session phienNguoiDung: phienTrongPhong) {
                phienNguoiDung.getUserProperties().put("cauHienTai", cauHienTai);
                if (phienNguoiDung.isOpen()) phienNguoiDung.getAsyncRemote().sendObject(lenhGuiCauHoi);
            }
        }

    }

}
