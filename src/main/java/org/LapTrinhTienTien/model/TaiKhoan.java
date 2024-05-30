package org.LapTrinhTienTien.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TaiKhoan")
@Getter
@Setter
@NoArgsConstructor@AllArgsConstructor
public class TaiKhoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Tk_NV",referencedColumnName = "MaNV")
    private NhanVien nhanVien;

    @Column(name = "TaiKhoan", length = 30,unique=true)
    private String taiKhoan;

    @Column(name = "MatKhau", length = 30)
    private String matKhau;
//


    // Constructors, getters, and setters
}
