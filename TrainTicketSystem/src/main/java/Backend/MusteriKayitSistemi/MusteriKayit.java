package Backend.MusteriKayitSistemi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MusteriKayit {
    private static final String FILE_PATH = "musteriKayit.json";
    private ArrayList<User> Users;
    
    public MusteriKayit(){
        this.Users=new ArrayList<>();
    } 
    public int findUserByNameAndSurname(String username, String password) {
       int i=0;
        for (User userx: getUsers()) {
            i++;
            if (userx.getUsername().equals(username) && userx.getPassword().equals(password)) {
                return i; 
            }
        }
        return -1; 
    }
    public User MusteriOlustur( String username, String password, String name, String surname) {
        User temp = new User( username, password,name,surname);  
       temp.setId(Users.size()+1);
       getUsers().add(temp);
       
       return temp;
    }
    public void MusteriSil(User userx) {
      getUsers().remove(userx);
    }
    public ArrayList <User> getUsers() {
        return Users;
    }        
    //4
    public static void saveDataToFile(MusteriKayit musterikayit) {
    try {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(new File(FILE_PATH), musterikayit);
        System.out.println("JSON verisi dosyaya yazıldı.");
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}

