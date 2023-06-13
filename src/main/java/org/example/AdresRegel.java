package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;

public class AdresRegel extends JPanel {


    public AdresRegel(String postcode, String huisnummer, String adres, int positie) {
        String url = "jdbc:mysql://localhost:3306/nerdygadgets";
        String username = "root";
        String password = "";

//        System.out.println(positie + 1 + "e locatie: " + postcode + " " + huisnummer);
        JLabel positieTekst = new JLabel(positie + 1 + "e locatie: ");
        JLabel volgordeAdressen = new JLabel(adres);
        JCheckBox checkBox = new JCheckBox();
        JPanel panel = new JPanel();
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
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        int intWidth = (int) width;
        panel.setPreferredSize(new Dimension(760,25));
        panel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        add(panel);
        add(positieTekst);
        add(volgordeAdressen);
        add(checkBox);

        setPreferredSize(new Dimension(intWidth, 25));
        setLayout(new GridLayout(1, 3));
        setVisible(true);
    }
}
