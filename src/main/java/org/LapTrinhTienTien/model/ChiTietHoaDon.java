package org.LapTrinhTienTien.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.LapTrinhTienTien.model.IdClass.ChiTietHoaDonId;
import org.LapTrinhTienTien.repository.HoaDonRepository;

import java.util.Set;
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "ChiTietHoaDon")
public class ChiTietHoaDon {

    @EmbeddedId
    private ChiTietHoaDonId chiTietHoaDonId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @MapsId("maSP")
    private SanPham sanPham;


    @ManyToOne( fetch = FetchType.EAGER)
    @MapsId("maHD")
    private HoaDon hoaDon;

    @Column(name = "GiaSanPham", nullable = false)
    private float giaThanhToan;


    @Column(name = "SoLuong", nullable = false)
    private int soLuong;

    @Column(name = "TongTien", nullable = false)
    private float tongTien;

    // Constructors, getters, and setters
}