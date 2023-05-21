package com.recipeFinder.views;

import com.recipeFinder.components.RecipeCard;
import com.recipeFinder.models.RecipeModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class CommunityRecipesView extends JPanel {
    public CommunityRecipesView() {
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

        add(recipeCardsPanel);

        SwingWorker<ArrayList<RecipeModel>, Void> recipeFetcher = new SwingWorker<ArrayList<RecipeModel>, Void>() {
            @Override
            protected ArrayList<RecipeModel> doInBackground() throws Exception {
                return RecipeModel.getAll(20);
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
