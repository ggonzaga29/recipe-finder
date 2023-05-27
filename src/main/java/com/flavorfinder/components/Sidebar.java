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

    // Sidebar configuration variables
    private boolean isAnimating = false;
    private boolean isSidebarToggled = true;
    private int minWidth = 50;
    private int maxWidth = 200;
    private int animationTime = 18;
    private int animationStep = 1; // AYAW NI HILABTI YAWAAAAA
    // end Sidebar configuration variables

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

        if (isSidebarToggled) {
            // If the sidebar is already visible, hide it gradually with animation
            if (isAnimating) {
                return; // Animation is already in progress, exit the method
            }

            isAnimating = true;

            Timer timer = new Timer(animationTime, new ActionListener() {
                int currentWidth = sidebar.getWidth();
                int startWidth = currentWidth;
                int targetWidth = minWidth;
                int distance = targetWidth - startWidth;
                int elapsedTime = 0;
                int duration = animationTime;

                /**
                 * Action performed when the timer ticks.
                 *
                 * @param e the action event
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    elapsedTime += animationStep;

                    if (elapsedTime >= duration) {
                        isSidebarToggled = false;
                        currentWidth = targetWidth; // Set the width to the target width
                        ((Timer) e.getSource()).stop(); // Stop the timer
                        isAnimating = false; // Animation is complete
                    } else {
                        // Calculate the eased value using the easing function
                        float t = (float) elapsedTime / duration;
                        float easedValue = quinticEasing(t);

                        currentWidth = startWidth + Math.round(easedValue * distance);
                    }

                    // Update the sidebar's size
                    sidebar.setPreferredSize(new Dimension(currentWidth, sidebar.getHeight()));
                    sidebar.revalidate(); // Revalidate the sidebar to reflect the new size
                    sidebar.repaint();
                }
            });

            timer.start(); // Start the animation
        } else {
            // If the sidebar is not visible, show it gradually with animation
            if (isAnimating) {
                return; // Animation is already in progress, exit the method
            }

            isAnimating = true;

            Timer timer = new Timer(animationTime, new ActionListener() {
                int currentWidth = getWidth();
                int startWidth = currentWidth;
                int targetWidth = maxWidth;
                int distance = targetWidth - startWidth;
                int elapsedTime = 0;
                int duration = animationTime;

                /**
                 * Action performed when the timer ticks.
                 *
                 * @param e the action event
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    elapsedTime += animationStep;

                    if (elapsedTime >= duration) {
                        isSidebarToggled = true;
                        currentWidth = targetWidth;
                        ((Timer) e.getSource()).stop();
                        isAnimating = false;
                    } else {

                        float t = (float) elapsedTime / duration;
                        float easedValue = quinticEasing(t); // Applies easing function to the time, creating a smooth animation

                        currentWidth = startWidth + Math.round(easedValue * distance); // The width is the start width plus the eased value multiplied by the distance
                    }

                    // Update the sidebar's size
                    sidebar.setPreferredSize(new Dimension(currentWidth, sidebar.getHeight()));
                    sidebar.revalidate();
                    sidebar.repaint();
                }
            });

            timer.start(); // Start the animation
        }
    }

    /**
     * Easing function for quinticEasing animation.
     *
     * @param t the current time (between 0 and 1)
     * @return the eased value
     * This quintic easing function allows for smooth and natural transitions between states or animations, with the option to incorporate a bounce effect when reaching the endpoint (\(t = 1\)).
     * The function is defined as:
     * the normal formula for quintic easing is just (t * t * t * t * t) but I added a bounce effect to it
     */
    private float quinticEasing(float t) { // ayaw hilabti sir lisud kaayo ni e explain hehe
        // if t is less than 0.5 then ease in
        if (t < 0.5f) {
            return 16 * t * t * t * t * t; // Ease in
            // 16 * t * t * t * t * t is equivalent to t * t * t * t * t * 2 * 2 * 2
            // The 2 * 2 * 2 is to make the value increase faster
            // The 2 * 2 * 2 is equivalent to 8
        }
        // else if t is greater than 0.5 then ease out with bounce
        else {
            float reversedT = 1 - t;
            // The reversedT is used to reverse the value of t
            // For example, if t is 0.6, reversedT is 0.4
            // This is used to make the animation ease out instead of ease in
            // The reversedT is used in the formula instead of t
            // This is because the reversedT is the opposite of t
            return 1 - 16 * reversedT * reversedT * reversedT * reversedT * reversedT; // Ease out with bounce

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
        btn.setBorder(new EmptyBorder(padding, 12, padding, padding));

        return btn;
    }
}

