package org.LapTrinhTienTien.repository;

import org.LapTrinhTienTien.model.ChucVu;
import org.springframework.data.repository.CrudRepository;

public interface ChucVuRepository extends CrudRepository<ChucVu,String> {
        ChucVu findByMaCV(String maCV);
}
