
package My_classes;


import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class NguoiDungDAO {
    private DB db = new DB();
    
    // Tìm kiếm người dùng theo ID
    public DefaultTableModel timKiemNguoiDungTheoID(int id) {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"ID", "Họ tên", "Email", "Số điện thoại"});

        try (Connection conn = db.con()) {
            String sql = "SELECT * FROM khach WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("ho_ten"),
                    rs.getString("email"),
                    rs.getString("so_dien_thoai")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    // Thêm người dùng mới
    public void themNguoiDung(String ten, String email, String sdt) {
        try (Connection conn = db.con()) {
            String sql = "INSERT INTO khach(ho_ten, email, so_dien_thoai) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ten);
            ps.setString(2, email);
            ps.setString(3, sdt);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm người dùng thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Thêm người dùng thất bại: " + e.getMessage());
        }
    }

    // Sửa thông tin người dùng
    public void suaNguoiDung(int id, String ten, String email, String sdt) {
        try (Connection conn = db.con()) {
            String sql = "UPDATE khach SET ho_ten=?, email=?, so_dien_thoai=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ten);
            ps.setString(2, email);
            ps.setString(3, sdt);
            ps.setInt(4, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "sửa người dùng thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "sửa người dùng thất bại: " + e.getMessage());
            
        }
    }

    // Xóa người dùng
    public void xoaNguoiDung(int id) {
        try (Connection conn = db.con()) {
            String sql = "DELETE FROM khach WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
