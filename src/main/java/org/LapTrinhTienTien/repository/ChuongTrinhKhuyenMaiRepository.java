package org.LapTrinhTienTien.repository;

import org.LapTrinhTienTien.model.ChuongTrinhKhuyenMai;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ChuongTrinhKhuyenMaiRepository extends CrudRepository<ChuongTrinhKhuyenMai, String> {
    List<ChuongTrinhKhuyenMai> findByNgayApDungBeforeAndNgayKetThucAfter(LocalDateTime ngayApDung, LocalDateTime ngayKetThuc);

}
