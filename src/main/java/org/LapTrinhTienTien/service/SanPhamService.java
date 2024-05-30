
package org.LapTrinhTienTien.service;

import org.LapTrinhTienTien.StaticApp.Global;
import org.LapTrinhTienTien.model.CuaHang;
import org.LapTrinhTienTien.model.CuaHangSanPham;
import org.LapTrinhTienTien.model.HoaDon;
import org.LapTrinhTienTien.model.IdClass.CuaHangSanPhamKey;
import org.LapTrinhTienTien.model.SanPham;
import org.LapTrinhTienTien.repository.CuaHangSanPhamRepository;
import org.LapTrinhTienTien.repository.SanPhamRepository;
import org.LapTrinhTienTien.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.List;
import java.util.Optional;
@Service
public class SanPhamService {
    @Autowired
    SanPhamRepository sanPhamRepository;
    public List<SanPham> getAllSanPham(){
        return (List<SanPham>) sanPhamRepository.findAll();
    }
    @Autowired
    CuaHangSanPhamRepository cuaHangSanPhamRepository;
    public String generateMaSP() {
        String prefix = "SP";
        String generatedId = null;
        int maxId = 0;

        // Tìm mã HoaDon lớn nhất trong cơ sở dữ liệu
        Optional<SanPham> maxHoaDon = sanPhamRepository.findFirstByOrderByMaSPDesc();

        if (maxHoaDon.isPresent()) {
            String lastMaHD = maxHoaDon.get().getMaSP();
            // Lấy phần số tự tăng từ mã HoaDon cuối cùng
            String numericPart = lastMaHD.substring(prefix.length());
            // Chuyển phần số tự tăng thành số nguyên
            maxId = Integer.parseInt(numericPart);
        }

        // Tạo mã HoaDon mới với số tự tăng tăng lên 1
        generatedId = prefix + String.format("%03d", maxId + 1);

        return generatedId;
    }
    @Transactional(rollbackFor = Exception.class)
    public Response themSanPham(SanPham sp,int soluong, File selectedFile) {
        try {
            sp.setMaSP(generateMaSP());
            SanPham sanPham = sanPhamRepository.save(sp);
            System.out.println("Them san pham thanh cong");
            if (selectedFile != null) {
                try {
                    InputStream inputStream = new FileInputStream(selectedFile);
                    String newFileName = sanPham.getMaSP() + ".png";
                    OutputStream outputStream = new FileOutputStream(new File("src/main/resources/Img/SanPham/" + newFileName));

                    int length;
                    byte[] buffer = new byte[1024];
                    while ((length = inputStream.read(buffer)) > 0) {
                        outputStream.write(buffer, 0, length);
                    }
                    inputStream.close();
                    outputStream.close();
                    System.out.println("File đã được lưu vào thư mục resources/images.");
                    String image = "Img/SanPham/" + newFileName;
                    sanPham.setImage(image);
                    SanPham sanPham1 =  sanPhamRepository.save(sanPham);
                    //
                    CuaHang cuaHang = Global.account.getNhanVien().getCuaHang();
                    CuaHangSanPham cuaHangSanPham = new CuaHangSanPham();
                    cuaHangSanPham.setId(new CuaHangSanPhamKey(cuaHang.getMaCH(),sanPham.getMaSP()));
                    cuaHangSanPham.setSanPham(sanPham);
                    cuaHangSanPham.setCuaHang(cuaHang);
                    cuaHangSanPham.setSoLuong(soluong);
                    cuaHangSanPhamRepository.save(cuaHangSanPham);

                } catch (IOException e) {
                    throw new Exception("Lỗi khi thêm hình ảnh", e); // Ném một Exception để hoàn tác giao dịch
                }
            }
            return new Response("Thêm sản phẩm thành công", true, null);
        } catch (Exception e) {
            throw new RuntimeException("Thêm Sản Phẩm Không thành công", e); // Ném một RuntimeException để hoàn tác giao dịch
        }
    }

}
