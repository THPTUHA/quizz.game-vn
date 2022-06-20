package model.object;

public class Phong {
    private int id;
    private NguoiDung nguoiDung;
    private int trangThai;
    private int soLuongNguoiDung;
    private String anhDaiDien;

    public Phong() {
    }


    public Phong(int id, NguoiDung nguoiDung, int trangThai, int soLuongNguoiDung, String anhDaiDien) {
        this.id = id;
        this.nguoiDung = nguoiDung;
        this.trangThai = trangThai;
        this.soLuongNguoiDung = soLuongNguoiDung;
        this.anhDaiDien = anhDaiDien;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NguoiDung getNguoiDung() {
        return this.nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public int getTrangThai() {
        return this.trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getSoLuongNguoiDung() {
        return this.soLuongNguoiDung;
    }

    public void setSoLuongNguoiDung(int soLuongNguoiDung) {
        this.soLuongNguoiDung = soLuongNguoiDung;
    }

    public String getAnhDaiDien() {
        return this.anhDaiDien;
    }

    public void setAnhDaiDien(String anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nguoiDung='" + getNguoiDung() + "'" +
            ", trangThai='" + getTrangThai() + "'" +
            ", soLuongNguoiDung='" + getSoLuongNguoiDung() + "'" +
            ", anhDaiDien='" + getAnhDaiDien() + "'" +
            "}";
    }

}
