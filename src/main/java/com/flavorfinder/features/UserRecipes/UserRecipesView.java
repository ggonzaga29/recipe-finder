package com.flavorfinder.features.UserRecipes;

import com.flavorfinder.features.View;

import javax.swing.*;
import java.awt.*;

public class UserRecipesView extends View {

    public UserRecipesView() {
        initComponents();
    }

    public void initComponents() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("User Recipes");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        JPanel titlePanel = new JPanel();
        titlePanel.add(title);

        JPanel userRecipesCardPanel = new JPanel();
        userRecipesCardPanel.setLayout(new GridLayout(0, 3, 10, 10));
        userRecipesCardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 0; i < 10; i++) {
            UserRecipeCard userRecipeCard = new UserRecipeCard();
            userRecipesCardPanel.add(userRecipeCard);
        }

        add(titlePanel, BorderLayout.NORTH);
        add(userRecipesCardPanel, BorderLayout.CENTER);
    }
}
