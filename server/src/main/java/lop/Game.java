package lop;

import java.util.ArrayList;

import model.object.CauHoi;
import model.object.NguoiDung;

public class Game {
    private ArrayList<CauHoi> danhSachCauHoi;
    private ArrayList<NguoiDung> danhSachNguoiDung;

    public Game() {
    }


    public Game(ArrayList<CauHoi> danhSachCauHoi, ArrayList<NguoiDung> danhSachNguoiDung) {
        this.danhSachCauHoi = danhSachCauHoi;
        this.danhSachNguoiDung = danhSachNguoiDung;
    }


    public ArrayList<CauHoi> getDanhSachCauHoi() {
        return this.danhSachCauHoi;
    }

    public void setDanhSachCauHoi(ArrayList<CauHoi> danhSachCauHoi) {
        this.danhSachCauHoi = danhSachCauHoi;
    }

    public ArrayList<NguoiDung> getDanhSachNguoiDung() {
        return this.danhSachNguoiDung;
    }

    public void setDanhSachNguoiDung(ArrayList<NguoiDung> danhSachNguoiDung) {
        this.danhSachNguoiDung = danhSachNguoiDung;
    }

}
