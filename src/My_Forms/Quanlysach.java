
package My_Forms;

import My_classes.DB;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Quanlysach extends javax.swing.JPanel {

    private DB db = new DB();
    
    public Quanlysach() {
        initComponents();
         loadDanhSachSach(bandanhsach);
        loadTheLoaiVaoComboBox(Comboboxtheloai);
        //nút lọc
        jButton2.addActionListener(e -> {
            String tenTheLoai = (String) Comboboxtheloai.getSelectedItem();

            // Bỏ qua nếu chọn mục mặc định
            if (tenTheLoai == null || tenTheLoai.equals("-- Chọn thể loại --")) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn thể loại để lọc!");
                return;
            }

            Connection conn = null;
            PreparedStatement pst = null;
            ResultSet rs = null;

            try {
                conn = db.con();

                // 1. Truy vấn ID của thể loại dựa trên tên
                String sqlId = "SELECT id FROM the_loai WHERE ten_the_loai = ?";
                pst = conn.prepareStatement(sqlId);
                pst.setString(1, tenTheLoai);
                rs = pst.executeQuery();
                // kiểm tra xem thể loại có tồn tại trong kết quả truy vấn không
                int theLoaiId = -1;
                if (rs.next()) {
                    theLoaiId = rs.getInt("id");
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy thể loại!");
                    return;
                }

                // Đóng lại để chuẩn bị truy vấn sách
                rs.close();
                pst.close();

                // 2. Truy vấn sách theo ID thể loại
                String sqlSach = """
                    SELECT s.id, s.ten_sach, s.nam_xuat_ban, tg.ten_tac_gia
                    FROM sach s
                    JOIN tac_gia tg ON s.id_tac_gia = tg.id
                    WHERE s.id_the_loai = ?
                """;

                pst = conn.prepareStatement(sqlSach);
                pst.setInt(1, theLoaiId);
                rs = pst.executeQuery();

                DefaultTableModel model = (DefaultTableModel) bandanhsach.getModel();
                model.setRowCount(0); // Xóa dữ liệu cũ

                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("ten_sach"),
                        rs.getString("nam_xuat_ban"),
                        rs.getString("ten_tac_gia")
                    });
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi lọc sách!");
            } finally {
                //đảm bảo đóng tài nguyên-dù có lỗi hay không vẫn sẽ được thực hiện
                try { if (rs != null) rs.close(); } catch (Exception ex) {}
                try { if (pst != null) pst.close(); } catch (Exception ex) {}
                try { if (conn != null) conn.close(); } catch (Exception ex) {}
            }
        });

            //nút tìm kiếm
        buttontimkiem.addActionListener(e -> {
            String inputMaSach = TextFieldmasach.getText().trim();
            String inputTenSach = TextFieldtensach.getText().trim();

            Connection conn = null;
            PreparedStatement pst = null;
            ResultSet rs = null;

            try {
                conn = db.con();

                StringBuilder sql = new StringBuilder("SELECT id, ten_sach, nam_xuat_ban FROM sach WHERE 1=1");
                List<Object> params = new ArrayList<>();
                    //Nối điều kiện theo Mã sách
                if (!inputMaSach.isEmpty()) {
                    sql.append(" AND id = ?");
                    params.add(Integer.parseInt(inputMaSach)); //  cần try-catch nếu người dùng nhập sai kiểu
                }
                    //Nối điều kiện theo Tên sách
                if (!inputTenSach.isEmpty()) {
                    sql.append(" AND ten_sach LIKE ?");
                    params.add("%" + inputTenSach + "%");
                }

                pst = conn.prepareStatement(sql.toString());

                // Gán vị trí và giá trị vào dấu ?
                for (int i = 0; i < params.size(); i++) {
                    pst.setObject(i + 1, params.get(i));
                }

                rs = pst.executeQuery();

                // Hiển thị kết quả vào bảng bangtimkiem
                DefaultTableModel model = (DefaultTableModel) bangtimkiem.getModel();
                model.setRowCount(0); // Xóa dòng cũ

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String tenSach = rs.getString("ten_sach");
                    String namXB = rs.getString("nam_xuat_ban");

                    model.addRow(new Object[]{id, tenSach, namXB});
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Mã sách phải là số nguyên!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } finally {
                try { if (rs != null) rs.close(); } catch (Exception ex) {}
                try { if (pst != null) pst.close(); } catch (Exception ex) {}
                try { if (conn != null) conn.close(); } catch (Exception ex) {}
            }
        });
        
        //hiển thị hình ảnh khi nhấn vào bảng tìm kiếm
        bangtimkiem.addMouseListener(new MouseAdapter() {
            @Override
            
            public void mouseClicked(MouseEvent e) {
                int selectedRow = bangtimkiem.getSelectedRow();
                if (selectedRow == -1) return; // Không có dòng nào được chọn

                // Lấy ID sách từ dòng được chọn (giả sử ID là cột đầu tiên)
                int maSach = (int) bangtimkiem.getValueAt(selectedRow, 0);

                // Truy vấn mô tả (link ảnh) từ CSDL
                Connection conn = null;
                PreparedStatement pst = null;
                ResultSet rs = null;

                try {
                    conn = db.con();
                    String sql = "SELECT mo_ta FROM sach WHERE id = ?";
                    pst = conn.prepareStatement(sql);
                    pst.setInt(1, maSach);
                    rs = pst.executeQuery();

                    if (rs.next()) {
                        String imagePath = rs.getString("mo_ta");

                        // Load ảnh vào JLabel
                        ImageIcon icon = new ImageIcon(imagePath);

                        // thay đổi kích thước ảnh cho vừa JLabel
                        Image img = icon.getImage().getScaledInstance(
                                labelhinhanh.getWidth(), 
                                labelhinhanh.getHeight(), 
                                Image.SCALE_SMOOTH
                        );
                        labelhinhanh.setIcon(new ImageIcon(img));
                    } else {
                        labelhinhanh.setIcon(null);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    labelhinhanh.setIcon(null);
                } finally {
                    try { if (rs != null) rs.close(); } catch (Exception ex) {}
                    try { if (pst != null) pst.close(); } catch (Exception ex) {}
                    try { if (conn != null) conn.close(); } catch (Exception ex) {}
                }
            }
        });
        
        
        //hiển thị hình ảnh khi nhấn vào bảng danh sách
       bandanhsach.addMouseListener(new MouseAdapter() {
            @Override
            
            public void mouseClicked(MouseEvent e) {
                int selectedRow = bandanhsach.getSelectedRow();
                if (selectedRow == -1) return; // Không có dòng nào được chọn

                // Lấy ID sách từ dòng được chọn (giả sử ID là cột đầu tiên)
                int maSach = (int) bandanhsach.getValueAt(selectedRow, 0);

                // Truy vấn mô tả (link ảnh) từ CSDL
                Connection conn = null;
                PreparedStatement pst = null;
                ResultSet rs = null;

                try {
                    conn = db.con();
                    String sql = "SELECT mo_ta FROM sach WHERE id = ?";
                    pst = conn.prepareStatement(sql);
                    pst.setInt(1, maSach);
                    rs = pst.executeQuery();

                    if (rs.next()) {
                        String imagePath = rs.getString("mo_ta");

                        // Load ảnh vào JLabel
                        ImageIcon icon = new ImageIcon(imagePath);

                        // thay đổi kích thước ảnh cho vừa JLabel
                        Image img = icon.getImage().getScaledInstance(
                                labelhinhanh.getWidth(), 
                                labelhinhanh.getHeight(), 
                                Image.SCALE_SMOOTH
                        );
                        labelhinhanh.setIcon(new ImageIcon(img));
                    } else {
                        labelhinhanh.setIcon(null);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    labelhinhanh.setIcon(null);
                } finally {
                    try { if (rs != null) rs.close(); } catch (Exception ex) {}
                    try { if (pst != null) pst.close(); } catch (Exception ex) {}
                    try { if (conn != null) conn.close(); } catch (Exception ex) {}
                }
            }
        });
    }
    
    //thêm dữ liệu từ cơ sở dữ liệu vào bảng 
     public void loadDanhSachSach(JTable bandanhsach) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = db.con(); // Kết nối CSDL

            String sql = """
                SELECT sach.id, sach.ten_sach, sach.nam_xuat_ban, tac_gia.ten_tac_gia
                FROM sach
                JOIN tac_gia ON sach.id_tac_gia = tac_gia.id
            """;

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            // Lấy model của bảng
            DefaultTableModel model = (DefaultTableModel) bandanhsach.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ

            while (rs.next()) {
                int id = rs.getInt("id");
                String tenSach = rs.getString("ten_sach");
                String namXB = rs.getString("nam_xuat_ban");
                String tenTacGia = rs.getString("ten_tac_gia");

                // Thêm dòng vào bảng
                model.addRow(new Object[]{id, tenSach, namXB, tenTacGia});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (pst != null) pst.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }

        //thêm thể loại vào combo box
    public void loadTheLoaiVaoComboBox(JComboBox<String> Comboboxtheloai) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = db.con(); // Kết nối đến CSDL

            String sql = "SELECT ten_the_loai FROM the_loai";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            Comboboxtheloai.removeAllItems(); // Xóa dữ liệu cũ (nếu có)
            Comboboxtheloai.addItem("-- Chọn thể loại --"); // Mục mặc định

            while (rs.next()) {
                String tenTheLoai = rs.getString("ten_the_loai");
                Comboboxtheloai.addItem(tenTheLoai); // Thêm vào combobox
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (pst != null) pst.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TextFieldmasach = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TextFieldtensach = new javax.swing.JTextField();
        buttontimkiem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        bangtimkiem = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        labelhinhanh = new javax.swing.JLabel();
        Comboboxtheloai = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        bandanhsach = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Trang quản lý sách");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Mã sách");

        TextFieldmasach.setBackground(new java.awt.Color(204, 204, 204));
        TextFieldmasach.setForeground(new java.awt.Color(0, 0, 0));
        TextFieldmasach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldmasachActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Tên sách");

        TextFieldtensach.setBackground(new java.awt.Color(204, 204, 204));
        TextFieldtensach.setForeground(new java.awt.Color(0, 0, 0));

        buttontimkiem.setText("Tìm kiếm");

        bangtimkiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Tên sách", "Năm xuất bản"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(bangtimkiem);

        jPanel3.setBackground(new java.awt.Color(0, 255, 153));

        labelhinhanh.setBackground(new java.awt.Color(0, 153, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(labelhinhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(labelhinhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        Comboboxtheloai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Thể loại");

        bandanhsach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Tên sách", "năm xuất bản", "Tác giả"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(bandanhsach);

        jButton2.setText("Lọc");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 102));
        jLabel5.setText("*nhấn vào bảng để hiện ảnh");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Comboboxtheloai, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(25, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(TextFieldmasach, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TextFieldtensach, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(buttontimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 666, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)))))
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(TextFieldtensach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttontimkiem)
                            .addComponent(TextFieldmasach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(Comboboxtheloai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void TextFieldmasachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldmasachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldmasachActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Comboboxtheloai;
    private javax.swing.JTextField TextFieldmasach;
    private javax.swing.JTextField TextFieldtensach;
    private javax.swing.JTable bandanhsach;
    private javax.swing.JTable bangtimkiem;
    private javax.swing.JButton buttontimkiem;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelhinhanh;
    // End of variables declaration//GEN-END:variables
}
