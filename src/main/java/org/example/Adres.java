package org.example;

import java.util.ArrayList;
import java.util.List;
public class Adres {
    public List<Berekening> bezorgLocaties = new ArrayList<>();
    private String adres;
    private String postcode;
    private String huisnummer;

    public Adres(String postcode, String huisnummer) {
        this.adres = postcode + " " + huisnummer;
        this.postcode = postcode;
        this.huisnummer = huisnummer;
    }

    public String getAdres() {
        return adres;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getHuisnummer() {
        return huisnummer;
    }
}