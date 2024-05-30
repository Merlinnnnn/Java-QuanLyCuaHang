package org.LapTrinhTienTien.model.IdClass;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.LapTrinhTienTien.model.NhanVien;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Setter
@Getter
public class LichLamId {

    @Column(name= "MaNV")
    private String maNV;

    @Column(name = "NgayThangNam", nullable = false)
    private LocalDate ngayThangNam;
}
