package com.flavorfinder.features.Recipe.views;

import com.flavorfinder.components.ImagePanel;
import com.flavorfinder.components.CommunityRecipeCard;
import com.flavorfinder.features.Recipe.controllers.CommunityRecipesController;
import com.flavorfinder.features.View;
import com.flavorfinder.models.RecipeModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class CommunityRecipesView extends View {
    private CommunityRecipesController controller;

    private ArrayList<RecipeModel> recipes;
    private JPanel recipeCardsPanel;

    public CommunityRecipesView() {
        initComponents();
    }

    public void initComponents() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(0, 0, 0, 0));

        recipeCardsPanel = new JPanel();
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
        recipeSearchPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        recipeSearchPanel.setDarknessLevel(200);
        recipeSearchPanel.setLayout(new GridBagLayout());

        JTextField recipeSearchInput = new JTextField();
        recipeSearchInput.setPreferredSize(new Dimension(300, 50));
        recipeSearchInput.setBorder(new EmptyBorder(10, 10, 10, 10));

        int width = (int) recipeCardsPanel.getPreferredSize().getWidth();
        recipeSearchPanel.setPreferredSize(new Dimension(width, 150));
        recipeSearchInput.setPreferredSize(new Dimension(300, 40));

        JButton searchButton = new JButton(new ImageIcon("src/main/resources/search-solid-white-16.png"));
        searchButton.setBackground(Color.decode("#2ecc71"));
        searchButton.setPreferredSize(new Dimension(40, 40));
        searchButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        searchButton.addActionListener(e -> {
            String searchQuery = recipeSearchInput.getText();
            recipeSearchInput.setText("");
            controller.handleSearch(searchQuery);
        });
        recipeSearchPanel.add(searchButton);

        GridBagConstraints constraints = new GridBagConstraints();

        // title
        JLabel title = new JLabel("Search Recipes");
        title.setFont(new Font(title.getName(), Font.PLAIN, 21));
        title.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 1.0;
        constraints.anchor = GridBagConstraints.CENTER;
        recipeSearchPanel.add(title, constraints);

        JPanel searchInputPanel = new JPanel();
        searchInputPanel.setOpaque(false);
        searchInputPanel.setLayout(new FlowLayout());
        searchInputPanel.add(recipeSearchInput);
        searchInputPanel.add(searchButton);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weighty = 1.0;
        constraints.anchor = GridBagConstraints.CENTER;
        recipeSearchPanel.add(searchInputPanel, constraints);

        JPanel paginationButtons = new JPanel();
        paginationButtons.setOpaque(false);
        paginationButtons.setLayout(new FlowLayout());
        paginationButtons.add(new JButton("Previous"));
        paginationButtons.add(new JButton("Next"));

        constraints.gridy = 2;
        recipeSearchPanel.add(paginationButtons, constraints);


        JScrollPane jScrollPane = new JScrollPane(recipeCardsPanel);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        jScrollPane.setPreferredSize(new Dimension(getWidth(), 600));
        jScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

        add(recipeSearchPanel, BorderLayout.NORTH);
        add(jScrollPane, BorderLayout.CENTER);

        recipes = RecipeModel.getAll();
        loadRecipes(recipes);
    }

    public void updateView(ArrayList<RecipeModel> recipes) {
        this.recipes = recipes;
        recipeCardsPanel.removeAll();
        loadRecipes(recipes);
        revalidate();
        repaint();
    }

    public void loadRecipes(ArrayList<RecipeModel> recipes) {
        SwingWorker<ArrayList<RecipeModel>, Void> recipeFetcher = new SwingWorker<>() {
            @Override
            protected ArrayList<RecipeModel> doInBackground() throws Exception {
                return null;
            }

            @Override
            protected void done() {
                try {
                    for (RecipeModel recipe : recipes) {
                        JPanel recipeCard = new CommunityRecipeCard(recipe);
                        SwingUtilities.invokeLater(() -> recipeCardsPanel.add(recipeCard));
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

    public void setController(CommunityRecipesController controller) {
        this.controller = controller;
    }
}
