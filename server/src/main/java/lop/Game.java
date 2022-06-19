package lop;

import java.util.ArrayList;

import model.object.CauHoi;

public class Game {
    private ArrayList<CauHoi> danhSachCauHoi;

    public Game() {
    }


    public Game(ArrayList<CauHoi> danhSachCauHoi) {
        this.danhSachCauHoi = danhSachCauHoi;
    }


    public ArrayList<CauHoi> getDanhSachCauHoi() {
        return this.danhSachCauHoi;
    }

    public void setQuestions(ArrayList<CauHoi> danhSachCauHoi) {
        this.danhSachCauHoi = danhSachCauHoi;
    }

}
