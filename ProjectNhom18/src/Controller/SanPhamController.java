package Controller;
import model.SanPham;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SanPhamController {
    public List<SanPham> hienThiTatCaSanPham() {
    	List<SanPham> list = new ArrayList<>();
        String sql = "SELECT * FROM SanPham";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                SanPham sp = new SanPham(
                    rs.getString("maSP"),
                    rs.getString("tenSP"),
                    rs.getDouble("donGia"),
                    rs.getInt("tonKho"),
                    rs.getString("xuatXu")
                );
                list.add(sp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
        
    }
}
