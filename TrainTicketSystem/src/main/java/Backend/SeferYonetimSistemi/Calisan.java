package Backend.SeferYonetimSistemi;

public class Calisan {
    private String isim, soyisim, pozisyon;
    
    public Calisan(){ }
    public Calisan(String isim, String soyisim, String pozisyon){
        this.isim=isim;
        this.soyisim=soyisim;
        this.pozisyon=pozisyon;
    }
    public String getIsim() {
        return isim;
    }
    public String getSoyisim() {
        return soyisim;
    }
    public String getPozisyon() {
        return pozisyon;
    }
    
}
