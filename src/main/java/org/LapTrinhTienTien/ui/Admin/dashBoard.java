/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.LapTrinhTienTien.ui.Admin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Builder;
import org.LapTrinhTienTien.model.ChiTietHoaDon;
import org.LapTrinhTienTien.model.CuaHangSanPham;

import org.LapTrinhTienTien.model.HoaDon;
import org.LapTrinhTienTien.repository.ChiTietHoaDonRepository;
import org.LapTrinhTienTien.repository.CuaHangSanPhamRepository;
import org.LapTrinhTienTien.ui.model.modelChartLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Hi
 */
@Controller 
public class dashBoard extends javax.swing.JPanel {

    ChiTietHoaDonRepository chiTietHDRepository;
    public dashBoard(@Autowired ChiTietHoaDonRepository chiTietHDRepository) {
        this.chiTietHDRepository = chiTietHDRepository;
        initComponents();
        loadDataToTable();
        initData();
    }
    public void initData()
    {
        List<modelChartLine> list = new ArrayList<>();
        /*list.add(new modelChartLine("Monday", 10));
        list.add(new modelChartLine("Tuesday", 150));
        list.add(new modelChartLine("Wednesday", 80));
        list.add(new modelChartLine("Thursday", 100));
        list.add(new modelChartLine("Friday", 125));
        list.add(new modelChartLine("Saturday", 80));
        list.add(new modelChartLine("Sunday", 200));*/

       /* for (ChiTietHoaDon chiTietHD : chiTietHDRepository.findAll()) {
            list.add(new modelChartLine(chiTietHD.getSanPham().getTenSP(), chiTietHD.getSoLuong()));
        }*/
        chartLine1.setModel(list);
    }
    public void loadDataToTable() {
        List<ChiTietHoaDon> chiTietHoaDonList = chiTietHDRepository.findAll();

        // Xóa dữ liệu cũ trong bảng
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        // Đưa dữ liệu mới từ danh sách khách hàng vào bảng
        for (ChiTietHoaDon chiTiet : chiTietHoaDonList) {
            model.addRow(new Object[]{

                    chiTiet.getSanPham().getTenSP(),
                    chiTiet.getSoLuong(),
                    chiTiet.getHoaDon().getNgayXuat()
            });
        }
    }
    public void thongKeNgay(int ngay, int thang, int nam)
    {
        Map<String, Long> productQuantityMap = new HashMap<>();
        List<ChiTietHoaDon> chiTietHoaDonList = chiTietHDRepository.findAll();

        for (ChiTietHoaDon chiTiet : chiTietHoaDonList) {
            LocalDateTime ngayXuat = chiTiet.getHoaDon().getNgayXuat();

            if (ngayXuat.getMonthValue() == thang && ngayXuat.getYear() == nam && ngayXuat.getDayOfMonth() == ngay) {
                String maSP = chiTiet.getSanPham().getMaSP();
                long soLuong = chiTiet.getSoLuong();

                if (productQuantityMap.containsKey(maSP)) {
                    productQuantityMap.put(maSP, productQuantityMap.get(maSP) + soLuong);
                } else {
                    productQuantityMap.put(maSP, soLuong);
                }
            }

        }

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        for (Map.Entry<String, Long> entry : productQuantityMap.entrySet()) {
            model.addRow(new Object[]{
                    entry.getKey(), // Mã sản phẩm
                    entry.getValue(), // Tổng số lượng
                    thang + "/" + nam // Tháng và năm
            });
        }
        int rowCount = model.getRowCount();

        List<modelChartLine> list = new ArrayList<>();

        for (int i = 0; i < rowCount; i++) {
            String tenSP = (String) model.getValueAt(i, 0);
            Long soLuong = (Long) model.getValueAt(i, 1);

            list.add(new modelChartLine(tenSP, soLuong));
        }
        chartLine1.setModel(list);

    }
    private boolean checkHopLeThangNam(String thang, String nam) {
        // Kiểm tra xem tháng và năm có phải là số hay không
        if (!thang.matches("\\d+") || !nam.matches("\\d+")) {
            return false;
        }

        // Chuyển đổi tháng và năm thành số nguyên
        int thangInt = Integer.parseInt(thang);
        int namInt = Integer.parseInt(nam);

        // Kiểm tra tháng
        if (thangInt < 1 || thangInt > 12) {
            return false;
        }

        // Kiểm tra năm
        int namHienTai = Year.now().getValue();
        if (namInt <= 0 || namInt > namHienTai) {
            return false;
        }

        return true;
    }
    private boolean checkHopLeNgayThangNam(String ngay, String thang, String nam) {
        // Kiểm tra xem ngày, tháng, năm có phải là số nguyên dương không
        if (!ngay.matches("\\d+")) {
            return false;
        }
        // Chuyển đổi sang kiểu số nguyên
        int ngayInt = Integer.parseInt(ngay);
        int thangInt = Integer.parseInt(thang);
        int namInt = Integer.parseInt(nam);

        // Kiểm tra số ngày hợp lệ trong tháng và năm đó
        if (ngayInt < 1 || ngayInt > soNgayTrongThang(thangInt, namInt)) {
            return false;
        }

        return true;
    }

    private int soNgayTrongThang(int thang, int nam) {
        if (thang == 2) {
            return nam % 4 == 0 && (nam % 100 != 0 || nam % 400 == 0) ? 29 : 28;
        } else if (thang == 4 || thang == 6 || thang == 9 || thang == 11) {
            return 30;
        } else {
            return 31;
        }
    }


    public void thongKeThang(int thang, int nam)
    {
        Map<String, Long> productQuantityMap = new HashMap<>();

        List<ChiTietHoaDon> chiTietHoaDonList = chiTietHDRepository.findAll();

        for (ChiTietHoaDon chiTiet : chiTietHoaDonList) {
            LocalDateTime ngayXuat = chiTiet.getHoaDon().getNgayXuat();

            if (ngayXuat.getMonthValue() == thang && ngayXuat.getYear() == nam) {
                String maSP = chiTiet.getSanPham().getMaSP();
                long soLuong = chiTiet.getSoLuong();

                if (productQuantityMap.containsKey(maSP)) {
                    productQuantityMap.put(maSP, productQuantityMap.get(maSP) + soLuong);
                } else {
                    productQuantityMap.put(maSP, soLuong);
                }
            }
        }

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        for (Map.Entry<String, Long> entry : productQuantityMap.entrySet()) {
            model.addRow(new Object[]{
                    entry.getKey(),
                    entry.getValue(),
                    thang + "/" + nam
            });
        }
        int rowCount = model.getRowCount();
        List<modelChartLine> list = new ArrayList<>();
        for (int i = 0; i < rowCount; i++) {
            String tenSP = (String) model.getValueAt(i, 0);
            Long soLuong = (Long) model.getValueAt(i, 1);

            list.add(new modelChartLine(tenSP, soLuong));
        }
        chartLine1.setModel(list);
    }

    public void thongKeToanTg()
    {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        List<Object[]> result = chiTietHDRepository.findTotalQuantityByMaSPGroupByMaSP();

        for (Object[] row : result) {
            String maSP = (String) row[0];
            Long totalQuantity = (Long) row[1];
            model.addRow(new Object[]{maSP, totalQuantity});
        }

        int rowCount = model.getRowCount();
        List<modelChartLine> list = new ArrayList<>();
        for (int i = 0; i < rowCount; i++) {
            String tenSP = (String) model.getValueAt(i, 0);
            Long soLuong = (Long) model.getValueAt(i, 1);
            list.add(new modelChartLine(tenSP, soLuong));
        }
        chartLine1.setModel(list);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chartLine1 = new org.LapTrinhTienTien.ui.customItem.ChartLine();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnNgay = new org.LapTrinhTienTien.ui.customItem.button();
        btnThang = new org.LapTrinhTienTien.ui.customItem.button();
        btnToanTg = new org.LapTrinhTienTien.ui.customItem.button();
        jLabel1 = new javax.swing.JLabel();
        dateTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        monthTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        yearTxt = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tên sản phẩm", "Số lượng bán ra", "Ngay ban"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
        }

        btnNgay.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnNgay.setText("Ngày");
        btnNgay.setBorderColor(new java.awt.Color(0, 51, 255));
        btnNgay.setRadius(35);
        btnNgay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnNgayMousePressed(evt);
            }
        });

        btnThang.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnThang.setText("Tháng");
        btnThang.setBorderColor(new java.awt.Color(0, 51, 255));
        btnThang.setRadius(35);
        btnThang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnThangMousePressed(evt);
            }
        });

        btnToanTg.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnToanTg.setText("Toàn bộ");
        btnToanTg.setBorderColor(new java.awt.Color(0, 51, 255));
        btnToanTg.setRadius(35);
        btnToanTg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnToanTgMousePressed(evt);
            }
        });

        jLabel1.setText("Ngày:");

        jLabel2.setText("Tháng: ");

        jLabel3.setText("Năm: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chartLine1, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnToanTg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateTxt)
                            .addComponent(monthTxt)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(yearTxt))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(chartLine1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(monthTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yearTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThang, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnToanTg, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnToanTgMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnToanTgMousePressed
        // TODO add your handling code here:
        TableColumn column = jTable1.getColumnModel().getColumn(2);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setWidth(0);
        column.setPreferredWidth(0);
        column.setResizable(false);
        thongKeToanTg();
    }//GEN-LAST:event_btnToanTgMousePressed

    private void btnThangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThangMousePressed
        // TODO add your handling code here:
        if(monthTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thang khong duoc de trong", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(yearTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nam khong duoc de trong", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(checkHopLeThangNam(monthTxt.getText(), yearTxt.getText())==false)
        {
            JOptionPane.showMessageDialog(this, "Thang nam khong hop le", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        TableColumn column = jTable1.getColumnModel().getColumn(2);
        column.setResizable(true); // Cho phép cột thay đổi kích thước nếu cần thiết
        column.setMinWidth(155);
        column.setMaxWidth(155);
        column.setWidth(155);
        column.setPreferredWidth(155);
        column.setResizable(false);
        int thang = Integer.parseInt(monthTxt.getText());
        int nam = Integer.parseInt(yearTxt.getText());
        thongKeThang(thang,nam);
    }//GEN-LAST:event_btnThangMousePressed

    private void btnNgayMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNgayMousePressed
        // TODO add your handling code here:
        if(dateTxt.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Ngay khong duoc de trong", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;}
        if(monthTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thang khong duoc de trong", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(yearTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nam khong duoc de trong", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(checkHopLeThangNam(monthTxt.getText(), yearTxt.getText())==false)
        {
            JOptionPane.showMessageDialog(this, "Thang nam khong hop le", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(checkHopLeNgayThangNam(dateTxt.getText(),monthTxt.getText(), yearTxt.getText())==false)
        {
            JOptionPane.showMessageDialog(this, "Ngay khong hop le", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }


        TableColumn column = jTable1.getColumnModel().getColumn(2);
        column.setResizable(true); // Cho phép cột thay đổi kích thước nếu cần thiết
        column.setMinWidth(155);
        column.setMaxWidth(155);
        column.setWidth(155);
        column.setPreferredWidth(155);
        column.setResizable(false);
        int ngay = Integer.parseInt(dateTxt.getText());
        int thang = Integer.parseInt(monthTxt.getText());
        int nam = Integer.parseInt(yearTxt.getText());
        thongKeNgay(ngay,thang,nam);
    }//GEN-LAST:event_btnNgayMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.LapTrinhTienTien.ui.customItem.button btnNgay;
    private org.LapTrinhTienTien.ui.customItem.button btnThang;
    private org.LapTrinhTienTien.ui.customItem.button btnToanTg;
    private org.LapTrinhTienTien.ui.customItem.ChartLine chartLine1;
    private javax.swing.JTextField dateTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField monthTxt;
    private javax.swing.JTextField yearTxt;
    // End of variables declaration//GEN-END:variables
}
