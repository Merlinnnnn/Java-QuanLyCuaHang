package org.LapTrinhTienTien.repository;

import jakarta.transaction.Transactional;
import org.LapTrinhTienTien.model.KhachHang;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KhachHangRepository extends CrudRepository<KhachHang, Long> {
    KhachHang findBySdt(String sdt);
    //KhachHang getAllByHoTenKH(String Hoten);
    List<KhachHang> findAll();

}
