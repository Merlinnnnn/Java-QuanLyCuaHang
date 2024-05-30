/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.LapTrinhTienTien.ui.Admin;

import org.LapTrinhTienTien.model.NhaCungCap;
import org.LapTrinhTienTien.model.SanPham;
import org.LapTrinhTienTien.repository.NhaCungCapRepository;
import org.LapTrinhTienTien.repository.SanPhamRepository;
import org.LapTrinhTienTien.service.SanPhamService;
import org.LapTrinhTienTien.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDate;

/**
 *
 * @author Hi
 */
@Controller
public class addSanPham extends javax.swing.JFrame {

    /**
     * Creates new form addSanPham
     */
    File selectedFileSanPham = null;
    @Autowired
    private NhaCungCapRepository nhaCungCapRepository;
    @Autowired
    private SanPhamService sanPhamService;

    public addSanPham() {
        initComponents();
        event();
    }

    private void event() {
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnThemPerformed();
            }
        });
        btnAddImg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAddImgPerormed();
            }
        });
        btnDltImg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedFileSanPham = null;
                lblLoadImage.setIcon(null);
            }
        });
    }
    private boolean isValidFloat(String value) {
        try {
            Float.parseFloat(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private void btnThemPerformed() {
        String mancc= tf_mancc.getText();
        String tenSP = tf_tensanpham.getText();
        String tiengoc = tf_tiengoc.getText();
        String noicungcap = tf_diachi.getText();
        String soluong = tf_soluong.getText();
        // Kiểm tra các điều kiện hợp lệ
        if (mancc.isEmpty() || tenSP.isEmpty() || tiengoc.isEmpty()||noicungcap.isEmpty()||soluong.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin sản phẩm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return; // Dừng lại nếu thông tin chưa đầy đủ
        }
        if (!isValidFloat(tiengoc)) {
            JOptionPane.showMessageDialog(null, "Tiền gốc phải là một số thực!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int soLuongInt =0;
        try {
            soLuongInt = Integer.parseInt(soluong);
            if (soLuongInt < 0) {
                JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ. Số lượng phải lớn hơn 0.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ. Đây không phải là một số nguyên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        // them san pham
        NhaCungCap ncc=  nhaCungCapRepository.findByMaNCC(mancc);
        if(ncc==null) {
            JOptionPane.showMessageDialog(this, "Mã Nhà Cung Cấp không tồn tại", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // them san Pham
        SanPham sanPham = new SanPham();
        sanPham.setTenSP(tenSP);
        sanPham.setNoiSanXuat(noicungcap);
        sanPham.setImage(null);
        sanPham.setNhaCungCap(ncc);
        sanPham.setTrangThai("Hoạt động");
        sanPham.setTienGoc(Float.parseFloat(tiengoc));
        sanPham.setTienThanhToan(Float.parseFloat(tiengoc));
        sanPham.setNgayNhapHang(LocalDate.now());

        Response response = sanPhamService.themSanPham(sanPham,soLuongInt,selectedFileSanPham);
        JOptionPane.showMessageDialog(this, response.getMessage(), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        if(response.getFlag()) {
            tf_soluong.setText("");
             tf_mancc.setText("");
            tf_tensanpham.setText("");
            tf_tiengoc.setText("");
            tf_diachi.setText("");
            selectedFileSanPham = null;
            lblLoadImage.setIcon(null);
        }
    }
    private void btnAddImgPerormed() {
        JFileChooser fileChooser = new JFileChooser();

        // Thiết lập bộ lọc cho JFileChooser chỉ chấp nhận các loại file hình ảnh
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        // Hiển thị hộp thoại chọn file hình ảnh và kiểm tra xem người dùng có chọn file không
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            // Lấy đường dẫn của file hình ảnh được chọn
            File selectedFile = fileChooser.getSelectedFile();
            String imagePath = selectedFile.getAbsolutePath();

            ImageIcon originalIcon = new ImageIcon(imagePath);

            int newWidth = 287;
            int newHeight = 287;

            // Scale hình ảnh gốc về kích thước mới
            Image scaledImage = originalIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

            // Tạo ImageIcon mới từ hình ảnh đã scale
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            selectedFileSanPham = selectedFile;
            // Set icon vào JLabel
            lblLoadImage.setIcon(scaledIcon);
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

        tf_tensp = new javax.swing.JPanel();
        myPanelBoxShadow1 = new org.LapTrinhTienTien.ui.customItem.MyPanelBoxShadow();
        jPanel1 = new javax.swing.JPanel();
        lblLoadImage = new javax.swing.JLabel();
        btnAddImg = new org.LapTrinhTienTien.ui.customItem.button();
        btnDltImg = new org.LapTrinhTienTien.ui.customItem.button();
        jLabel1 = new javax.swing.JLabel();
        tf_diachi = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        tf_tensanpham = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        tf_tiengoc = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tf_mancc = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tf_soluong = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        btnThem = new org.LapTrinhTienTien.ui.customItem.button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tf_tensp.setBackground(new java.awt.Color(255, 255, 255));

        myPanelBoxShadow1.setBackground(new java.awt.Color(24, 145, 143));

        jPanel1.setBackground(new java.awt.Color(24, 145, 143));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLoadImage, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLoadImage, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
        );

        btnAddImg.setBackground(new java.awt.Color(24, 145, 143));
        btnAddImg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        btnAddImg.setText("Thêm ảnh");
        btnAddImg.setBorderColor(new java.awt.Color(255, 255, 255));
        btnAddImg.setRadius(35);

        btnDltImg.setBackground(new java.awt.Color(24, 145, 143));
        btnDltImg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        btnDltImg.setText("Xóa ảnh");
        btnDltImg.setBorderColor(new java.awt.Color(255, 255, 255));
        btnDltImg.setRadius(35);

        javax.swing.GroupLayout myPanelBoxShadow1Layout = new javax.swing.GroupLayout(myPanelBoxShadow1);
        myPanelBoxShadow1.setLayout(myPanelBoxShadow1Layout);
        myPanelBoxShadow1Layout.setHorizontalGroup(
            myPanelBoxShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myPanelBoxShadow1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(myPanelBoxShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(myPanelBoxShadow1Layout.createSequentialGroup()
                        .addComponent(btnAddImg, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(btnDltImg, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        myPanelBoxShadow1Layout.setVerticalGroup(
            myPanelBoxShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myPanelBoxShadow1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(myPanelBoxShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddImg, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDltImg, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel1.setText("Nơi sản xuất: ");

        tf_diachi.setBorder(null);

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel2.setText("Tên sản phẩm:");

        tf_tensanpham.setBorder(null);

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        tf_tiengoc.setBorder(null);

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel3.setText("Tiền gốc:");

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel5.setText("Nhà cung cấp");

        tf_mancc.setBorder(null);

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel4.setText("Số lượng");

        tf_soluong.setBorder(null);

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));

        btnThem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnThem.setText("Thêm");
        btnThem.setBorderColor(new java.awt.Color(0, 0, 0));
        btnThem.setRadius(35);

        javax.swing.GroupLayout tf_tenspLayout = new javax.swing.GroupLayout(tf_tensp);
        tf_tensp.setLayout(tf_tenspLayout);
        tf_tenspLayout.setHorizontalGroup(
            tf_tenspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tf_tenspLayout.createSequentialGroup()
                .addComponent(myPanelBoxShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(tf_tenspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tf_tenspLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tf_tenspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tf_tenspLayout.createSequentialGroup()
                                .addGroup(tf_tenspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tf_tenspLayout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel1))
                                    .addComponent(jLabel3)
                                    .addGroup(tf_tenspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(16, 16, 16))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tf_tenspLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)))
                        .addGroup(tf_tenspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator1)
                            .addComponent(tf_diachi)
                            .addComponent(tf_tensanpham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                            .addComponent(jSeparator2)
                            .addComponent(tf_tiengoc)
                            .addComponent(jSeparator3)
                            .addComponent(tf_mancc)
                            .addComponent(tf_soluong)
                            .addComponent(jSeparator4)
                            .addComponent(jSeparator5))
                        .addContainerGap(35, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tf_tenspLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56))))
        );
        tf_tenspLayout.setVerticalGroup(
            tf_tenspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(myPanelBoxShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(tf_tenspLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(tf_tenspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tf_diachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tf_tenspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_tensanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tf_tenspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tf_tenspLayout.createSequentialGroup()
                        .addComponent(tf_tiengoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addGap(61, 61, 61)
                .addGroup(tf_tenspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tf_mancc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(tf_tenspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tf_soluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tf_tensp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tf_tensp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(addSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new addSanPham().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.LapTrinhTienTien.ui.customItem.button btnAddImg;
    private org.LapTrinhTienTien.ui.customItem.button btnDltImg;
    private org.LapTrinhTienTien.ui.customItem.button btnThem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel lblLoadImage;
    private org.LapTrinhTienTien.ui.customItem.MyPanelBoxShadow myPanelBoxShadow1;
    private javax.swing.JTextField tf_diachi;
    private javax.swing.JTextField tf_mancc;
    private javax.swing.JTextField tf_soluong;
    private javax.swing.JTextField tf_tensanpham;
    private javax.swing.JPanel tf_tensp;
    private javax.swing.JTextField tf_tiengoc;
    // End of variables declaration//GEN-END:variables
}
