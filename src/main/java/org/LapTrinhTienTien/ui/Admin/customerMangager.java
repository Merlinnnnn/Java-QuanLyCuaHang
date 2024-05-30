/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.LapTrinhTienTien.ui.Admin;

import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.LapTrinhTienTien.model.KhachHang;
import org.LapTrinhTienTien.repository.KhachHangRepository;
import org.LapTrinhTienTien.service.KhachHangService;
import org.LapTrinhTienTien.ui.model.modelCard;
import org.LapTrinhTienTien.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hi
 */
@Controller
public class customerMangager extends javax.swing.JPanel {
@Autowired
    addCustomer addForm;
    KhachHangRepository khachHangRepository;
    KhachHangService khachHangService;
    public customerMangager(@Autowired KhachHangRepository khachHangRepository,@Autowired KhachHangService khachHangService){
        this.khachHangRepository = khachHangRepository;
        this.khachHangService = khachHangService;
        initComponents();
        loadDataToTable();
        cardThongTin.setData(new modelCard(new ImageIcon(getClass().getResource("/user.png")),"Thông tin", "tên khách hàng", "sđt", ""));
        cardDiemSo.setData(new modelCard(new ImageIcon(getClass().getResource("/user.png")),"Điểm khuyến mãi", "Số điểm đã tích", "Số điểm đã dùng", "Số điểm dư"));
        j1.setVisible(false);
        j2.setVisible(false);
        t1.setVisible(false);
        t2.setVisible(false);
        btnSave.setVisible(false);
        myPanelBoxShadow1.setSize(625, 500);
    }
    public void loadDataToTable() {
        List<KhachHang> khachHangList = khachHangRepository.findAll();

        // Xóa dữ liệu cũ trong bảng
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        // Đưa dữ liệu mới từ danh sách khách hàng vào bảng
        for (KhachHang khachHang : khachHangList) {
            model.addRow(new Object[]{
                    khachHang.getId(),
                    khachHang.getHoTenKH(),
                    khachHang.getSdt(),
                    khachHang.getSoDiemDaDung(),
                    khachHang.getSoDiemHienCo()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        myPanelBoxShadow1 = new org.LapTrinhTienTien.ui.customItem.MyPanelBoxShadow();
        cardThongTin = new org.LapTrinhTienTien.ui.customItem.card();
        cardDiemSo = new org.LapTrinhTienTien.ui.customItem.card();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnThem = new org.LapTrinhTienTien.ui.customItem.button();
        btnSua = new org.LapTrinhTienTien.ui.customItem.button();
        t2 = new javax.swing.JTextField();
        j1 = new javax.swing.JSeparator();
        j2 = new javax.swing.JSeparator();
        btnSave = new org.LapTrinhTienTien.ui.customItem.button();
        t1 = new javax.swing.JTextField();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        cardThongTin.setColor1(new java.awt.Color(0, 51, 153));

        cardDiemSo.setColor1(new java.awt.Color(102, 0, 102));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Họ và tên", "Số điện thoại", "Số điểm đã dùng", "Số điểm hiện có"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMaxWidth(40);
            jTable1.getColumnModel().getColumn(1).setMinWidth(140);
        }

        btnThem.setText("Thêm");
        btnThem.setRadius(30);
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnThemMousePressed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.setRadius(30);
        btnSua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaMouseClicked(evt);
            }
        });
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        t2.setText("jTextField1");

        j1.setForeground(new java.awt.Color(0, 0, 0));

        j2.setForeground(new java.awt.Color(0, 0, 0));

        btnSave.setText("Save");
        btnSave.setRadius(30);
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveMouseClicked(evt);
            }
        });
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        t1.setText("jTextField1");

        javax.swing.GroupLayout myPanelBoxShadow1Layout = new javax.swing.GroupLayout(myPanelBoxShadow1);
        myPanelBoxShadow1.setLayout(myPanelBoxShadow1Layout);
        myPanelBoxShadow1Layout.setHorizontalGroup(
            myPanelBoxShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myPanelBoxShadow1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(myPanelBoxShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(myPanelBoxShadow1Layout.createSequentialGroup()
                        .addComponent(cardThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cardDiemSo, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(myPanelBoxShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(myPanelBoxShadow1Layout.createSequentialGroup()
                        .addGroup(myPanelBoxShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(myPanelBoxShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                                .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap(12, Short.MAX_VALUE))
                    .addGroup(myPanelBoxShadow1Layout.createSequentialGroup()
                        .addGroup(myPanelBoxShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(myPanelBoxShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(j2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(j1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        myPanelBoxShadow1Layout.setVerticalGroup(
            myPanelBoxShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myPanelBoxShadow1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(myPanelBoxShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cardDiemSo, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(cardThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(myPanelBoxShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(myPanelBoxShadow1Layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(j1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(j2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(myPanelBoxShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(myPanelBoxShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        j1.setVisible(false);
        j2.setVisible(false);
        t1.setVisible(false);
        t2.setVisible(false);
        btnSave.setVisible(false);
    }//GEN-LAST:event_formMouseClicked

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseClicked
        // TODO add your handling code here:
        int selectedRow = jTable1.getSelectedRow();
        long customerId = (long) jTable1.getValueAt(selectedRow, 0);
        String newName = t1.getText();
        String newSdt = t2.getText();
        Response response = khachHangService.addCustomer(newName,newSdt);
        if(response.getFlag()==false)
        {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        else{
            khachHangService.updateCustomer(customerId, newName, newSdt);
            JOptionPane.showMessageDialog(this, "Đã cập nhật thông tin khách hàng thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadDataToTable(); // Load lại dữ liệu vào bảng sau khi cập nhật
            j1.setVisible(false);
            j2.setVisible(false);
            t1.setVisible(false);
            t2.setVisible(false);
            btnSave.setVisible(false);
        }


    }//GEN-LAST:event_btnSaveMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        j1.setVisible(true);
        j2.setVisible(true);
        t1.setVisible(true);
        t2.setVisible(true);
        btnSave.setVisible(true);
        String name = (String) jTable1.getValueAt(selectedRow, 1); // Giả sử cột 1 là tên
        String sdt = (String) jTable1.getValueAt(selectedRow, 2); // Giả sử cột 2 là số điện thoại
        t1.setText(name);
        t2.setText(sdt);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnSuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuaMouseClicked

    private void btnThemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMousePressed
        // TODO add your handling code here:
        addForm.parentForm = this ;
        addForm.setVisible(true);
        addForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loadDataToTable();
    }//GEN-LAST:event_btnThemMousePressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int rowIndex = jTable1.getSelectedRow();
        String hoTen = jTable1.getValueAt(rowIndex, 1).toString();
        String sdt = jTable1.getValueAt(rowIndex, 2).toString();
        cardThongTin.setData(new modelCard(new ImageIcon(getClass().getResource("/user.png")),"Thông tin", hoTen, sdt, ""));
    }//GEN-LAST:event_jTable1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.LapTrinhTienTien.ui.customItem.button btnSave;
    private org.LapTrinhTienTien.ui.customItem.button btnSua;
    private org.LapTrinhTienTien.ui.customItem.button btnThem;
    private org.LapTrinhTienTien.ui.customItem.card cardDiemSo;
    private org.LapTrinhTienTien.ui.customItem.card cardThongTin;
    private javax.swing.JSeparator j1;
    private javax.swing.JSeparator j2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private org.LapTrinhTienTien.ui.customItem.MyPanelBoxShadow myPanelBoxShadow1;
    private javax.swing.JTextField t1;
    private javax.swing.JTextField t2;
    // End of variables declaration//GEN-END:variables
}
