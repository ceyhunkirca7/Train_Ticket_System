package Backend.SeferYonetimSistemi;

import java.util.ArrayList;

public class Hat {
    private String HatIsmi;
    private ArrayList<String> duraklar;
    
    public Hat() { this.duraklar=new ArrayList<>(); }

    public void setHatIsmi(String HatIsmi) {
        this.HatIsmi = HatIsmi;
    }
    public Hat(String HatIsmi){
        this.HatIsmi=HatIsmi;
        this.duraklar=new ArrayList<>();
    }
    public void addDurak(String durakNamex){
        getDuraklar().add(durakNamex);
    }
    public String getHatIsmi() {
        return HatIsmi;
    }
    public void listDuraklar(){
        for(String x: getDuraklar()){
            System.out.println(x+"\n");
        }
    }
    public ArrayList<String> getDuraklar() {
        return duraklar;
    }

}
