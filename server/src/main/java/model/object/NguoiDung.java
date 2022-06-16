package model.object;

public class NguoiDung {
    private int id = 0;
    private String username;
    private String email;
    private String matKhau;
    private String quyen ;
    private String avatar;
    private String moTa;
    private int gioiTinh;
    private long dangNhapLanCuoi;
    private long kinhNghiem;
    private long vang;
    private int trangThai;


    public NguoiDung() {
    }



    public NguoiDung(int id, String username, String email, String matKhau, String quyen, String avatar, String moTa, int gioiTinh, long dangNhapLanCuoi, long kinhNghiem, long vang, int trangThai) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.matKhau = matKhau;
        this.quyen = quyen;
        this.avatar = avatar;
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

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
            ", username='" + getUsername() + "'" +
            ", email='" + getEmail() + "'" +
            ", matKhau='" + getMatKhau() + "'" +
            ", quyen='" + getQuyen() + "'" +
            ", avatar='" + getAvatar() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", gioiTinh='" + getGioiTinh() + "'" +
            ", dangNhapLanCuoi='" + getDangNhapLanCuoi() + "'" +
            ", kinhNghiem='" + getKinhNghiem() + "'" +
            ", vang='" + getVang() + "'" +
            ", trangThai='" + getTrangThai() + "'" +
            "}";
    }
    
}
