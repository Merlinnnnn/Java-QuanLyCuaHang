/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.LapTrinhTienTien.service;

import jakarta.transaction.Transactional;
import javax.swing.JOptionPane;

import org.LapTrinhTienTien.model.NhaCungCap;
import org.LapTrinhTienTien.repository.NhaCungCapRepository;
import org.LapTrinhTienTien.utils.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class NhaCungCapService {
    
    @Autowired
    private NhaCungCapRepository nhaCungCapRepository;
    
    
    public NhaCungCap addNCC(String maNCC, String tenNCC, String diaChi, String sdt) {
        NhaCungCap ncc = new NhaCungCap();
        
        ncc.setMaNCC(maNCC);
        ncc.setTenNCC(tenNCC);
        ncc.setDiaChi(diaChi);
        ncc.setSdt(sdt);
        
        nhaCungCapRepository.save(ncc);
        return ncc;
    }
    public void updateNCC(String maNCC, String tenNCC, String diaChi, String sdt)
    {
        if(!checkEmpty(tenNCC,diaChi, sdt)) {
            JOptionPane.showMessageDialog(null, "Thông tin không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        if (!isValidPhoneNumber(sdt)) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        NhaCungCap ncc = nhaCungCapRepository.findById(maNCC).orElse(null);
        ncc.setTenNCC(tenNCC);
        ncc.setDiaChi(diaChi);
        ncc.setSdt(sdt);
        
        nhaCungCapRepository.save(ncc);
    }
    private boolean checkEmpty(String name, String diaChi, String sdt)
        {
            return !name.isEmpty() && !diaChi.isEmpty() && !sdt.isEmpty();
        }

    private boolean isValidPhoneNumber(String sdt) {
        // Kiểm tra xem số điện thoại có đúng độ dài 10 ký tự không
        if (sdt.length() != 10) {
            return false;
        }

        // Kiểm tra xem số điện thoại có bắt đầu bằng số 0 không và có chứa toàn bộ ký tự số không
        return sdt.matches("^0\\d+$");
    }
}
