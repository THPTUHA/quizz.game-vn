package lop;

public class Loi {
    private int trangThai;
    private String loiNhan;

    public Loi() {
    }


    public Loi(int trangThai, String loiNhan) {
        this.trangThai = trangThai;
        this.loiNhan = loiNhan;
    }
    

    public int getTrangThai() {
        return this.trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getLoiNhan() {
        return this.loiNhan;
    }

    public void setLoiNhan(String loiNhan) {
        this.loiNhan = loiNhan;
    }

}
