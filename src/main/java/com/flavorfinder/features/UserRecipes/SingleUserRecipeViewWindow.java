package com.flavorfinder.features.UserRecipes;

import com.flavorfinder.features.View;

import javax.swing.*;

public class SingleUserRecipeViewWindow extends JFrame {
    public SingleUserRecipeViewWindow() {
        super("Single User Recipe View");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel viewPanel = new View();
        add(viewPanel);

        setVisible(true);
    }
}
