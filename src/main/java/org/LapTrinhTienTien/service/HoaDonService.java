package org.LapTrinhTienTien.service;

import org.LapTrinhTienTien.StaticApp.Global;
import org.LapTrinhTienTien._enum.Status;
import org.LapTrinhTienTien.model.HoaDon;
import org.LapTrinhTienTien.repository.HoaDonRepository;
import org.LapTrinhTienTien.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HoaDonService {
    @Autowired
    HoaDonRepository hoaDonRepository;
    public HoaDon getHoaDon(String maHD){
        return hoaDonRepository.findByMaHD(maHD);
    }
    public List<HoaDon> getAllAdminHoaDon(){
        return (List<HoaDon>) hoaDonRepository.findAll();
    }
    public List<HoaDon> getAllHoaDon(){
       List<HoaDon> lst =  (List<HoaDon>) hoaDonRepository.findAll();
       lst = lst.stream().filter(hd->hd.getStatus().equals(Status.ACTIVE)).toList();
       return lst;
    }

    public HoaDon saveHoaDon(HoaDon hoaDon){
        return hoaDonRepository.save(hoaDon);
    }

    public String generateMaHD() {
        String prefix = "HD";
        String generatedId = null;
        int maxId = 0;

        // Tìm mã HoaDon lớn nhất trong cơ sở dữ liệu
        Optional<HoaDon> maxHoaDon = hoaDonRepository.findFirstByOrderByMaHDDesc();

        if (maxHoaDon.isPresent()) {
            String lastMaHD = maxHoaDon.get().getMaHD();
            // Lấy phần số tự tăng từ mã HoaDon cuối cùng
            String numericPart = lastMaHD.substring(prefix.length());
            // Chuyển phần số tự tăng thành số nguyên
            maxId = Integer.parseInt(numericPart);
        }

        // Tạo mã HoaDon mới với số tự tăng tăng lên 1
        generatedId = prefix + String.format("%03d", maxId + 1);

        return generatedId;
    }
    public List<HoaDon> timKiemHoaDon(String maHD, LocalDate ngayXuat, String sdt) {
        LocalDateTime startOfDay = ngayXuat.atStartOfDay();
        LocalDateTime endOfDay = ngayXuat.atTime(LocalTime.MAX);
        List<HoaDon> danhSachHoaDon = hoaDonRepository.findAllByNgayXuatBetween(startOfDay, endOfDay);
        String maCH = Global.account.getNhanVien().getCuaHang().getMaCH();
        danhSachHoaDon = danhSachHoaDon.stream().filter(hd -> hd.getCuaHang().getMaCH().equals(maCH)).collect(Collectors.toList());

        if(!maHD.isEmpty()){
            danhSachHoaDon = danhSachHoaDon.stream().filter(hd->hd.getMaHD().equals(maHD)).collect(Collectors.toList());
        }
        if(!sdt.isEmpty()){
            danhSachHoaDon = danhSachHoaDon.stream().filter(hd->{
               if(hd.getKhachHang()==null)  return false;
               if(hd.getKhachHang().getSdt().equals(sdt)) return true;
               return false;
            }).collect(Collectors.toList());
        }
        danhSachHoaDon = danhSachHoaDon.stream().filter(hd->hd.getStatus().equals(Status.ACTIVE)).toList();
        return danhSachHoaDon;
    }

    public Response updateHoaDon(HoaDon hoaDon){
        try {
            if (hoaDonRepository.existsById(hoaDon.getMaHD())) {
                Status status = hoaDon.getStatus();
                if(status.equals(Status.INACTIVE)){
                    status = Status.ACTIVE;
                }else{
                    status = Status.INACTIVE;
                }
                hoaDon.setStatus(status);
                HoaDon updatedHoaDon = hoaDonRepository.save(hoaDon);
                if(status.equals(Status.INACTIVE)) {
                    return new Response("Cập nhật trạng thái từ Active sang InActive thành công", true, updatedHoaDon);
                }
                return new Response("Cập nhật trạng thái từ InActive sang Active thành công", true, updatedHoaDon);
            } else {
                return new Response("Hóa đơn không tồn tại", false, null);
            }
        } catch (Exception e) {
            return new Response("Lỗi khi cập nhật hóa đơn: " , false, null);
        }
    }
}
