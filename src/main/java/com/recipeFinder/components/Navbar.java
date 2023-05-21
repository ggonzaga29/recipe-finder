package com.recipeFinder.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Navbar extends JPanel {
    public JButton menuButton;

    public Navbar() {
        menuButton = new JButton(new ImageIcon("src/main/resources/bars-solid.png"));
        menuButton.setBorder(new EmptyBorder(10, 10, 10, 10));
        menuButton.setBackground(Color.decode("#2a2c2e"));
        setPreferredSize(new Dimension(getWidth(), 50));
        setBackground(Color.decode("#2a2c2e"));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(new EmptyBorder(0, 0, 0, 10));
        add(menuButton);
        add(Box.createHorizontalStrut(20));
        add(new JLabel("<html><h2>Flavor Finder</h2></html>"));
        add(Box.createHorizontalGlue());
        add(new JLabel("Welcome, Gian Gonzaga"));
    }
}
