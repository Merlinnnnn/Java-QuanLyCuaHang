package org.LapTrinhTienTien.repository;

import org.LapTrinhTienTien.model.CuaHang;
import org.LapTrinhTienTien.model.CuaHangSanPham;
import org.LapTrinhTienTien.model.IdClass.CuaHangSanPhamKey;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CuaHangSanPhamRepository extends CrudRepository<CuaHangSanPham, CuaHangSanPhamKey> {

    List<CuaHangSanPham> findAllByCuaHang(CuaHang cuaHang);
    CuaHangSanPham findBySanPham_MaSPAndCuaHang_MaCH(String maSP, String maCH);
}
