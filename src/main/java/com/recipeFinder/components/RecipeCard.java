package com.recipeFinder.components;

import com.recipeFinder.models.RecipeModel;
import com.recipeFinder.features.Recipe.views.SingleRecipeViewWindow;
import com.recipeFinder.shared.enums.SQLResult;
import com.recipeFinder.shared.exceptions.RecordAlreadyExistsException;
import com.recipeFinder.shared.utils.DBHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RecipeCard extends JPanel {
    public RecipeCard(RecipeModel recipe) {
        setPreferredSize(new Dimension(330, 220)); // Set preferred size for the card panel
        setLayout(new BorderLayout());
        setOpaque(true);

        JLabel titleLabel = new JLabel(String.format("<html><h3 style='width: 200px'>%s</h3></html>", recipe.getLabel()));
        JButton favoriteButton = new JButton(new ImageIcon("src/main/resources/heart-solid.png"));

        JPanel cardBody = new JPanel();
        cardBody.setLayout(new BoxLayout(cardBody, BoxLayout.X_AXIS));
        cardBody.add(titleLabel);
        cardBody.add(Box.createHorizontalGlue());
        cardBody.add(favoriteButton);
        cardBody.setBackground(Color.decode("#555658"));

        titleLabel.setAlignmentY(Component.TOP_ALIGNMENT);
        titleLabel.setForeground(Color.decode("#FFFFFF"));
        titleLabel.setBorder(new EmptyBorder(10, 10, 10, 10));

        favoriteButton.setBorder(new EmptyBorder(15, 10, 10, 10));
        favoriteButton.setBackground(Color.decode("#555658"));
        favoriteButton.setForeground(Color.decode("#FFFFFF"));
        favoriteButton.setFocusPainted(false);
        favoriteButton.setBorderPainted(false);
        favoriteButton.setContentAreaFilled(false);
        favoriteButton.setOpaque(true);
        favoriteButton.setAlignmentY(Component.TOP_ALIGNMENT);
        favoriteButton.putClientProperty("isHearted", false);
        favoriteButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                favoriteButton.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                favoriteButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if(favoriteButton.getClientProperty("isHearted").equals(false)) {
                    favoriteButton.putClientProperty("isHearted", true);
                    favoriteButton.setIcon(new ImageIcon("src/main/resources/heart-solid-red.png"));
                    handleFavorite();
                } else {
                    favoriteButton.putClientProperty("isHearted", false);
                    favoriteButton.setIcon(new ImageIcon("src/main/resources/heart-solid.png"));
                }
            }
        });

        add(cardBody, BorderLayout.CENTER);

        JPanel cardFooter = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        cardFooter.setBackground(Color.decode("#FFFFFF"));
        add(cardFooter, BorderLayout.SOUTH);

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
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor());
            }
        });
    }

    protected SQLResult handleFavorite() throws RecordAlreadyExistsException {
        try (DBHandler db = new DBHandler()) {
            db.connect();

            String sql = "SELECT COUNT(*) FROM grocery_lists WHERE grocery_list_name = '" + name + "'";
            try (ResultSet resultSet = db.executeQuery(sql)) {
                resultSet.next();
                int count = resultSet.getInt(1);

                if (count > 0) {
                    throw new RecordAlreadyExistsException("Record already exists. Cannot insert.");
                }
            }

            sql = "INSERT INTO grocery_lists (grocery_list_name, grocery_list_date) VALUES ('" + getName() + "', '" + getDate() + "')";
            try (Statement statement = db.getConnection().createStatement()) {
                int rowsAffected = statement.executeUpdate(sql);

                if (rowsAffected > 0) {
                    return SQLResult.SUCCESS;
                } else {
                    return SQLResult.FAILURE;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return SQLResult.FAILURE;
        }

    }
}
