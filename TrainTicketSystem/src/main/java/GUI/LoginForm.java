package GUI;

import Backend.MusteriKayitSistemi.MusteriKayit;
import Backend.MusteriKayitSistemi.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

public class LoginForm extends JFrame {
    private static final String FILE_PATH = "musteriKayit.json";
    public static MusteriKayit musterikayitx;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tfUsername;
    private JPasswordField tfPassword;

    private static void saveDataToFile(MusteriKayit musterikayit) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(FILE_PATH), musterikayit);
            System.out.println("JSON verisi dosyaya yazıldı.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static MusteriKayit loadDataFromFile() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(FILE_PATH), MusteriKayit.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
					
			        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			        int screenWidth = screenSize.width;
			        int screenHeight = screenSize.height;
			        
			       
			        int frameWidth = frame.getWidth();
			        int frameHeight = frame.getHeight();
			        int x = (screenWidth - frameWidth) / 2;
			        int y = (screenHeight - frameHeight) / 2;
			        
			   
			        frame.setLocation(x, y);
			        
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public LoginForm() {
		musterikayitx = loadDataFromFile();
                if(musterikayitx == null){
                    musterikayitx = new MusteriKayit();
                    saveDataToFile(musterikayitx);
                }
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 51));
		panel.setBounds(0, 0, 450, 272);
		contentPane.add(panel);
		panel.setLayout(null);
		
		tfUsername = new JTextField();
		tfUsername.setToolTipText("");
		tfUsername.setBounds(180, 103, 166, 38);
		panel.add(tfUsername);
		tfUsername.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblNewLabel.setForeground(new Color(255, 250, 250));
		lblNewLabel.setBounds(102, 114, 66, 16);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setForeground(new Color(255, 250, 250));
		lblNewLabel_1.setBounds(108, 164, 64, 16);
		panel.add(lblNewLabel_1);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			String username=tfUsername.getText();
			char[] passwordChars = tfPassword.getPassword();
			String password = new String(passwordChars).trim();
			if(username.equals("admin") && password.equals("admin")){
                        
                            Menu menuFrame = new Menu();
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		        int screenWidth = screenSize.width;
		        int screenHeight = screenSize.height;
		        
		        // Calculate the position of the frame
		        int frameWidth = menuFrame.getWidth();
		        int frameHeight = menuFrame.getHeight();
		        int x = (screenWidth - frameWidth) / 2;
		        int y = (screenHeight - frameHeight) / 2;
		        
		        // Set the position of the frame
		        menuFrame.setLocation(x, y);
		        //Menu.menuFrame = menuFrame;
				menuFrame.setVisible(true);
                        
                        } else if(username.isEmpty() || password.isEmpty()) {
                                    JOptionPane.showMessageDialog(btnLogin, "Please fill in all the text fields.");
				}else {
					int user = musterikayitx.findUserByNameAndSurname(username, password);
					if(user== -1) {
						JFrame parentFrame = new JFrame();
				        parentFrame.setSize(300, 200);
				        
				        // Show a message dialog with a message, title, and message type
				        JOptionPane.showMessageDialog(parentFrame,
				                "Invalid Username or Password!",
				                "Invalid Username or Password!",
				               JOptionPane.INFORMATION_MESSAGE);
				        
				        // Close the parent frame
				        parentFrame.dispose();
					}else{
						//User musteri=musterikayitx.getUsers().get(user-1);
                                                
                                                //System.out.println(musteri.getId());
                                                MusteriMenu musteriMenuFrame = new MusteriMenu(user-1);
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		        int screenWidth = screenSize.width;
		        int screenHeight = screenSize.height;
		        
		        // Calculate the position of the frame
		        int frameWidth = musteriMenuFrame.getWidth();
		        int frameHeight = musteriMenuFrame.getHeight();
		        int x = (screenWidth - frameWidth) / 2;
		        int y = (screenHeight - frameHeight) / 2;
		        
		        // Set the position of the frame
		        musteriMenuFrame.setLocation(x, y);
		        //Menu.menuFrame = menuFrame;
				musteriMenuFrame.setVisible(true);
                        
                                        
                                        }
					
					
				}
			
			}
		});
		btnLogin.setForeground(new Color(0, 0, 51));
		btnLogin.setBounds(261, 203, 85, 29);
		panel.add(btnLogin);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                        SignUpForm frame = new SignUpForm(musterikayitx);
                        // Get the screen dimensions
		        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		        int screenWidth = screenSize.width;
		        int screenHeight = screenSize.height;
		        
		        // Calculate the position of the frame
		        int frameWidth = frame.getWidth();
		        int frameHeight = frame.getHeight();
		        int x = (screenWidth - frameWidth) / 2;
		        int y = (screenHeight - frameHeight) / 2;
		        
		        // Set the position of the frame
		        frame.setLocation(x, y);
		        SignUpForm.frame = frame;
				frame.setVisible(true);
			}
		});
		btnSignUp.setForeground(new Color(0, 0, 51));
		btnSignUp.setBounds(180, 203, 85, 29);
		panel.add(btnSignUp);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(0, 0, 450, 61);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Login Form");
		lblNewLabel_2.setForeground(new Color(255, 250, 250));
		lblNewLabel_2.setFont(new Font("PT Serif", Font.BOLD, 26));
		lblNewLabel_2.setBounds(6, 6, 228, 49);
		panel_1.add(lblNewLabel_2);
		
		tfPassword = new JPasswordField();
		tfPassword.setBounds(180, 153, 166, 38);
		panel.add(tfPassword);
	}
}
