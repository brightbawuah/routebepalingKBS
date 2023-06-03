package org.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Gui extends JFrame {
    String voornaam;
    String achternaam;
    String email;
    String wachtwoord;
    public List<String> databaseAdressen = new ArrayList<>();


    public Gui() {
        String url = "jdbc:mysql://localhost:3306/nerdygadgets";
        String username = "root";
        String password = "";

        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        JDialog jd = new JDialog(this, "Login", true);
        jd.setLayout(new FlowLayout());
        jd.setSize(200, 125);
        jd.setTitle("Login");
        JPanel jdPanel1 = new JPanel();
        jdPanel1.setPreferredSize(new Dimension(180, 50));
        jdPanel1.setLayout(new GridLayout(2, 2));
        JLabel loginLabel = new JLabel("Email");
        jdPanel1.add(loginLabel);
        JTextField textEmail = new JTextField(10);
        jdPanel1.add(textEmail);
        JLabel loginLabel2 = new JLabel("Password");
        jdPanel1.add(loginLabel2);
        JTextField textPassword = new JTextField(10);
        jdPanel1.add(textPassword);
        JButton button = new JButton("Login");
        jd.add(jdPanel1);
        jd.add(button);
        JPanel mainPanel = new JPanel();

        mainPanel.setSize(new Dimension(600, 400));
        mainPanel.setPreferredSize(new Dimension(600, 400));
        add(mainPanel);
        jd.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try (Connection connection = DriverManager.getConnection(url, username, password)) {

                    String sql = "SELECT * FROM inloggegevens WHERE email =? AND wachtwoord =? AND medewerker = 'JA'";
                    PreparedStatement statement1 = null;
                    statement1 = connection.prepareStatement(sql);
                    statement1.setString(1, textEmail.getText());
                    statement1.setString(2, textPassword.getText());


                    ResultSet rs1 = statement1.executeQuery();

                    if (rs1.next() == false) {
                        JOptionPane.showMessageDialog(mainPanel, "De inlog is incorrect!", "Incorrect", JOptionPane.ERROR_MESSAGE);
                        textEmail.setText("");
                        textPassword.setText("");
                    } else {
                        JOptionPane.showMessageDialog(mainPanel, "De inlog is correct!", "Correct", JOptionPane.INFORMATION_MESSAGE);
                        voornaam = rs1.getString("Voornaam");
                        achternaam = rs1.getString("Achternaam");
                        email = rs1.getString("Email");
                        wachtwoord = rs1.getString("Wachtwoord");
                        jd.dispose();
                    }
                    statement1.close();
                    connection.close();
                } catch (SQLException ee) {
                    JOptionPane.showMessageDialog(mainPanel, ee.getMessage(), "Connection error", JOptionPane.ERROR_MESSAGE);
                }


            }
        });
//        jd.setIconImage(img.getImage());
        jd.setResizable(false);
        jd.setLocationRelativeTo(null);
        jd.setVisible(true);

        System.out.println();
//        try (Connection connection = DriverManager.getConnection(url, username, password)) {
//
//            String sql = "SELECT TOP 10 Postcode, Huisnummer FROM bestelling WHERE";
//            Statement statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery(sql);
//            while (rs.next()){
//                String postcode = rs.getString("Postcode");
//                String huisnummer = rs.getString("Huisnummer");
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        String dummiePostcode = "1319AJ";
        String dummieHuisnummer = "9";
        String dummiePostcode2 = "1323LP";
        String dummieHuisnummer2 = "9";
        String dummiePostcode3 = "1102AK";
        String dummieHuisnummer3 = "100";
        String dummiePostcode4 = "1272BC";
        String dummieHuisnummer4 = "26";
        String dummiePostcode5 = "1103JP";
        String dummieHuisnummer5 = "96";
        String dummiePostcode6 = "1313GA";
        String dummieHuisnummer6 = "60";
        String dummiePostcode7 = "1104SE";
        String dummieHuisnummer7 = "1389";
        String magazinPostcode = "1315RC";
        String magazijnHuisnummer = "5";

        ArrayList<Routebepaling.Adres> gesorteedeAdressen = new ArrayList<>();

        Routebepaling locatie = new Routebepaling();
        LocatieApi api = new LocatieApi(dummiePostcode, dummieHuisnummer);
        locatie.adressen.add(new Routebepaling.Adres(dummiePostcode, dummieHuisnummer, Double.parseDouble(api.getLatitude()), Double.parseDouble(api.getLongitude())));
        LocatieApi api2 = new LocatieApi(dummiePostcode2, dummieHuisnummer2);
        locatie.adressen.add(new Routebepaling.Adres(dummiePostcode2, dummieHuisnummer2, Double.parseDouble(api2.getLatitude()), Double.parseDouble(api2.getLongitude())));
        LocatieApi api3 = new LocatieApi(dummiePostcode3, dummieHuisnummer3);
        locatie.adressen.add(new Routebepaling.Adres(dummiePostcode3, dummieHuisnummer3, Double.parseDouble(api3.getLatitude()), Double.parseDouble(api3.getLongitude())));
        LocatieApi api4 = new LocatieApi(dummiePostcode4, dummieHuisnummer4);
        locatie.adressen.add(new Routebepaling.Adres(dummiePostcode4, dummieHuisnummer4, Double.parseDouble(api4.getLatitude()), Double.parseDouble(api4.getLongitude())));
        LocatieApi api5 = new LocatieApi(dummiePostcode5, dummieHuisnummer5);
        locatie.adressen.add(new Routebepaling.Adres(dummiePostcode5, dummieHuisnummer5, Double.parseDouble(api5.getLatitude()), Double.parseDouble(api5.getLongitude())));
        LocatieApi api6 = new LocatieApi(dummiePostcode6, dummieHuisnummer6);
        locatie.adressen.add(new Routebepaling.Adres(dummiePostcode6, dummieHuisnummer6, Double.parseDouble(api6.getLatitude()), Double.parseDouble(api6.getLongitude())));
        LocatieApi api7 = new LocatieApi(dummiePostcode7, dummieHuisnummer7);
        locatie.adressen.add(new Routebepaling.Adres(dummiePostcode7, dummieHuisnummer7, Double.parseDouble(api7.getLatitude()), Double.parseDouble(api7.getLongitude())));


        LocatieApi magazijnApi = new LocatieApi(magazinPostcode, magazijnHuisnummer);
        Routebepaling.Adres startPoint = new Routebepaling.Adres(magazinPostcode, magazijnHuisnummer, Double.parseDouble(magazijnApi.getLatitude()), Double.parseDouble(magazijnApi.getLongitude()));
        Routebepaling.Adres nearestNeighbor = Routebepaling.findNearestNeighbor(locatie.adressen, startPoint);


        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JLabel label = new JLabel("Volgende locatie: " + nearestNeighbor.getAdres());
        panel.add(label);

        System.out.println(locatie.adressen.size());
        System.out.println("Eerste Locatie: " + nearestNeighbor.getAdres());
        System.out.println(locatie.adressen.size());
        System.out.println(locatie.adressen);

        for (int i = 0; i < locatie.adressen.size() * locatie.adressen.size(); i++) {
            if (i == 0) {
                Routebepaling.Adres adres = Routebepaling.findNearestNeighbor(locatie.adressen, startPoint);
                gesorteedeAdressen.add(adres);
                locatie.adressen.remove(adres);

            } else {
                Routebepaling.Adres adres = Routebepaling.findNearestNeighbor(locatie.adressen, gesorteedeAdressen.get(i - 1));
                gesorteedeAdressen.add(adres);
                locatie.adressen.remove(adres);
            }
        }
        System.out.println(gesorteedeAdressen.size());
        System.out.println("Startpunt: " + magazinPostcode + " " + magazijnHuisnummer);
        for (int i = 0; i < gesorteedeAdressen.size(); i++) {
            System.out.println(i + 1 + "e locatie: " + gesorteedeAdressen.get(i).getAdres());
        }
        panel.setLayout(new FlowLayout());
        panel.add(label);
        mainPanel.add(panel);
        mainPanel.setSize(250, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


}