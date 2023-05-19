package com.recipeFinder.views;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.recipeFinder.components.FormControl;
import com.recipeFinder.controllers.CreateRecipeController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreateRecipeView extends JPanel {
    CreateRecipeController controller;
    FormControl recipeName;
    FormControl recipeCalories;
    FormControl recipeWeight;
    FormControl recipeYield;

    public CreateRecipeView() {

        JPanel createRecipeFormPanel = new JPanel();
        createRecipeFormPanel.setPreferredSize(new Dimension(700, 700));
        JLabel title = new JLabel("<html><h1>Create a new Recipe</h1></html>");
        title.setPreferredSize(new Dimension(600, 100));
        createRecipeFormPanel.add(title);

        recipeName = new FormControl("Recipe Name");

        recipeCalories = new FormControl("Calorie Count");
        recipeCalories.lockDouble();

        recipeWeight = new FormControl("Recipe Weight");
        recipeWeight.lockDouble();

        recipeYield = new FormControl("Recipe Yield");
        recipeYield.lockInt();

        JLabel textAreaLabel = new JLabel("<html><h2    >Instructions</h2></html>");
        textAreaLabel.setPreferredSize(new Dimension(600, 70));
        JTextArea instructions = new JTextArea();
        instructions.setPreferredSize(new Dimension(600, 125));

        JButton submitButton = new JButton("Create recipe");
        submitButton.setPreferredSize(new Dimension(300, 40));
        submitButton.setBackground(Color.decode("#2ecc71"));
        submitButton.setForeground(Color.decode("#2c3e50"));
        submitButton.setBorder(new EmptyBorder(0, 0, 0, 0));

        submitButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                final String label = recipeName.getText();
                final Double calories = recipeCalories.getDouble();
                final Double weight = recipeWeight.getDouble();
                final int yield = recipeYield.getInt();

                controller.handleCreateRecipe(label, calories, weight, yield, instructions.getText());
                recipeName.setText("");
                recipeCalories.setText("");
                recipeWeight.setText("");
                recipeYield.setText("");
                instructions.setText("");
            }
        });

        createRecipeFormPanel.add(recipeName);
        createRecipeFormPanel.add(recipeCalories);
        createRecipeFormPanel.add(recipeWeight);
        createRecipeFormPanel.add(recipeYield);
        createRecipeFormPanel.add(textAreaLabel);
        createRecipeFormPanel.add(instructions);
        createRecipeFormPanel.add(submitButton);
        add(createRecipeFormPanel);
    }

    public void setController(CreateRecipeController controller) {
        this.controller = controller;
    }
}
