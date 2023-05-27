//package org.example;
//
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.List;
//import javax.swing.*;
//
//
//public class Routebepaling extends JDialog{
//
//    public List<Locatie> bezorgLocaties = new ArrayList<>();
//    private int teller;
//
//    public Routebepaling() {
//        Adres mijnAdres = new Adres("1319AJ", "9");
//        LocatieApi api = new LocatieApi(mijnAdres.getPostcode(), mijnAdres.getHuisnummer());
//        Adres dummieAdres2 = new Adres("1323LP", "9");
//        LocatieApi api2 = new LocatieApi(dummieAdres2.getPostcode(), dummieAdres2.getHuisnummer());
//        Adres dummieAdres3 = new Adres("1321GZ", "21");
//        LocatieApi api3 = new LocatieApi(dummieAdres3.getPostcode(), dummieAdres3.getHuisnummer());
//        Adres dummieAdres4 = new Adres("1102AK", "100");
//        LocatieApi api4 = new LocatieApi(dummieAdres4.getPostcode(), dummieAdres4.getHuisnummer());
//
//
//        this.bezorgLocaties.add(new Locatie(mijnAdres.getPostcode(), mijnAdres.getHuisnummer(), Double.parseDouble(api.getLatitude()), Double.parseDouble(api.getLongitude())));
//        this.bezorgLocaties.add(new Locatie(dummieAdres2.getPostcode(), dummieAdres2.getHuisnummer(), Double.parseDouble(api2.getLatitude()), Double.parseDouble(api2.getLongitude())));
//        this.bezorgLocaties.add(new Locatie(dummieAdres3.getPostcode(), dummieAdres3.getHuisnummer(), Double.parseDouble(api3.getLatitude()), Double.parseDouble(api3.getLongitude())));
//        this.bezorgLocaties.add(new Locatie(dummieAdres4.getPostcode(), dummieAdres4.getHuisnummer(), Double.parseDouble(api4.getLatitude()), Double.parseDouble(api4.getLongitude())));
//
//        Adres magazijnAdres = new Adres("1315RC", "5");
//        LocatieApi apiMagazijn = new LocatieApi(magazijnAdres.getPostcode(), magazijnAdres.getHuisnummer());
//        Locatie startPoint = new Locatie(magazijnAdres.getPostcode(), magazijnAdres.getHuisnummer(), Double.parseDouble(apiMagazijn.getLatitude()), Double.parseDouble(apiMagazijn.getLongitude()));
//        Locatie nearestNeighbour = findNearestNeighbour(bezorgLocaties, startPoint);
//
//
//        JDialog dialog = new JDialog();
//        JPanel panel = new JPanel();
//        panel.setLayout(new FlowLayout());
//        JLabel label = new JLabel("Nearest Neighbour: " + nearestNeighbour.getAdres());
//        panel.add(label);
//
//
////        for (NearestNeighbour.Locatie i:
////        int teller;
////        for (Locatie bestemming : locaties.bezorgLocaties) {
////
////            LocatieApi api = new LocatieApi(bezorgLocaties.get(teller).);
//////            for(int i = 0; i > locaties.cities.size(); i++){
//////
//////            }
////            Adres adres = new Adres("1319AJ", "9");
////            JLabel bestemming. =new JLabel(adres.getPostcode() + " " + adres.getHuisnummer());
////            panel.add(locatie);
////        }
//        JTextField textField = new JTextField();
//        textField.setPreferredSize(new Dimension(100, 25));
//        dialog.add(textField);
//        JButton button = new JButton();
//        button.setPreferredSize(new Dimension(100, 25));
//        button.setText("Button");
//        panel.add(textField);
//        panel.add(button);
//        frame.add(panel);
//        frame.setSize(250, 300);
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////        frame.setVisible(true);
//    }
//
//    public static Locatie findNearestNeighbour(List<Locatie> locaties, Locatie startPoint) {
//        if (locaties.isEmpty()) {
//            return null;
//        }
//
//        Locatie nearestNeighbour = locaties.get(0);
//        double shortestDistance = startPoint.distanceTo(nearestNeighbour);
//
//        for (int i = 1; i < locaties.size(); i++) {
//            Routebepaling.Locatie currentCity = locaties.get(i);
//            double currentDistance = startPoint.distanceTo(currentCity);
//
//            if (currentDistance < shortestDistance) {
//                nearestNeighbour = currentCity;
//                shortestDistance = currentDistance;
//            }
//        }
//
//        return nearestNeighbour;
//    }

//    public  class Locatie {
//        private String adres;
//        private String postcode;
//        private String huisnummer;
//
//        private double latitude;
//        private double longitude;
//
//        public Locatie(String postcode, String huisnummer, double latitude, double longitude) {
//            this.adres = postcode + " " + huisnummer;
//            this.latitude = latitude;
//            this.longitude = longitude;
//        }
//
//        public String getAdres() {
//            return adres;
//        }
//
//        public double getLatitude() {
//            return latitude;
//        }
//
//        public double getLongitude() {
//            return longitude;
//        }
//
//        public double distanceTo(Locatie other) {
//            double lat1 = Math.toRadians(this.latitude);
//            double lon1 = Math.toRadians(this.longitude);
//            double lat2 = Math.toRadians(other.latitude);
//            double lon2 = Math.toRadians(other.longitude);
//
//            double earthRadius = 6371; // in kilometers
//
//            double dLat = lat2 - lat1;
//            double dLon = lon2 - lon1;
//
//            double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
//                    Math.cos(lat1) * Math.cos(lat2) *
//                            Math.sin(dLon / 2) * Math.sin(dLon / 2);
//
//            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//
//            double distance = earthRadius * c;
//            return distance;
//        }
//    }
//}
//
