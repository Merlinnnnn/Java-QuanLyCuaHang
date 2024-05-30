package org.LapTrinhTienTien.data;

import org.LapTrinhTienTien._enum.ChucVuEnum;
import org.LapTrinhTienTien.model.*;
import org.LapTrinhTienTien.model.IdClass.ChiTietHoaDonId;
import org.LapTrinhTienTien.model.IdClass.CuaHangSanPhamKey;
import org.LapTrinhTienTien.model.IdClass.LichLamId;
import org.LapTrinhTienTien.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;

@Component
public class FakeData   implements CommandLineRunner{
    private final NhanVienRepository nhanVienRepository;
    private final HoaDonRepository hoaDonRepository;
    private final ChuongTrinhKhuyenMaiRepository chuongTrinhKhuyenMaiRepository;
    private final ChucVuRepository chucVuRepository;
    private final  CuaHangRepository cuaHangRepository;
    private final  NhaCungCapRepository nhaCungCapRepository;
    private final SanPhamRepository sanPhamRepository;
    private  final  CaLamViecRepository caLamViecRepository;
    private final  KhachHangRepository khachHangRepository;
    private final LichLamRepository lichLamRepository;
    private final TaiKhoanRepository taiKhoanRepository;
    private final ChiTietHoaDonRepository chiTietHoaDonRepository;
    private final  CuaHangSanPhamRepository cuaHangSanPhamRepository;

    @Autowired
    public  FakeData(ChucVuRepository chucVuRepository, NhanVienRepository nhanVienRepository, HoaDonRepository hoaDonRepository, ChuongTrinhKhuyenMaiRepository chuongTrinhKhuyenMaiRepository, CuaHangRepository cuaHangRepository, NhaCungCapRepository nhaCungCapRepository, SanPhamRepository sanPhamRepository, CaLamViecRepository caLamViecRepository, KhachHangRepository khachHangRepository, LichLamRepository lichLamRepository, TaiKhoanRepository taiKhoanRepository, ChiTietHoaDonRepository chiTietHoaDonRepository, CuaHangSanPhamRepository cuaHangSanPhamRepository) {
        this.nhanVienRepository = nhanVienRepository;
        this.hoaDonRepository = hoaDonRepository;
        this.chuongTrinhKhuyenMaiRepository = chuongTrinhKhuyenMaiRepository;
        this.chucVuRepository = chucVuRepository;

        this.cuaHangRepository = cuaHangRepository;
        this.nhaCungCapRepository = nhaCungCapRepository;
        this.sanPhamRepository = sanPhamRepository;
        this.caLamViecRepository = caLamViecRepository;
        this.khachHangRepository = khachHangRepository;
        this.lichLamRepository = lichLamRepository;
        this.taiKhoanRepository = taiKhoanRepository;
        this.chiTietHoaDonRepository = chiTietHoaDonRepository;
        this.cuaHangSanPhamRepository = cuaHangSanPhamRepository;
    }

    @Override
    public void run(String... args) throws Exception {

//        insertChucVu();
//        insertCalamViec();
//        insertNhaCungCap();
//        insertSanPham();
//        insertKhachHang();
//        insertCuaHang();//cua hang + kho //Trung khoa chinh
//        insertNhanVien();//tai khoan voi nhan vien
//       insertLichLam();// insert theo thu tu
//        insertCuaHangSanPham();
//       insertChuongTrinh();// chuong trinh
//        insertHoaDon();//hoa don va chi tiet hoa don

        //chitiethoadon



    }

    private void insertHoaDon() {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaHD("HD001");
        hoaDon.setNgayXuat(LocalDateTime.now());
        hoaDon.setGiaTri(0);
        hoaDon.setDiemTich(0);
        hoaDon.setDiemSuDung(0);
        hoaDon.setGiaTri(1000000);
        hoaDon.setThanhTien(100000);
        hoaDon.setMaKhuyenMai(null);

        // Tìm nhân viên và khách hàng từ cơ sở dữ liệu
        NhanVien nhanVien = nhanVienRepository.findByMaNV("NV001");
       // KhachHang khachHang = khachHangRepository.findBySdt("0123456781");
        SanPham sanPham = sanPhamRepository.findByMaSP("SP001");
        CuaHang cuaHang = cuaHangRepository.findByMaCH("CH002");

        // Gán nhân viên và khách hàng cho hóa đơn
        hoaDon.setNhanVien(nhanVien);
        //hoaDon.setKhachHang(khachHang);
        hoaDon.setCuaHang(cuaHang);
        cuaHang.getHoaDon().add(hoaDon);

        // Lưu hóa đơn vào cơ sở dữ liệu


        // Tạo chi tiết hóa đơn
        ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
        ChiTietHoaDonId chiTietHoaDonId = new ChiTietHoaDonId();
        chiTietHoaDonId.setMaSP(sanPham.getMaSP());
        chiTietHoaDonId.setMaHD(hoaDon.getMaHD());
        chiTietHoaDon.setChiTietHoaDonId(chiTietHoaDonId);
        chiTietHoaDon.setHoaDon(hoaDon);
        chiTietHoaDon.setSanPham(sanPham);
        chiTietHoaDon.setGiaThanhToan(50000); // Giá thanh toán cho mỗi sản phẩm trong chi tiết hóa đơn
        chiTietHoaDon.setSoLuong(2); // Số lượng sản phẩm
        chiTietHoaDon.setTongTien(chiTietHoaDon.getGiaThanhToan() * chiTietHoaDon.getSoLuong()); // Tính tổng tiền


        // Tạo chi tiết hóa đơn
        SanPham sanPham1 = sanPhamRepository.findByMaSP("SP002");
        ChiTietHoaDon chiTietHoaDon1 = new ChiTietHoaDon();
        ChiTietHoaDonId chiTietHoaDonId1 = new ChiTietHoaDonId();
        chiTietHoaDonId1.setMaSP(sanPham1.getMaSP());
        chiTietHoaDonId1.setMaHD(hoaDon.getMaHD());
        chiTietHoaDon1.setChiTietHoaDonId(chiTietHoaDonId1);
        chiTietHoaDon1.setHoaDon(hoaDon);
        chiTietHoaDon1.setSanPham(sanPham1);
        chiTietHoaDon1.setGiaThanhToan(50000); // Giá thanh toán cho mỗi sản phẩm trong chi tiết hóa đơn
        chiTietHoaDon1.setSoLuong(2); // Số lượng sản phẩm
        chiTietHoaDon1.setTongTien(chiTietHoaDon1.getGiaThanhToan() * chiTietHoaDon1.getSoLuong());
        // Lưu chi tiết hóa đơn vào cơ sở dữ liệu
      //  hoaDon.getChiTietHoaDon().add(chiTietHoaDon);
        try {

            hoaDonRepository.save(hoaDon);
            chiTietHoaDonRepository.save(chiTietHoaDon);
            chiTietHoaDonRepository.save(chiTietHoaDon1);
        } catch (Exception e) {
            System.out.println("HoaDon:" + e.getMessage());
        }

       // chiTietHoaDonRepository.save(chiTietHoaDon);
    }
    private void insertCuaHangSanPham(){
        CuaHangSanPham cuaHangSanPham = new CuaHangSanPham();
        for(int i = 1;i < 10;i ++){
            SanPham sanPham = sanPhamRepository.findByMaSP("SP00"+i);
            CuaHang cuaHang = cuaHangRepository.findByMaCH("CH002");
            cuaHangSanPham.setId(new CuaHangSanPhamKey(cuaHang.getMaCH(),sanPham.getMaSP()));
            cuaHangSanPham.setSanPham(sanPham);
            cuaHangSanPham.setCuaHang(cuaHang);
            cuaHangSanPham.setSoLuong(10);
            try{
                cuaHangSanPhamRepository.save(cuaHangSanPham);
            }catch (Exception e){
                System.out.println("CuaHangSanPham:" + e.getMessage());
            }

        }


    }
    private void insertKho(){
    }
    private void insertTaiKhoan(){
        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setTaiKhoan("username2");
        taiKhoan.setMatKhau("password");// Gán đối tượng NhanVien nếu cần
//        NhanVien nv = nhanVienRepository.findByMaNV("NV002");
//        taiKhoan.setNhanVien(nv);
        // Gọi phương thức để chèn vào cơ sở dữ liệu

       taiKhoanRepository.save(taiKhoan);
    }
    private void insertLichLam(){
        LichLam lichLam = new LichLam();
        CaLamViec caLamViec = caLamViecRepository.findByMaCa("CA001");
        NhanVien nhanVien = nhanVienRepository.findByMaNV("NV001");
        lichLam.setId(new LichLamId(nhanVien.getMaNV(), LocalDate.now())); // Sử dụng constructor của LichLamId để tạo đối tượng mới
        lichLam.setCaLamViec(caLamViec);
        lichLam.setNhanVien(nhanVien);
        lichLamRepository.save(lichLam);


    }
    private void insertKhachHang(){
        int n = 5;
        for(int i = 1; i <= n; i++){
            KhachHang khachHang = new KhachHang();
            khachHang.setId((long) i);
            khachHang.setSdt("012345678"+i);
            khachHang.setHoTenKH("Nguyen Van "+i);
            khachHang.setSoDiemDaTich(BigDecimal.ZERO);
            khachHang.setSoDiemDaDung(BigDecimal.ZERO);
            khachHang.setSoDiemHienCo(BigDecimal.ZERO);
            khachHangRepository.save(khachHang);
        }

    }
    private void insertCalamViec(){
        CaLamViec caLamViec = new CaLamViec();
        caLamViec.setMaCa("CA001");
        caLamViec.setGioBatDau(LocalTime.of(6, 0)); // 06:00
        caLamViec.setGiaKetThuc(LocalTime.of(14, 0)); // 14:00
        caLamViec.setPhanTramThuongThem(0);

        CaLamViec caLamViec2 = new CaLamViec();
        caLamViec2.setMaCa("CA002");
        caLamViec2.setGioBatDau(LocalTime.of(14, 0)); // 06:00
        caLamViec2.setGiaKetThuc(LocalTime.of(22, 0)); // 14:00
        caLamViec2.setPhanTramThuongThem(0);

        CaLamViec caLamViec3 = new CaLamViec();
        caLamViec3.setMaCa("CA003");
        caLamViec3.setGioBatDau(LocalTime.of(22, 0)); // 06:00
        caLamViec3.setGiaKetThuc(LocalTime.of(6, 0)); // 14:00
        caLamViec3.setPhanTramThuongThem(20);

        caLamViecRepository.save(caLamViec);
        caLamViecRepository.save(caLamViec2);
        caLamViecRepository.save(caLamViec3);


    }
    private void insertNhaCungCap() {

        int n = 5;
        for(int i = 1; i<=n;i++){
            NhaCungCap nhaCungCap = new NhaCungCap();
            nhaCungCap.setMaNCC("NCC00" +i);
            nhaCungCap.setTenNCC("Nhà cung cấp " +i);
            nhaCungCap.setDiaChi("Địa chỉ "+i);
            nhaCungCap.setSdt("012345678"+i);
            nhaCungCapRepository.save(nhaCungCap);
        }

    }
    private void insertChucVu() {

        ChucVu chucVuQL = new ChucVu(ChucVuEnum.QUAN_LY);
        ChucVu chucVuNV = new ChucVu(ChucVuEnum.NHAN_VIEN);

        chucVuRepository.save(chucVuNV);
        chucVuRepository.save(chucVuQL);


    }
    private void insertChuongTrinh() {
            LocalDateTime now = LocalDateTime.now();

        ChuongTrinhKhuyenMai chuongTrinhKhuyenMai = new ChuongTrinhKhuyenMai();
        chuongTrinhKhuyenMai.setMaCT("CT001");
        chuongTrinhKhuyenMai.setTenChuongTrinh("Chương trình giảm giá mùa hè");
        chuongTrinhKhuyenMai.setPhanTramGiamGia(10);
        chuongTrinhKhuyenMai.setNgayApDung(LocalDateTime.of(2024, 5, 1, 0, 0)); // Ngày 1 tháng 6 năm 2024
        chuongTrinhKhuyenMai.setNgayKetThuc(LocalDateTime.of(2024, 8, 31, 23, 59)); // Ngày 31 tháng 8 năm 2024
        chuongTrinhKhuyenMai.setTonghonDon(1000000); // Điều kiện áp dụng: tổng hóa đơn từ 1,000,000 đồng trở lên

        chuongTrinhKhuyenMaiRepository.save(chuongTrinhKhuyenMai);
           // chuongTrinhKhuyenMaiRepository.save(chuongTrinhKhuyenMai2);

        }
    private  void insertNhanVien(){
        int n = 5;
        CuaHang cuaHang = cuaHangRepository.findByMaCH("CH002");
        ChucVu chucVu = chucVuRepository.findByMaCV("CV0001");
        for(int i =1;i <=n;i++){
            NhanVien nhanVien = new NhanVien();
            nhanVien.setMaNV("NV00"+i);
            nhanVien.setHoTenNV("John Doe");
            nhanVien.setNgaySinh(LocalDate.of(1990, 5, 15));
            nhanVien.setNgayVaoLam(LocalDate.now());
            nhanVien.setSdt("123456789"+i);
            nhanVien.setDiaChi("123 Street, City");
            nhanVien.setCccd("123456789012");
            nhanVien.setGioiTinh("Male");
            nhanVien.setUrlImage("https://example.com/image.jpg");

            nhanVien.setChucVu(chucVu);
            nhanVien.setCuaHang(cuaHang);


            TaiKhoan taiKhoan = new TaiKhoan();
            taiKhoan.setTaiKhoan("NV00"+i);
            taiKhoan.setMatKhau("NV00"+i);
            taiKhoan.setNhanVien(nhanVien);
        try{
            nhanVienRepository.save(nhanVien);
            try{
                taiKhoanRepository.save(taiKhoan);
            }catch(Exception e) {
                System.out.println("Tai khoan : "+e);
            }

        }catch(Exception e){
            System.out.println("NhanVien : "+e);
        }



        }




    }
    private void insertCuaHang(){
        try{
            CuaHang cuaHang = new CuaHang();
            cuaHang.setMaCH("CH002");
            cuaHang.setTenCH("Cửa hàng A");
            cuaHang.setDiaChiCH("Địa chỉ A");
            cuaHang.setSdt("0123456789");


            // khoRepository.save(kho);
            cuaHangRepository.save(cuaHang);
        }catch (Exception e){
            System.out.println("Cua Hang da insert:"+e);
        }


        // Tạo đối tượng Kho
//        KhoId khoId = new KhoId("CH001","SP001"); // Tham chiếu đến maCH của CuaHang
//       // KhoId khoId2 = new KhoId("CH001","SP002");
//        Kho kho = new Kho();
//        kho.setKhoId(khoId);
//        kho.setTenKho("Kho A");
//        kho.setSoLuong(10);
//        kho.setCuaHang(cuaHang);
//
//        // Thiết lập các thông tin khác cho Kho nếu cần
//
//// Gán Kho vào CuaHang
//        cuaHang.setKho(kho);

// Lưu CuaHang vào cơ sở dữ liệu

    }
    private void insertSanPham(){
        int n = 10;
        for(int i = 1;i <10;i++){
            SanPham sanPham = new SanPham();
            sanPham.setMaSP("SP00"+i);
            sanPham.setTenSP("Tên sản phẩm " +i);
            sanPham.setNoiSanXuat("Nơi sản xuất "+i);
            sanPham.setTrangThai("Hoạt động");
            sanPham.setImage("Img/SanPham/1.jpg");
            sanPham.setTienGoc(1000000);
            sanPham.setTienThanhToan(100000);
            sanPham.setNgayNhapHang(LocalDate.now());

            NhaCungCap nhaCungCap = nhaCungCapRepository.findByMaNCC("NCC001");
            sanPham.setNhaCungCap(nhaCungCap);
            sanPhamRepository.save(sanPham);

        }

//        NhaCungCap ncc = nhaCungCapRepository.findByMaNCC("NCC002");
//        sanPham.setNhaCungCap(ncc);
//        ncc.getSanPham().add(sanPham);


    }
}
