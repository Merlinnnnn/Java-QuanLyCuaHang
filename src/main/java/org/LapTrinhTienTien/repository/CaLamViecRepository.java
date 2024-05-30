package org.LapTrinhTienTien.repository;

import org.LapTrinhTienTien.model.CaLamViec;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CaLamViecRepository extends CrudRepository<CaLamViec,String> {
    CaLamViec findByMaCa(String maCa);
}
