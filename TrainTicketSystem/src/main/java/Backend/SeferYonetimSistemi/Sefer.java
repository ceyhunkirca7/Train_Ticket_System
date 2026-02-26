package Backend.SeferYonetimSistemi;

import java.util.ArrayList;
import java.util.Date;

public class Sefer {
    ArrayList<Bilet> biletler;
    private ArrayList<Calisan> calisanlar;
    private Date tarih;
    private Hat hat;
    private String seferNo;
    private Double fiyat;
    
    public Sefer() { 
        this.biletler=new ArrayList<>(); 
        this.calisanlar=new ArrayList<>(); 
    }
    
    public Sefer(String seferNo, Date tarih, Hat hat, Double fiyat){
        this.seferNo=seferNo;
        this.tarih=tarih;
        this.hat=hat;
        this.biletler=new ArrayList<>();
        this.calisanlar=new ArrayList<>();
        this.fiyat=fiyat;
    }
    public void biletEkle(Bilet bilet) {
        biletler.add(bilet);
    }
    public void biletSil(Bilet bilet) {
        biletler.remove(bilet);
    }
    public Date getTarih() {
        return tarih;
    }
    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }
    public Hat getHat() {
        return hat;
    } 
    public String getSeferNo() {
        return seferNo;
    }
    public void setSeferNo(String seferNo) {
        this.seferNo = seferNo;
    }
    public ArrayList<Bilet> getBiletler() {
        return biletler;
    }
    public void setHat(Hat hat) {
        this.hat = hat;
    }
    public Double getFiyat() {
        return fiyat;
    }
    public void setFiyat(Double fiyat) {
        this.fiyat = fiyat;
    }
    public void calisanEkle(Calisan calisanx) {
        getCalisanlar().add(calisanx);
    }
    public void calisanSil(Calisan calisanx) {
        getCalisanlar().remove(calisanx);
    }
    public ArrayList<Calisan> getCalisanlar() {
        return calisanlar;
    }
    
}
