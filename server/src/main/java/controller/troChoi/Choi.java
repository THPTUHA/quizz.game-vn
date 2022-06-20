package controller.troChoi;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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

import hangSo.HangSo;
import lop.GiaiMa;
import lop.Lenh;
import lop.MaHoa;
import model.dao.CauHoiDao;
import model.object.CauHoi;
import model.object.GhiChepNguoiDung;
import socket.CauHinh;


@ServerEndpoint(value="/tro-choi", configurator = CauHinh.class ,
                decoders = GiaiMa.class, 
                encoders = MaHoa.class
            )
public class Choi {
    static Map<Integer,ArrayList<Session>> danhSachPhong =  Collections.synchronizedMap(new HashMap<Integer,ArrayList<Session>>());
    // static Set<Session> chatroom_users = Collections.synchronizedSet(new HashSet<Session>());
    final ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();

    @OnOpen
    public void handleOpen(EndpointConfig endpoint_config, Session phien) throws IOException {
        if(endpoint_config.getUserProperties().get("nguoiChoi") == null){
                System.out.println("Không tồn tại endpoint");
                try {
                    phien.close();
                } catch (Exception e) {
                   e.printStackTrace();
                }
                return;
        }

        GhiChepNguoiDung nguoiDung = (GhiChepNguoiDung) endpoint_config.getUserProperties().get("nguoiChoi");

        int phongId = nguoiDung.getPhong().getId();
        System.out.println("PHONG_ID"+phongId);
        Lenh lenhThongBao = new Lenh("khoiTao");
        if (danhSachPhong.containsKey(phongId)) {
            // Da ton tai phong
            ArrayList<Session> phongHienTai =  danhSachPhong.get(phongId);
            ArrayList<Session> phong = danhSachPhong.get(phongId);
            System.out.println("Kich thuoc phong "+ phong.size());
            if (phong.size() == 1) {
                // Gui lenh bat dau cho chu phong khi nguoi choi da vao du
                lenhThongBao.setLenh("batDau");
                lenhThongBao.setPhongId(phongId);
                phongHienTai.add(phien);

                phien.getUserProperties().put("chuPhong", false);

                lenhThongBao.setChuPhong((GhiChepNguoiDung)phongHienTai.get(0).getUserProperties().get("nguoiChoi"));
                lenhThongBao.setKhach((GhiChepNguoiDung)phongHienTai.get(1).getUserProperties().get("nguoiChoi"));
                for (Session phienNguoiDung: phongHienTai) {
                    phienNguoiDung.getAsyncRemote().sendObject(lenhThongBao);
                }
            }
            else {
                System.out.println("Loi so luong phien trong phong");
                phien.close();
            }
        }
        else {
            // Tao phong moi -> set chu phong
            phien.getUserProperties().put("chuPhong", true);
            System.out.println("PHONG_MOI"+phongId);
            ArrayList<Session> phongMoi = new ArrayList<>();
            phongMoi.add(phien);
            danhSachPhong.put(phongId, phongMoi);

            lenhThongBao.setChuPhong(nguoiDung);
            phien.getAsyncRemote().sendObject(lenhThongBao);
        }
    }

    @OnMessage
    public void handleMessage(Lenh lenh,final Session phien) throws IOException{
        System.out.println("lenh "+ lenh);
        GhiChepNguoiDung nguoiDung = (GhiChepNguoiDung) phien.getUserProperties().get("nguoiChoi");
        Lenh lenhMoi = new Lenh();
        int phongId = nguoiDung.getPhong().getId();
        System.out.println("phongIdTRC"+ phongId);

        final ArrayList<Session> phongHienTai = danhSachPhong.get(phongId);
        final ArrayList<CauHoi> danhSachCauHoi = new ArrayList<>();

        switch(lenh.getLenh()){
            // Lenh batDau duoc gui tu client chu phong den may chu de tien hay tao bo cau hoi va bat dau dong ho dem nguoc
            case "batDau":

                // kiem tra neu khong phai chu phong -> break

                if ((boolean)phien.getUserProperties().get("chuPhong") == false) break;
                System.out.println("chu phong");
                // Sinh bo cau hoi ngau nhien
                danhSachCauHoi.addAll(CauHoiDao.layCauHoiTheoCapDo(4,1));
                danhSachCauHoi.addAll(CauHoiDao.layCauHoiTheoCapDo(3,2));
                danhSachCauHoi.addAll(CauHoiDao.layCauHoiTheoCapDo(3,3));

                // Set cau hoi hien tai
                System.out.println("phongId"+ phongId);
                System.out.println("danh sach cau hoi : "+ danhSachCauHoi.size());
                System.out.println("kich thuoc phong"+ phongHienTai.size());

                for (Session phienNguoiDung: phongHienTai) {
                    phienNguoiDung.getUserProperties().put("cauHienTai", 0);
                }

                timer.scheduleWithFixedDelay(new Runnable() {
                    @Override
                    public void run(){
                        guiCauHoiChoClient(phien, danhSachCauHoi);
                    }
                }, 3000, HangSo.THOI_GIAN, TimeUnit.MILLISECONDS); 
                break;
            case "guiDapAn":
                int idCauHoi = lenh.getCauHoi().getId();
                // Neu cau hoi co dap an gui dc gui tu client khac cau hien tai -> loi~
                if (idCauHoi != (int)phien.getUserProperties().get("idCauHienTai")) {
                    Lenh lenhLoi = new Lenh();
                    lenhLoi.setLenh("Loi");
                    phien.getAsyncRemote().sendObject(lenhLoi);
                }
                else {
                    int khoangCachThoiGian = (int) ChronoUnit.MILLIS.between(LocalDateTime.now(), (LocalDateTime)phien.getUserProperties().get("thoiGianGuiCauHoi"));

                    if (khoangCachThoiGian > HangSo.THOI_GIAN) {
                        Lenh lenhLoi = new Lenh();
                        lenhLoi.setLenh("Loi");
                        phien.getAsyncRemote().sendObject(lenhLoi);
                    }
                    // request gui dap an hop le
                    else {
                        CauHoi cauHoi = danhSachCauHoi.get((int)phien.getUserProperties().get("cauHienTai"));
                        if (cauHoi.getDapAn() == lenh.getCauHoi().getDapAn()) {
                            // Tra loi dung
                            GhiChepNguoiDung khach = (GhiChepNguoiDung)phongHienTai.get(1).getUserProperties().get("nguoiDung");
                            GhiChepNguoiDung chuPhong = (GhiChepNguoiDung)phongHienTai.get(0).getUserProperties().get("nguoiDung");
                            
                            int delta = (int)(((float)HangSo.THOI_GIAN- khoangCachThoiGian) / (HangSo.THOI_GIAN) * cauHoi.getDiem());
                            if (phien == phongHienTai.get(0)) chuPhong.setDiem(chuPhong.getDiem() + delta);
                            else khach.setDiem(khach.getDiem() + delta);
                            Lenh lenhGuiKetQua = new Lenh();

                            lenhGuiKetQua.setLenh("guiKetQua");

                            for (Session phienNguoiDung: phongHienTai) {
                                phienNguoiDung.getAsyncRemote().sendObject(lenhGuiKetQua);
                            }

                        }

                    }
                }
                break;
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
        GhiChepNguoiDung nguoiDung = (GhiChepNguoiDung) phien.getUserProperties().get("nguoiChoi");
        int phongId = nguoiDung.getPhong().getId();
        // xoa phong khoi map danh sach phong
        danhSachPhong.remove(phongId);
    }

    @OnError
    public void handleError(Throwable t){}

    private void guiCauHoiChoClient(Session phien, ArrayList<CauHoi> danhSachCauHoi) {
        // kiem tra cau hoi hien tai
        System.out.println("wtf");
        GhiChepNguoiDung nguoiDung = (GhiChepNguoiDung) phien.getUserProperties().get("nguoiChoi");
        int phongId = nguoiDung.getPhong().getId();

        ArrayList<Session> phienTrongPhong = danhSachPhong.get(phongId);
        int cauHienTai = (int)phien.getUserProperties().get("cauHienTai") ;
        if (cauHienTai >= 10) {
            System.out.println("Het cau hoi");
            Lenh lenhKetThuc = new Lenh("ketThuc");
            for (Session phienNguoiDung: phienTrongPhong) {
                phienNguoiDung.getAsyncRemote().sendObject(lenhKetThuc);
            }

            // Huy dong ho dem nguoc
            timer.shutdown();

            // Thoat het cac phien
            for (Session phienNguoiDung: phienTrongPhong) {
                try {
                    phienNguoiDung.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            System.out.println("Cau hoi hien tai "+ cauHienTai);
            CauHoi cauHoi = danhSachCauHoi.get(cauHienTai);
            cauHoi.setSoThuTu(cauHienTai);
            cauHoi.setThoiGianTonTai(HangSo.THOI_GIAN);
            
            Lenh lenhGuiCauHoi = new Lenh("guiCauHoi", cauHoi);
            
            System.out.println("Cau hoi moi : "+  danhSachCauHoi.get(cauHienTai));
            cauHienTai += 1;
            // An dap an khoi cau hoi
            lenhGuiCauHoi.getCauHoi().setDapAn(null);

            // Gui lenhGuiCauHoi cho cac phien trong phong
            for (Session phienNguoiDung: phienTrongPhong) {
                phienNguoiDung.getUserProperties().put("cauHienTai", cauHienTai);
                phienNguoiDung.getUserProperties().put("idCauHienTai", lenhGuiCauHoi.getCauHoi().getId());
                LocalDateTime thoiGian = LocalDateTime.now();
                phienNguoiDung.getUserProperties().put("thoiGianGuiCauHoi", thoiGian);
                if (phienNguoiDung.isOpen()) phienNguoiDung.getAsyncRemote().sendObject(lenhGuiCauHoi);
            }
        }

    }

}
