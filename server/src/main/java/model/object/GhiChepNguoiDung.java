package model.object;

public class GhiChepNguoiDung {
    NguoiDung nguoiDung;
    Phong phong;
    int diem;
    int trangThai;

    public GhiChepNguoiDung(){

    }
    
    public GhiChepNguoiDung(NguoiDung nguoiDung, Phong phong, int diem, int trangThai) {
        this.nguoiDung = nguoiDung;
        this.phong = phong;
        this.diem = diem;
        this.trangThai = trangThai;
    }


    public NguoiDung getNguoiDung() {
        return this.nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public Phong getPhong() {
        return this.phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }

    public int getDiem() {
        return this.diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    public int getTrangThai() {
        return this.trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "{" +
            " nguoiDung='" + getNguoiDung() + "'" +
            ", phong='" + getPhong() + "'" +
            ", diem='" + getDiem() + "'" +
            ", trangThai='" + getTrangThai() + "'" +
            "}";
    }
}

