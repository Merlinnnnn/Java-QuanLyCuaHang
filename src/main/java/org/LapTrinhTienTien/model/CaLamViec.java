package org.LapTrinhTienTien.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "CaLamViec")
@Setter
@Getter
@NoArgsConstructor
public class CaLamViec {
    @Id
    @Column(name = "MaCa", length = 10)
    private String maCa;

    @Column(name = "GioBatDau", nullable = false, unique = true)
    private LocalTime gioBatDau;

    @Column(name = "GiaKetThuc", nullable = false, unique = true)
    private  LocalTime giaKetThuc;

    @Column(name = "PhanTramThuongThem", nullable = false)
    private int phanTramThuongThem;

    @OneToMany(mappedBy = "caLamViec")
    private Set<LichLam> lichLam;

    // Constructors, getters, and setters
}