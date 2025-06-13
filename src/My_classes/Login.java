
package My_classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// kiểm tra dữ liệu đăng nhập
public class Login {
   public boolean checkLogin(String username, String password) {
        DB db = new DB();
        Connection con = null;
        boolean isValid = false;

        try {
            con = (Connection) db.con();
            String query = "SELECT * FROM dang_nhap WHERE ten_dang_nhap = ? AND mat_khau = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            isValid = rs.next(); // true nếu có kết quả (tài khoản tồn tại)

            rs.close();
            ps.close();
        } catch (Exception e) {
            System.err.println("Lỗi khi kiểm tra đăng nhập: " + e.getMessage());
        } finally {
            db.offcon(con);
        }

        return isValid;
    }
}
