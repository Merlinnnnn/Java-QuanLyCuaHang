package org.LapTrinhTienTien.service;

import org.LapTrinhTienTien.model.KhachHang;
import org.LapTrinhTienTien.model.TaiKhoan;
import org.LapTrinhTienTien.repository.KhachHangRepository;
import org.LapTrinhTienTien.repository.TaiKhoanRepository;
import org.LapTrinhTienTien.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;

@Service
public class KhachHangService {
    @Autowired
    KhachHangRepository khRepository;
    private boolean checkEmpty(String name, String sdt)
    {
        return !name.isEmpty() && !sdt.isEmpty();
    }

    private boolean isValidPhoneNumber(String sdt) {
        // Kiểm tra xem số điện thoại có đúng độ dài 10 ký tự không
        if (sdt.length() != 10) {
            return false;
        }

        // Kiểm tra xem số điện thoại có bắt đầu bằng số 0 không và có chứa toàn bộ ký tự số không
        return sdt.matches("^0\\d+$");
    }
    public Response addCustomer(String name, String Sdt)
    {
        if(!checkEmpty(name,Sdt)) {
            //JOptionPane.showMessageDialog(null, "Họ tên và số điện thoại không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return new Response("Ho ten va so dien thoai khong duoc de trong", false, null);
        }
        if (!isValidPhoneNumber(Sdt)) {
            return new Response("Số điện thoại không hợp lệ", false, null);
        }
        KhachHang kh = khRepository.findBySdt(Sdt);
        if(kh!=null)
            return new Response("So dien thoai da ton tai",false,null);
        return new Response("Them thanh cong",true,null);
    }
    public void updateCustomer(Long id, String name, String sdt) {
        // Kiểm tra khách hàng có tồn tại trong cơ sở dữ liệu không
        KhachHang khachHang = khRepository.findById(id).orElse(null);
        khachHang.setHoTenKH(name);
        khachHang.setSdt(sdt);

        // Lưu thông tin đã cập nhật vào cơ sở dữ liệu
        khRepository.save(khachHang);
    }

    public boolean isCheckSDTExist(String sdt){
        return khRepository.findBySdt(sdt)!=null;
    }
}