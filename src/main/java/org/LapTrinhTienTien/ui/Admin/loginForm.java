/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.LapTrinhTienTien.ui.Admin;

import jakarta.annotation.Resource;
import org.LapTrinhTienTien.StaticApp.Global;
import org.LapTrinhTienTien._enum.ChucVuEnum;
import org.LapTrinhTienTien.model.NhanVien;
import org.LapTrinhTienTien.model.TaiKhoan;
import org.LapTrinhTienTien.service.NhanVienService;
import org.LapTrinhTienTien.service.TaiKhoanService;
import org.LapTrinhTienTien.ui.Staff.staffForm;
import org.LapTrinhTienTien.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;

/**
 *
 * @author Hi
 */
@Controller
public class loginForm extends javax.swing.JFrame {
    /**
     * Creates new form loginForm
     */

    @Autowired
    TaiKhoanService taikhoanService;
    @Autowired adminForm adminform;
    @Autowired staffForm staffForm;
    @Autowired NhanVienService nhanvienService;
//    @Autowired
//    staffForm staffform;
    public loginForm(){
        initComponents();
        setSize(700,500);
        ClassPathResource resource1 = new ClassPathResource("/grocery-cart.png");
        ClassPathResource resource2 = new ClassPathResource("/user.png");
        ClassPathResource resource3 = new ClassPathResource("/padlock.png");
        try {
            jLabel1.setIcon(new ImageIcon(resource1.getURL()));
            jLabel5.setIcon(new ImageIcon(resource2.getURL()));
            jLabel6.setIcon(new ImageIcon(resource3.getURL()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tf_pass = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        tf_user = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnLogin = new org.LapTrinhTienTien.ui.customItem.button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(102, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 350, 400);

        jSeparator1.setForeground(new java.awt.Color(51, 0, 51));
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(420, 150, 0, 3);

        jSeparator2.setForeground(new java.awt.Color(51, 0, 51));
        jSeparator2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(420, 150, 0, 3);

        jSeparator3.setForeground(new java.awt.Color(51, 0, 51));
        getContentPane().add(jSeparator3);
        jSeparator3.setBounds(420, 160, 0, 3);
        getContentPane().add(jSeparator4);
        jSeparator4.setBounds(420, 160, 0, 3);

        jSeparator5.setForeground(new java.awt.Color(51, 0, 51));
        getContentPane().add(jSeparator5);
        jSeparator5.setBounds(420, 130, 0, 3);
        getContentPane().add(jSeparator6);
        jSeparator6.setBounds(430, 160, 0, 3);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(null);

        jLabel2.setFont(new java.awt.Font("SVNtimes new roman", 1, 24)); // NOI18N
        jLabel2.setText("Login");
        jPanel3.add(jLabel2);
        jLabel2.setBounds(140, 30, 70, 40);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Password:");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(120, 150, 90, 20);

        tf_pass.setBorder(null);
        jPanel3.add(tf_pass);
        tf_pass.setBounds(120, 170, 150, 30);

        jSeparator7.setForeground(new java.awt.Color(51, 0, 51));
        jPanel3.add(jSeparator7);
        jSeparator7.setBounds(120, 200, 150, 10);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("User: ");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(120, 80, 50, 20);

        tf_user.setBorder(null);
        jPanel3.add(tf_user);
        tf_user.setBounds(120, 100, 150, 30);

        jSeparator8.setForeground(new java.awt.Color(51, 0, 51));
        jPanel3.add(jSeparator8);
        jSeparator8.setBounds(120, 130, 150, 10);

       // jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/LapTrinhTienTien/Img/user.png"))); // NOI18N
        jPanel3.add(jLabel5);
        jLabel5.setBounds(80, 80, 0, 30);
        jPanel3.add(jLabel6);
        jLabel6.setBounds(80, 140, 20, 40);

        btnLogin.setText("Login");
        btnLogin.setBorderColor(new java.awt.Color(51, 0, 51));
        btnLogin.setRadius(35);
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        jPanel3.add(btnLogin);
        btnLogin.setBounds(120, 240, 90, 40);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(350, 0, 350, 400);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        String username = tf_user.getText()+"";
        String password = tf_pass.getText()+"";
        username = "NV006";
        password = "NV006";
        if(tf_user.getText().equals("") || tf_pass.getText().equals("")){
            return;
        }
        Response response =taikhoanService.login(username, password);
        if(response.getFlag()){
            System.out.println(response.getMessage());
            Global.account = (TaiKhoan) response.getData();
            //Chuyen toi form Admin main
            if(Global.account.getNhanVien().getTrangThai()!=null){
                JOptionPane.showMessageDialog(this, "Tài khoản đã bị khóa.");
                Global.account = null;
                return;
            }
            if(Global.account.getNhanVien().getChucVu().getMaCV().equals(ChucVuEnum.NHAN_VIEN.getMaCV())){
                System.out.println("staff");
                staffForm.setVisible(true);
                staffForm.setLogin(this);
            }else  {
                adminform.setLogin(this);
                adminform.setVisible(true);
            }
            tf_user.setText("");
            tf_pass.setText("");
            this.dispose();

        }else System.out.println(response.getMessage());
    }//GEN-LAST:event_btnLoginActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(loginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(loginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(loginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(loginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new loginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.LapTrinhTienTien.ui.customItem.button btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTextField tf_pass;
    private javax.swing.JTextField tf_user;
    // End of variables declaration//GEN-END:variables
}
