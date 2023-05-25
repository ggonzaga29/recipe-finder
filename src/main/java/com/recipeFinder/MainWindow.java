package com.recipeFinder;

import com.recipeFinder.components.Navbar;
import com.recipeFinder.components.Sidebar;
import com.recipeFinder.features.GroceryList.controllers.CreateGroceryListController;
import com.recipeFinder.features.MealPlan.controllers.CreateMealPlanController;
import com.recipeFinder.features.Recipe.controllers.CreateRecipeController;
import com.recipeFinder.features.Auth.controllers.LoginController;
import com.recipeFinder.features.Recipe.views.CommunityRecipesView;
import com.recipeFinder.shared.utils.Constants;
import com.recipeFinder.features.Auth.views.LoginView;
import com.recipeFinder.features.GroceryList.views.CreateGroceryListView;
import com.recipeFinder.features.MealPlan.views.CreateMealPlanView;
import com.recipeFinder.features.Recipe.views.CreateRecipeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The main window of the Flavor Finder application.
 * This window serves as the main interface for the user,
 */
public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("Flavor Finder " + Constants.VERSION_NUMBER());
        setIconImage(Constants.STANDARD_ICON);
        setSize(1366, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(1366, 768));

        Navbar navbar = new Navbar();
        Sidebar sidebar = new Sidebar();

        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BorderLayout());

        navbar.menuButton.addActionListener(e -> {
            sidebar.toggle();
        });

        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(cardLayout);
        mainContentPanel.add(cardPanel);

        // Initialize MVC Components
        CommunityRecipesView communityRecipesView = new CommunityRecipesView();

        CreateRecipeView createRecipeView = new CreateRecipeView();
        CreateRecipeController createRecipeController = new CreateRecipeController(createRecipeView);


        CreateGroceryListView createGroceryListView = new CreateGroceryListView();
        CreateGroceryListController createGroceryListController = new CreateGroceryListController(createGroceryListView);

        CreateMealPlanView createMealPlanView = new CreateMealPlanView();
        CreateMealPlanController createMealPlanController = new CreateMealPlanController(createMealPlanView);


        cardPanel.add(communityRecipesView, "recipe-cards");
        cardPanel.add(createRecipeView, "create-recipes");
        cardPanel.add(createGroceryListView, "create-grocery-list");
        cardPanel.add(createMealPlanView, "create-meal-plan");

        sidebar.homeButton.addActionListener(e -> cardLayout.show(cardPanel, "recipe-cards"));
        sidebar.createRecipeButton.addActionListener(e -> cardLayout.show(cardPanel, "create-recipes"));
        sidebar.createGroceryListButton.addActionListener(e -> cardLayout.show(cardPanel, "create-grocery-list"));
        sidebar.createMealPlanButton.addActionListener(e -> cardLayout.show(cardPanel, "create-meal-plan"));
        sidebar.logoutButton.addActionListener(e -> logout());

        setLayout(new BorderLayout());
        add(navbar, BorderLayout.NORTH);
        add(sidebar, BorderLayout.WEST);
        add(mainContentPanel, BorderLayout.CENTER);
        setVisible(true);

    }

    public void performCleanup() {
        SwingUtilities.invokeLater(this::close);
    }

    public void logout() {
        SwingUtilities.invokeLater(() -> {
            performCleanup();
            LoginView loginView = new LoginView();
            LoginController loginController = new LoginController(loginView);
            loginView.open();

            loginController.on("submit_success", (Void) -> {
                loginView.close();
                MainWindow mainWindowView = new MainWindow();
            });
        });
    }

    public void close() {
        setVisible(false);
    }
}
