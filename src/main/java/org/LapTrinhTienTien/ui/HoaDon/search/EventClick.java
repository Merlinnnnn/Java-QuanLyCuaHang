package org.LapTrinhTienTien.ui.HoaDon.search;

import org.LapTrinhTienTien.model.GioHang;

import java.awt.Component;

public interface EventClick {

    public void itemClick(GioHang data);

    public void itemRemove(Component com, GioHang data);
}
