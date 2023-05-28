package com.flavorfinder.features.UserRecipes;

import com.flavorfinder.components.ImagePanel;
import com.flavorfinder.features.Reviews.CreateReviewWindow;
import com.flavorfinder.features.Reviews.ViewReviewsWindow;
import com.flavorfinder.shared.utils.DBHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UserRecipeCard extends JPanel {
    private UserRecipeModel recipe;

    public UserRecipeCard(UserRecipeModel recipe) {
        this.recipe = recipe;
//        loadBackgroundImage();
        initComponents();
    }

    public void initComponents() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(330, 170));
        setOpaque(true);
        setBackground(Color.decode("#555658"));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SingleUserRecipeViewWindow window = new SingleUserRecipeViewWindow(recipe);
                SingleUserRecipeController controller = new SingleUserRecipeController(window);
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

        ImagePanel imagePanel = new ImagePanel();
        imagePanel.setPreferredSize(new Dimension(300, 75));
        imagePanel.setBackgroundImage(loadBackgroundImage());
        imagePanel.setDarknessLevel(150);
        add(imagePanel, BorderLayout.NORTH);

        JLabel recipeName = new JLabel(recipe.getCustom_recipe_label());
        recipeName.setFont(new Font("Arial", Font.BOLD, 18));
        recipeName.setForeground(Color.WHITE);

        JPanel recipeNamePanel = new JPanel();
        recipeNamePanel.setOpaque(false);
        recipeNamePanel.add(recipeName);
        add(recipeNamePanel, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.decode("#666769"));
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(buttonsPanel, BorderLayout.SOUTH);


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
        buttonsPanel.add(deleteButton);

        JButton reviewButton = new JButton();
        reviewButton.setIcon(new ImageIcon(getClass().getResource("/star-solid.png")));
        reviewButton.setFocusPainted(false);
        reviewButton.setBorderPainted(false);
        reviewButton.setContentAreaFilled(false);
        reviewButton.setOpaque(false);
        reviewButton.setAlignmentY(Component.TOP_ALIGNMENT);
        buttonsPanel.add(reviewButton);

        JButton viewReviewsButton = new JButton();
        viewReviewsButton.setIcon(new ImageIcon(getClass().getResource("/comment-solid.png")));
        viewReviewsButton.setFocusPainted(false);
        viewReviewsButton.setBorderPainted(false);
        viewReviewsButton.setContentAreaFilled(false);
        viewReviewsButton.setOpaque(false);
        viewReviewsButton.setAlignmentY(Component.TOP_ALIGNMENT);
        buttonsPanel.add(viewReviewsButton);

        JButton favoriteButton = new JButton();
        favoriteButton.setBorder(new EmptyBorder(0, 0, 0, 10));
        favoriteButton.setBackground(Color.decode("#555658"));
        favoriteButton.setForeground(Color.decode("#FFFFFF"));
        favoriteButton.setFocusPainted(false);
        favoriteButton.setBorderPainted(false);
        favoriteButton.setContentAreaFilled(false);
        favoriteButton.setOpaque(true);

        if (recipe.getIsFavorite() == 1) {
            favoriteButton.setIcon(new ImageIcon(getClass().getResource("/heart-solid-red.png")));
        } else {
            favoriteButton.setIcon(new ImageIcon(getClass().getResource("/heart-solid.png")));
        }

        buttonsPanel.add(favoriteButton);



        reviewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                reviewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                reviewButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                new CreateReviewWindow("user", recipe.getCustom_recipe_id());
            }
        });

        viewReviewsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                new ViewReviewsWindow("user", recipe.getCustom_recipe_id());
            }
        });

        favoriteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                favoriteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                favoriteButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                try {
                    toggleFavorite();
                    updateView();
                } catch (Exception evt) {
                    evt.printStackTrace();
                }
            }
        });
    }

    private void updateView() {
        removeAll();
        revalidate();
        repaint();
        initComponents();
    }


    private void toggleFavorite() {
        int newFavoriteValue = (recipe.getIsFavorite() == 1) ? 0 : 1;
        recipe.setIsFavorite(newFavoriteValue);

        try (DBHandler db = new DBHandler()) {
            db.connect();

            String sql = "UPDATE custom_recipes SET isFavorite = ? WHERE custom_recipe_id = ?";
            try (PreparedStatement statement = db.getConnection().prepareStatement(sql)) {
                statement.setInt(1, newFavoriteValue);
                statement.setInt(2, recipe.getCustom_recipe_id());

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    // Handle success
                } else {
                    // Handle failure
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle failure
        }
    }

    private BufferedImage loadBackgroundImage() {
        try (InputStream inputStream = getClass().getResourceAsStream("/recipe.jpg")) {
            if (inputStream == null) return null;

            BufferedImage image = ImageIO.read(inputStream);
            return image;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
