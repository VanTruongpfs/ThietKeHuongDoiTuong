
package model;

public class SanPham {
    private String maSP;
    private String tenSP;
    private double donGia;
    private int tonKho;
    private String xuatXu;

    public SanPham(String maSP, String tenSP, double donGia, int tonKho, String xuatXu) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.donGia = donGia;
        this.tonKho = tonKho;
        this.xuatXu = xuatXu;
    }
    public String getMaSP() { return maSP; }
    public String getTenSP() { return tenSP; }
    public double getDonGia() { return donGia; }
    public int getTonKho() { return tonKho; }
    public String getXuatXu() { return xuatXu; }
    
    
    public void capNhatSL(int sl) {
    	this.tonKho+=sl;
    }
    
   
}
