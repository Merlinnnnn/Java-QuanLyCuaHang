package org.LapTrinhTienTien.repository;

import org.LapTrinhTienTien.model.CuaHang;
import org.springframework.data.repository.CrudRepository;

public interface CuaHangRepository extends CrudRepository<CuaHang,String > {
    CuaHang findByMaCH(String maCH);
}
