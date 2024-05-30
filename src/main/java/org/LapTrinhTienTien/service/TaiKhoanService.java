package org.LapTrinhTienTien.service;

import org.LapTrinhTienTien.model.TaiKhoan;
import org.LapTrinhTienTien.repository.TaiKhoanRepository;
import org.LapTrinhTienTien.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaiKhoanService {
    @Autowired
    TaiKhoanRepository tkRepository;
     private boolean isCheckEmpty(String username, String password) {
        return !username.isEmpty() && !password.isEmpty();
    }
    public Response login(String username, String password) {
         if(!isCheckEmpty(username, password)) {
             return new Response("Tai khoan, mat khau không để trống",false,null);
         }
         TaiKhoan tk = tkRepository.findByTaiKhoan(username);
         if(tk == null) {
             return new Response("Tai khoan khong chinh xac",false,null);
         }
         if(!tk.getMatKhau().equals(password))
            return new Response("Mat khau khong chinh xac",false,null);
         return new Response("Login thanh cong ",true,tk);
    }
}
