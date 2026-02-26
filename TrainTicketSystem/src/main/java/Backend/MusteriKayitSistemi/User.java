package Backend.MusteriKayitSistemi;

import Backend.SeferYonetimSistemi.Bilet;
import java.util.ArrayList;

public class User {
    private Integer id;	
    private String username;	
    private String password;	
    private String name;       
    private String adres;
    private String ePosta;
    private String numara;
    private String surname;
    ArrayList<Bilet> biletler;
    private Double Bakiye;
    
    public User () {}
    public User(String username, String password, String name, String surname) {
            super();		
            this.username = username;
            this.password = password;
            this.name = name;
            this.surname = surname;
            this.biletler=new ArrayList<>();
            this.Bakiye=0.0;
    }
    public int biletSatinAl(Bilet biletx){      
        Double mevcutBakiye=getBakiye();
        Double biletFiyat = biletx.getSefer().getFiyat();
        setBakiye(mevcutBakiye-biletFiyat);
        if(mevcutBakiye>=biletFiyat){
            setBakiye(mevcutBakiye-biletFiyat);
            biletler.add(biletx);
            return 1;
        }
        return -1;
    }
    public String getAdres() {
        return adres;
    }
    public void setAdres(String adres) {
        this.adres = adres;
    }
    public String getePosta() {
        return ePosta;
    }
    public void setePosta(String ePosta) {
        this.ePosta = ePosta;
    }
    public String getNumara() {
        return numara;
    }
    public void setNumara(String numara) {
        this.numara = numara;
    }	
    public String getName() {
            return name;
    }
    public void setName(String name) {
            this.name = name;
    }
    public String getSurname() {
            return surname;
    }
    public void setSurname(String surname) {
            this.surname = surname;
    }		

    public Integer getId() {
            return id;
    }
    public void setId(Integer id) {
            this.id = id;
    }
    public String getUsername() {
            return username;
    }
    public void setUsername(String userrname) {
            this.username = userrname;
    }
    public String getPassword() {
            return password;
    }
    public void setPassword(String password) {
            this.password = password;
    }
    @Override
    public String toString() {
            return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", surname="
                            + surname + "]";
    }
    public Double getBakiye() {
        return Bakiye;
    }
    public void setBakiye(Double Bakiye) {
        this.Bakiye = Bakiye;
    }
    public ArrayList<Bilet> getBiletler() {
        return biletler;
    }
	
}

