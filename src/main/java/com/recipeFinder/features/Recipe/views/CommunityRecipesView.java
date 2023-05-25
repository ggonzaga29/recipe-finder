package com.recipeFinder.features.Recipe.views;

import com.recipeFinder.components.ImagePanel;
import com.recipeFinder.components.RecipeCard;
import com.recipeFinder.features.View;
import com.recipeFinder.models.RecipeModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class CommunityRecipesView extends View {
    public CommunityRecipesView() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(0, 0, 0, 0));

        JPanel recipeCardsPanel = new JPanel();
        recipeCardsPanel.setLayout(new GridLayout(0, 3, 10, 10));
        recipeCardsPanel.setBorder(new EmptyBorder(20, 20, 20, 20));


        BufferedImage image = null;
        try {
            InputStream inputStream = new FileInputStream("src/main/resources/restaurant.jpg");
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImagePanel recipeSearchPanel = new ImagePanel(image);
        recipeSearchPanel.setDarknessLevel(200);
        JTextField recipeSearchInput = new JTextField();

        int width = (int) recipeCardsPanel.getPreferredSize().getWidth();
        recipeSearchPanel.setPreferredSize(new Dimension(width, 100));
        recipeSearchPanel.add(recipeSearchInput, BorderLayout.CENTER);

        JButton clearButton = new JButton("SDF");
        clearButton.addActionListener(e -> {
            LayoutManager oldLayout = recipeCardsPanel.getLayout();
            recipeCardsPanel.removeAll();

            recipeCardsPanel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0; // Expand horizontally
            gbc.weighty = 1.0; // Expand vertically
            gbc.fill = GridBagConstraints.CENTER;

            URL url = this.getClass().getResource("/loading.gif");
            if(url != null) {
                ImageIcon imageIcon = new ImageIcon(url);
                JLabel label = new JLabel(imageIcon);
                recipeCardsPanel.add(label, gbc);
            }

            recipeCardsPanel.revalidate();
            recipeCardsPanel.repaint();
        });
        recipeSearchPanel.add(clearButton);

        JScrollPane jScrollPane = new JScrollPane(recipeCardsPanel);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        jScrollPane.setPreferredSize(new Dimension(getWidth(), 600));
        jScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

        add(recipeSearchPanel, BorderLayout.NORTH);
        add(jScrollPane, BorderLayout.CENTER);


        SwingWorker<ArrayList<RecipeModel>, Void> recipeFetcher = new SwingWorker<>() {
            @Override
            protected ArrayList<RecipeModel> doInBackground() {
                return RecipeModel.getAll(30);
            }

            @Override
            protected void done() {
                try {
                    ArrayList<RecipeModel> recipes = get();
                    for (RecipeModel recipe : recipes) {
                        JPanel recipeCard = new RecipeCard(recipe);
                        recipeCardsPanel.add(recipeCard);
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
