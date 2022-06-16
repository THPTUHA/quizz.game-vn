package lop;

import java.util.ArrayList;

public class DapAn {
    private ArrayList<LuaChon> danhSachLuaChon;


    public DapAn() {
    }


    public DapAn(ArrayList<LuaChon> danhSachLuaChon) {
        this.danhSachLuaChon = danhSachLuaChon;
    }



    public ArrayList<LuaChon> getDanhSachLuaChon() {
        return this.danhSachLuaChon;
    }

    public void setDanhSachLuaChon(ArrayList<LuaChon> danhSachLuaChon) {
        this.danhSachLuaChon = danhSachLuaChon;
    }
   

    
}
