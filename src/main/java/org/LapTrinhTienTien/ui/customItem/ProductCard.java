package org.LapTrinhTienTien.ui.customItem;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import org.LapTrinhTienTien.model.CuaHangSanPham;
import org.LapTrinhTienTien.ui.events.ProductCardClick;
import org.LapTrinhTienTien.ui.model.modelProduct;

public class ProductCard extends javax.swing.JPanel {

    private final modelProduct data;
    private final Timer timer;
    private final Timer timerStop;
    private final ProductInfo productIn4;
    private int y = 140;
    private int speed = 2;
    private boolean showing = false;
    private CuaHangSanPham cuaHangSanPham;
    private ProductCardClick productCardClick;;
    public ProductCard(modelProduct data, ProductCardClick productCardClick,CuaHangSanPham cuaHangSanPham) {
        this.productCardClick = productCardClick;
        this.cuaHangSanPham = cuaHangSanPham;
        this.data = data;
        initComponents();
        setOpaque(false);
        productIn4 = new ProductInfo(data.getTitle(), data.getDescription());
        productIn4.setLocation(0, y);
        setPreferredSize(new Dimension(180, 200));
        productIn4.setSize(new Dimension(180, 150));
        add(productIn4);
        timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (showing) {
                    y -= speed;
                    if (y <= 50) {
                        y = 50;
                        productIn4.setLocation(0, y);
                        timer.stop();
                    } else {
                        productIn4.setLocation(0, y);
                    }
                } else {
                    y += speed;
                    if (y >= 140) {
                        y = 140;
                        productIn4.setLocation(0, y);
                        timer.stop();
                    } else {
                        productIn4.setLocation(0, y);
                    }
                }
            }
        });
        //  500 for delay hide description
        timerStop = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                showing = false;
                timerStop.stop();
                timer.start();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                showing = true;
                timerStop.stop();
                timer.start();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                timerStop.start();
            }
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setOpaque(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 165, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
       productCardClick.onCLickCard(cuaHangSanPham);
    }//GEN-LAST:event_formMousePressed

@Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        Rectangle size = getAutoSize(data.getIcon());
        g2.drawImage(toImage(data.getIcon()), size.x, size.y, size.width, size.height, null);
        super.paintComponent(grphcs);
    }

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, getHeight(), new Color(64, 67, 186, 200), 0, getHeight() - 50, new Color(0, 0, 0, 0));
        g2.setPaint(g);
        g2.fillRect(0, 0, getWidth(), getHeight());
    }

    private Rectangle getAutoSize(Icon image) {
        int w = 150;
        int h = 200;
        int iw = image.getIconWidth();
        int ih = image.getIconHeight();
        double xScale = (double) w / iw;
        double yScale = (double) h / ih;
        double scale = Math.max(xScale, yScale);
        int width = (int) (scale * iw);
        int height = (int) (scale * ih);
        int x = (w - width) / 2;
        int y = (h - height) / 2;
        return new Rectangle(new Point(x, y), new Dimension(width, height));
    }

    private Image toImage(Icon icon) {
        return ((ImageIcon) icon).getImage();
    }

    // Variables declaration - do not modify                     
    // End of variables declaration                   
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

