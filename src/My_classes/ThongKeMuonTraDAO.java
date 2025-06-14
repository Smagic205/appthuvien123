
package My_classes;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class ThongKeMuonTraDAO {
    private DB db = new DB();

    //hàm lấy danh sách mượn trả 
    public DefaultTableModel layDanhSachMuonTra() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Mã mượn", "Tên người mượn", "Tên sách", "Tình trạng"});

       String sql = "SELECT mt.mamuon, kh.ho_ten, s.ten_sach, mt.tinh_trang_tra " +
             "FROM muon_tra mt " +
             "JOIN khach kh ON mt.id_khach = kh.id " +
             "JOIN sach s ON mt.id_sach = s.id " +
             "ORDER BY mt.mamuon ASC";   // sắp xếp theo mã mượn

        try (Connection conn = db.con();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                int mamuon = rs.getInt("mamuon");
                String tenKhach = rs.getString("ho_ten");
                String tenSach = rs.getString("ten_sach").trim();
                String tinhTrang = rs.getString("tinh_trang_tra");

                model.addRow(new Object[]{mamuon, tenKhach, tenSach, tinhTrang});
            }

        } catch (Exception e) {
            System.out.println("Lỗi lấy danh sách mượn trả: " + e.getMessage());
        }

        return model;
    }
    //hàm cập nhập danh sách (dùng cho nút cập nhập)
    public DefaultTableModel capnhapDanhSachMuonTra() {
    DefaultTableModel model = new DefaultTableModel(
        new Object[]{"Mã mượn", "Tên người mượn", "Tên sách", "Tình trạng"}, 0);

    Connection con = db.con();
    String sql = "SELECT mt.mamuon, k.ho_ten, s.ten_sach, mt.tinh_trang_tra " +
                 "FROM muon_tra mt " +
                 "JOIN khach k ON mt.id_khach = k.id " +
                 "JOIN sach s ON mt.id_sach = s.id " +
                 "ORDER BY mt.mamuon ASC";  // sắp xếp theo mã mượn

    try (PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            Object[] row = {
                rs.getInt("mamuon"),
                rs.getString("ho_ten"),
                rs.getString("ten_sach"),
                rs.getString("tinh_trang_tra")
            };
            model.addRow(row);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    db.offcon(con);
    return model;
}
}
