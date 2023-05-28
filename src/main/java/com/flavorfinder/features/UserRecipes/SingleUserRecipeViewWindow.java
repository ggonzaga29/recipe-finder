package com.flavorfinder.features.UserRecipes;

import com.flavorfinder.features.View;

import javax.swing.*;

public class SingleUserRecipeViewWindow extends JFrame {
    private SingleUserRecipeController controller;

    public SingleUserRecipeViewWindow(UserRecipeModel recipe) {
        super("Single User Recipe View");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel viewPanel = new View();
        add(viewPanel);
        JEditorPane editorPane = new JEditorPane();
        editorPane.setContentType("text/html");
        editorPane.setEditable(false);
        editorPane.setOpaque(false);
        editorPane.setText(recipe.toHtml());
        viewPanel.add(editorPane);
        setVisible(true);
    }

    public void setController(SingleUserRecipeController controller) {
        this.controller = controller;
    }
}
