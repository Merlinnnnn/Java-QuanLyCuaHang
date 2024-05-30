package org.LapTrinhTienTien.repository;
import org.LapTrinhTienTien.model.HoaDon;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface HoaDonRepository extends CrudRepository<HoaDon,String> {
    HoaDon findByMaHD(String maHD);
    Optional<HoaDon> findFirstByOrderByMaHDDesc();

    List<HoaDon> findAllByNgayXuatBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);

}


