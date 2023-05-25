package com.recipeFinder.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents the sidebar component of the Recipe Finder application.
 * The sidebar provides navigation options for the user.
 */
public class Sidebar extends JPanel {
    public JButton homeButton;
    public JButton createRecipeButton;
    public JButton favoritesButton;
    public JButton groceryListButton;
    public JButton createGroceryListButton;
    public JButton createMealPlanButton;
    public JButton logoutButton;
    private boolean isAnimating = false;

    /**
     * Constructs a Sidebar object.
     * Initializes and configures the sidebar component with buttons for navigation.
     */
    public Sidebar() {
        // Sidebar configuration...
        setPreferredSize(new Dimension(200, getHeight()));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode("#2a2c2e"));

        // Create buttons for the sidebar
        homeButton = createSidebarButton("Home", "src/main/resources/home-solid.png");
        createRecipeButton = createSidebarButton("Create Recipe", "src/main/resources/plus-solid.png");
        logoutButton = createSidebarButton("Logout", "src/main/resources/arrow-right-solid.png");
        favoritesButton = createSidebarButton("Favorites", "src/main/resources/heart-solid.png");
        groceryListButton = createSidebarButton("Grocery List", "src/main/resources/shopping-cart-solid.png");
        createGroceryListButton = createSidebarButton("Create Grocery List", "src/main/resources/cart-plus-solid.png");
        createMealPlanButton = createSidebarButton("Create Meal Plan", "src/main/resources/list-ol-solid.png");

        add(homeButton);
        add(createRecipeButton);
        add(favoritesButton);
        add(groceryListButton);
        add(createGroceryListButton);
        add(createMealPlanButton);
        add(Box.createVerticalGlue()); // Vertical glue to fill the remaining space
        add(logoutButton);
    }

    /**
     * Toggles the visibility of the sidebar panel with a smooth animation.
     * If the sidebar is visible, it gradually hides it. If it's hidden, it gradually shows it.
     */
    public void toggle() {
        JPanel sidebar = this;

        if (sidebar.isVisible()) {
            // If the sidebar is already visible, hide it gradually with animation
            if (isAnimating) {
                return; // Animation is already in progress, exit the method
            }

            isAnimating = true;

            Timer timer = new Timer(1, new ActionListener() {
                int currentWidth = sidebar.getWidth();

                /**
                 * Action performed when the timer ticks.
                 *
                 * @param e the action event
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentWidth -= 10; // Decrease the width by 10 pixels

                    if (currentWidth <= 0) {
                        sidebar.setVisible(false); // Hide the sidebar
                        ((Timer) e.getSource()).stop(); // Stop the timer
                        isAnimating = false; // Animation is complete
                    }

                    // Update the sidebar's size
                    sidebar.setPreferredSize(new Dimension(currentWidth, sidebar.getHeight()));
                    sidebar.revalidate(); // Revalidate the sidebar to reflect the new size
                }
            });

            timer.start(); // Start the animation
        } else {
            // If the sidebar is not visible, show it gradually with animation
            if (isAnimating) {
                return; // Animation is already in progress, exit the method
            }

            isAnimating = true;

            sidebar.setVisible(true);
            int targetWidth = 200;
            int initialWidth = 0;

            Timer timer = new Timer(1, new ActionListener() {
                int currentWidth = initialWidth;

                /**
                 * Action performed when the timer ticks.
                 *
                 * @param e the action event
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentWidth += 10; // Increase the width by 10 pixels

                    if (currentWidth >= targetWidth) {
                        currentWidth = targetWidth; // Set the width to the target width
                        ((Timer) e.getSource()).stop(); // Stop the timer
                        isAnimating = false; // Animation is complete
                    }

                    // Update the sidebar's size
                    sidebar.setPreferredSize(new Dimension(currentWidth, sidebar.getHeight()));
                    sidebar.revalidate(); // Revalidate the sidebar to reflect the new size
                }
            });

            timer.start(); // Start the animation
        }
    }



    /**
     * Creates a sidebar button with the specified text and icon.
     *
     * @param text      The text to display on the button.
     * @param iconPath  The path to the icon image file.
     * @return          The created JButton with the specified text and icon.
     */
    protected JButton createSidebarButton(String text, String iconPath) {
        Dimension buttonSize = new Dimension(Integer.MAX_VALUE, 50);
        int margin = 10;

        // always scale down to 24px
        ImageIcon icon = new ImageIcon(iconPath);
        Image scaledImage = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        JButton btn = new JButton(text);
        btn.setIcon(resizedIcon);
        btn.setMaximumSize(buttonSize);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setIconTextGap(16);
        btn.setBorder(new EmptyBorder(margin, margin, margin, margin));

        return btn;
    }
}

