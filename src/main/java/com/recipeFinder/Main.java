package com.recipeFinder;

import com.recipeFinder.features.Auth.LoginController;
import com.recipeFinder.shared.utils.Theme;

import com.recipeFinder.features.Auth.LoginView;

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

//
//            if (args.length > 0 && !args[0].equals("--skip-login")) {
//            } else {
                SwingUtilities.invokeLater(loginView::open);
//
//                loginController.setOnLoginSuccessListener(() -> {
//                    SwingUtilities.invokeLater(loginView::close);
//                    MainWindow mainWindowView = new MainWindow();
//                });
//            }

            loginController.on("submit_success", (Void) -> {
                SwingUtilities.invokeLater(loginView::close);
                MainWindow mainWindowView = new MainWindow();
            });

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
