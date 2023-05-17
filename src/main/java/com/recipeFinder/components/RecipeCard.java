package com.recipeFinder.components;

import com.recipeFinder.models.RecipeModel;
import com.recipeFinder.views.SingleRecipeViewWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class RecipeCard extends JPanel {
    public RecipeCard(RecipeModel recipe) {
        setPreferredSize(new Dimension(330, 220)); // Set preferred size for the card panel
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel(String.format("<html><h3 style='width: 200px'>%s</h3></html>", recipe.getLabel()));
//        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);

        JPanel cardBody = new JPanel(new FlowLayout(FlowLayout.LEADING));
        cardBody.add(titleLabel);
        add(cardBody, BorderLayout.CENTER);

        if (recipe.getLocalImageUrl() != null) {
            new Thread(() -> {
                try {
                    InputStream inputStream = new FileInputStream("src/main/resources/recipe_images/" + recipe.getLocalImageUrl());
                    BufferedImage image = ImageIO.read(inputStream);
                    SwingUtilities.invokeLater(() -> {
                        ImagePanel imagePanel = new ImagePanel(image);
                        imagePanel.setPreferredSize(new Dimension(getWidth(), 50));
                        add(imagePanel, BorderLayout.NORTH);
                        revalidate();
                        repaint();
                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new SingleRecipeViewWindow(recipe);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor());
            }
        });
    }
}
