package com.flavorfinder.features.UserRecipes;

import com.flavorfinder.features.Auth.models.User;
import com.flavorfinder.features.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

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
        userRecipesCardPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        userRecipesCardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(userRecipesCardPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        userRecipesCardPanel.setPreferredSize(new Dimension(scrollPane.getWidth(), scrollPane.getHeight()));


        ArrayList<UserRecipeModel> userRecipes = UserRecipeModel.getAll();

        for(UserRecipeModel recipe : userRecipes) {
            UserRecipeCard card = new UserRecipeCard(recipe);
            userRecipesCardPanel.add(card);
        }

        add(titlePanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
}
