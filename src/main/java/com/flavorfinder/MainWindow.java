package com.flavorfinder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import com.flavorfinder.components.Navbar;
import com.flavorfinder.components.Sidebar;
import com.flavorfinder.features.Auth.controllers.LoginController;
import com.flavorfinder.features.Auth.views.LoginView;
import com.flavorfinder.features.Favorites.FavoritesView;
import com.flavorfinder.features.GroceryList.controllers.GroceryListController;
import com.flavorfinder.features.GroceryList.views.GroceryListView;
import com.flavorfinder.features.MealPlan.controllers.CreateMealPlanController;
import com.flavorfinder.features.MealPlan.views.CreateMealPlanView;
import com.flavorfinder.features.Recipe.controllers.CommunityRecipesController;
import com.flavorfinder.features.Recipe.views.CommunityRecipesView;
import com.flavorfinder.features.UserRecipes.CreateUserRecipeController;
import com.flavorfinder.features.UserRecipes.CreateUserRecipeView;
import com.flavorfinder.features.UserRecipes.UserRecipesController;
import com.flavorfinder.features.UserRecipes.UserRecipesView;
import com.flavorfinder.shared.CurrentUser;
import com.flavorfinder.shared.ViewNames;
import com.flavorfinder.shared.utils.Constants;
import com.flavorfinder.shared.utils.ViewManager;

/**
 * The main window of the Flavor Finder application.
 * This window serves as the main interface for the user,
 */
public class MainWindow extends JFrame {

    private ViewManager viewManager;
    private Navbar navbar;
    private Sidebar sidebar;

    public MainWindow() {
        setTitle("Flavor Finder " + Constants.VERSION_NUMBER());
        setIconImage(Constants.STANDARD_ICON);
        setSize(1366, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(1366, 768));
        setLayout(new BorderLayout());
        initComponents();
        setVisible(true);
    }

    public void initComponents() {
        navbar = new Navbar();
        sidebar = new Sidebar();

        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BorderLayout());

        navbar.menuButton.addActionListener(e -> {
            sidebar.toggle();
        });

        JPanel cardPanel = new JPanel(new CardLayout());
        mainContentPanel.add(cardPanel);

        viewManager = new ViewManager(cardPanel);

        // Initialize MVC Components
        CommunityRecipesView communityRecipesView = new CommunityRecipesView();
        CommunityRecipesController communityRecipesController = new CommunityRecipesController(communityRecipesView);

        CreateUserRecipeView createUserRecipeView = new CreateUserRecipeView();
        CreateUserRecipeController createUserRecipeController = new CreateUserRecipeController(createUserRecipeView);

        GroceryListView groceryListView = new GroceryListView();
        GroceryListController groceryListController = new GroceryListController(groceryListView);

        CreateMealPlanView createMealPlanView = new CreateMealPlanView();
        CreateMealPlanController createMealPlanController = new CreateMealPlanController(createMealPlanView);

        FavoritesView favoritesView = new FavoritesView();

        UserRecipesView userRecipesView = new UserRecipesView();
        UserRecipesController userRecipesController = new UserRecipesController(userRecipesView);

        // Add views to the cardPanel
        cardPanel.add(communityRecipesView, ViewNames.COMMUNITY_RECIPES);
        cardPanel.add(createUserRecipeView, ViewNames.CREATE_RECIPES);
        cardPanel.add(groceryListView, ViewNames.GROCERY_LIST);
        cardPanel.add(createMealPlanView, ViewNames.CREATE_MEAL_PLAN);
        cardPanel.add(favoritesView, ViewNames.FAVORITES);
        cardPanel.add(userRecipesView, ViewNames.USER_RECIPES);

        // Add views to the viewManager
        viewManager.addView(ViewNames.COMMUNITY_RECIPES, communityRecipesView);
        viewManager.addView(ViewNames.CREATE_RECIPES, createUserRecipeView);
        viewManager.addView(ViewNames.GROCERY_LIST, groceryListView);
        viewManager.addView(ViewNames.CREATE_MEAL_PLAN, createMealPlanView);
        viewManager.addView(ViewNames.FAVORITES, favoritesView);
        viewManager.addView(ViewNames.USER_RECIPES, userRecipesView);

        // Map each button to its corresponding view name
        // To eliminate Redundant code
        Map<JButton, String> buttonToViewMap = new HashMap<>();
        buttonToViewMap.put(sidebar.homeButton, ViewNames.COMMUNITY_RECIPES);
        buttonToViewMap.put(sidebar.createRecipeButton, ViewNames.CREATE_RECIPES);
        buttonToViewMap.put(sidebar.favoritesButton, ViewNames.FAVORITES);
        buttonToViewMap.put(sidebar.groceryListButton, ViewNames.GROCERY_LIST);
        buttonToViewMap.put(sidebar.createMealPlanButton, ViewNames.CREATE_MEAL_PLAN);
        buttonToViewMap.put(sidebar.userRecipesButton, ViewNames.USER_RECIPES);

        viewManager.showView(ViewNames.COMMUNITY_RECIPES);
        sidebar.setActiveButton(ViewNames.COMMUNITY_RECIPES);

        ActionListener sidebarButtonListener = e -> {
            JButton button = (JButton) e.getSource();
            String viewName = buttonToViewMap.get(button);
            viewManager.showView(viewName);
            sidebar.setActiveButton(viewName);
        };

        buttonToViewMap.forEach((button, viewName) -> button.addActionListener(sidebarButtonListener));
        sidebar.logoutButton.addActionListener(e -> logout());

        add(navbar, BorderLayout.NORTH);
        add(sidebar, BorderLayout.WEST);
        add(mainContentPanel, BorderLayout.CENTER);
    }

    public void logout() {
        SwingUtilities.invokeLater(() -> {
            this.close();

            CurrentUser user = CurrentUser.getInstance();
            user.reset();

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
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow.this.setVisible(false);
            }
        });
    }
}
