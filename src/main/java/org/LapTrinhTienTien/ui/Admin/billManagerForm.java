    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.LapTrinhTienTien.ui.Admin;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.LapTrinhTienTien.TableModel.HoaDonAdminTableModel;
import org.LapTrinhTienTien.model.ChiTietHoaDon;
import org.LapTrinhTienTien.model.HoaDon;
import org.LapTrinhTienTien.service.ChiTietHoaDonService;
import org.LapTrinhTienTien.service.HoaDonService;
import org.LapTrinhTienTien.ui.model.modelCard;
import org.LapTrinhTienTien.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.event.*;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Hi
 */
@Service
public class billManagerForm extends javax.swing.JPanel {
    @Autowired BillDetail billDetail;
    private HoaDonService hoaDonService;
    private ChiTietHoaDonService chiTietHoaDonService;
    private HoaDonAdminTableModel hoaDonAdminTableModel;
    private  int tblHoaDonIndexSelected = -1;
    modelCard card = new modelCard(new ImageIcon(getClass().getResource("/user.png")),"", "", "", "");
    /**
     * Creates new form billManagerForm
     */
    public billManagerForm(@Autowired HoaDonService hoaDonService,@Autowired ChiTietHoaDonService chiTietHoaDonService) {
        initComponents();
        cardThongtin.setData(card);
        this.hoaDonService = hoaDonService;
        this.chiTietHoaDonService = chiTietHoaDonService;
        loadData();
        events();

    }
    private void events(){
        tblHoaDon.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Đảm bảo sự kiện chỉ được kích hoạt một lần khi chọn hàng
                    int selectedRow = tblHoaDon.getSelectedRow();
                    tblHoaDonIndexSelected = selectedRow;
                    if (selectedRow != -1) {
                       HoaDon hd =  hoaDonAdminTableModel.getRow(selectedRow);
                       System.out.println("Mã NV: "+hd.getNhanVien().getMaNV());
                       card.setName("Mã NV: "+ hd.getNhanVien().getMaNV());
                       card.setTitle("Mã HD: "+hd.getMaHD());
                       card.setValues("Thời gian: "+String.valueOf(hd.getNgayXuat()));
                       card.setDescription("Thành Tiền: "+String.valueOf(hd.getThanhTien()));
                       cardThongtin.setData(card);
                    }
                }
            }
        });
        mpbChiTiet.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               mpbChiTietClick();
            }
        });
        mpbChinhSua.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               mpbChinhSuaClick();
            }
        });
        mpbReload.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadData();
                JOptionPane.showMessageDialog(billManagerForm.this, "Reload dữ liệu thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            }
        });
    }
    private void mpbChiTietClick(){
        if(tblHoaDonIndexSelected == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng từ bảng trước khi chỉnh sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        HoaDon hoaDon = hoaDonAdminTableModel.getRow(tblHoaDonIndexSelected);
        //JFrame rootFrame = (JFrame)SwingUtilities.getWindowAncestor(this);
        billDetail.setHoaDon(hoaDon);
        System.out.println("-------Hoadon---" + hoaDon.getChiTietHoaDon().size());
        
        billDetail.setVisible(true);
        billDetail.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    private void mpbChinhSuaClick(){
        if(tblHoaDonIndexSelected == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng từ bảng trước khi chỉnh sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirmation = JOptionPane.showConfirmDialog(this, "Bạn có muốn chỉnh sửa trạng thái của cửa hàng không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            HoaDon hoaDon = hoaDonAdminTableModel.getRow(tblHoaDonIndexSelected);
           Response response = hoaDonService.updateHoaDon(hoaDon);
            JOptionPane.showMessageDialog(this, response.getMessage(), "Thông báo", JOptionPane.WARNING_MESSAGE);
            loadData();

        }



    }
    private void loadData(){
        List<HoaDon> hoaDons = hoaDonService.getAllAdminHoaDon();
        hoaDonAdminTableModel = new HoaDonAdminTableModel(hoaDons);
        tblHoaDon.setModel(hoaDonAdminTableModel);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new org.LapTrinhTienTien.ui.customItem.panelBorder();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDon = new org.LapTrinhTienTien.ui.customItem.table();
        cardThongtin = new org.LapTrinhTienTien.ui.customItem.card();
        myPanelBoxShadow1 = new org.LapTrinhTienTien.ui.customItem.MyPanelBoxShadow();
        mpbReload = new org.LapTrinhTienTien.ui.customItem.MyPanelBoxShadow();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        mpbChinhSua = new org.LapTrinhTienTien.ui.customItem.MyPanelBoxShadow();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        mpbChiTiet = new org.LapTrinhTienTien.ui.customItem.MyPanelBoxShadow();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        jScrollPane2.setViewportView(tblHoaDon);

        cardThongtin.setColor1(new java.awt.Color(142, 142, 250));
        cardThongtin.setColor2(new java.awt.Color(123, 123, 245));

        myPanelBoxShadow1.setBackground(new java.awt.Color(109, 216, 234));

        mpbReload.setBackground(new java.awt.Color(10, 200, 186));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/find.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(127, 127, 127));
        jLabel3.setText("Reload");

        javax.swing.GroupLayout mpbReloadLayout = new javax.swing.GroupLayout(mpbReload);
        mpbReload.setLayout(mpbReloadLayout);
        mpbReloadLayout.setHorizontalGroup(
            mpbReloadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mpbReloadLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(0, 16, Short.MAX_VALUE))
        );
        mpbReloadLayout.setVerticalGroup(
            mpbReloadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mpbReloadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(mpbReloadLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel3)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        mpbChinhSua.setBackground(new java.awt.Color(10, 200, 186));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/delete.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(127, 127, 127));
        jLabel5.setText("Edit");

        javax.swing.GroupLayout mpbChinhSuaLayout = new javax.swing.GroupLayout(mpbChinhSua);
        mpbChinhSua.setLayout(mpbChinhSuaLayout);
        mpbChinhSuaLayout.setHorizontalGroup(
            mpbChinhSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mpbChinhSuaLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        mpbChinhSuaLayout.setVerticalGroup(
            mpbChinhSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mpbChinhSuaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(mpbChinhSuaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel5)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/transaction.png"))); // NOI18N

        mpbChiTiet.setBackground(new java.awt.Color(10, 200, 186));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/find.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(127, 127, 127));
        jLabel8.setText("Chi tiết");

        javax.swing.GroupLayout mpbChiTietLayout = new javax.swing.GroupLayout(mpbChiTiet);
        mpbChiTiet.setLayout(mpbChiTietLayout);
        mpbChiTietLayout.setHorizontalGroup(
            mpbChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mpbChiTietLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(0, 14, Short.MAX_VALUE))
        );
        mpbChiTietLayout.setVerticalGroup(
            mpbChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mpbChiTietLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(mpbChiTietLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel8)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout myPanelBoxShadow1Layout = new javax.swing.GroupLayout(myPanelBoxShadow1);
        myPanelBoxShadow1.setLayout(myPanelBoxShadow1Layout);
        myPanelBoxShadow1Layout.setHorizontalGroup(
            myPanelBoxShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myPanelBoxShadow1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(myPanelBoxShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(mpbChinhSua, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mpbReload, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
            .addGroup(myPanelBoxShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(myPanelBoxShadow1Layout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addComponent(mpbChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(174, Short.MAX_VALUE)))
        );
        myPanelBoxShadow1Layout.setVerticalGroup(
            myPanelBoxShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myPanelBoxShadow1Layout.createSequentialGroup()
                .addGroup(myPanelBoxShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(myPanelBoxShadow1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(myPanelBoxShadow1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(mpbReload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mpbChinhSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(myPanelBoxShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, myPanelBoxShadow1Layout.createSequentialGroup()
                    .addContainerGap(86, Short.MAX_VALUE)
                    .addComponent(mpbChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(66, 66, 66)))
        );

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(cardThongtin, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(myPanelBoxShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(13, 13, 13)))
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(myPanelBoxShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cardThongtin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.LapTrinhTienTien.ui.customItem.card cardThongtin;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private org.LapTrinhTienTien.ui.customItem.MyPanelBoxShadow mpbChiTiet;
    private org.LapTrinhTienTien.ui.customItem.MyPanelBoxShadow mpbChinhSua;
    private org.LapTrinhTienTien.ui.customItem.MyPanelBoxShadow mpbReload;
    private org.LapTrinhTienTien.ui.customItem.MyPanelBoxShadow myPanelBoxShadow1;
    private org.LapTrinhTienTien.ui.customItem.panelBorder panelBorder1;
    private org.LapTrinhTienTien.ui.customItem.table tblHoaDon;
    // End of variables declaration//GEN-END:variables
}
