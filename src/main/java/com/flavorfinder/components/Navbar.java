package com.flavorfinder.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Navbar extends JPanel {
    public JButton menuButton;

    public Navbar() {
        setPreferredSize(new Dimension(getWidth(), 50));
        setBackground(Color.decode("#1d1f20"));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(new EmptyBorder(0, 0, 0, 10));

        menuButton = new JButton(new ImageIcon("src/main/resources/bars-solid.png"));
        menuButton.setBackground(Color.decode("#2a2c2e"));
        menuButton.setBorder(new EmptyBorder(50, 11, 50, 15));
        menuButton.setOpaque(false);
        add(menuButton);
        add(Box.createHorizontalStrut(20));
        JTextPane title = new JTextPane();
        add(new JLabel("<html><body style='font-family: 'Century Gothic'; font-size: 32px;'><h2>Flavor Finder</h2></body></html>"));
        add(new JLabel("Welcome, "));
    }
}
