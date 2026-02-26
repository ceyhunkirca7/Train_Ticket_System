package GUI;

import Backend.MusteriKayitSistemi.MusteriKayit;
import Backend.MusteriKayitSistemi.User;
import Backend.SeferYonetimSistemi.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SeferGoruntule extends JFrame implements ActionListener {
    private static final String FILE_PATH = "seferYonetim.json";
    private JButton[][] seats;
    private JButton forwardButton, backwardButton;
    private JPanel seatPanel;
    private int currentVagon = 0;
    ArrayList<Bilet> biletler;
    private JButton selectedSeat = null; // selectedSeat tanımlandı
    MusteriKayit musteriKayitx; int musteriIndex; SeferYonetim seferYonetimx;
    Sefer selectedSefer; String fromStop, toStop;
    
    public SeferGoruntule(SeferYonetim seferYonetimx, Sefer selectedSefer) {
        super("Tren Koltuk Rezervasyonu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 250);
        
        seferYonetimx = loadDataFromFile();
        if(seferYonetimx==null){
            seferYonetimx = new SeferYonetim();
        }
        int i=0, selectedIndex1=-1;
        ArrayList<Sefer> seferler=seferYonetimx.getSeferler();
        for(Sefer x:seferler){
            i++;
            if(x.getSeferNo().equals(selectedSefer.getSeferNo())){
                selectedIndex1=i;
            }
        }       
        if(selectedIndex1!=-1){
             this.biletler=seferYonetimx.getSeferler().get(selectedIndex1-1).getBiletler();            
        }else{

        }           
        this.selectedSefer=selectedSefer;
        // Ana panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Üst koltuk paneli
        seatPanel = new JPanel(new GridLayout(4, 20, 5, 5));
        addSeatsToPanel(seatPanel, currentVagon);

        // Buton paneli (ödeme, iptal, ileri, geri)
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        forwardButton = new JButton("İleri");
        forwardButton.addActionListener(this);
        backwardButton = new JButton("Geri");
        backwardButton.addActionListener(this);
        buttonPanel.add(forwardButton);
        buttonPanel.add(backwardButton);

        // Ana paneli oluşturulan panellere ekleme
        mainPanel.add(seatPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Pencereye ana paneli ekleme
        add(mainPanel);

    }
    
    private static SeferYonetim loadDataFromFile() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(FILE_PATH), SeferYonetim.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private static void saveDataToFile(SeferYonetim seferYonetim) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(FILE_PATH), seferYonetim);
            System.out.println("JSON verisi dosyaya yazıldı.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
private void addSeatsToPanel(JPanel panel, int vagon) {
    panel.removeAll();
    panel.setLayout(new GridLayout(5, 10, 5, 5)); // 5 satır ve 10 sütun olacak şekilde güncellendi
    seats = new JButton[5][10]; // 5 satır ve 10 sütun olacak şekilde güncellendi
    int koltukNo = vagon * 40; // Koltuk numarası hesaplanırken vagon sayısıyla çarpım yapılacak
    for (int i = 0; i < 5; i++) { // 5 satır olacak şekilde güncellendi
        for (int j = 0; j < 10; j++) { // 10 sütun olacak şekilde güncellendi
            if (i == 2) { // 2. ve 4. satır için
                panel.add(new JLabel()); // Koridor boşluğu eklenir
            } else {
                JButton seat = new JButton(""+(koltukNo + 1)); // Koltuk numarası her koltuk için gösteriliyor
                seat.setPreferredSize(new Dimension(50, 30));
                seat.addActionListener(this);
                panel.add(seat);
                seats[i][j] = seat;
                if (isSeatSold(koltukNo + 1)) {
                    seat.setBackground(Color.RED);
                    //seat.setEnabled(false);
                }
                koltukNo++; // Sonraki koltuk numarası için
            }
        }
    }
    panel.revalidate();
    panel.repaint();
}

public int getSelectedSeatNumber() {
    if (selectedSeat != null) {
        String seatText = selectedSeat.getText();
        return Integer.parseInt(seatText);
    } else {
        return -1; // Eğer hiçbir koltuk seçilmediyse -1 döndür
    }
}

    private boolean isSeatSold(int koltukNo) {
        for (Bilet bilet : biletler) {
            if (bilet.getKoltukNo() == koltukNo) {
                return true;
            }
        }
        return false;
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == forwardButton) {
            if (currentVagon < 5) {
                currentVagon++;
                addSeatsToPanel(seatPanel, currentVagon);
                selectedSeat = null;
                
            }
        } else if (e.getSource() == backwardButton) {
            if (currentVagon > 0) {
                currentVagon--;
                addSeatsToPanel(seatPanel, currentVagon);
                selectedSeat = null;
                
            }
        } else {
            // Koltuk seçimi
            JButton clickedSeat = (JButton) e.getSource();
            
            if (selectedSeat == clickedSeat) {
                selectedSeat = null; // Tekrar aynı koltuğa tıklanırsa seçimi kaldır
            } else {
                selectedSeat = clickedSeat;
            }
                int selectedSeatNumber = getSelectedSeatNumber();
                if (selectedSeatNumber != -1) {
                    for(Bilet x:biletler){
                        if (x.getKoltukNo()==selectedSeatNumber) {
                            String str = x.getYolcu().toString();
                            JOptionPane.showMessageDialog(this, str+" Koltuk no: " + selectedSeatNumber);
                        }
                    }
                } else {
                    
                }
                
        }
        int vagonNo=currentVagon+1;
        setTitle("Tren Koltuk Rezervasyonu - Vagon "+vagonNo);
    }
}