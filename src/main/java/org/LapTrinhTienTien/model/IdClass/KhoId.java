package org.LapTrinhTienTien.model.IdClass;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class KhoId {
    @Column(name = "MaCH")
    private String maCH;
}
