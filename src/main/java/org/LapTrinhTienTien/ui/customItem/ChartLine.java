/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.LapTrinhTienTien.ui.customItem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;
import org.LapTrinhTienTien.ui.model.modelChartLine;

/**
 *
 * @author Hi
 */
public class ChartLine extends javax.swing.JPanel {
    public List<modelChartLine> getModel() {
        return model;
    }

    public void setModel(List<modelChartLine> model) {
        this.model = model;
        initData();
    }

    private List<modelChartLine> model;

    public ChartLine() {
        initComponents();
        setOpaque(false);
        setBackground(Color.WHITE);
    }

    private void initData() {
        panelChartLine.removeAllData();
        if (model != null) {
            for (modelChartLine data : model) {
                panelChartLine.addItem(data);
                panelData.add(new ItemChartLine(data));
            }
        }
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(grphcs);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelChartLine = new org.LapTrinhTienTien.ui.customItem.PanelChartLine();
        panelData = new javax.swing.JPanel();

        javax.swing.GroupLayout panelChartLineLayout = new javax.swing.GroupLayout(panelChartLine);
        panelChartLine.setLayout(panelChartLineLayout);
        panelChartLineLayout.setHorizontalGroup(
            panelChartLineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 309, Short.MAX_VALUE)
        );
        panelChartLineLayout.setVerticalGroup(
            panelChartLineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 195, Short.MAX_VALUE)
        );

        panelData.setOpaque(false);
        panelData.setLayout(new javax.swing.BoxLayout(panelData, javax.swing.BoxLayout.Y_AXIS));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelChartLine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelData, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelChartLine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.LapTrinhTienTien.ui.customItem.PanelChartLine panelChartLine;
    private javax.swing.JPanel panelData;
    // End of variables declaration//GEN-END:variables
}
