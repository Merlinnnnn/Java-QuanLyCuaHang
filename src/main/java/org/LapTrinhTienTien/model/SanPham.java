package org.LapTrinhTienTien.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "SanPham")
public class SanPham {
    @Id
    @Column(name = "MaSP", length = 10)
    private String maSP;

    @Column(name = "TenSP", length = 30, nullable = false)
    private String tenSP;

    @Column(name = "NoiSanXuat", length = 100, nullable = false)
    private String noiSanXuat;

    @Column(name = "TrangThai", length = 10, nullable = false)
    private String trangThai;

    @Column(name = "TienGoc", nullable = false)
    private float tienGoc;

    @Column(name = "TienThanhToan", nullable = false)
    private float tienThanhToan;
    @Column(name = "Image")
    private String image;

    @Temporal(TemporalType.DATE)
    @Column(name = "NgayNhapHang", nullable = false)
    private LocalDate ngayNhapHang;

    @ManyToOne
    @JoinColumn(name ="SP_NCC",referencedColumnName = "MaNCC")
    private NhaCungCap nhaCungCap;
    //them hoac khong
    @OneToMany(mappedBy = "sanPham",fetch = FetchType.EAGER)
    private Set<CuaHangSanPham>  cuaHangSanPham = new HashSet<>();

    // Constructors, getters, and setters
}
