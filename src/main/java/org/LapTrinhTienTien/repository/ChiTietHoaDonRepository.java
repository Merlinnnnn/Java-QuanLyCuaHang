package org.LapTrinhTienTien.repository;

import org.LapTrinhTienTien.model.ChiTietHoaDon;
import org.LapTrinhTienTien.model.HoaDon;
import org.LapTrinhTienTien.model.IdClass.ChiTietHoaDonId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface ChiTietHoaDonRepository extends CrudRepository<ChiTietHoaDon, ChiTietHoaDonId> {

    List<ChiTietHoaDon> findAllByHoaDon(HoaDon hoaDon);
    List<ChiTietHoaDon> findAll();
    /*@Query("SELECT c.hoaDon.maHD, SUM(c.soLuong) FROM ChiTietHoaDon c WHERE c.hoaDon.ngayXuat BETWEEN :startDate AND :endDate GROUP BY c.sanPham.maSP")
    List<Object[]> countProductSoldBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    //List<ChiTietHoaDon> ();*/
    @Query("SELECT c.sanPham.maSP, SUM(c.soLuong) AS total_quantity FROM ChiTietHoaDon c GROUP BY c.sanPham.maSP")
    List<Object[]> findTotalQuantityByMaSPGroupByMaSP();
}
