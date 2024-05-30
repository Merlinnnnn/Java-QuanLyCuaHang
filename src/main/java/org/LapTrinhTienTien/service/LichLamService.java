package org.LapTrinhTienTien.service;

import org.LapTrinhTienTien.StaticApp.Global;
import org.LapTrinhTienTien.model.CuaHang;
import org.LapTrinhTienTien.model.LichLam;
import org.LapTrinhTienTien.repository.LichLamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.LapTrinhTienTien.model.IdClass.LichLamId;

@Service
public class LichLamService {
    
    private final LichLamRepository lichLamRepository;

    
    public LichLamService(@Autowired LichLamRepository lichLamRepository) {
        this.lichLamRepository = lichLamRepository;
    }

    public List<LichLam> findLichLamByMaCa(String maCaLamViec) {
      //  return lichLamRepository.findByCaLamViecMaCaLamViec(maCaLamViec);
      return null;
    }

    public List<LichLam> findLichLamByNgayThangNam(List<LichLam> danhSachLichLam, LocalDate ngayThangNam) {
        return danhSachLichLam.stream()
                .filter(lichLam -> lichLam.getId().getNgayThangNam().equals(ngayThangNam))
                .collect(Collectors.toList());
    }

    public String findMaNVByMaCaAndNgayThangNam(String maCaLamViec, LocalDate ngayThangNam) {
        List<LichLam> danhSachLichLam = findLichLamByMaCa(maCaLamViec);
        List<LichLam> lichLamTheoNgayThangNam = findLichLamByNgayThangNam(danhSachLichLam, ngayThangNam);
        return lichLamTheoNgayThangNam.isEmpty() ? null : lichLamTheoNgayThangNam.get(0).getId().getMaNV();
    }

    public List<LichLam> getAllLichLam(){
        String cuaHang = Global.account.getNhanVien().getCuaHang().getMaCH();
        List<LichLam> lichLams = (List<LichLam>) lichLamRepository.findAll();
        System.out.println("1---ll---" +lichLams.size());
        lichLams = lichLams.stream().filter(ll->ll.getNhanVien().getCuaHang().getMaCH().equals(cuaHang)).toList();
        System.out.println("---ll---" +lichLams.size());
        return lichLams;
    }
    public List<LichLam> timKiemLichLam(String manv,String macv,LocalDate ngaybd,LocalDate ngaykt){
        List<LichLam>  lichLams = getAllLichLam();
        lichLams = lichLams.stream().filter(ll->ll.getId().getNgayThangNam().compareTo(ngaybd) >= 0 && ll.getId().getNgayThangNam().compareTo(ngaykt) <= 0).toList();
        if(!manv.isEmpty()){
            lichLams = lichLams.stream().filter(ll->ll.getId().getMaNV().equals(manv)).toList();
        }
        if(!macv.isEmpty()){
            lichLams = lichLams.stream().filter(ll->ll.getCaLamViec().getMaCa().equals(macv)).toList();
        }
        return lichLams;
    }
}