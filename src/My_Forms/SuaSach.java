
package My_Forms;

import My_Forms.Quanlysach;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import My_classes.DB;
import javax.swing.JOptionPane;
public class SuaSach extends javax.swing.JFrame {

    public SuaSach() {
        initComponents();
    }

   public void suaSachTrongCSDL(int idSach, String tenSach, int namXuatBan, String moTa, String tenTheLoai, int idTacGia) {
    Connection conn = null;
    PreparedStatement psCheck = null;
    PreparedStatement psInsertTL = null;
    PreparedStatement psUpdate = null;
    ResultSet rs = null;

    try {
        DB db = new DB();
        conn = db.con();

        int idTheLoai = -1;

        // 1. Kiểm tra thể loại đã tồn tại chưa
        String sqlCheck = "SELECT id FROM the_loai WHERE ten_the_loai = ?";
        psCheck = conn.prepareStatement(sqlCheck);
        psCheck.setString(1, tenTheLoai);
        rs = psCheck.executeQuery();

        if (rs.next()) {
            idTheLoai = rs.getInt("id");
        } else {
            // Nếu chưa có, thêm mới
            String sqlInsertTL = "INSERT INTO the_loai (ten_the_loai) VALUES (?)";
            psInsertTL = conn.prepareStatement(sqlInsertTL, Statement.RETURN_GENERATED_KEYS);
            psInsertTL.setString(1, tenTheLoai);
            psInsertTL.executeUpdate();

            ResultSet rsKey = psInsertTL.getGeneratedKeys();
            if (rsKey.next()) {
                idTheLoai = rsKey.getInt(1);
            }
            rsKey.close();
        }

        // 2. Cập nhật sách
        String sqlUpdate = "UPDATE sach SET ten_sach = ?, id_tac_gia = ?, id_the_loai = ?, nam_xuat_ban = ?, mo_ta = ? WHERE id = ?";
        psUpdate = conn.prepareStatement(sqlUpdate);
        psUpdate.setString(1, tenSach);
        psUpdate.setInt(2, idTacGia);
        psUpdate.setInt(3, idTheLoai);
        psUpdate.setInt(4, namXuatBan);
        psUpdate.setString(5, moTa);
        psUpdate.setInt(6, idSach);

        int rows = psUpdate.executeUpdate();
        if (rows > 0) {
            System.out.println("Cập nhật sách thành công.");
        } else {
            System.out.println("Không tìm thấy sách để cập nhật.");
        }

    } catch (Exception e) {
        System.out.println("Lỗi khi cập nhật sách:");
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (psCheck != null) psCheck.close();
            if (psInsertTL != null) psInsertTL.close();
            if (psUpdate != null) psUpdate.close();
            if (conn != null) conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
   
   private int idSachDangSua;
   private Runnable callbackReload; // lớp chứa bảng bandanhsach

public SuaSach(int idSach, int namXuatBan, String tenSach, String moTa, String theLoai, int idTacGia, Runnable callbackReload) {
    this.idSachDangSua = idSach;
    this.callbackReload = callbackReload; // gán hàm cần gọi lại
    initComponents();
    this.setLocationRelativeTo(null);

    // Gán dữ liệu vào các trường nhập
    jTextField1.setText(String.valueOf(namXuatBan));
    jTextField2.setText(tenSach);
    jTextField3.setText(moTa);
    jTextField4.setText(theLoai);
    jTextField5.setText(String.valueOf(idTacGia));
}
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText(" năm xuất bản");

        jTextField1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("tên sách");

        jTextField2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("link hình ảnh ");

        jTextField3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("thể loại");

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("tác giả");

        jTextField4.setBackground(new java.awt.Color(204, 204, 204));

        jTextField5.setBackground(new java.awt.Color(204, 204, 204));

        jButton1.setText("Sửa");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Sửa Sách");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(25, 25, 25)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2)
                            .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField4)
                            .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))))
                .addGap(49, 49, 49))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(127, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addComponent(jButton1)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         try {
        String tenSach = jTextField2.getText().trim();
        int namXuatBan = Integer.parseInt(jTextField1.getText().trim());
        String moTa = jTextField3.getText().trim();
        String theLoai = jTextField4.getText().trim();
        int idTacGia = Integer.parseInt(jTextField5.getText().trim());

        if (tenSach.isEmpty() || theLoai.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ tên sách và thể loại!", "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Kết nối CSDL
        DB db = new DB();
        Connection conn = db.con();

        int idTheLoai = -1;

        // 1. Kiểm tra thể loại đã tồn tại chưa
        String sqlCheck = "SELECT id FROM the_loai WHERE ten_the_loai = ?";
        PreparedStatement psCheck = conn.prepareStatement(sqlCheck);
        psCheck.setString(1, theLoai);
        ResultSet rs = psCheck.executeQuery();

        if (rs.next()) {
            idTheLoai = rs.getInt("id");
        } else {
            // Thêm mới thể loại nếu chưa có
            String sqlInsertTL = "INSERT INTO the_loai (ten_the_loai) VALUES (?)";
            PreparedStatement psInsertTL = conn.prepareStatement(sqlInsertTL, Statement.RETURN_GENERATED_KEYS);
            psInsertTL.setString(1, theLoai);
            psInsertTL.executeUpdate();
            ResultSet rsKey = psInsertTL.getGeneratedKeys();
            if (rsKey.next()) {
                idTheLoai = rsKey.getInt(1);
            }
            rsKey.close();
            psInsertTL.close();
        }

        rs.close();
        psCheck.close();

        // 2. Cập nhật sách
        String sqlUpdate = "UPDATE sach SET ten_sach = ?, id_tac_gia = ?, id_the_loai = ?, nam_xuat_ban = ?, mo_ta = ? WHERE id = ?";
        PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate);
        psUpdate.setString(1, tenSach);
        psUpdate.setInt(2, idTacGia);
        psUpdate.setInt(3, idTheLoai);
        psUpdate.setInt(4, namXuatBan);
        psUpdate.setString(5, moTa.isEmpty() ? null : moTa);
        psUpdate.setInt(6, idSachDangSua);

        int rows = psUpdate.executeUpdate();
        if (rows > 0) {
            JOptionPane.showMessageDialog(this, "Cập nhật sách thành công!");

            if (callbackReload != null) {
                callbackReload.run(); // gọi hàm loadDanhSach() nếu có truyền
            }

            this.dispose(); // đóng cửa sổ sửa
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sách để cập nhật!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

        psUpdate.close();
        conn.close();

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Năm xuất bản và ID tác giả phải là số!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật sách: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SuaSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SuaSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SuaSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SuaSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SuaSach().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
