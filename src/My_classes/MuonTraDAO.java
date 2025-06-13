/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package My_classes;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class MuonTraDAO {
    private DB db = new DB();

    public DefaultTableModel timKiemKhachTheoId(String id) {
        DefaultTableModel model = new DefaultTableModel();
    model.setColumnIdentifiers(new String[]{"ID", "Họ tên", "Email", "SĐT"});

    String sql = "SELECT * FROM khach WHERE id = ?";
    try (Connection conn = new DB().con(); PreparedStatement pst = conn.prepareStatement(sql)) {
        pst.setString(1, id);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            int id_khach = rs.getInt("id");
            String ten = rs.getString("ho_ten");
            String email = rs.getString("email");
            String sdt = rs.getString("so_dien_thoai");

            model.addRow(new Object[]{id_khach, ten, email, sdt});
        }
    } catch (Exception e) {
        System.out.println("Lỗi khi tìm khách: " + e.getMessage());
    }

    return model;
    }

    public DefaultTableModel timKiemSachTheoTen(String tenSach) {
        DefaultTableModel model = new DefaultTableModel();
    model.setColumnIdentifiers(new Object[]{"id sách", "tên sách", "tác giả", "năm xuất bản"});

    try {
        Connection conn = new DB().con();
        String sql = "SELECT s.id, s.ten_sach, tg.ten_tac_gia, s.nam_xuat_ban " +
                     "FROM sach s " +
                     "JOIN tac_gia tg ON s.id_tac_gia = tg.id " + // sửa ở đây
                     "WHERE s.ten_sach LIKE ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, "%" + tenSach + "%");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String ten = rs.getString("ten_sach");
            String tacGia = rs.getString("ten_tac_gia");
            String nam = rs.getString("nam_xuat_ban");

            model.addRow(new Object[]{id, ten, tacGia, nam});
        }

        rs.close();
        stmt.close();
        conn.close();
    } catch (Exception e) {
        System.out.println("Lỗi tìm sách theo tên: " + e.getMessage());
    }

    return model;
    }

    public void muonSach(int idKhach, int idSach) {
        Connection con = db.con();
    String sql = "INSERT INTO muon_tra (id_khach, id_sach, ngay_muon, tinh_trang_tra) " +
                 "VALUES (?, ?, CURDATE(), 'dang_muon')";
    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, idKhach);
        ps.setInt(2, idSach);
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
    db.offcon(con);
    }

    public void traSach(int idKhach, int idSach) {
        Connection con = db.con();
        String sql = "UPDATE muon_tra SET tinh_trang_tra = 'da_tra', ngay_tra = CURDATE() WHERE id_khach = ? AND id_sach = ? AND tinh_trang_tra = 'dang_muon'";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idKhach);
            ps.setInt(2, idSach);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.offcon(con);
    }
}
