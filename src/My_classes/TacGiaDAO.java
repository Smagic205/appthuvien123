
package My_classes;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
public class TacGiaDAO {
    public void addAuthor(TacGia TG) {
    String insert = "INSERT INTO tac_gia (ten_tac_gia, ngay_sinh, quoc_tich) VALUES (?, ?, ?)";
    DB db = new DB(); // tạo đối tượng DB để kết nối
    Connection c = null;
    PreparedStatement ps = null;

    try {
        c = (Connection) db.con(); // lấy kết nối
        ps = c.prepareStatement(insert);

        // Gán giá trị từ đối tượng TacGia
        ps.setString(1, TG.getName());
        ps.setDate(2, Date.valueOf(TG.getBirthday())); // chuyển LocalDate -> java.sql.Date
        ps.setString(3, TG.getNationality());

        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Thêm tác giả thành công.");
        } else {
            System.out.println("Không thể thêm tác giả.");
        }

    } catch (Exception e) {
        System.out.println("Lỗi khi thêm tác giả:");
        e.printStackTrace();
    } finally {
        // Đóng kết nối và PreparedStatement
        try {
            if (ps != null) ps.close();
            if (c != null) c.close();
        } catch (Exception e) {
            System.out.println("Lỗi khi đóng kết nối:");
            e.printStackTrace();
        }
    }
}
    // sửa tác giả
    public void updateAurhor(TacGia TG){
        String edit = "UPDATE `tac_gia` SET `ten_tac_gia`=?,`ngay_sinh`=?,`quoc_tich`=? WHERE `id`=?";
        DB db = new DB(); // tạo đối tượng DB để kết nối
        Connection c = null;
        PreparedStatement ps = null;
        try{
            c = db.con(); // Lấy kết nối đến CSDL
            ps = c.prepareStatement(edit);
            
            ps.setString(1, TG.getName());                            // tên tác giả mới
            ps.setDate(2, Date.valueOf(TG.getBirthday()));            // ngày sinh (chuyển từ LocalDate)
            ps.setString(3, TG.getNationality());                     // quốc tịch mới
            ps.setInt(4, TG.getId());
            
            int rows = ps.executeUpdate(); // Thực thi UPDATE
            if (rows > 0) {
                System.out.println("Cập nhật tác giả thành công.");
            } else {
                System.out.println("Không tìm thấy tác giả để cập nhật.");
            }
        }
        catch(Exception e){
            System.out.println("Lỗi khi cập nhật tác giả:");
            e.printStackTrace();
        }
        finally{
            try{
                if (ps != null) ps.close();
                if (c != null) c.close();
            } catch (Exception e) {
                System.out.println("Lỗi khi đóng kết nối:");
                e.printStackTrace();
            }
        }
    }
    // xóa tác giả
    public void deleteAuthor(int id){
        String sql = "DELETE FROM `tac_gia` WHERE `id` = ?";
        DB db = new DB(); // tạo đối tượng DB để kết nối
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = db.con(); // kết nối tới CSDL
            ps = c.prepareStatement(sql);

            // Gán giá trị id vào câu lệnh SQL
            ps.setInt(1, id);

            int rows = ps.executeUpdate(); // thực hiện câu lệnh DELETE

            if (rows > 0) {
                System.out.println("Xóa tác giả thành công.");
            } else {
                System.out.println("Không tìm thấy tác giả để xóa.");
            }

        } catch (Exception e) {
            System.out.println("Lỗi khi xóa tác giả:");
            e.printStackTrace();
        } finally {
            // đóng tài nguyên
            try {
                if (ps != null) ps.close();
                if (c != null) c.close();
            } catch (Exception e) {
            System.out.println("Lỗi khi đóng kết nối:");
            e.printStackTrace();
            }
        } 
    }
    // danh sách tác giả
    public ArrayList<TacGia> listAuthur(){
        ArrayList<TacGia> list = new ArrayList<>();
        DB db = new DB();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql ="SELECT * FROM `tac_gia`";
        try{
            c = db.con(); // kết nối CSDL
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            
        // Duyệt từng dòng dữ liệu trong kết quả
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("ten_tac_gia");
            String birthday = rs.getDate("ngay_sinh").toString();
            String nationality = rs.getString("quoc_tich");

            // Tạo đối tượng TacGia và thêm vào danh sách
            TacGia tg = new TacGia(id, name, birthday, nationality);
            list.add(tg);
        }
        }
        catch(Exception e){
            System.out.println("Lỗi khi lấy danh sách tác giả:");
            e.printStackTrace();
        }
        finally{
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (c != null) c.close();
            } catch (Exception e) {
                System.out.println("Lỗi khi đóng kết nối:");
                e.printStackTrace();
            } 
        }
        return list;
    }
    
    
}

