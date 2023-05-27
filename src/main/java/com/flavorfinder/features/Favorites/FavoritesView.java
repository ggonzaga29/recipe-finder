package com.flavorfinder.features.Favorites;

import com.flavorfinder.components.CommunityRecipeCard;
import com.flavorfinder.features.View;
import com.flavorfinder.models.RecipeModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class FavoritesView extends View {
    public FavoritesView() {
        initComponents();
    }

    public void initComponents() {
        setLayout(new BorderLayout());

        JPanel favoritesPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel titleLabel = new JLabel("Favorites");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(10, 10, 10, 10);
        favoritesPanel.add(titleLabel, constraints);

        JPanel recipeCardsPanel = new JPanel();
        recipeCardsPanel.setLayout(new GridLayout(0, 3, 10, 10));
        recipeCardsPanel.setBorder(new EmptyBorder(20, 10, 20, 0));

        JScrollPane scrollPane = new JScrollPane(recipeCardsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

        add(favoritesPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        SwingWorker<ArrayList<RecipeModel>, Void> recipeFetcher = new SwingWorker<>() {
            @Override
            protected ArrayList<RecipeModel> doInBackground() {
                return RecipeModel.getAllFavorites();
            }

            @Override
            protected void done() {
                try {
                    ArrayList<RecipeModel> recipes = get();
                    for (RecipeModel recipe : recipes) {
                        JPanel recipeCard = new CommunityRecipeCard(recipe);
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
