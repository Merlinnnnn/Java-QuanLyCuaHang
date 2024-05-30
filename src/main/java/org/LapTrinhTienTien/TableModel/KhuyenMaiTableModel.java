package org.LapTrinhTienTien.TableModel;


import org.LapTrinhTienTien.model.ChuongTrinhKhuyenMai;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class KhuyenMaiTableModel extends AbstractTableModel {
    private List<ChuongTrinhKhuyenMai> lstKhuyenMais;
    private final String[] COLUMNS ={"Mã KM","Tên Chương Trinh","Ngày Bắt đầu","Ngày Kết thúc","Phần Trăm giảm giá","Diều kiện áp dụng","Trạng Trái"};
    public KhuyenMaiTableModel(List<ChuongTrinhKhuyenMai> lstKhuyenMais){
        this.lstKhuyenMais = lstKhuyenMais;
    }
    @Override
    public int getRowCount() {
        return lstKhuyenMais.size();
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
            case 0->lstKhuyenMais.get(rowIndex).getMaCT();
            case 1->lstKhuyenMais.get(rowIndex).getTenChuongTrinh();
            case 2->lstKhuyenMais.get(rowIndex).getNgayApDung();
            case 3->lstKhuyenMais.get(rowIndex).getNgayKetThuc();
            case 4->lstKhuyenMais.get(rowIndex).getPhanTramGiamGia();
            case 5->lstKhuyenMais.get(rowIndex).getTonghonDon();
            case 6->lstKhuyenMais.get(rowIndex).getStatus();

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
    private boolean[] canEdit = {false,false,false,false,false,false,false};
    private boolean cloumnCanEidt(int columnIndex){
        return canEdit[columnIndex];
        
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return cloumnCanEidt(columnIndex); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    public ChuongTrinhKhuyenMai getRow(int row){
        return lstKhuyenMais.get(row);
    }
    
    
}
