package lop;

public class LuaChon {
    private int id;
    private String giaiThich;
    private String noiDung;


    public LuaChon(int id, String giaiThich, String noiDung) {
        this.id = id;
        this.giaiThich = giaiThich;
        this.noiDung = noiDung;
    }



    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGiaiThich() {
        return this.giaiThich;
    }

    public void setGiaiThich(String giaiThich) {
        this.giaiThich = giaiThich;
    }

    public String getNoiDung() {
        return this.noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }
    

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", giaiThich='" + getGiaiThich() + "'" +
            ", noiDung='" + getNoiDung() + "'" +
            "}";
    }


}
