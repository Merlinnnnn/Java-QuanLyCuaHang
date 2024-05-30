package org.LapTrinhTienTien.TableModel;

import lombok.Setter;
import org.LapTrinhTienTien.model.ChiTietHoaDon;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ChiTietHoaDonTableModel extends AbstractTableModel {
    @Setter
    private List<ChiTietHoaDon> lstCTHDs;
    private final String[] COLUMNS ={"Tên SP","Số lượng","Giá tiền","Thành Tiền"};
    public ChiTietHoaDonTableModel(List<ChiTietHoaDon> lstCTHDs) {
        this.lstCTHDs = lstCTHDs;
    }

    @Override
    public int getRowCount() {
        return lstCTHDs.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return COLUMNS[columnIndex];
    }
    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }


    /**
     *
     * @param rowIndex
     * @param columnIndex
     * @return
     */

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex){
            case 0->lstCTHDs.get(rowIndex).getSanPham().getTenSP();
            case 1->lstCTHDs.get(rowIndex).getGiaThanhToan();
            case 2->lstCTHDs.get(rowIndex).getSoLuong();
            case 3->lstCTHDs.get(rowIndex).getTongTien();
            default -> "-";
        };
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(getValueAt(0,columnIndex)!=null){
            return getValueAt(0,columnIndex).getClass();
        }else{
            return Object.class;
        }
    }
    private boolean[] canEdit = {false,false,false,false};
    private boolean cloumnCanEidt(int columnIndex){
        return canEdit[columnIndex];

    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return cloumnCanEidt(columnIndex); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    public ChiTietHoaDon getRow(int row){
        return lstCTHDs.get(row);
    }

}
