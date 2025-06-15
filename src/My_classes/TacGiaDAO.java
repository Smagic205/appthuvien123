
package My_classes;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
public class TacGiaDAO {
     private DB db = new DB();

    // Tìm tác giả theo ID
    public TacGia timTheoId(int id) {
        TacGia tg = null;
        String sql = "SELECT * FROM tac_gia WHERE id = ?";
        try (Connection conn = db.con(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tg = new TacGia(
                    rs.getInt("id"),
                    rs.getString("ten_tac_gia"),
                    rs.getString("ngay_sinh"),
                    rs.getString("quoc_tich")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tg;
    }

    // Hiển thị dữ liệu lên bảng
    public void hienThiTacGia(JTable table, TacGia tg) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // clear
        if (tg != null) {
            model.addRow(new Object[]{tg.getId(), tg.getTenTacGia(), tg.getNgaySinh(), tg.getQuocTich()});
        }
    }

    // Thêm tác giả
    public boolean themTacGia(TacGia tg) {
        String sql = "INSERT INTO tac_gia (ten_tac_gia, ngay_sinh, quoc_tich) VALUES (?, ?, ?)";
        try (Connection conn = db.con(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tg.getTenTacGia());
            ps.setString(2, tg.getNgaySinh());
            ps.setString(3, tg.getQuocTich());
            int rowsAffected = ps.executeUpdate();
           if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Thêm tác giả thành công!");
            return true;
        }
        } catch (Exception e) {
            e.printStackTrace();
             JOptionPane.showMessageDialog(null, "Thêm tác giả thất bại: " + e.getMessage());
        }
        return false;
    }

    // Sửa tác giả
    public boolean suaTacGia(TacGia tg) {
        String sql = "UPDATE tac_gia SET ten_tac_gia=?, ngay_sinh=?, quoc_tich=? WHERE id=?";
        try (Connection conn = db.con(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tg.getTenTacGia());
            ps.setString(2, tg.getNgaySinh());
            ps.setString(3, tg.getQuocTich());
            ps.setInt(4, tg.getId());
            int rowsAffected = ps.executeUpdate();
           if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "sửa tác giả thành công!");
            return true;
        }
        } catch (Exception e) {
            e.printStackTrace();
             JOptionPane.showMessageDialog(null, "sửa tác giả thất bại: " + e.getMessage());
        }
        return false;
    }

    // Xóa tác giả
    public boolean xoaTacGia(int id) {
        String sql = "DELETE FROM tac_gia WHERE id = ?";
        try (Connection conn = db.con(); 
          PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
           int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Xóa tác giả thành công!");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Không tìm thấy tác giả để xóa.");
        }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Xóa tác giả thất bại vì tác giả vẫn còn sách " );
        }
        return false;
    }
}
