package com.flavorfinder.features.UserRecipes;

import javax.swing.*;
import java.awt.*;

public class UserRecipeCard extends JPanel {
    private UserRecipeModel recipe;

    public UserRecipeCard(UserRecipeModel recipe) {
        this.recipe = recipe;

        setPreferredSize(new Dimension(330, 120));
        setLayout(new BorderLayout());
        setOpaque(true);
        setBackground(Color.decode("#555658"));

        initComponents();
    }

    public void initComponents() {
        JLabel recipeName = new JLabel(recipe.getCustom_recipe_label());
        recipeName.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel recipeDescription = new JLabel("Recipe Description");

        JPanel recipeNamePanel = new JPanel();
        recipeNamePanel.setBackground(Color.decode("#555658"));

        recipeNamePanel.add(recipeName);

        add(recipeNamePanel, BorderLayout.NORTH);
        add(recipeDescription, BorderLayout.CENTER);
    }
}
