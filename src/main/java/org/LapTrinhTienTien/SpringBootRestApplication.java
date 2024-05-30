package org.LapTrinhTienTien;


import org.LapTrinhTienTien._enum.ChucVuEnum;
import org.LapTrinhTienTien.model.ChucVu;
import org.LapTrinhTienTien.model.HoaDon;
import org.LapTrinhTienTien.model.NhanVien;
import org.LapTrinhTienTien.repository.ChucVuRepository;
import org.LapTrinhTienTien.repository.HoaDonRepository;
import org.LapTrinhTienTien.repository.NhanVienRepository;
import org.LapTrinhTienTien.ui.Admin.adminForm;
import org.LapTrinhTienTien.ui.Admin.loginForm;
import org.LapTrinhTienTien.ui.HoaDon.CreateBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import java.awt.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class SpringBootRestApplication {
    public static void main(String[] args) {
       // SpringApplication.run(SpringBootRestApplication.class, args);
        new SpringApplicationBuilder(SpringBootRestApplication.class)
                .headless(false)
                .web(WebApplicationType.NONE)
                .run(args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(loginForm controller) {

        return args -> {
           // EventQueue.invokeLater(()->f.setVisible(true));
            EventQueue.invokeLater(() -> controller.setVisible(true));
        };
    }
}