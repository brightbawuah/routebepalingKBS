package org.example;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.JSONObject;


public class LocatieApi {
    private String postcode;
    private String huisnummer;

    private String latitude;
    private String longitude;
    public LocatieApi(String postcode, String huisnummer) {
        this.postcode = postcode;
        this.huisnummer = huisnummer;
        String API= "31716eb2-d459-4bc8-8e96-d9ced1e7c4a1";

        try {
            String APIlink = "https://postcode.tech/api/v1/postcode/full?postcode=" + postcode + "&number=" + huisnummer;
            URL url = new URL(APIlink);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + API);
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                JSONObject data = new JSONObject(response.toString());
                String coordinaten = data.optString("geo");
                JSONObject data2 = new JSONObject(coordinaten.toString());
                String latitude = data2.optString("lat");
                String longitude = data2.optString("lon");
                System.out.println(response);
                System.out.println("latitude = " + latitude);
                System.out.println("longitude = " + longitude);
                this.latitude = latitude;
                this.longitude = longitude;

            } else {
                System.out.println("Adres bestaat niet");
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
