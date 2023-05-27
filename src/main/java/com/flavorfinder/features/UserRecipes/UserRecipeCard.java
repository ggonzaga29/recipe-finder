package com.flavorfinder.features.UserRecipes;

import javax.swing.*;
import java.awt.*;

public class UserRecipeCard extends JPanel {
    public UserRecipeCard() {
        setPreferredSize(new Dimension(330, 120));
        setLayout(new BorderLayout());
        setOpaque(true);
        setBackground(Color.decode("#555658"));

        JLabel recipeName = new JLabel("Recipe Name");
        recipeName.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel recipeDescription = new JLabel("Recipe Description");

        JPanel recipeNamePanel = new JPanel();
        recipeNamePanel.setBackground(Color.decode("#555658"));

        recipeNamePanel.add(recipeName);

        add(recipeNamePanel, BorderLayout.NORTH);
        add(recipeDescription, BorderLayout.CENTER);
    }
}
