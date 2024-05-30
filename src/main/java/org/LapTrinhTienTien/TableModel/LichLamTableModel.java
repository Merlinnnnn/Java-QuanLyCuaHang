package org.LapTrinhTienTien.TableModel;

import lombok.Getter;
import lombok.Setter;
import org.LapTrinhTienTien.model.LichLam;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class LichLamTableModel extends AbstractTableModel {
    @Setter@Getter
    private List<LichLam> lstLichLams;
    private final String[] COLUMNS ={"Mã Nhân Viên","Giờ Bắt Đầu","Giờ Kết Thúc","Mã Ca Làm Việc","Ngày làm"};
    public LichLamTableModel(List<LichLam> lstLichLams){
        this.lstLichLams = lstLichLams;
    }
    @Override
    public int getRowCount() {
        return lstLichLams.size();
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
            case 0->lstLichLams.get(rowIndex).getId().getMaNV();
            case 1->lstLichLams.get(rowIndex).getCaLamViec().getMaCa();
            case 2->lstLichLams.get(rowIndex).getCaLamViec().getGioBatDau();
            case 3->lstLichLams.get(rowIndex).getCaLamViec().getGiaKetThuc();
            case 4->lstLichLams.get(rowIndex).getId().getNgayThangNam();
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

    public boolean AddSP(LichLam sp){
        return lstLichLams.add(sp);
    }
    public Boolean removeSP(LichLam sp){
        return lstLichLams.remove(sp);
    }
    public LichLam getRow(int index){
        if(index >= getRowCount())
            return null;
        return lstLichLams.get(index);
    }
}
