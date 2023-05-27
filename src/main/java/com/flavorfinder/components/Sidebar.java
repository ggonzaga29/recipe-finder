package com.flavorfinder.components;

import com.flavorfinder.shared.ViewNames;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Represents the sidebar component of the Recipe Finder application.
 * The sidebar provides navigation options for the user.
 */
public class Sidebar extends JPanel {
    public JButton homeButton;
    public JButton createRecipeButton;
    public JButton userRecipesButton;
    public JButton favoritesButton;
    public JButton groceryListButton;
    public JButton createMealPlanButton;
    public JButton collectionButton;
    public JButton categoriesButton;
    public JButton logoutButton;
    private boolean isAnimating = false;
    private int activeIndex = 0;
    private ArrayList<JButton> buttons;

    /**
     * Constructs a Sidebar object.
     * Initializes and configures the sidebar component with buttons for navigation.
     */
    public Sidebar() {
        buttons = new ArrayList<>();
        // Sidebar configuration...
        setPreferredSize(new Dimension(200, getHeight()));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode("#2a2c2e"));

        // Create buttons for the sidebar
        homeButton = createSidebarButton("Home", "home-solid.png", ViewNames.COMMUNITY_RECIPES);
        createRecipeButton = createSidebarButton("Create User Recipe", "plus-solid.png", ViewNames.CREATE_RECIPES);
        userRecipesButton = createSidebarButton("View User Recipes", "utensils-solid.png", ViewNames.USER_RECIPES);
        logoutButton = createSidebarButton("Logout", "arrow-right-solid.png", "LOGOUT");
        favoritesButton = createSidebarButton("Favorites", "heart-solid.png", ViewNames.FAVORITES);
        groceryListButton = createSidebarButton("Grocery List", "shopping-cart-solid.png", ViewNames.GROCERY_LIST);
        createMealPlanButton = createSidebarButton("Create Meal Plan", "ppt.png", ViewNames.CREATE_MEAL_PLAN);
//        collectionButton = createSidebarButton("Recipe Collections", "src/main/resources/list-ol-solid.png");
//        categoriesButton = createSidebarButton("Categories", "src/main/resources/list-alt-solid.png");

        add(homeButton);
        add(createRecipeButton);
        add(userRecipesButton);
        add(favoritesButton);
        add(groceryListButton);
        add(createMealPlanButton);
//        add(collectionButton);
//        add(categoriesButton);
        add(Box.createVerticalGlue()); // Vertical glue to fill the remaining space
        add(logoutButton);
    }

    @Override
    public Component add(Component comp) {
        super.add(comp);

        // para ma change ang colors sa buttons HAHAHHAHAH
        if(comp instanceof JButton) {
            JButton button = (JButton) comp;
            buttons.add(button);
        }

        return comp;
    }

    public void addButtonListener(String viewName, ActionListener listener) {
        JButton button = getButton(viewName);
        button.addActionListener(listener);
    }

    public JButton getButton(String viewName) {
        for(JButton button : buttons) {
            if(button.getName().equals(viewName)) {
                return button;
            }
        }

        return null;
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

            Timer timer = new Timer(10, new ActionListener() {
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
                    sidebar.revalidate();
                }
            });

            timer.start(); // Start the animation
        }
    }

    public void setActiveButton(String viewName) {
        for(JButton button : buttons) {
            if(button.getClientProperty("viewName").equals(viewName)) {
                button.setBackground(Color.decode("#d4d5d5"));
                button.setForeground(Color.decode("#080909"));
            } else {
                button.setBackground(Color.decode("#2a2c2e"));
                button.setForeground(Color.decode("#aaabab"));
            }
        }
    }

    /**
     * Creates a sidebar button with the specified text and icon.
     *
     * @param text      The text to display on the button.
     * @param iconPath  The path to the icon image file.
     * @return          The created JButton with the specified text and icon.
     */
    protected JButton createSidebarButton(String text, String iconPath, String viewName) {
        Dimension buttonSize = new Dimension(Integer.MAX_VALUE, 50);
        int padding = 10;

        // always scale down to 24px
        ImageIcon icon = new ImageIcon(getClass().getResource("/" + iconPath));
        Image scaledImage = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        JButton btn = new JButton(text);
        btn.putClientProperty("viewName", viewName);
        btn.setBackground(Color.decode("#2a2c2e"));
        btn.setIcon(resizedIcon);
        btn.setMaximumSize(buttonSize);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setIconTextGap(16);
        btn.setBorder(new EmptyBorder(padding, padding, padding, padding));

        return btn;
    }
}

