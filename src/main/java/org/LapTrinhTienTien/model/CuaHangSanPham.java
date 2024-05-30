package org.LapTrinhTienTien.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.LapTrinhTienTien.model.IdClass.CuaHangSanPhamKey;
@Setter
@Getter
@NoArgsConstructor@AllArgsConstructor
@Entity
public class CuaHangSanPham {
    @EmbeddedId
    CuaHangSanPhamKey id;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("cuaHangId")
    @JoinColumn(name= "cuahang_id")
    CuaHang cuaHang;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("sanPhamId")
    @JoinColumn(name= "sanpham_id")
    SanPham sanPham;

    @Column(name = "soLuong")
    private int soLuong;
}
