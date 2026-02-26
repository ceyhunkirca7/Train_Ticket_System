package Backend.SeferYonetimSistemi;

import java.util.ArrayList;
import java.util.Date;

public class SeferYonetim {
    private final ArrayList<Sefer> seferler;
    private final ArrayList<Hat> hatlar;
    
    public SeferYonetim(){
        this.seferler=new ArrayList<>();
        this.hatlar=new ArrayList<>();
    }
    public void seferEkle(Sefer sefer) {
        getSeferler().add(sefer);
    }
    public void seferSil(Sefer sefer) {
        getSeferler().remove(sefer);
    }
    public void hatEkle(Hat hat) {
        getHatlar().add(hat);
    }
    public void hatSil(Hat hat) {
        getHatlar().remove(hat);
    }
    public Hat hatOlustur(String namex){
        Hat hatx = new Hat(namex); 
        return hatx;
    }
    public Sefer seferOlustur(String seferNo, Date tarih, Hat hat, Double fiyat){
        Sefer seferx = new Sefer(seferNo,tarih,hat,fiyat);
        return seferx;
    }
    public void sefereCalisanEkle(Sefer seferx, Calisan calisanx){
        seferx.calisanEkle(calisanx);
    }
    public void seferdenCalisanSil(Sefer seferx, Calisan calisanx){
        seferx.calisanSil(calisanx);
    } 
    public ArrayList<Sefer> getSeferler() {
        return seferler;
    }
    public ArrayList<Hat> getHatlar() {
        return hatlar;
    }
    public void printSeferler(){
        for(Sefer x:seferler){
            System.out.println(x.getSeferNo()+" "+x.getTarih()+"\n");
        }
    }
}
