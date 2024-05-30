/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.LapTrinhTienTien.ui.HoaDon.tablecustom;

import org.LapTrinhTienTien.TableModel.HoaDonTableModel;
import org.LapTrinhTienTien.model.GioHang;
import org.LapTrinhTienTien.TableModel.GioHangTableModel;
import org.LapTrinhTienTien.service.CuaHangSanPhamService;
import org.LapTrinhTienTien.ui.HoaDon.CreateBill;
import org.LapTrinhTienTien.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.Component;
import javax.swing.*;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author quyth
 */
public class TableTextViewEditer extends DefaultCellEditor{
    CuaHangSanPhamService cuaHangSanPhamService;

    private JSpinner input;

    private JTable table;
    private int row;
    GioHang item;
    private EventCellInputChange event;
   // private ModelItemSell item;


    public TableTextViewEditer(EventCellInputChange event, CuaHangSanPhamService cuaHangSanPhamService) {
       
        super(new JCheckBox());
          this.event = event;
        input = new JSpinner();
        SpinnerNumberModel numberModel = (SpinnerNumberModel) input.getModel();
        numberModel.setMinimum(1);
        this.cuaHangSanPhamService = cuaHangSanPhamService;
         input.addChangeListener((ChangeEvent e) -> {
            inputChange();
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
         super.getTableCellEditorComponent(table, value, isSelected, row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody;
         this.table = table;
        this.row = row;
       GioHangTableModel tableModel = (GioHangTableModel) table.getModel();
        this.item =(GioHang) tableModel.getRow(row);
        int qty = Integer.parseInt(value.toString());
        input.setValue(qty);
        input.setEnabled(false);
        enable();
         return input;
    }
        private void enable() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    input.setEnabled(true);
                } catch (Exception e) {

                }
            }
        }).start();
    }
    @Override
    public Object getCellEditorValue() {
        return input.getValue(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
    private void inputChange() {
        int qty = Integer.parseInt(input.getValue().toString());
        if (qty != item.getSoLuong()) {
            //DecimalFormat df = new DecimalFormat("#,##0.##");
            Response response =  cuaHangSanPhamService.isCheckSoLuong(item.getMaSP(),qty);
            if(response.getFlag()){
                item.setSoLuong(qty);
            }else {
                JOptionPane.showMessageDialog(null,response.getMessage());
            }

            //table.setValueAt("$ " + df.format(item.getTongTien()), row, 3);
           ((GioHangTableModel)table.getModel()).fireTableCellUpdated(row, 4);
            ((GioHangTableModel)table.getModel()).fireTableCellUpdated(row, 2);
            event.inputChanged();
        }
    }
    
}
