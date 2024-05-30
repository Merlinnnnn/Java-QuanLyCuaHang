package org.LapTrinhTienTien.model.IdClass;

import jakarta.persistence.*;
import lombok.*;
import org.LapTrinhTienTien.model.CuaHang;
import org.LapTrinhTienTien.model.HoaDon;
import org.LapTrinhTienTien.model.SanPham;

import java.io.Serializable;
@Setter
@Getter
@AllArgsConstructor@NoArgsConstructor
@Embeddable
public class ChiTietHoaDonId implements Serializable {

    @Column(name = "MaSP")
    private String maSP;

    @Column(name = "MaHD")
    private String maHD;

}
