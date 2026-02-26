package Backend.SeferYonetimSistemi;

import Backend.MusteriKayitSistemi.User;

public class Bilet {
    String baslangicDurak;
    String bitisDurak;
    Sefer sefer;
    int koltukNo;
    User yolcu;
    int biletDurum;
        
    public Bilet(){};
    public Bilet(String baslangicDurak, String bitisDurak, Sefer sefer, int koltukNo){
        this.baslangicDurak=baslangicDurak;
        this.bitisDurak=bitisDurak;
        this.sefer=sefer;
        this.koltukNo=koltukNo;
        //this.biletDurum=0; //-1:bilet alindi iptal edildi 0:bilet alindi sefer henuz gerçekleşmedi 1:bilet alindi sefer gerçekleşti
    }
    public String getBaslangicDurak() {
        return baslangicDurak;
    }
    public String getBitisDurak() {
        return bitisDurak;
    }
    public Sefer getSefer() {
        return sefer;
    }
    public int getKoltukNo() {
        return koltukNo;
    }
    public User getYolcu() {
        return yolcu;
    }
    public int getBiletDurum() {
        return biletDurum;
    }
    public void BiletDurumGuncelle(int biletDurum) {
        this.biletDurum = biletDurum;
    }
  
}
