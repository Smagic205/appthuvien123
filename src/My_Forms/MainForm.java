
package My_Forms;


import java.awt.CardLayout;
import java.awt.Image;
import javax.swing.ImageIcon;


public class MainForm extends javax.swing.JFrame {
     CardLayout cl;
    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
        setTitle("Quản lý thư viện");
        // chuyen form ra giua
        this.setLocationRelativeTo(null);
        
        displayImage();
        anhmain();
        anhlogo();
                
        cl=new CardLayout();
        Panelcontainer.setLayout(cl);
        Panelcontainer.add(mainpanel,"panelchinh");
        Panelcontainer.add( new Quanlymuontra(),"muontra");
        Panelcontainer.add( new Quanlytacgia(),"tacgia");
        Panelcontainer.add( new Quanlysach(),"sach");
        Panelcontainer.add( new Quanlynguoidung(),"nguoidung");
        Panelcontainer.add( new Thongkemuontra(),"thongke");
    }
    
    public void displayImage(){
        // lay anh
        ImageIcon logo_haui=new ImageIcon(getClass().getResource("/images/Haui_logo.jpg"));
        Image logo= logo_haui.getImage().getScaledInstance(jButton_haui_logo.getWidth(), jButton_haui_logo.getHeight(),Image.SCALE_SMOOTH);
        jButton_haui_logo.setIcon(new ImageIcon(logo));
          
    }
    
    public void anhmain(){
        // lay anh
        ImageIcon logo_haui=new ImageIcon(getClass().getResource("/images/main.jpg"));
        Image logo= logo_haui.getImage().getScaledInstance(jLabelanh.getWidth(), jLabelanh.getHeight(),Image.SCALE_SMOOTH);
        jLabelanh.setIcon(new ImageIcon(logo));
          
    }
    public void anhlogo(){
        // lay anh
        ImageIcon anh=new ImageIcon(getClass().getResource("/images/logosach.jpg"));
        Image logo= anh.getImage().getScaledInstance(jLabellogo.getWidth(), jLabellogo.getHeight(),Image.SCALE_SMOOTH);
        jLabellogo.setIcon(new ImageIcon(logo));
          
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton_haui_logo = new javax.swing.JButton();
        jButtonsach = new javax.swing.JButton();
        jButtonnguoidung = new javax.swing.JButton();
        jButtonmuontra = new javax.swing.JButton();
        jButtonthongke = new javax.swing.JButton();
        jButtontacgia = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        Panelcontainer = new javax.swing.JPanel();
        mainpanel = new javax.swing.JPanel();
        jLabelanh = new javax.swing.JLabel();
        jPanel_x4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabellogo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));

        jLabel3.setBackground(new java.awt.Color(102, 204, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 0));
        jLabel3.setText("HaUI");

        jLabel2.setBackground(new java.awt.Color(102, 204, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 0));
        jLabel2.setText("LIBRARY");

        jButton_haui_logo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_haui_logoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addComponent(jButton_haui_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_haui_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)))
                .addGap(35, 35, 35))
        );

        jButtonsach.setText("TÌM KIẾM SÁCH");
        jButtonsach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonsachMouseClicked(evt);
            }
        });
        jButtonsach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonsachActionPerformed(evt);
            }
        });

        jButtonnguoidung.setText("QUẢN LÝ NGƯỜI DÙNG");
        jButtonnguoidung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonnguoidungMouseClicked(evt);
            }
        });
        jButtonnguoidung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonnguoidungActionPerformed(evt);
            }
        });

        jButtonmuontra.setText("QUẢN LÝ MƯỢN TRẢ");
        jButtonmuontra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonmuontraMouseClicked(evt);
            }
        });
        jButtonmuontra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonmuontraActionPerformed(evt);
            }
        });

        jButtonthongke.setText("THỐNG KÊ MƯỢN TRẢ");
        jButtonthongke.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonthongkeMouseClicked(evt);
            }
        });
        jButtonthongke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonthongkeActionPerformed(evt);
            }
        });

        jButtontacgia.setText("QUẢN LÝ TÁC GIẢ");
        jButtontacgia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtontacgiaMouseClicked(evt);
            }
        });
        jButtontacgia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtontacgiaActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Đăng Xuất");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 32, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButtontacgia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonsach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonnguoidung, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                            .addComponent(jButtonmuontra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonthongke, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jButtontacgia, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonsach, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jButtonnguoidung, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jButtonmuontra, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jButtonthongke, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        Panelcontainer.setBackground(new java.awt.Color(102, 153, 255));

        mainpanel.setBackground(new java.awt.Color(102, 204, 255));

        javax.swing.GroupLayout mainpanelLayout = new javax.swing.GroupLayout(mainpanel);
        mainpanel.setLayout(mainpanelLayout);
        mainpanelLayout.setHorizontalGroup(
            mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mainpanelLayout.setVerticalGroup(
            mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelanh, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelcontainerLayout = new javax.swing.GroupLayout(Panelcontainer);
        Panelcontainer.setLayout(PanelcontainerLayout);
        PanelcontainerLayout.setHorizontalGroup(
            PanelcontainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelcontainerLayout.setVerticalGroup(
            PanelcontainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelcontainerLayout.createSequentialGroup()
                .addComponent(mainpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jPanel_x4.setBackground(new java.awt.Color(255, 255, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 2, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sách là chìa khóa mở cửa tri thức");

        javax.swing.GroupLayout jPanel_x4Layout = new javax.swing.GroupLayout(jPanel_x4);
        jPanel_x4.setLayout(jPanel_x4Layout);
        jPanel_x4Layout.setHorizontalGroup(
            jPanel_x4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_x4Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 929, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabellogo, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_x4Layout.setVerticalGroup(
            jPanel_x4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
            .addComponent(jLabellogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Panelcontainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_x4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel_x4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Panelcontainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtontacgiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtontacgiaMouseClicked
                 cl.show(Panelcontainer, "tacgia");
    }//GEN-LAST:event_jButtontacgiaMouseClicked

    private void jButtonsachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonsachMouseClicked
                 cl.show(Panelcontainer, "sach");
    }//GEN-LAST:event_jButtonsachMouseClicked

    private void jButtonnguoidungMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonnguoidungMouseClicked
                 cl.show(Panelcontainer, "nguoidung");
    }//GEN-LAST:event_jButtonnguoidungMouseClicked

    private void jButtonmuontraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonmuontraMouseClicked
                cl.show(Panelcontainer, "muontra");
    }//GEN-LAST:event_jButtonmuontraMouseClicked

    private void jButtonthongkeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonthongkeMouseClicked
                cl.show(Panelcontainer, "thongke");
    }//GEN-LAST:event_jButtonthongkeMouseClicked

    private void jButtonthongkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonthongkeActionPerformed
        cl.show(Panelcontainer, "tacgia");
    }//GEN-LAST:event_jButtonthongkeActionPerformed

    private void jButtonmuontraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonmuontraActionPerformed
        cl.show(Panelcontainer, "tacgia");
    }//GEN-LAST:event_jButtonmuontraActionPerformed

    private void jButtonnguoidungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonnguoidungActionPerformed
        cl.show(Panelcontainer, "tacgia");
    }//GEN-LAST:event_jButtonnguoidungActionPerformed

    private void jButtonsachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonsachActionPerformed
        cl.show(Panelcontainer, "tacgia");
    }//GEN-LAST:event_jButtonsachActionPerformed

    private void jButtontacgiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtontacgiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtontacgiaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose(); 
    
    // Hiển thị LoginForm
    LoginForm login = new LoginForm();
    login.setVisible(true);
    login.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton_haui_logoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_haui_logoActionPerformed
        cl.show(Panelcontainer, "panelchinh");
    }//GEN-LAST:event_jButton_haui_logoActionPerformed
    
    
    
    
    
    
    
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
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panelcontainer;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton_haui_logo;
    private javax.swing.JButton jButtonmuontra;
    private javax.swing.JButton jButtonnguoidung;
    private javax.swing.JButton jButtonsach;
    private javax.swing.JButton jButtontacgia;
    private javax.swing.JButton jButtonthongke;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelanh;
    private javax.swing.JLabel jLabellogo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel_x4;
    private javax.swing.JPanel mainpanel;
    // End of variables declaration//GEN-END:variables
}
