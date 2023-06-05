package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;

public class AdresRegel extends JPanel {


    public AdresRegel(String postcode, String huisnummer, int positie) {
        String url = "jdbc:mysql://localhost:3306/nerdygadgets";
        String username = "root";
        String password = "";

//        System.out.println(positie + 1 + "e locatie: " + postcode + " " + huisnummer);
        JLabel positieTekst = new JLabel(positie + 1 + "e locatie: ");
        JLabel volgordeAdressen = new JLabel(postcode + " " + huisnummer);
        JCheckBox checkBox = new JCheckBox();
        checkBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                try (Connection connection = DriverManager.getConnection(url, username, password)) {
                    if (checkBox.isSelected()) {
                        String sql = "Update bestelling set afgeleverd = 'Ja' WHERE Postcode ='" + postcode + "' AND Huisnummer = '" + huisnummer + "'";
                        Statement statement = connection.createStatement();
                        int modified = statement.executeUpdate(sql);
                    } else if (!checkBox.isSelected()){
                        String sql = "Update bestelling set afgeleverd = NULL WHERE Postcode ='" + postcode + "' AND Huisnummer = '" + huisnummer + "'";
                        Statement statement = connection.createStatement();
                        int modified = statement.executeUpdate(sql);
                    }
                } catch (SQLException ee) {
                    JOptionPane.showMessageDialog(AdresRegel.this, ee.getMessage(), "Connection error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(positieTekst);
        add(volgordeAdressen);
        add(checkBox);
        setPreferredSize(new Dimension(380, 25));
        setLayout(new GridLayout(1, 3));
        setAlignmentX(LEFT_ALIGNMENT);
        setVisible(true);
    }
}
