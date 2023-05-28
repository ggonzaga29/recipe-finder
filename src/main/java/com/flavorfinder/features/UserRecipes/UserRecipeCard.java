package com.flavorfinder.features.UserRecipes;

import com.flavorfinder.components.ImagePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class UserRecipeCard extends ImagePanel {
    private UserRecipeModel recipe;

    public UserRecipeCard(UserRecipeModel recipe) {
        this.recipe = recipe;
        loadBackgroundImage();
        initComponents();
    }

    public void initComponents() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(300, 150));
        setOpaque(true);
        setBackground(Color.decode("#555658"));

        GridBagConstraints constraints = new GridBagConstraints();

        JLabel recipeName = new JLabel(recipe.getCustom_recipe_label());
        recipeName.setFont(new Font("Arial", Font.BOLD, 18));
        recipeName.setForeground(Color.WHITE);

        JPanel recipeNamePanel = new JPanel();
        recipeNamePanel.setOpaque(false);
        recipeNamePanel.add(recipeName);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        add(recipeNamePanel, constraints);

        JButton deleteButton = new JButton(new ImageIcon(getClass().getResource("/trash-alt-solid.png")));
        deleteButton.setBorder(BorderFactory.createEmptyBorder());
        deleteButton.setContentAreaFilled(false);
        deleteButton.setFocusPainted(false);
        deleteButton.setOpaque(false);
        deleteButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this recipe?", "Delete Recipe", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    recipe.delete();
                    setVisible(false);
                }
            }
        });

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.EAST;
        add(deleteButton, constraints);
    }

    private void loadBackgroundImage() {
        try (InputStream inputStream = getClass().getResourceAsStream("/recipe.jpg")) {
            if (inputStream == null) return;

            BufferedImage image = ImageIO.read(inputStream);
            setBackgroundImage(image);
            setDarknessLevel(150);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
