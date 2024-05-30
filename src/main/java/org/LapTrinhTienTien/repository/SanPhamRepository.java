package org.LapTrinhTienTien.repository;

import java.util.List;

import org.LapTrinhTienTien.model.SanPham;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SanPhamRepository extends CrudRepository<SanPham,String> {
    SanPham findByMaSP(String maSP);
    List<SanPham> findAll();
    Optional<SanPham> findFirstByOrderByMaSPDesc();
}
