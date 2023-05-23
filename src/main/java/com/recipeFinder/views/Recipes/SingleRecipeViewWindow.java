package com.recipeFinder.views.Recipes;

import com.recipeFinder.components.ImagePanel;
import com.recipeFinder.utils.Constants;
import com.recipeFinder.models.RecipeModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;

public class SingleRecipeViewWindow extends JFrame {
    private RecipeModel recipe; // The recipe model

    public SingleRecipeViewWindow(RecipeModel recipe) {
        this.recipe = recipe;

        setIconImage(Constants.STANDARD_ICON);
        setTitle(recipe.getLabel());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(400, 600);

        initComponents();
    }

    private void initComponents() {
        InputStream inputStream = null;
        BufferedImage image = null;
        try {
            inputStream = new FileInputStream("src/main/resources/recipe_images/" + recipe.getLocalImageUrl());
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImagePanel panel = new ImagePanel(image);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setDarknessLevel(180);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Display recipe details using JLabels
        // Create a JEditorPane to display HTML content
        JEditorPane editorPane = new JEditorPane();
        editorPane.setContentType("text/html");
        editorPane.setEditable(false);
        editorPane.setOpaque(false); // Make the background transparent

        editorPane.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    try {
                        // Create a URI object with the link
                        URI uri = new URI(recipe.getRecipeUrl());

                        // Check if Desktop browsing is supported
                        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                            // Open the link in the default browser
                            Desktop.getDesktop().browse(uri);
                        } else {
                            System.out.println("Desktop browsing is not supported.");
                        }
                    } catch (Exception err) {
                        System.out.println("Error opening the link: " + err.getMessage());
                    }
                }
            }
        });

        // Set the HTML content
        StringBuilder liContent = new StringBuilder();

        ArrayList<String> ingredientLines = recipe.getIngredientLines();
        for (String line : ingredientLines) {
            liContent.append("<li>");
            liContent.append(line);
            liContent.append("</li>");
        }

        String htmlContent = "<html>" +
                "<body style='font-family: Inter, sans-serif;'>" +
                "<h1>" + recipe.getLabel() + "</h1>" +
                "<h3>Source: " + recipe.getSource() + "</h3>" +
                "<p><span>Calories: </span>" + recipe.getCalories() + "</p>" +
                "<p><span>Total Time: </span>" + recipe.getTotalTime() + " minutes</p>" +
                "<p><span>Yield: </span>" + recipe.getYield() + "</p>" +
                "<p><a href='" + recipe.getRecipeUrl() + "'>Go to source</a></p>" +
                "<br><hr>" +
                "<h3>Ingredients</h3>" +
                "<ul>" +
                liContent +
                "</ul>" +
                "</body>" +
                "</html>";

        editorPane.setText(htmlContent);

        //            labelYield.setText("<html><a href='" + recipe.getSourceUrl() + "'>Go to source</a></html>");
        // Add more JLabels for other recipe details

        JScrollPane jScrollPane = new JScrollPane(editorPane);
        jScrollPane.getViewport().setOpaque(false); // Set the viewport's background color to transparent
        jScrollPane.setOpaque(false);
        jScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        jScrollPane.setPreferredSize(getPreferredSize());
        jScrollPane.getVerticalScrollBar().setUnitIncrement(2);

        System.out.println(recipe);

        JButton addToGroceryListButton = new JButton("Add to Grocery List");
        addToGroceryListButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        addToGroceryListButton.setMaximumSize(new Dimension(150, 20));

        String week[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        JComboBox<String> groceryLists = new JComboBox<>(week);
        groceryLists.setAlignmentX(Component.LEFT_ALIGNMENT);
        groceryLists.setMaximumSize(new Dimension(150, 20));

        addToGroceryListButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });

//            addPanel.add(addToGroceryListButton);
//            addPanel.add(groceryLists);

        panel.add(jScrollPane);
        panel.add(Box.createVerticalStrut(30));
        panel.add(addToGroceryListButton);
        panel.add(groceryLists);

        getContentPane().add(panel);
        setVisible(true);
    }

}
