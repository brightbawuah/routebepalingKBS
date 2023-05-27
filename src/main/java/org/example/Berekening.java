package org.example;

import java.util.ArrayList;
import java.util.List;
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

    public double distanceTo(Berekening other) {
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
    public static Berekening findNearestNeighbour(List<Berekening> locaties, Berekening startPoint) {
        if (locaties.isEmpty()) {
            return null;
        }

        Berekening nearestNeighbour = locaties.get(0);
        double shortestDistance = startPoint.distanceTo(nearestNeighbour);

        for (int i = 1; i < locaties.size(); i++) {
            Berekening currentCity = locaties.get(i);
            double currentDistance = startPoint.distanceTo(currentCity);

            if (currentDistance < shortestDistance) {
                nearestNeighbour = currentCity;
                shortestDistance = currentDistance;
            }
        }

        return nearestNeighbour;
    }

}