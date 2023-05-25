package com.recipeFinder.features.Recipe.views;

import com.recipeFinder.components.FormControl;
import com.recipeFinder.features.Recipe.controllers.CreateRecipeController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreateRecipeView extends JPanel {
    private CreateRecipeController controller;
    private FormControl recipeName;
    private FormControl recipeCalories;
    private FormControl recipeWeight;
    private FormControl recipeYield;

    public CreateRecipeView() {

        JPanel createRecipeFormPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 10));
        createRecipeFormPanel.setPreferredSize(new Dimension(700, 700));
        JLabel title = new JLabel("<html><h1>Create a new Recipe</h1></html>");
        title.setPreferredSize(new Dimension(600, 100));
        createRecipeFormPanel.add(title);

        recipeName = new FormControl("Recipe Name *");

        recipeCalories = new FormControl("Calorie Count");
        recipeCalories.lockDouble();

        recipeWeight = new FormControl("Recipe Weight");
        recipeWeight.lockDouble();

        recipeYield = new FormControl("Recipe Yield");
        recipeYield.lockInt();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 0, 10, 0);

        JPanel ingredientsControl = new JPanel(new GridBagLayout());
        JLabel ingredientsLabel = new JLabel("Ingredients *");
        ingredientsLabel.setHorizontalAlignment(SwingConstants.LEFT);
        JTextArea ingredientsTextArea = new JTextArea();
        ingredientsTextArea.setPreferredSize(new Dimension(300, 100));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.LINE_START;
        ingredientsControl.add(ingredientsLabel, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        ingredientsControl.add(ingredientsTextArea, constraints);

        JPanel instructionsControl = new JPanel(new GridBagLayout());
        JLabel instructionsLabel = new JLabel("Instructions *");
        instructionsLabel.setHorizontalAlignment(SwingConstants.LEFT);
        JTextArea instructionsTextArea = new JTextArea();
        instructionsTextArea.setPreferredSize(new Dimension(300, 100));
        constraints.gridx = 0;
        constraints.gridy = 0;
        instructionsControl.add(instructionsLabel, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        instructionsControl.add(instructionsTextArea, constraints);

        JButton submitButton = new JButton("Create recipe");
        submitButton.setPreferredSize(new Dimension(300, 40));
        submitButton.setBackground(Color.decode("#2ecc71"));
        submitButton.setForeground(Color.decode("#2c3e50"));
        submitButton.setBorder(new EmptyBorder(0, 0, 0, 0));

        JPanel mPanel = new JPanel();
        mPanel.setPreferredSize(new Dimension(300, 40));
        constraints.gridx = 0;
        constraints.gridy = 2;
        instructionsControl.add(mPanel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(20, 0, 0, 0);
        ingredientsControl.add(submitButton, constraints);

        submitButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if(recipeName.getText().isEmpty() || ingredientsTextArea.getText().isEmpty() || instructionsTextArea.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Do not leave recipe name, ingredients, and instructions empty.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                final String label = recipeName.getText();
                final Double calories = recipeCalories.getDouble();
                final Double weight = recipeWeight.getDouble();
                final int yield = recipeYield.getInt();
                final String ingredients = ingredientsTextArea.getText();
                final String instructions = instructionsTextArea.getText();


                controller.handleCreateRecipe(label, calories, weight, yield, instructions, ingredients);
                recipeName.setText("");
                recipeCalories.setText("");
                recipeWeight.setText("");
                recipeYield.setText("");
                ingredientsTextArea.setText("");
                instructionsTextArea.setText("");
            }
        });

        createRecipeFormPanel.add(recipeName);
        createRecipeFormPanel.add(recipeCalories);
        createRecipeFormPanel.add(recipeWeight);
        createRecipeFormPanel.add(recipeYield);
        createRecipeFormPanel.add(ingredientsControl);
        createRecipeFormPanel.add(instructionsControl);
        add(createRecipeFormPanel);
    }

    public void setController(CreateRecipeController controller) {
        this.controller = controller;
    }
}
