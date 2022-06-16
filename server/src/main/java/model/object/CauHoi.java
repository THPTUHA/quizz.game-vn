package model.object;

import lop.DapAn;
import lop.GoiY;

public class CauHoi {
    private int id;
    private String noiDung;
    private GoiY goiY;
    private DapAn dapAn;
    private NguoiDung nguoiDung;
    private int capDo;
    private int diem;
    private int loai;


    public CauHoi() {
    }


    public CauHoi(int id, String noiDung, GoiY goiY, DapAn dapAn, NguoiDung nguoiDung, int capDo, int diem, int loai) {
        this.id = id;
        this.noiDung = noiDung;
        this.goiY = goiY;
        this.dapAn = dapAn;
        this.nguoiDung = nguoiDung;
        this.capDo = capDo;
        this.diem = diem;
        this.loai = loai;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoiDung() {
        return this.noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public GoiY getGoiY() {
        return this.goiY;
    }

    public void setGoiY(GoiY goiY) {
        this.goiY = goiY;
    }

    public DapAn getDapAn() {
        return this.dapAn;
    }

    public void setDapAn(DapAn dapAn) {
        this.dapAn = dapAn;
    }

    public NguoiDung getNguoiDung() {
        return this.nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public int getCapDo() {
        return this.capDo;
    }

    public void setCapDo(int capDo) {
        this.capDo = capDo;
    }

    public int getDiem() {
        return this.diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    public int getLoai() {
        return this.loai;
    }

    public void setLoai(int loai) {
        this.loai = loai;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", noiDung='" + getNoiDung() + "'" +
            ", goiY='" + getGoiY() + "'" +
            ", dapAn='" + getDapAn() + "'" +
            ", nguoiDung='" + getNguoiDung() + "'" +
            ", capDo='" + getCapDo() + "'" +
            ", diem='" + getDiem() + "'" +
            ", loai='" + getLoai() + "'" +
            "}";
    }


}
