package org.LapTrinhTienTien.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.LapTrinhTienTien.model.IdClass.LichLamId;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;

@Entity
@Table(name = "LichLam")
@Getter
@Setter
@NoArgsConstructor
public class LichLam implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private LichLamId id;


    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("maNV")
    private NhanVien nhanVien ;


    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "LL_CLV")
    private CaLamViec caLamViec;

    // Constructors, getters, and setters
}
