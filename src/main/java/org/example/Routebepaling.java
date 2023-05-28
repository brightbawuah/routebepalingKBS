package org.example;

import java.util.ArrayList;
import java.util.List;

public class Routebepaling {
    public List<Adres> adressen = new ArrayList<>();
//    public static void main(String[] args) {
//        Routebepaling locatie = new Routebepaling();
//        locatie.cities.add(new City("New York", 40.7128, -74.0060));
//        locatie.cities.add(new City("Los Angeles", 34.0522, -118.2437));
//        locatie.cities.add(new City("Chicago", 41.8781, -87.6298));
//        locatie.cities.add(new City("Houston", 29.7604, -95.3698));
//        locatie.cities.add(new City("Miami", 25.7617, -80.1918));
//
//        City startPoint = new City("San Francisco", 37.7749, -122.4194);
//        City nearestNeighbor = findNearestNeighbor(locatie.cities, startPoint);
//
//        System.out.println("Nearest Neighbor: " + nearestNeighbor.getName());
//    }


    public static Adres findNearestNeighbor(List<Adres> adressen, Adres startPoint) {
        if (adressen.isEmpty()) {
            return null;
        }

        Adres nearestNeighbor = adressen.get(0);
        double shortestDistance = startPoint.distanceTo(nearestNeighbor);

        for (int i = 1; i < adressen.size(); i++) {
            Adres currentAdres = adressen.get(i);
            double currentDistance = startPoint.distanceTo(currentAdres);

            if (currentDistance < shortestDistance) {
                nearestNeighbor = currentAdres;
                shortestDistance = currentDistance;
            }
        }

        return nearestNeighbor;
    }

    public static class Adres {
        private String postcode;
        private String huisnummer;
        private double latitude;
        private double longitude;

        public Adres(String postcode, String huisnummer, double latitude, double longitude) {
            this.postcode = postcode;
            this.huisnummer = huisnummer;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String getAdres() {
            return postcode + " " + huisnummer;
        }

        public String getPostcode() {
            return postcode;
        }

        public String getHuisnummer() {
            return huisnummer;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public double distanceTo(Adres other) {
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
}
