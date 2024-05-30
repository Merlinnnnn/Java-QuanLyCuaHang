package org.LapTrinhTienTien.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.LapTrinhTienTien._enum.ChucVuEnum;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ChucVu")
@Data
@NoArgsConstructor
public class ChucVu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "MaCV", length = 10)
    private String maCV;

    @Column(name = "TenChucVu")
    private String tenChucVu;

    @Column(name = "GiaTienMotTieng")
    private float giaTienMotTieng;

    @OneToMany(mappedBy="chucVu",fetch = FetchType.EAGER)
    Set<NhanVien> nhanVien;

    public ChucVu(ChucVuEnum chucVuEnum) {
        this.maCV = chucVuEnum.getMaCV();
        this.tenChucVu = chucVuEnum.getTenCV();
        this.giaTienMotTieng = chucVuEnum.getGiaTienMotTieng();
    }


}