package org.LapTrinhTienTien.repository;

import java.time.LocalDate;
import java.util.List;
import org.LapTrinhTienTien.model.CaLamViec;
import org.LapTrinhTienTien.model.IdClass.LichLamId;
import org.LapTrinhTienTien.model.LichLam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface LichLamRepository  extends CrudRepository<LichLam, LichLamId> {
    //List<LichLam> findByCaLamViec_MaCaLamViec(String maCaLamViec);
}


