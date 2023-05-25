package com.recipeFinder.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Navbar extends JPanel {
    public JButton menuButton;
    public JButton closeButton;
    public JButton maximizeButton;
    public JButton minimizeButton;
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
//        add(Box.createHorizontalStrut(20));
        add(new JLabel("Welcome, "));
//        add(Box.createHorizontalGlue());

//        closeButton = createButton("close.png");
//        maximizeButton = createButton("maximize2.png");
//        minimizeButton = createButton("minus.png");
//        add(minimizeButton);
//        add(Box.createHorizontalStrut(5));
//        add(maximizeButton);
//        add(Box.createHorizontalStrut(5));
//        add(closeButton);
    }

    protected JButton createButton(String imgPath) {
        JButton button = new JButton("");
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        ImageIcon icon = new ImageIcon("src/main/resources/" + imgPath);
        Image resizedImage;
        if(imgPath == "maximize2.png") {
            resizedImage = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        } else {
            resizedImage = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        }
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        button.setIcon(resizedIcon);
        return button;
    }
}
