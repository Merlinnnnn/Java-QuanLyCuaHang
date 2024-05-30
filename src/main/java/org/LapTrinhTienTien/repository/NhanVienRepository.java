package org.LapTrinhTienTien.repository;

import java.util.List;
import org.LapTrinhTienTien.model.NhanVien;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhanVienRepository extends CrudRepository<NhanVien,String> {
    NhanVien findByMaNV(String maNV);
    List<NhanVien> findAll();
    List<NhanVien> findByHoTenNVContainingIgnoreCase(String tenNV);
    NhanVien findTopByOrderByMaNVDesc();
}
