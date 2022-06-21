package lop;

import model.object.CauHoi;
import model.object.GhiChepNguoiDung;

public class Lenh {
    private String lenh;
    private int phongId;
    private CauHoi cauHoi;
    private Loi loi;
    private GhiChepNguoiDung chuPhong;
    private GhiChepNguoiDung khach;
    private int phienGuiDapAn;

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

    public GhiChepNguoiDung getChuPhong() {
        return chuPhong;
    }

    /**
     * @param chuPhong the chuPhong to set
     */
    public void setChuPhong(GhiChepNguoiDung chuPhong) {
        this.chuPhong = chuPhong;
    }

    /**
     * @return the khach
     */
    public GhiChepNguoiDung getKhach() {
        return khach;
    }

    /**
     * @param khach the khach to set
     */
    public void setKhach(GhiChepNguoiDung khach) {
        this.khach = khach;
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
    
    /**
     * @return the phienGuiDapAn
     */
    public int getPhienGuiDapAn() {
        return phienGuiDapAn;
    }

    /**
     * @param phienGuiDapAn the phienGuiDapAn to set
     */
    public void setPhienGuiDapAn(int phienGuiDapAn) {
        this.phienGuiDapAn = phienGuiDapAn;
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
