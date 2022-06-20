package lop;

import model.object.CauHoi;

public class Lenh {
    private String lenh;
    private int phongId;
    private CauHoi cauHoi;
    private Loi loi;

    public Lenh() {
    }

    public Lenh(String lenh, int phongId, CauHoi cauHoi, Loi loi) {
        this.lenh = lenh;
        this.phongId = phongId;
        this.cauHoi = cauHoi;
        this.loi = loi;
    }

    public Lenh(String lenh, CauHoi cauHoi) {
        this.lenh = lenh;
        this.cauHoi = cauHoi;
    }

    public Lenh(String lenh) {
        this.lenh = lenh;
    }



    public String getLenh() {
        return this.lenh;
    }

    public void setLenh(String lenh) {
        this.lenh = lenh;
    }

    public int getPhongId() {
        return this.phongId;
    }

    public void setPhongId(int phongId) {
        this.phongId = phongId;
    }

    public CauHoi getCauHoi() {
        return this.cauHoi;
    }

    public void setCauHoi(CauHoi cauHoi) {
        this.cauHoi = cauHoi;
    }

    public Loi getLoi() {
        return this.loi;
    }

    public void setLoi(Loi loi) {
        this.loi = loi;
    }
    

    @Override
    public String toString() {
        return "{" +
            " lenh='" + getLenh() + "'" +
            ", phongId='" + getPhongId() + "'" +
            ", cauHoi='" + getCauHoi() + "'" +
            "}";
    }

}
