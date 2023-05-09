package com.recipeFinder.controllers;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import com.formdev.flatlaf.FlatLightLaf;

import com.recipeFinder.views.HomeView;

import com.recipeFinder.views.LoginView;


public class MainController {
    private MainController() {
    }

    public static void initialize() {

        try {
            try {

                FlatLightLaf.setup();

                UIManager.put( "Component.focusWidth", 1 );
                // UIManager.setLookAndFeel(new MaterialLookAndFeel(new MaterialLiteTheme()));
                // by including the https://github.com/material-ui-swing/DarkStackOverflowTheme
//                UIManager.setLookAndFeel(new MaterialLookAndFeel(new JMarsDarkTheme()));
//
//                ImageIcon icon = new ImageIcon("src/main/resources/recipe.png");
//                UIManager.put("Frame.icon", new ImageIcon(icon.getImage()));
//
//                UIManager.put("Button[border].enable", false);
//                UIManager.put("Button[border].toAll", false);
//
                Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/Inter.ttf"));
                customFont = customFont.deriveFont(Font.PLAIN, 14);

                UIManager.put("Label.font", customFont);
                UIManager.put("Button.font", customFont);
                UIManager.put("TextField.font", customFont);
                UIManager.put("PasswordField.font", customFont);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // look for a remembered user

            // if there is a remembered user, launch the main window
            HomeView homeView = new HomeView();

            // if there isn't any, launch the login
//            LoginController loginController = new LoginController();
//            loginController.initialize();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
