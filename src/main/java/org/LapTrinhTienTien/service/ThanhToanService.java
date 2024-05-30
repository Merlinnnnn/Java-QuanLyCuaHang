package org.LapTrinhTienTien.service;

import org.LapTrinhTienTien.StaticApp.Global;
import org.LapTrinhTienTien.model.*;
import org.LapTrinhTienTien.model.IdClass.ChiTietHoaDonId;
import org.LapTrinhTienTien.repository.*;
import org.LapTrinhTienTien.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ThanhToanService {
    @Autowired
    HoaDonService hoaDonService;
    @Autowired
    ChiTietHoaDonRepository chiTietHoaDonRepository;;
    @Autowired
    CuaHangSanPhamService cuaHangSanPhamService;
    @Autowired
    ChuongTrinhKhuyenMaiService chuongTrinhKhuyenMaiService;
    @Autowired
    SanPhamRepository sanPhamRepository;
    @Autowired
    TaiKhoanService taiKhoanService;
    @Autowired
    KhachHangRepository khachHangRepository;
    @Transactional(rollbackFor = Exception.class)
    public Response thanhToan(List<GioHang> gioHangList,String sdt){
        float tongTien = getTongTien(gioHangList);
        ChuongTrinhKhuyenMai ctkm = chuongTrinhKhuyenMaiService.getMaxGiamGia(tongTien);

        String maHd = hoaDonService.generateMaHD();
        float thanhtieng = getThanhTien(tongTien);
        // chinh sua khi can thiet
        NhanVien nhanVien = Global.account.getNhanVien();
        CuaHang cuaHang = Global.account.getNhanVien().getCuaHang();
        KhachHang khachHang = khachHangRepository.findBySdt(sdt);
        //
        try {

            //insert hoaDon
            HoaDon hoaDon = new HoaDon();
            hoaDon.setMaHD(maHd);
            hoaDon.setNgayXuat(LocalDateTime.now());
            hoaDon.setGiaTri(0);
            hoaDon.setDiemTich(0);
            hoaDon.setDiemSuDung(0);
            hoaDon.setGiaTri(tongTien);
            hoaDon.setThanhTien(thanhtieng);
            if(ctkm != null){
                hoaDon.setMaKhuyenMai(ctkm.getMaCT());
            }
            if(khachHang != null){
                hoaDon.setKhachHang(khachHang);
            }
            hoaDon.setNhanVien(nhanVien);
            hoaDon.setCuaHang(cuaHang);
            hoaDonService.saveHoaDon(hoaDon);
            //insert chiIetHoaDOn
            for (GioHang gioHang : gioHangList) {
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                SanPham sanPham = sanPhamRepository.findByMaSP(gioHang.getMaSP());
                ChiTietHoaDonId chiTietHoaDonId = new ChiTietHoaDonId();
                chiTietHoaDonId.setMaSP(gioHang.getMaSP());
                chiTietHoaDonId.setMaHD(hoaDon.getMaHD());
                chiTietHoaDon.setChiTietHoaDonId(chiTietHoaDonId);
                chiTietHoaDon.setHoaDon(hoaDon);
                chiTietHoaDon.setSanPham(sanPham);
                chiTietHoaDon.setGiaThanhToan(gioHang.getGiaTien());
                chiTietHoaDon.setSoLuong(gioHang.getSoLuong());
                chiTietHoaDon.setTongTien(gioHang.getTongTien());
                // Lưu chiTietHoaDon vào cơ sở dữ liệu
                chiTietHoaDonRepository.save(chiTietHoaDon);
                cuaHangSanPhamService.updateSoLuong(gioHang.getMaSP(), gioHang.getSoLuong());
            }

            //-- san pham trong cuaHangSanPhamService
            return new Response("Thanh toán thành công", true, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Lỗi khi thanh toán: ", false, null);
        }
    }

    private float getTongTien(List<GioHang> gioHangList){
        return(float)gioHangList.stream().mapToDouble(GioHang::getTongTien).sum();
    }
    private float getThanhTien(float tongtien){
        return tongtien - chuongTrinhKhuyenMaiService.getGiamGia(tongtien);
    }
}
