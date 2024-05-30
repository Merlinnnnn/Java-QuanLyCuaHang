/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.LapTrinhTienTien.ui.Admin;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.LapTrinhTienTien.model.CuaHangSanPham;
import org.LapTrinhTienTien.service.CuaHangSanPhamService;
import org.LapTrinhTienTien.ui.customItem.ProductCard;
import org.LapTrinhTienTien.ui.customItem.ScrollBar;
import org.LapTrinhTienTien.ui.customItem.WrapLayout;
import org.LapTrinhTienTien.ui.events.ProductCardClick;
import org.LapTrinhTienTien.ui.model.modelProduct;
import org.LapTrinhTienTien.utils.Response;
import org.apache.poi.EncryptedDocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hi
 */
@Service
public class formQlKho extends javax.swing.JPanel {

    /**
     * Creates new form formQlKho
     */
    @Autowired
    CuaHangSanPhamService cuahangSanPhamService;
    @Autowired
    addSanPham addAdminSanPham;
    private CuaHangSanPhamService cuaHangSanPhamService;
    List<CuaHangSanPham> cuaHangSanPhamList = new ArrayList<>();

    public formQlKho(@Autowired CuaHangSanPhamService cuaHangSanPhamService) {
        initComponents();
        this.cuaHangSanPhamService = cuaHangSanPhamService;
        loadData();
        events();
    }

    private void events() {
        btnReload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });
        btnThem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                themSanPham();
            }
        });
        btnNhapKho.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnNhapExcel();
            }
        });

    }
    private boolean isRowEmpty(Row row) {
        for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false; // Dòng không rỗng
            }
        }
        return true; // Dòng rỗng
    }
    private void btnNhapExcel() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel files", "xlsx", "xls");
        fileChooser.setFileFilter(filter);

        int returnValue = fileChooser.showOpenDialog(null);
        String error = "";
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                File selectedFile = fileChooser.getSelectedFile();
                FileInputStream file = new FileInputStream(selectedFile);
                Workbook workbook = WorkbookFactory.create(file);
                Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên
                int rowNum = 0;
                for (Row row : sheet) {
                    if (rowNum == 0) {
                        rowNum++;
                        continue;
                    }
                    ++rowNum;
                    if (isRowEmpty(row)) {
                        System.out.println("Dòng rỗng");
                        return; // Bỏ qua dòng nếu dòng là rỗng
                    }
                    Cell maSPCell = row.getCell(0);
                    Cell maCHCell = row.getCell(1);
                    Cell soLuongCell = row.getCell(2);

                    String maSP = maSPCell.getStringCellValue();
                    String maCH = maCHCell.getStringCellValue();
                    int soLuong = (int) soLuongCell.getNumericCellValue();

                   Response response =  cuahangSanPhamService.updateNhapKho(maCH,maSP,soLuong);
                    if(!response.getFlag()){
                        error += response.getMessage()+" row"+row+", ";
                    }
                }
                if(!error.isEmpty()){
                    JOptionPane.showMessageDialog(this,error,"Lỗi",JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(this,"Thêm file thành công","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                file.close();
            } catch (IOException | EncryptedDocumentException ex) {
                ex.printStackTrace();
            }
        } else {
            // User canceled the file chooser dialog
            // Handle this case if needed
        }

    }

    private void themSanPham() {

        

        // Hiển thị form saleForm
        addAdminSanPham.setVisible(true);
        addAdminSanPham.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);;
    }

    private void loadData() {
        try {
            cuaHangSanPhamList.clear();
            cuaHangSanPhamList = cuaHangSanPhamService.getAll();
            init();
        } catch (Exception e) {
           // e.printStackTrace();
            // Xử lý exception ở đây nếu cần thiết
        }

    }
    private void onClickToCardProduct(CuaHangSanPham chsp){
        lblMaSP.setText("Mã SP: "+chsp.getSanPham().getMaSP());
        lblSoLuong.setText("Sô Lượng: "+ chsp.getSoLuong());
    }

    private void init() {
        panel.removeAll();
        panel.setLayout(new WrapLayout(WrapLayout.LEADING));
        scrollPane.setVerticalScrollBar(new ScrollBar());
        for(CuaHangSanPham chsp : cuaHangSanPhamList){

            String imageResource = chsp.getSanPham().getImage();
            ImageIcon icon = null;
            System.out.println("Image"+imageResource);
            if (imageResource != null) {
                ClassPathResource resource1 = new ClassPathResource(imageResource);
                try {
                    Image image = ImageIO.read(resource1.getInputStream());
                    int width = 400; // Đặt chiều rộng mong muốn
                    int height =400; // Đặt chiều cao mong muốn
                    Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

                    icon = new ImageIcon(scaledImage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }


            panel.add(new ProductCard(new modelProduct(icon, chsp.getSanPham().getTenSP(), "Mã Sản Phẩm: "+chsp.getSanPham().getMaSP()+"\n"+"Giá Tiên: "+chsp.getSanPham().getTienThanhToan()+"\nSố lượng:" + chsp.getSoLuong()+"\nthank for watch"), new ProductCardClick() {
                @Override
                public void onCLickCard(CuaHangSanPham cuaHangSanPham) {
                    onClickToCardProduct(cuaHangSanPham);
                }
            },chsp));
        }

        panel.revalidate();
        panel.repaint();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        myPanelBoxShadow1 = new org.LapTrinhTienTien.ui.customItem.MyPanelBoxShadow();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        btnReload = new org.LapTrinhTienTien.ui.customItem.button();
        btnNhapKho = new org.LapTrinhTienTien.ui.customItem.button();
        btnThem = new org.LapTrinhTienTien.ui.customItem.button();
        btnKLuu = new org.LapTrinhTienTien.ui.customItem.button();
        lblMaSP = new javax.swing.JLabel();
        lblSoLuong = new javax.swing.JLabel();

        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);

        panel.setOpaque(false);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 613, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 328, Short.MAX_VALUE)
        );

        scrollPane.setViewportView(panel);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Kho hàng");

        jSeparator1.setForeground(new java.awt.Color(51, 51, 51));

        jSeparator2.setForeground(new java.awt.Color(51, 51, 51));

        btnReload.setText("Reload");
        btnReload.setActionCommand("");
        btnReload.setBorderColor(new java.awt.Color(0, 0, 204));
        btnReload.setRadius(35);

        btnNhapKho.setText("Nhập Kho");
        btnNhapKho.setActionCommand("");
        btnNhapKho.setBorderColor(new java.awt.Color(0, 0, 204));
        btnNhapKho.setRadius(35);

        btnThem.setText("Thêm");
        btnThem.setActionCommand("");
        btnThem.setBorderColor(new java.awt.Color(0, 0, 204));
        btnThem.setRadius(35);

        btnKLuu.setText("K.Lưu");
        btnKLuu.setActionCommand("");
        btnKLuu.setBorderColor(new java.awt.Color(0, 0, 204));
        btnKLuu.setRadius(35);

        lblMaSP.setText("Mã SP:");

        lblSoLuong.setText("Số lượng: ");

        javax.swing.GroupLayout myPanelBoxShadow1Layout = new javax.swing.GroupLayout(myPanelBoxShadow1);
        myPanelBoxShadow1.setLayout(myPanelBoxShadow1Layout);
        myPanelBoxShadow1Layout.setHorizontalGroup(
            myPanelBoxShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myPanelBoxShadow1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(myPanelBoxShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(myPanelBoxShadow1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(myPanelBoxShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(myPanelBoxShadow1Layout.createSequentialGroup()
                        .addComponent(btnNhapKho, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnKLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(myPanelBoxShadow1Layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnReload, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(48, 48, 48))
        );
        myPanelBoxShadow1Layout.setVerticalGroup(
            myPanelBoxShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myPanelBoxShadow1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMaSP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(lblSoLuong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
            .addGroup(myPanelBoxShadow1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(myPanelBoxShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReload, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(myPanelBoxShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNhapKho, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                    .addComponent(myPanelBoxShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(myPanelBoxShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.LapTrinhTienTien.ui.customItem.button btnKLuu;
    private org.LapTrinhTienTien.ui.customItem.button btnNhapKho;
    private org.LapTrinhTienTien.ui.customItem.button btnReload;
    private org.LapTrinhTienTien.ui.customItem.button btnThem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblMaSP;
    private javax.swing.JLabel lblSoLuong;
    private org.LapTrinhTienTien.ui.customItem.MyPanelBoxShadow myPanelBoxShadow1;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
