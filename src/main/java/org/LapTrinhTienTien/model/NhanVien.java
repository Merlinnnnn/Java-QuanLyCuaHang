package org.LapTrinhTienTien.model;

import com.mysql.cj.protocol.ColumnDefinition;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "NhanVien")
public class NhanVien implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "MaNV", length = 10)
    private String maNV;

    @Column(name = "HoTenNV", length = 30, nullable = false)
    private String hoTenNV;

    @Column(name = "NgaySinh", nullable = false)
    private LocalDate ngaySinh;

    @Column(name = "NgayVaoLam", nullable = false)
    private LocalDate ngayVaoLam;

    @Column(name = "SDT", length = 10, nullable = false)
    private String sdt;

    @Column(name = "DiaChi", length = 100)
    private String diaChi;

    @Column(name = "CCCD", length = 12)
    private String cccd;

    @Column(name = "GioiTinh", length = 5)
    private String gioiTinh;
    @Column(name = "urlImage")
    private String urlImage ;
    @Column(columnDefinition = "DATETIME DEFAULT NULL",nullable = true)
    LocalDateTime trangThai;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "NV_CV")
    private ChucVu chucVu;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="NV_CH")
    private CuaHang cuaHang;
    @OneToMany(mappedBy="nhanVien",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    Set<HoaDon> hoaDon;

    @OneToOne(mappedBy = "nhanVien",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private TaiKhoan taikhoan;

    @OneToMany(mappedBy = "nhanVien",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    Set<LichLam> lichLam;
}
