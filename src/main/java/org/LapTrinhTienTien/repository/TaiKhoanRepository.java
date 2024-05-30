package org.LapTrinhTienTien.repository;

import org.LapTrinhTienTien.model.TaiKhoan;
import org.springframework.data.repository.CrudRepository;

public interface TaiKhoanRepository extends CrudRepository<TaiKhoan, Long> {
    TaiKhoan findByTaiKhoan(String taiKhoan);
}
