package org.LapTrinhTienTien.model;

import jakarta.persistence.*;
import lombok.*;
import org.LapTrinhTienTien._enum.Status;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ChuongTrinhKhuyenMai")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChuongTrinhKhuyenMai implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "MaCT", length = 10)
    private String maCT;

    @Column(name = "TenChuongTrinh", length = 100, nullable = false)
    private String tenChuongTrinh;

    @Column(name = "PhanTramGiamGia", nullable = false)
    private Integer phanTramGiamGia;

    @Column(name = "NgayApDung", nullable = false)
    private LocalDateTime ngayApDung;

    @Column(name = "NgayKetThuc", nullable = false)
    private LocalDateTime ngayKetThuc;
    @Column(name = "DieuKienApDung")
    private float tonghonDon =0;

    @Column(name = "trangthai", columnDefinition = "VARCHAR(255) DEFAULT 'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    // Constructors, getters, and setters
}
