package Backend.SeferYonetimSistemi;

import java.util.ArrayList;

public class CalisanYonetim {
    private final ArrayList<Calisan> calisanlar;
    
    public CalisanYonetim(){
        this.calisanlar=new ArrayList<>();
    }
    public int CalisanOlustur(String isim, String soyisim, String pozisyon){
        for(Calisan x:getCalisanlar()){
            if((x.getIsim() == null ? isim == null : x.getIsim().equals(isim)) && (x.getSoyisim() == null ? soyisim == null : x.getSoyisim().equals(soyisim))){
                return -1;
            }
        }
        Calisan tmp = new Calisan(isim,soyisim,pozisyon);
        getCalisanlar().add(tmp);
        return 1;
    }
    public ArrayList<Calisan> getCalisanlar() {
        return calisanlar;
    }
    
}
