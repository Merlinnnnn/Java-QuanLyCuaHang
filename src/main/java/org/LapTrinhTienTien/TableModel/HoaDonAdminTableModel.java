package org.LapTrinhTienTien.TableModel;

import lombok.Getter;
import lombok.Setter;
import org.LapTrinhTienTien.model.HoaDon;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class HoaDonAdminTableModel extends AbstractTableModel {
    @Setter@Getter
    private List<HoaDon> lstHoaDons;
    private final String[] COLUMNS ={"Mã HD","Mã NV","Đơn Giá","Ngày Xuất","Trang Thái"};
    public HoaDonAdminTableModel(List<HoaDon> lstHoaDons){
        this.lstHoaDons = lstHoaDons;
    }
    @Override
    public int getRowCount() {
        return lstHoaDons.size();
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
            case 0->lstHoaDons.get(rowIndex).getMaHD();
            case 1->lstHoaDons.get(rowIndex).getNhanVien().getMaNV();
            case 2->lstHoaDons.get(rowIndex).getThanhTien();
            case 3->lstHoaDons.get(rowIndex).getNgayXuat();
            case 4->lstHoaDons.get(rowIndex).getStatus();
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

    public boolean AddSP(HoaDon sp){
        return lstHoaDons.add(sp);
    }
    public Boolean removeSP(HoaDon sp){
        return lstHoaDons.remove(sp);
    }
    public HoaDon getRow(int index){
        if(index >= getRowCount())
            return null;
        return lstHoaDons.get(index);
    }
}
