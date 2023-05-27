package org.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;
import java.util.List;
import javax.swing.*;

public class Gui extends JFrame {
    String voornaam;
    String achternaam;
    String email;
    String wachtwoord;

    public Gui() {
        String url = "jdbc:mysql://localhost:3306/nerdygadgets";
        String username = "root";
        String password = "";


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
                        Routebepaling routebepaling = new Routebepaling();
                        routebepaling.setVisible(true);
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

        Adres mijnAdres = new Adres("1319AJ", "9");
        LocatieApi api = new LocatieApi(mijnAdres.getPostcode(), mijnAdres.getHuisnummer());
        Adres dummieAdres2 = new Adres("1323LP", "9");
        LocatieApi api2 = new LocatieApi(dummieAdres2.getPostcode(), dummieAdres2.getHuisnummer());
        Adres dummieAdres3 = new Adres("1321GZ", "21");
        LocatieApi api3 = new LocatieApi(dummieAdres3.getPostcode(), dummieAdres3.getHuisnummer());
        Adres dummieAdres4 = new Adres("1102AK", "100");
        LocatieApi api4 = new LocatieApi(dummieAdres4.getPostcode(), dummieAdres4.getHuisnummer());


        Berekening.bezorgLocaties.add(new Routebepaling.Locatie(mijnAdres.getPostcode(), mijnAdres.getHuisnummer(), Double.parseDouble(api.getLatitude()), Double.parseDouble(api.getLongitude())));
        this.bezorgLocaties.add(new Routebepaling.Locatie(dummieAdres2.getPostcode(), dummieAdres2.getHuisnummer(), Double.parseDouble(api2.getLatitude()), Double.parseDouble(api2.getLongitude())));
        this.bezorgLocaties.add(new Routebepaling.Locatie(dummieAdres3.getPostcode(), dummieAdres3.getHuisnummer(), Double.parseDouble(api3.getLatitude()), Double.parseDouble(api3.getLongitude())));
        this.bezorgLocaties.add(new Routebepaling.Locatie(dummieAdres4.getPostcode(), dummieAdres4.getHuisnummer(), Double.parseDouble(api4.getLatitude()), Double.parseDouble(api4.getLongitude())));

        Adres magazijnAdres = new Adres("1315RC", "5");
        LocatieApi apiMagazijn = new LocatieApi(magazijnAdres.getPostcode(), magazijnAdres.getHuisnummer());
        Berekening startPoint = new Berekening(magazijnAdres.getPostcode(), magazijnAdres.getHuisnummer(), Double.parseDouble(apiMagazijn.getLatitude()), Double.parseDouble(apiMagazijn.getLongitude()));
        Berekening nearestNeighbour = findNearestNeighbour(bezorgLocaties, startPoint);


        JDialog dialog = new JDialog();
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JLabel label = new JLabel("Nearest Neighbour: " + nearestNeighbour.getAdres());
        panel.add(label);


//        for (NearestNeighbour.Locatie i:
//        int teller;
//        for (Locatie bestemming : locaties.bezorgLocaties) {
//
//            LocatieApi api = new LocatieApi(bezorgLocaties.get(teller).);
////            for(int i = 0; i > locaties.cities.size(); i++){
////
////            }
//            Adres adres = new Adres("1319AJ", "9");
//            JLabel bestemming. =new JLabel(adres.getPostcode() + " " + adres.getHuisnummer());
//            panel.add(locatie);
//        }
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(100, 25));
        dialog.add(textField);
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(100, 25));
        button.setText("Button");
        panel.add(textField);
        panel.add(button);
        frame.add(panel);
        frame.setSize(250, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
    }

    public static Routebepaling.Locatie findNearestNeighbour(List<Routebepaling.Locatie> locaties, Routebepaling.Locatie startPoint) {
        if (locaties.isEmpty()) {
            return null;
        }

        Routebepaling.Locatie nearestNeighbour = locaties.get(0);
        double shortestDistance = startPoint.distanceTo(nearestNeighbour);

        for (int i = 1; i < locaties.size(); i++) {
            Routebepaling.Locatie currentCity = locaties.get(i);
            double currentDistance = startPoint.distanceTo(currentCity);

            if (currentDistance < shortestDistance) {
                nearestNeighbour = currentCity;
                shortestDistance = currentDistance;
            }
        }

        return nearestNeighbour;
    }

    public static class Locatie {
        private String adres;
        private String postcode;
        private String huisnummer;

        private double latitude;
        private double longitude;

        public Locatie(String postcode, String huisnummer, double latitude, double longitude) {
            this.adres = postcode + " " + huisnummer;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String getAdres() {
            return adres;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public double distanceTo(Routebepaling.Locatie other) {
            double lat1 = Math.toRadians(this.latitude);
            double lon1 = Math.toRadians(this.longitude);
            double lat2 = Math.toRadians(other.latitude);
            double lon2 = Math.toRadians(other.longitude);

            double earthRadius = 6371; // in kilometers

            double dLat = lat2 - lat1;
            double dLon = lon2 - lon1;

            double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                    Math.cos(lat1) * Math.cos(lat2) *
                            Math.sin(dLon / 2) * Math.sin(dLon / 2);

            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

            double distance = earthRadius * c;
            return distance;
        }
    }
    public static class Berekening {
        private String adres;
//    private String postcode;
//    private String huisnummer;

        private double latitude;
        private double longitude;

        public Berekening(String postcode, String huisnummer, double latitude, double longitude) {
            this.adres = postcode + " " + huisnummer;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String getAdres() {
            return adres;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public double distanceTo(org.example.Berekening other) {
            double lat1 = Math.toRadians(this.latitude);
            double lon1 = Math.toRadians(this.longitude);
            double lat2 = Math.toRadians(other.latitude);
            double lon2 = Math.toRadians(other.longitude);

            double earthRadius = 6371; // in kilometers

            double dLat = lat2 - lat1;
            double dLon = lon2 - lon1;

            double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                    Math.cos(lat1) * Math.cos(lat2) *
                            Math.sin(dLon / 2) * Math.sin(dLon / 2);

            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

            double distance = earthRadius * c;
            return distance;
        }
        public static org.example.Berekening findNearestNeighbour(List<org.example.Berekening> locaties, org.example.Berekening startPoint) {
            if (locaties.isEmpty()) {
                return null;
            }

            org.example.Berekening nearestNeighbour = locaties.get(0);
            double shortestDistance = startPoint.distanceTo(nearestNeighbour);

            for (int i = 1; i < locaties.size(); i++) {
                org.example.Berekening currentCity = locaties.get(i);
                double currentDistance = startPoint.distanceTo(currentCity);

                if (currentDistance < shortestDistance) {
                    nearestNeighbour = currentCity;
                    shortestDistance = currentDistance;
                }
            }

            return nearestNeighbour;
        }

    }

}