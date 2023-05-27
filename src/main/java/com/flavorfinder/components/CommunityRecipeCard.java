package com.flavorfinder.components;

import com.flavorfinder.features.Recipe.views.SingleRecipeViewWindow;
import com.flavorfinder.models.RecipeModel;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CommunityRecipeCard extends JPanel {

    private RecipeModel recipe;
    private JButton favoriteButton;
    ImagePanel imagePanel;

    public CommunityRecipeCard(RecipeModel recipe) {
        this.recipe = recipe;
        initializeComponents();

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

    private void initializeComponents() {
        setPreferredSize(new Dimension(330, 220)); // Set preferred size for the card panel
        setLayout(new BorderLayout());
        setOpaque(true);

        JLabel titleLabel = new JLabel(String.format("<html><h3 style='width: 200px; font-family: 'Century Gothic''>%s</h3></html>", recipe.getLabel()));
        favoriteButton = new JButton();

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

        if (recipe.getIsFavorite() == 1) {
            favoriteButton.setIcon(new ImageIcon(getResourceImage("heart-solid-red.png")));
        } else {
            favoriteButton.setIcon(new ImageIcon(getResourceImage("heart-solid.png")));
        }

        add(cardBody, BorderLayout.CENTER);

        JPanel cardFooter = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        cardFooter.setBackground(Color.decode("#FFFFFF"));

        if (recipe.getLocalImageUrl() != null) {
            loadRecipeImage();
        }

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

                try {
                    toggleFavorite();
                    updateView();
                } catch (Exception evt) {
                    evt.printStackTrace();
                }
            }
        });
    }

    private void loadRecipeImage() {
        // Execute the code in a separate thread
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try (InputStream inputStream = getResourceAsStream("recipe_images/" + recipe.getLocalImageUrl())) {
                BufferedImage image = ImageIO.read(inputStream);
                imagePanel = new ImagePanel(image);
                imagePanel.setPreferredSize(new Dimension(getWidth(), 150));
                imagePanel.setDarknessLevel(100);


                imagePanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        imagePanel.setDarknessLevel(0, true, 200);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        imagePanel.setDarknessLevel(100, true, 200);
                    }
                });

                // Update the UI on the Event Dispatch Thread (EDT)
                SwingUtilities.invokeLater(() -> {
                    add(imagePanel, BorderLayout.NORTH);
                    revalidate();
                    repaint();
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // Shutdown the executor when finished
        executor.shutdown();
    }

    private void toggleFavorite() {
        int newFavoriteValue = (recipe.getIsFavorite() == 1) ? 0 : 1;
        recipe.setIsFavorite(newFavoriteValue);

        try (DBHandler db = new DBHandler()) {
            db.connect();

            String sql = "UPDATE recipes SET is_favorite = ? WHERE recipe_id = ?";
            try (PreparedStatement statement = db.getConnection().prepareStatement(sql)) {
                statement.setInt(1, newFavoriteValue);
                statement.setInt(2, recipe.getRecipeId());

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

    private void updateView() {
        removeAll();
        revalidate();
        repaint();
        initializeComponents();
    }

    private InputStream getResourceAsStream(String resourcePath) {
        return getClass().getClassLoader().getResourceAsStream(resourcePath);
    }

    private Image getResourceImage(String resourcePath) {
        try (InputStream inputStream = getResourceAsStream(resourcePath)) {
            return ImageIO.read(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
