/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.LapTrinhTienTien.ui.Staff;

import org.LapTrinhTienTien.TableModel.ChiTietHoaDonTableModel;
import org.LapTrinhTienTien.TableModel.HoaDonTableModel;
import org.LapTrinhTienTien.model.ChiTietHoaDon;
import org.LapTrinhTienTien.model.HoaDon;
import org.LapTrinhTienTien.service.ChiTietHoaDonService;
import org.LapTrinhTienTien.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.LapTrinhTienTien.ui.HoaDon.CreateBill;

/**
 *
 * @author Admin
 */
@Controller
public class Invoice extends javax.swing.JPanel {

    /**
     * Creates new form Invoice
     */
    @Autowired
    private CreateBill createBill;
    HoaDonService hoaDonService;
    ChiTietHoaDonService chiTietHoaDonService;
    HoaDonTableModel hoaDonTableModel;
    List<HoaDon> hoaDonList = new ArrayList<>();
    ChiTietHoaDonTableModel cTHDTableModel;
    List<ChiTietHoaDon> chiTietHoaDonList = new ArrayList<>();
    public Invoice(@Autowired  HoaDonService hoaDonService,  @Autowired ChiTietHoaDonService chiTietHoaDonService ) {
        initComponents();
        LocalDate currentDate = LocalDate.now();
        Instant instant = currentDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        dcNgayXuat.setDate(date);
        this.hoaDonService = hoaDonService;
        this.chiTietHoaDonService = chiTietHoaDonService;
        loadData();
        events();
    }
    private void loadData() {
        hoaDonList = hoaDonService.getAllHoaDon();
        hoaDonTableModel = new HoaDonTableModel(hoaDonList);
        tblHoaDon.setModel(hoaDonTableModel);
        //
        chiTietHoaDonList.clear();
        cTHDTableModel = new ChiTietHoaDonTableModel(chiTietHoaDonList);
        tblCTHD.setModel(cTHDTableModel);
    }
    private void events() {
        btnTaohoaDon.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnCreateHoaDonPerformed(e);
            }
        });
        btnTimKiem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnTimKiemActionPerformed(e);
            }
        });
        btnReload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Invoice --- reload");
                loadData();
            }
        });
        tblHoaDon.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Đảm bảo sự kiện chỉ được kích hoạt một lần khi chọn hàng
                   System.out.println("tbl Hoa don change");
                    chiTietHoaDonList.clear();
                    int selectedRow = tblHoaDon.getSelectedRow();
                    if (selectedRow != -1) {
                        HoaDon hoaDon = hoaDonList.get(selectedRow);
                        chiTietHoaDonList = chiTietHoaDonService.getListCTHD(hoaDon);
                        cTHDTableModel.setLstCTHDs(chiTietHoaDonList);
                        cTHDTableModel.fireTableDataChanged();
                    }

                }
            }
        });
    }
    private void btnCreateHoaDonPerformed(ActionEvent e) {
        JFrame rootFrame = (JFrame)SwingUtilities.getWindowAncestor(this);
        // Đặt cửa sổ của saleForm là cửa sổ cha của cửa sổ gốc
        createBill.setVisible(true);
        createBill.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    private void btnTimKiemActionPerformed(ActionEvent e) {
        String maHD = tf_mahd.getText()+"";
        String sdt = tf_sdt.getText()+"";
        Date date = dcNgayXuat.getDate();
        Instant instant = date.toInstant();
        LocalDate ngayxuat = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        hoaDonList=   hoaDonService.timKiemHoaDon(maHD,ngayxuat,sdt);
        chiTietHoaDonList.clear();
        cTHDTableModel.setLstCTHDs(chiTietHoaDonList);
        cTHDTableModel.fireTableDataChanged();

        hoaDonTableModel.setLstHoaDons(hoaDonList);
        hoaDonTableModel.fireTableDataChanged();
        System.out.println("Hoadon-------"+hoaDonList);
        System.out.println("Hoadon-------"+hoaDonTableModel.getLstHoaDons());
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tf_mahd = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnTimKiem = new javax.swing.JButton();
        tf_sdt = new javax.swing.JTextField();
        btnTaohoaDon = new javax.swing.JButton();
        dcNgayXuat = new com.toedter.calendar.JDateChooser();
        btnReload = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCTHD = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(590, 390));

        jScrollPane1.setViewportView(tblHoaDon);

        jLabel1.setText("Mã HD:");

        jLabel2.setText("SDT khách hàng");

        jLabel3.setText("Ngày xuất:");

        btnTimKiem.setText("Tìm kiếm");

        btnTaohoaDon.setText("Tạo hóa đơn");

        btnReload.setText("Reload");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tf_mahd))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_sdt)
                                    .addComponent(dcNgayXuat, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnReload)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTimKiem)
                        .addGap(18, 18, 18)
                        .addComponent(btnTaohoaDon)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tf_mahd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tf_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(dcNgayXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimKiem)
                    .addComponent(btnTaohoaDon)
                    .addComponent(btnReload))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(tblCTHD);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReload;
    private javax.swing.JButton btnTaohoaDon;
    private javax.swing.JButton btnTimKiem;
    private com.toedter.calendar.JDateChooser dcNgayXuat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblCTHD;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField tf_mahd;
    private javax.swing.JTextField tf_sdt;
    // End of variables declaration//GEN-END:variables
}
