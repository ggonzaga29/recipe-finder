package com.recipeFinder.views;

import com.recipeFinder.components.ImagePanel;
import com.recipeFinder.components.RecipeCard;
import com.recipeFinder.components.SideMenuPanel;
import com.recipeFinder.lib.Constants;
import com.recipeFinder.lib.Recipe;
import com.recipeFinder.lib.RecipeAPIHelper;
import com.recipeFinder.lib.WrapLayout;
import com.recipeFinder.models.RecipeModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.TimerTask;

public class MainWindowView extends JFrame {
    public MainWindowView() {
        setTitle("Main window");
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setIconImage(Constants.STANDARD_ICON);
//        setSize(screenSize.width, screenSize.height);
        setSize(1235, 768);
//        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
//        setUndecorated(true);
        setMinimumSize(new Dimension(850, 600));

        JPanel navigationBar = new JPanel();
        JButton menuButton = new JButton(new ImageIcon("src/main/resources/bars-solid.png"));
        menuButton.setBorder(new EmptyBorder(10, 10, 10, 10));
        menuButton.setBackground(Color.decode("#2a2c2e"));
//        navigationBar.add(name);
        navigationBar.setPreferredSize(new Dimension(getWidth(), 50));
        navigationBar.setBackground(Color.decode("#2a2c2e"));
        navigationBar.setLayout(new BoxLayout(navigationBar, BoxLayout.X_AXIS));
        navigationBar.setBorder(new EmptyBorder(0, 0, 0, 10));
        navigationBar.add(menuButton);
        navigationBar.add(Box.createHorizontalStrut(20));
        navigationBar.add(new JLabel("<html><h2>Flavor Finder</h2></html>"));
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

        // card layout
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(cardLayout);
//        cardPanel.setBackground(Color.blue);
        mainContentPanel.add(cardPanel);

        // recipes view
        // Create Panel 1
        JPanel recipePanel = new JPanel(new BorderLayout());
        recipePanel.setBorder(new EmptyBorder(0, 0, 0, 0));

        JPanel recipeCardsPanel = new JPanel();
        recipeCardsPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
        recipeCardsPanel.setPreferredSize(new Dimension(recipePanel.getWidth(), Integer.MAX_VALUE));
        recipeCardsPanel.setBackground(Color.decode("#636567"));

        JPanel recipeSearchPanel = new JPanel();
        JTextField recipeSearchInput = new JTextField();

        int width = (int) recipeCardsPanel.getPreferredSize().getWidth();
        System.out.println(width);
        recipeSearchPanel.setPreferredSize(new Dimension(width, 100));
        recipeSearchPanel.add(recipeSearchInput, BorderLayout.CENTER);

        JScrollPane jScrollPane = new JScrollPane(recipeCardsPanel);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        jScrollPane.setPreferredSize(new Dimension(recipeCardsPanel.getWidth(), Integer.MAX_VALUE));
        jScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("Panel 2"));

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

        SwingWorker<ArrayList<RecipeModel>, Void> recipeFetcher = new SwingWorker<ArrayList<RecipeModel>, Void>() {
            @Override
            protected ArrayList<RecipeModel> doInBackground() throws Exception {
                return RecipeModel.getRecipes(20);
            }

            @Override
            protected void done() {
                try {
                    ArrayList<RecipeModel> recipes = get();
                    for (RecipeModel recipe : recipes) {
                        recipeCardsPanel.add(new RecipeCard(recipe));
                    }

                    recipeCardsPanel.revalidate();
                    recipeCardsPanel.repaint();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        recipeFetcher.execute();
    }
}
