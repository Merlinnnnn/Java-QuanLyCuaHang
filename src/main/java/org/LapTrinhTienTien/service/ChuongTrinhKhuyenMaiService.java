package org.LapTrinhTienTien.service;

import org.LapTrinhTienTien._enum.Status;
import org.LapTrinhTienTien.model.ChuongTrinhKhuyenMai;
import org.LapTrinhTienTien.repository.ChuongTrinhKhuyenMaiRepository;
import org.LapTrinhTienTien.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChuongTrinhKhuyenMaiService {
    @Autowired
    ChuongTrinhKhuyenMaiRepository chuongTrinhKhuyenMaiRepository;
    public List<ChuongTrinhKhuyenMai> getChuongTrinhKhuyenMaiHomNay() {
        LocalDateTime homNay = LocalDateTime.now();
        return  chuongTrinhKhuyenMaiRepository. findByNgayApDungBeforeAndNgayKetThucAfter(homNay,homNay);
    }
    public ChuongTrinhKhuyenMai getMaxGiamGia(float tongtien){
        List<ChuongTrinhKhuyenMai> chuongTrinhKhuyenMais = getChuongTrinhKhuyenMaiHomNay();
        System.out.println("ctkm-------"+chuongTrinhKhuyenMais.size());
        if(chuongTrinhKhuyenMais.isEmpty()){
            return null;
        }
        ChuongTrinhKhuyenMai chuongTrinhKhuyenMai = null;
        for(ChuongTrinhKhuyenMai ctkm : chuongTrinhKhuyenMais){
            System.out.println("ctkm-------"+ctkm.getTonghonDon());
            if(ctkm.getTonghonDon()<=tongtien && ctkm.getStatus().equals(Status.ACTIVE)){
                if(chuongTrinhKhuyenMai==null){
                    chuongTrinhKhuyenMai = ctkm;
                }
                if(ctkm.getPhanTramGiamGia()>chuongTrinhKhuyenMai.getPhanTramGiamGia()){
                    chuongTrinhKhuyenMai = ctkm;
                }
            }
        }
        return  chuongTrinhKhuyenMai;
    }
    public float getGiamGia(float tongtien){
        ChuongTrinhKhuyenMai ctkm = getMaxGiamGia(tongtien);

        if(ctkm==null){
            return 0f;
        }
        return ctkm.getPhanTramGiamGia()*ctkm.getTonghonDon()/100;
    }
    public List<ChuongTrinhKhuyenMai> getAllKhuyenMai(){
        return (List<ChuongTrinhKhuyenMai>) chuongTrinhKhuyenMaiRepository.findAll();
    }
    public Response saveChuongTrinhKM(ChuongTrinhKhuyenMai chuongTrinhKhuyenMai){
        try{
            ChuongTrinhKhuyenMai ctkm =  chuongTrinhKhuyenMaiRepository.save(chuongTrinhKhuyenMai);
            return new Response("Them thanh cong",true,ctkm);
        }catch (Exception e){
            return new Response("Them khong thanh cong",false,null);
        }

    }
    public Response updateChuongTrinhKM(ChuongTrinhKhuyenMai chuongTrinhKhuyenMai){
        try{
            ChuongTrinhKhuyenMai ctkm =  chuongTrinhKhuyenMaiRepository.save(chuongTrinhKhuyenMai);
            return new Response("Them thanh cong",true,ctkm);
        }catch (Exception e){
            return new Response("Them khong thanh cong",false,null);
        }

    }

}
