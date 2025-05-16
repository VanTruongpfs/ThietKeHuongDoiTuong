package model;

import java.util.List;

public abstract class KhachHang implements Observer{
    protected String maKH;
    protected String tenKH;
    protected String sdt;
    protected String diachi;
    protected HoaDon hoadon;
     public KhachHang(String maKH, String tenKH, String sdt, String diachi,HoaDon hoadon) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.sdt = sdt;
        this.diachi = diachi;
        this.hoadon = hoadon;
    }

}
