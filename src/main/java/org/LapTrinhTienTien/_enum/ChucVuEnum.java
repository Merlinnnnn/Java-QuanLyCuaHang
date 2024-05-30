package org.LapTrinhTienTien._enum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

public enum ChucVuEnum {
    NHAN_VIEN("CV0001", "Nhân viên",200000),
    QUAN_LY("CV0002", "Quản lý",400000);
    private final String maCV;
    private final String tenCV;
    private int giaTienMotTieng;
    ChucVuEnum(String maCV, String tenCV,int giaTienMotTieng) {
        this.maCV = maCV;
        this.tenCV = tenCV;
        this.giaTienMotTieng = giaTienMotTieng;
    }

    public String getMaCV() {
        return maCV;
    }

    public String getTenCV() {
        return tenCV;
    }
    public int getGiaTienMotTieng() {
        return giaTienMotTieng;
    }
}
