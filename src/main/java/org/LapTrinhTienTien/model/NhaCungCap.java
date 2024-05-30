package org.LapTrinhTienTien.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "NhaCungCap")
public class NhaCungCap implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "MaNCC", length = 10)
    private String maNCC;

    @NotNull
    @Column(name = "TenNCC", length = 30)
    private String tenNCC;

    @NotNull
    @Column(name = "DiaChi", length = 100)
    private String diaChi;

    @NotNull
    @Column(name = "SDT", length = 10)
    private String sdt;

    @OneToMany(mappedBy="nhaCungCap", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<SanPham> sanPham = new HashSet<>();

    // Constructors, getters, and setters
}
