package org.LapTrinhTienTien.StaticApp;

import org.LapTrinhTienTien.model.TaiKhoan;

public class Global {
    public static TaiKhoan account = null;
    public static void logout(){
        account = null;
    }
}
