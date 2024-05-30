package org.LapTrinhTienTien.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "CuaHang")
public class CuaHang implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "MaCH", length = 10)
    private String maCH;

    @Column(name = "TenCH", length = 30, nullable = false)
    private String tenCH;

    @Column(name = "DiaChiCH", length = 100, nullable = false)
    private String diaChiCH;

    @Column(name = "SDT", length = 10)
    private String sdt;
    @OneToMany(mappedBy="cuaHang", fetch = FetchType.EAGER)
    Set<NhanVien> nhanVien = new HashSet<>();
    //them hoac khong
    @OneToMany(mappedBy = "cuaHang",fetch = FetchType.EAGER)
    private Set<CuaHangSanPham> cuaHangSanPham = new HashSet<>();
    @OneToMany(mappedBy="cuaHang", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<HoaDon> hoaDon = new HashSet<>();


}
