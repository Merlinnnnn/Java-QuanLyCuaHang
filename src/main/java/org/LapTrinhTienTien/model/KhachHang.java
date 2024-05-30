package org.LapTrinhTienTien.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "KhachHang")
public class KhachHang implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "SDT", length = 10, unique = true)
    private String sdt;

    @Column(name = "HoTenKH", length = 30, nullable = false)
    private String hoTenKH;

    @Column(name = "SoDiemDaTich", nullable = false, scale = 2)
    private BigDecimal soDiemDaTich;

    @Column(name = "SoDiemDaDung", scale = 2)
    private BigDecimal soDiemDaDung;

    @Column(name = "SoDiemHienCo", scale = 2)
    private BigDecimal soDiemHienCo;

    @OneToMany(mappedBy="khachHang",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    Set<HoaDon> hoaDon;

}
