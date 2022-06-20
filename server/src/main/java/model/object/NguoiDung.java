package model.object;

public class NguoiDung {
    private int id = 0;
    private String ten;
    private String matKhau;
    private String quyen ;
    private String anhDaiDien;
    private String moTa;
    private int gioiTinh;
    private long dangNhapLanCuoi;
    private long kinhNghiem;
    private long vang;
    private int trangThai;


    public NguoiDung() {
    }



    public NguoiDung(int id, String ten, String matKhau, String quyen, String anhDaiDien, String moTa, int gioiTinh, long dangNhapLanCuoi, long kinhNghiem, long vang, int trangThai) {
        this.id = id;
        this.ten = ten;
        this.matKhau = matKhau;
        this.quyen = quyen;
        this.anhDaiDien = anhDaiDien;
        this.moTa = moTa;
        this.gioiTinh = gioiTinh;
        this.dangNhapLanCuoi = dangNhapLanCuoi;
        this.kinhNghiem = kinhNghiem;
        this.vang = vang;
        this.trangThai = trangThai;
    }
    
    public boolean la(String quyen){
        return this.quyen != null && this.quyen.equals(quyen);
    }
    

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return this.ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMatKhau() {
        return this.matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getQuyen() {
        return this.quyen;
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }

    public String getAnhDaiDien() {
        return this.anhDaiDien;
    }

    public void setAnhDaiDien(String anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
    }

    public String getMoTa() {
        return this.moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getGioiTinh() {
        return this.gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public long getDangNhapLanCuoi() {
        return this.dangNhapLanCuoi;
    }

    public void setDangNhapLanCuoi(long dangNhapLanCuoi) {
        this.dangNhapLanCuoi = dangNhapLanCuoi;
    }

    public long getKinhNghiem() {
        return this.kinhNghiem;
    }

    public void setKinhNghiem(long kinhNghiem) {
        this.kinhNghiem = kinhNghiem;
    }

    public long getVang() {
        return this.vang;
    }

    public void setVang(long vang) {
        this.vang = vang;
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
            " id='" + getId() + "'" +
            ", ten='" + getTen() + "'" +
            ", matKhau='" + getMatKhau() + "'" +
            ", quyen='" + getQuyen() + "'" +
            ", anhDaiDien='" + getAnhDaiDien() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", gioiTinh='" + getGioiTinh() + "'" +
            ", dangNhapLanCuoi='" + getDangNhapLanCuoi() + "'" +
            ", kinhNghiem='" + getKinhNghiem() + "'" +
            ", vang='" + getVang() + "'" +
            ", trangThai='" + getTrangThai() + "'" +
            "}";
    }
    
}
