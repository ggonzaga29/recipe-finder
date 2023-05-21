package com.recipeFinder.views;

import com.recipeFinder.components.Navbar;
import com.recipeFinder.components.RecipeCard;
import com.recipeFinder.components.Sidebar;
import com.recipeFinder.controllers.CreateMealPlanController;
import com.recipeFinder.controllers.CreateRecipeController;
import com.recipeFinder.controllers.LoginController;
import com.recipeFinder.utils.Constants;
import com.recipeFinder.models.RecipeModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * The main window of the Flavor Finder application.
 * This window serves as the main interface for the user,
 */
public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("Flavor Finder " + Constants.VERSION_NUMBER());
        setIconImage(Constants.STANDARD_ICON);
        setSize(1250, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(850, 600));

        Navbar navbar = new Navbar();
        Sidebar sidebar = new Sidebar();

        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BorderLayout());

        navbar.menuButton.addActionListener(e -> {
            if (sidebar.isVisible()) {
                sidebar.setVisible(false);
            } else {
                sidebar.setVisible(true);
            }
        });

        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(cardLayout);
        mainContentPanel.add(cardPanel);

        JPanel recipePanel = new JPanel(new BorderLayout());
        recipePanel.setBorder(new EmptyBorder(0, 0, 0, 0));

        JPanel recipeCardsPanel = new JPanel();
        recipeCardsPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
        recipeCardsPanel.setPreferredSize(new Dimension(recipePanel.getWidth(), Integer.MAX_VALUE));

        JPanel recipeSearchPanel = new JPanel();
        JTextField recipeSearchInput = new JTextField();

        int width = (int) recipeCardsPanel.getPreferredSize().getWidth();
        System.out.println(width);
        recipeSearchPanel.setPreferredSize(new Dimension(width, 100));
        recipeSearchPanel.add(recipeSearchInput, BorderLayout.CENTER);

        JScrollPane jScrollPane = new JScrollPane(recipeCardsPanel);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        jScrollPane.setPreferredSize(new Dimension(recipeCardsPanel.getWidth(), Integer.MAX_VALUE));
        jScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

        CreateRecipeView createRecipeView = new CreateRecipeView();
        CreateRecipeController createRecipeController = new CreateRecipeController(createRecipeView);

        GroceryListView groceryListView = new GroceryListView();

        CreateGroceryListView createGroceryListView = new CreateGroceryListView();

        CreateMealPlanView createMealPlanView = new CreateMealPlanView();
        CreateMealPlanController createMealPlanController = new CreateMealPlanController(createMealPlanView);

        cardPanel.add(jScrollPane, "recipe-cards");
        cardPanel.add(createRecipeView, "create-recipes");
        cardPanel.add(groceryListView, "grocery-list");
        cardPanel.add(createGroceryListView, "create-grocery-list");
        cardPanel.add(createMealPlanView, "create-meal-plan");

        sidebar.homeButton.addActionListener(e -> cardLayout.show(cardPanel, "recipe-cards"));
        sidebar.createRecipeButton.addActionListener(e -> cardLayout.show(cardPanel, "create-recipes"));
        sidebar.groceryListButton.addActionListener(e -> cardLayout.show(cardPanel, "grocery-list"));
        sidebar.createGroceryListButton.addActionListener(e -> cardLayout.show(cardPanel, "create-grocery-list"));
        sidebar.createMealPlanButton.addActionListener(e -> cardLayout.show(cardPanel, "create-meal-plan"));

        sidebar.logoutButton.addActionListener(e -> {
            SwingUtilities.invokeLater(this::close);

            LoginView loginView = new LoginView();
            LoginController loginController = new LoginController(loginView);
            SwingUtilities.invokeLater(loginView::open);

            loginController.setOnLoginSuccessListener(() -> {
                SwingUtilities.invokeLater(loginView::close);
                MainWindow mainWindowView = new MainWindow();
            });

        });

        setLayout(new BorderLayout());
        add(navbar, BorderLayout.NORTH);
        add(sidebar, BorderLayout.WEST);
        add(mainContentPanel, BorderLayout.CENTER);
        setVisible(true);

        SwingWorker<ArrayList<RecipeModel>, Void> recipeFetcher = new SwingWorker<ArrayList<RecipeModel>, Void>() {
            @Override
            protected ArrayList<RecipeModel> doInBackground() throws Exception {
                return RecipeModel.getRecipes(30);
            }

            @Override
            protected void done() {
                try {
                    ArrayList<RecipeModel> recipes = get();
                    for (RecipeModel recipe : recipes) {
                        JPanel recipeCard = new RecipeCard(recipe);
                        recipeCard.setBackground(Color.white);
                        recipeCardsPanel.add(recipeCard);
                    }

                    recipeCardsPanel.revalidate();
                    recipeCardsPanel.repaint();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        recipeFetcher.execute();
    }

    public void close() {
        setVisible(false);
    }
}
