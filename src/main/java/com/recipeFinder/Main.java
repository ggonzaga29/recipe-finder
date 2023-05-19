package com.recipeFinder;

import com.recipeFinder.utils.Theme;

import com.recipeFinder.controllers.*;
import com.recipeFinder.views.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            Theme.setFlatLafTheme("Dark");
            UIManager.put("Component.focusWidth", 1);
            UIManager.put("Button[border].enable", false);
            UIManager.put("Button[border].toAll", false);

            // launch the login
            LoginView loginView = new LoginView();
            LoginController loginController = new LoginController(loginView);
//            SwingUtilities.invokeLater(loginView::open);

//            loginController.setOnLoginSuccessListener(() -> {
//                SwingUtilities.invokeLater(loginView::close);
                MainWindow mainWindowView = new MainWindow();
//            });
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
