package com.recipeFinder.controllers;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import com.recipeFinder.views.LoginView;
import mdlaf.*;
import mdlaf.themes.JMarsDarkTheme;


public class MainController {
    private MainController() {
    }

    public static void initialize() {

        try {
            try {
                // UIManager.setLookAndFeel(new MaterialLookAndFeel(new MaterialLiteTheme()));
                // by including the https://github.com/material-ui-swing/DarkStackOverflowTheme
                UIManager.setLookAndFeel(new MaterialLookAndFeel(new JMarsDarkTheme()));

//                ImageIcon icon = new ImageIcon("src/main/resources/recipe.png");
//                UIManager.put("Frame.icon", new ImageIcon(icon.getImage()));

//                UIManager.put("Button[border].enable", false);
//                UIManager.put("Button[border].toAll", false);

                Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/Inter.ttf"));
                customFont = customFont.deriveFont(Font.PLAIN, 14);

                UIManager.put("Label.font", customFont);
                UIManager.put("Button.font", customFont);
                UIManager.put("TextField.font", customFont);
                UIManager.put("PasswordField.font", customFont);
            } catch (IOException | UnsupportedLookAndFeelException | FontFormatException e) {
                throw new RuntimeException(e);
            }

            // look for a remembered user

            // if there is a remembered user, launch the main window

            // if there isn't any, launch the login
            LoginView loginView = new LoginView();
            loginView.showLogin();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
