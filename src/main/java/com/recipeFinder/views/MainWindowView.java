package com.recipeFinder.views;

import com.recipeFinder.components.SideMenuPanel;
import com.recipeFinder.lib.Constants;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.TimerTask;

public class MainWindowView extends JFrame {
    public MainWindowView() {
        setTitle("Main window");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setIconImage(Constants.STANDARD_ICON);
//        setSize(screenSize.width, screenSize.height);
        setSize(1366, 768);
//        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
//        setUndecorated(true);

        JPanel navigationBar = new JPanel();
        JButton menuButton = new JButton(new ImageIcon("src/main/resources/bars-solid.png"));
        menuButton.setBorder(new EmptyBorder(10,10,10,10));
        menuButton.setBackground(Color.decode("#2a2c2e"));
//        navigationBar.add(name);
        navigationBar.setPreferredSize(new Dimension(getWidth(), 50));
        navigationBar.setBackground(Color.decode("#2a2c2e"));
        navigationBar.setLayout(new BoxLayout(navigationBar, BoxLayout.X_AXIS));
        navigationBar.setBorder(new EmptyBorder(0, 0, 0,10));
        navigationBar.add(menuButton);
        navigationBar.add(Box.createHorizontalGlue());
        navigationBar.add(new JLabel("Welcome, Gian Gonzaga"));

        // Create sidebar panel
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setPreferredSize(new Dimension(200, getHeight()));
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(Color.decode("#2a2c2e"));

        // Create buttons for the sidebar
        JButton button1 = new JButton("Home");
        JButton button2 = new JButton("Create Recipe");
        JButton button3 = new JButton("Logout");
        JButton button4 = new JButton("Favorites");
        JButton button5 = new JButton("Shopping List");

        ImageIcon icon = new ImageIcon("src/main/resources/home-solid.png");
        Image scaledImage = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        // Set the icon on the button
        button1.setIcon(resizedIcon);
        button2.setIcon(new ImageIcon("src/main/resources/plus-solid.png"));
        button3.setIcon(new ImageIcon("src/main/resources/arrow-right-solid.png"));
        button4.setIcon(new ImageIcon("src/main/resources/heart-solid.png"));
        button5.setIcon(new ImageIcon("src/main/resources/shopping-cart-solid.png"));

        Dimension buttonSize = new Dimension(Integer.MAX_VALUE, 50);
        button1.setMaximumSize(buttonSize);
        button2.setMaximumSize(buttonSize);
        button3.setMaximumSize(buttonSize);
        button4.setMaximumSize(buttonSize);
        button5.setMaximumSize(buttonSize);

        button1.setHorizontalAlignment(SwingConstants.LEFT);
        button2.setHorizontalAlignment(SwingConstants.LEFT);
        button3.setHorizontalAlignment(SwingConstants.LEFT);
        button4.setHorizontalAlignment(SwingConstants.LEFT);
        button5.setHorizontalAlignment(SwingConstants.LEFT);

        button1.setIconTextGap(16);
        button2.setIconTextGap(16);
        button3.setIconTextGap(16);
        button4.setIconTextGap(16);
        button5.setIconTextGap(16);

        // Add margins between buttons
        int margin = 10;
        Component rigidArea = Box.createRigidArea(new Dimension(0, margin));
        button1.setBorder(new EmptyBorder(margin, margin, margin, margin));
        button2.setBorder(new EmptyBorder(margin, margin, margin, margin));
        button3.setBorder(new EmptyBorder(margin, margin, margin, margin));
        button4.setBorder(new EmptyBorder(margin, margin, margin, margin));
        button5.setBorder(new EmptyBorder(margin, margin, margin, margin));
        // Add buttons to the sidebar panel
//        sidebarPanel.add(Box.createVerticalStrut(100));
        sidebarPanel.add(button1);
//        sidebarPanel.add(Box.createRigidArea(new Dimension(0, margin)));
        sidebarPanel.add(button2);
        sidebarPanel.add(button4);
        sidebarPanel.add(button5);
//        sidebarPanel.add(Box.createRigidArea(new Dimension(0, margin)));
        sidebarPanel.add(Box.createVerticalGlue()); // Vertical glue to fill the remaining space
        sidebarPanel.add(button3);

        // Create main content panel
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BorderLayout());

        menuButton.addActionListener(e -> {
            if (sidebarPanel.isVisible()) {
                sidebarPanel.setVisible(false);
            } else {
                sidebarPanel.setVisible(true);
            }
        });

        // Create components for the main content panel
        JLabel mainLabel = new JLabel("Main Content");
        mainLabel.setHorizontalAlignment(JLabel.CENTER);
        mainContentPanel.add(mainLabel, BorderLayout.CENTER);
        mainContentPanel.setBackground(Color.decode("#3c3f41"));

        // card layout
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(cardLayout);
        cardPanel.setBackground(Color.white);
        mainContentPanel.add(cardPanel);

        // recipes view
        // Create Panel 1
        JPanel recipeCardsPanel = new JPanel();
        recipeCardsPanel.setBackground(Color.white);
//        recipeCardsPanel.add();
//        recipeCardsPanel.setLayout(new WrapLayout(FlowLayout.LEADING, 10, 10));
        recipeCardsPanel.add(createCard("12"));
        recipeCardsPanel.add(createCard("12"));
        recipeCardsPanel.add(createCard("12"));
        recipeCardsPanel.add(createCard("12"));
        recipeCardsPanel.add(createCard("12"));
        recipeCardsPanel.add(createCard("12"));
        recipeCardsPanel.add(createCard("12"));
        recipeCardsPanel.add(createCard("12"));
        JScrollPane jScrollPane = new JScrollPane(recipeCardsPanel);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setSize(cardPanel.getWidth(), cardPanel.getHeight());
        // Create Panel 2
        JPanel panel2 = new JPanel();
//        panel2.setBackground(Color.GREEN);
        panel2.add(new JLabel("Panel 2"));

        // Add the panels to the cardPanel with unique names
        cardPanel.add(jScrollPane, "panel1");
        cardPanel.add(panel2, "panel2");

        button1.addActionListener(e -> {
            cardLayout.show(cardPanel, "panel1");
        });

        button2.addActionListener(e -> {
            cardLayout.show(cardPanel, "panel2");
        });


        // Create layout for the frame
        setLayout(new BorderLayout());
        add(navigationBar, BorderLayout.NORTH);
        add(sidebarPanel, BorderLayout.WEST);
        add(mainContentPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createCard(String cardTitle) {
        JPanel cardPanel = new JPanel();
        cardPanel.setPreferredSize(new Dimension(300, 200)); // Set preferred size for the card panel
        cardPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel(cardTitle);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cardPanel.add(titleLabel, BorderLayout.CENTER);

        return cardPanel;
    }

}
