package org.LapTrinhTienTien.model.IdClass;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor@NoArgsConstructor
@Setter@Getter
@Embeddable
public class CuaHangSanPhamKey {
    @Column(name= "cuahang_id")
    String cuaHangId;
    @Column(name = "sanpham_id")
    String sanPhamId;
}
