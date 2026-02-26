package GUI;

import Backend.MusteriKayitSistemi.MusteriKayit;
import Backend.MusteriKayitSistemi.User;
import Backend.SeferYonetimSistemi.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TicketSellingSystem {
    private static final String FILE_PATH = "seferYonetim.json";
    private static ArrayList<Sefer> seferler;
    private static ArrayList<Hat> hatlar;
    static ArrayList<Bilet> biletler;
    private static Sefer selectedSefer;
    private static ArrayList<String> butunDuraklar;
    MusteriKayit musteriKayitx; int musteriIndex;
            
    public static void main(String[] args) {
        TicketSellingSystem ticketSellingSystem = new TicketSellingSystem();
        ticketSellingSystem.start();
    }
    public TicketSellingSystem(){
        
    }
    public TicketSellingSystem(MusteriKayit musteriKayitx, int musteriIndex){
        this.musteriIndex=musteriIndex;
        this.musteriKayitx=musteriKayitx;
    }
    
    public void start() {
        SeferYonetim seferYonetimx = loadDataFromFile();
        if (seferYonetimx == null) {
            seferYonetimx = new SeferYonetim();
        }
        seferler = seferYonetimx.getSeferler();
        hatlar = seferYonetimx.getHatlar();

        // Tüm durakları kaydet
        butunDuraklar = new ArrayList<>();
        for (Hat hat : seferYonetimx.getHatlar()) {
            for (String durak : hat.getDuraklar()) {
                if (!butunDuraklar.contains(durak)) {
                    butunDuraklar.add(durak);
                }
            }
        }

        showSearchTicketDialog();
    }

    private void showSearchTicketDialog() {
        
        // Sefer arama penceresini oluştur
        JFrame searchTicketFrame = new JFrame("Sefer Ara");
        searchTicketFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchTicketFrame.setSize(400, 300);
        searchTicketFrame.setLayout(new BorderLayout());

        // Sefer arama panelini oluştur
        JPanel searchTicketPanel = new JPanel();
        searchTicketPanel.setLayout(new GridLayout(6, 2));

        JComboBox<Integer> dayComboBox = new JComboBox<>();
        for (int i = 1; i <= 31; i++) {
            dayComboBox.addItem(i);
        }

        JComboBox<Integer> monthComboBox = new JComboBox<>();
        for (int i = 1; i <= 12; i++) {
            monthComboBox.addItem(i);
        }

        JComboBox<Integer> yearComboBox = new JComboBox<>();
        for (int i = 2022; i <= 2030; i++) {
            yearComboBox.addItem(i);
        }

        JComboBox<String> fromComboBox = new JComboBox<>(butunDuraklar.toArray(new String[0]));
        JComboBox<String> toComboBox = new JComboBox<>(butunDuraklar.toArray(new String[0]));

        JButton searchButton = new JButton("Ara");
        JButton cancelButton = new JButton("İptal");

        searchTicketPanel.add(new JLabel("Gün: "));
        searchTicketPanel.add(dayComboBox);
        searchTicketPanel.add(new JLabel("Ay: "));
        searchTicketPanel.add(monthComboBox);
        searchTicketPanel.add(new JLabel("Yıl: "));
        searchTicketPanel.add(yearComboBox);
        searchTicketPanel.add(new JLabel("Başlangıç Durak: "));
        searchTicketPanel.add(fromComboBox);
        searchTicketPanel.add(new JLabel("Varış Durak: "));
        searchTicketPanel.add(toComboBox);
        searchTicketPanel.add(searchButton);
        searchTicketPanel.add(cancelButton);

        // Olay dinleyicilerini ekle
        searchButton.addActionListener(new ActionListener() {
            private ArrayList<Bilet> biletler;
            @Override
            public void actionPerformed(ActionEvent e) {
                int day = (int) dayComboBox.getSelectedItem();
                int month = (int) monthComboBox.getSelectedItem();
                int year = (int) yearComboBox.getSelectedItem();

                // Sefer arama tarihini oluştur
                Calendar searchDateCalendar = Calendar.getInstance();
                searchDateCalendar.set(year, month - 1, day);

                Date searchDate = new Date(year - 1900, month - 1, day);
                String fromStop = (String) fromComboBox.getSelectedItem();
                String toStop = (String) toComboBox.getSelectedItem();
                ArrayList<Sefer> matchingSeferler = new ArrayList<>();

                for (Sefer sefer : seferler) {
                    Date seferDate = new Date(sefer.getTarih().getYear(), sefer.getTarih().getMonth(), sefer.getTarih().getDay());
                    // Sefer tarihini al
                    Calendar seferDateCalendar = Calendar.getInstance();
                    seferDateCalendar.setTime(sefer.getTarih());

                    if (seferDateCalendar.get(Calendar.YEAR) == searchDateCalendar.get(Calendar.YEAR) &&
                            seferDateCalendar.get(Calendar.MONTH) == searchDateCalendar.get(Calendar.MONTH) &&
                            seferDateCalendar.get(Calendar.DAY_OF_MONTH) == searchDateCalendar.get(Calendar.DAY_OF_MONTH) && sefer.getHat().getDuraklar().contains(fromStop) && sefer.getHat().getDuraklar().contains(toStop)) {
                        matchingSeferler.add(sefer);
                    }
                }

                if (matchingSeferler.isEmpty()) {
                    JOptionPane.showMessageDialog(searchTicketFrame, "Seçilen tarihte ve güzergahta uygun sefer bulunamadı.");
                } else {
                    JPanel seferListPanel = new JPanel();
                    seferListPanel.setLayout(new GridLayout(matchingSeferler.size() + 1, 1));

                    JLabel headerLabel = new JLabel("Sefer No | Tarih | Hat ");
                    headerLabel.setFont(new Font("Arial", Font.BOLD, 14));
                    seferListPanel.add(headerLabel);

                    ButtonGroup seferButtonGroup = new ButtonGroup();

                    for (Sefer matchingSefer : matchingSeferler) {
                        JRadioButton radioButton = new JRadioButton(matchingSefer.getSeferNo() + " | " + matchingSefer.getTarih() + " | " + matchingSefer.getHat().getHatIsmi());
                        radioButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                selectedSefer = matchingSefer;
                            }
                        });
                        seferButtonGroup.add(radioButton);
                        seferListPanel.add(radioButton);
                    }

                    int result = JOptionPane.showConfirmDialog(searchTicketFrame, seferListPanel, "Uygun seferler", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                    if (result == JOptionPane.OK_OPTION) {
                        if (selectedSefer != null) {
                            this.biletler=selectedSefer.getBiletler();
                            TrainSeatReservation trainSeatReservation = new TrainSeatReservation(selectedSefer,musteriKayitx, musteriIndex, fromStop, toStop);
                            trainSeatReservation.setVisible(true);
                            //selectedSefer.getBiletler().add(bilet); PROGRAMI BOZUYOR BU KOD SAKIN ACMAYIN
                            
                        } else {
                            JOptionPane.showMessageDialog(searchTicketFrame, "Lütfen bir sefer seçin.");
                        }
                    }
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchTicketFrame.dispose();
            }
        });

        // Sefer arama panelini sefer arama penceresine ekle
        searchTicketFrame.add(searchTicketPanel, BorderLayout.CENTER);

        // Sefer arama penceresini görünür yap
        searchTicketFrame.setVisible(true);
    }

    private SeferYonetim loadDataFromFile() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(FILE_PATH), SeferYonetim.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
