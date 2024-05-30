/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.LapTrinhTienTien.ui.HoaDon;
import org.LapTrinhTienTien.StaticApp.Global;
import org.LapTrinhTienTien.TableModel.GioHangTableModel;
import org.LapTrinhTienTien.model.GioHang;
import org.LapTrinhTienTien.service.*;
import org.LapTrinhTienTien.ui.HoaDon.search.*;
import org.LapTrinhTienTien.ui.HoaDon.tablecustom.*;
import org.LapTrinhTienTien.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


@Controller
public class CreateBill extends javax.swing.JFrame {
    CuaHangSanPhamService khoService;
    @Autowired
    ChuongTrinhKhuyenMaiService chuongTrinhKhuyenMaiService;
    @Autowired
    ThanhToanService thanhToanService;
    @Autowired
    KhachHangService khachHangService;
    private JPopupMenu menu;
    private PanelSearch search;
    private GioHangTableModel hoaDonTableModel;
    List<GioHang> gioHangs = new ArrayList<>();
    List<GioHang> storegioHang = new ArrayList<>();
    public CreateBill(@Autowired CuaHangSanPhamService cuaHangSanPhamService) {
        initComponents();
        this.khoService = cuaHangSanPhamService;
        menu = new JPopupMenu();
        search = new PanelSearch();
        hoaDonTableModel = new GioHangTableModel(gioHangs);
        tblHoaDon.setModel(hoaDonTableModel);
        menu.setBorder(BorderFactory.createLineBorder(new Color(164, 164, 164)));
        menu.add(search);
        menu.setFocusable(false);
        tblHoaDon.setRowHeight(40);
        
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                System.err.println("onEdit");
            }

            @Override
            public void onDelete(int row) {
                gioHangs.remove(row);
                hoaDonTableModel.fireTableDataChanged();
            }

            @Override
            public void onView(int row) {
               System.err.println("onView");
            }
        };
        
        tblHoaDon.getColumnModel().getColumn(2).setCellEditor(new TableTextViewEditer(new EventCellInputChange() {
            @Override
            public void inputChanged() {
               gioHangs.forEach(gioHang-> System.out.println(gioHang.getTongTien()));
            }
        },khoService));
        tblHoaDon.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(event));
        tblHoaDon.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
        tblHoaDon.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                setHorizontalAlignment(SwingConstants.RIGHT);
                return super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
            }
        });
        search.addEventClick(new EventClick() {
            private Boolean isCheckSPAdd(GioHang data){
                for(GioHang gh : gioHangs){
                    if(gh.getMaSP().equals(data.getMaSP())){
                        JOptionPane.showMessageDialog(CreateBill.this,"San Pham đã tồn tại trong giỏ hàng");
                        return false;
                    }
                }
                return true;
            }
            @Override
            public void itemClick(GioHang data) {
                menu.setVisible(false);
                txtSearch.setText("");
                if(isCheckSPAdd(data)){
                    gioHangs.add(data);
                    hoaDonTableModel.fireTableDataChanged();
                }
                menu.setVisible(false);
                hoaDonTableModel.fireTableDataChanged();
            }

            @Override
            public void itemRemove(Component com, GioHang data) {
                //search.remove(com);
               // removeHistory(data.getText());
                menu.setPopupSize(menu.getWidth(), (search.getItemSize() * 35) + 2);
                if (search.getItemSize() == 0) {
                    menu.setVisible(false);
                }
                 menu.setVisible(false);
                txtSearch.setText("");
                String maCh = Global.account.getNhanVien().getCuaHang().getMaCH();
                Response response =  khoService.isCheckSoLuong(data.getMaSP(),data.getSoLuong());
                if(response.getFlag()){
                    if(isCheckSPAdd(data)){
                        gioHangs.add(data);
                        hoaDonTableModel.fireTableDataChanged();
                    }
                }else {
                    JOptionPane.showMessageDialog(CreateBill.this,response.getMessage());
                }

                 
               // System.out.println("Remove Item : " + data.getText());
            }
        });
        events();
    }
    private void events(){
        btnThanhToan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnThanhToanPerformed(e);
            }
        });
        btnBill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnBillPerformed(e);
            }
        });
        btnKiemTraSdt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnCheckSdt(e);
            }
        });
    }
    private void btnCheckSdt(ActionEvent e){
        String sdt = tf_sdt.getText()+"";
        if(sdt.equals("")){
            return;
        }
        if (khachHangService.isCheckSDTExist(sdt)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Số điện thoại chưa có trong hệ thống!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }


    }
    private float getGiamGia(){
        float tongTien = getTongTien();
        return chuongTrinhKhuyenMaiService.getGiamGia(tongTien);
    }
    private float getTongTien(){
       return (float) gioHangs.stream().mapToDouble(GioHang::getTongTien).sum();
    }
    private void btnBillPerformed(ActionEvent e) {
        bill.setText("                         The Little mall \n");
        bill.setText(bill.getText() + "\tSố 1, Võ Văn Ngân \n");
        bill.setText(bill.getText() + "\tThủ đức, Thành phố Hồ Chí Minh, \n");
        bill.setText(bill.getText() + "\t+084 123456789, \n");
        bill.setText(bill.getText() + "----------------------------------------------------------------\n");
        bill.setText(bill.getText() + " Ten \tSo Luong \tGia \tThanh Tien \n");
        bill.setText(bill.getText() + "----------------------------------------------------------------\n");


        for(GioHang gh : gioHangs){

            String name =gh.getTenSP();
            String qt =String.valueOf(gh.getSoLuong()) ;
            String prc =String.valueOf( gh.getGiaTien());
            String tongtien = String.valueOf(gh.getTongTien());
            bill.setText(bill.getText() + name+"\t  "+qt+"\t"+prc+"S\t"+tongtien+" \n");

        }
        bill.setText(bill.getText() + "----------------------------------------------------------------\n");
        bill.setText(bill.getText() + "Tổng Tiền :    \t"+getTongTien()+"\n");
        bill.setText(bill.getText() + "Giam gia:         \t"+getGiamGia()+"\n");
        bill.setText(bill.getText() + "Thành Tiền : \t"+(getTongTien()-getGiamGia())+"\n");
        bill.setText(bill.getText() + "====================================\n");
        bill.setText(bill.getText() +"                     Thanks For Your Business...!"+"\n");
        bill.setText(bill.getText() + "----------------------------------------------------------------\n");
    }
    protected  void btnThanhToanPerformed(ActionEvent e){
        if(gioHangs.size() ==0){
            JOptionPane.showMessageDialog(this,"Vừa lòng chọn sản phẩm thanh toán");
            return;
        }
      Response response =   thanhToanService.thanhToan(gioHangs,tf_sdt.getText()+"");
        if (response.getFlag()) {
            btnThanhToan.setEnabled(false);
            JOptionPane.showMessageDialog(this, response.getMessage(), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Lỗi: " + response.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
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

        jScrollPane2 = new javax.swing.JScrollPane();
        bill = new javax.swing.JTextArea();
        myPanelBoxShadow1 = new org.LapTrinhTienTien.ui.customItem.MyPanelBoxShadow();
        tf_sdt = new javax.swing.JTextField();
        btnKiemTraSdt = new org.LapTrinhTienTien.ui.customItem.button();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        txtSearch = new org.LapTrinhTienTien.ui.HoaDon.search.MyTextField();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnThanhToan = new org.LapTrinhTienTien.ui.customItem.button();
        btnBill = new org.LapTrinhTienTien.ui.customItem.button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bill.setEditable(false);
        bill.setColumns(20);
        bill.setRows(5);
        jScrollPane2.setViewportView(bill);

        tf_sdt.setBackground(new java.awt.Color(242, 242, 242));
        tf_sdt.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        tf_sdt.setBorder(null);

        btnKiemTraSdt.setText("Kiểm tra");
        btnKiemTraSdt.setRadius(35);

        jScrollPane1.setViewportView(tblHoaDon);

        txtSearch.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); // NOI18N
        txtSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSearchMouseClicked(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Nhập sđt:");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        btnThanhToan.setText("Thanh toán");
        btnThanhToan.setRadius(35);

        btnBill.setText("Xuất hóa đơn");
        btnBill.setRadius(35);

        javax.swing.GroupLayout myPanelBoxShadow1Layout = new javax.swing.GroupLayout(myPanelBoxShadow1);
        myPanelBoxShadow1.setLayout(myPanelBoxShadow1Layout);
        myPanelBoxShadow1Layout.setHorizontalGroup(
            myPanelBoxShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myPanelBoxShadow1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(myPanelBoxShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(myPanelBoxShadow1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(142, 142, 142))
                    .addGroup(myPanelBoxShadow1Layout.createSequentialGroup()
                        .addComponent(btnKiemTraSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBill, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(myPanelBoxShadow1Layout.createSequentialGroup()
                        .addGroup(myPanelBoxShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        myPanelBoxShadow1Layout.setVerticalGroup(
            myPanelBoxShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, myPanelBoxShadow1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGroup(myPanelBoxShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(myPanelBoxShadow1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tf_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(myPanelBoxShadow1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addGroup(myPanelBoxShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnKiemTraSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBill, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(myPanelBoxShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(myPanelBoxShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchMouseClicked
       System.out.println("----------");
        if (search.getItemSize() > 0) {
            menu.show(txtSearch, 0, txtSearch.getHeight());
        }
    }//GEN-LAST:event_txtSearchMouseClicked

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        String text = txtSearch.getText().trim().toLowerCase();
        search.setData(search(text));
        if (search.getItemSize() > 0) {
            //  * 2 top and bot border
            menu.show(txtSearch, 0, txtSearch.getHeight());
            menu.setPopupSize(menu.getWidth(), (search.getItemSize() * 35) + 2);
        } else {
            menu.setVisible(false);
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private List<GioHang> search(String search) {
        return khoService.searchTenSP(search);
    }
    String dataStory[] = {"300 - Rise of an Empire",
        "Empire Records",
        "Empire State",
        "Frozen",
        "The Courier"};

    private void removeHistory(String text) {
        for (int i = 0; i < dataStory.length; i++) {
            String d = dataStory[i];
            if (d.toLowerCase().equals(text.toLowerCase())) {
                dataStory[i] = "";
            }
        }
    }

    private boolean isStory(String text) {
        for (String d : dataStory) {
            if (d.toLowerCase().equals(text.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

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
            java.util.logging.Logger.getLogger(CreateBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new CreateBill().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea bill;
    private org.LapTrinhTienTien.ui.customItem.button btnBill;
    private org.LapTrinhTienTien.ui.customItem.button btnKiemTraSdt;
    private org.LapTrinhTienTien.ui.customItem.button btnThanhToan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private org.LapTrinhTienTien.ui.customItem.MyPanelBoxShadow myPanelBoxShadow1;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField tf_sdt;
    private org.LapTrinhTienTien.ui.HoaDon.search.MyTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
