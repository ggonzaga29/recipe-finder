package com.flavorfinder;

import com.flavorfinder.features.Auth.controllers.LoginController;
import com.flavorfinder.shared.utils.Constants;
import com.flavorfinder.shared.utils.Theme;

import com.flavorfinder.features.Auth.views.LoginView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            Theme.setFlatLafTheme("Dark");
            UIManager.put("Component.focusWidth", 1);
            UIManager.put("Button[border].enable", false);
            UIManager.put("Button[border].toAll", false);

            UIManager.put("Label.font", Constants.getMainFont());
            UIManager.put("Button.font", Constants.getMainFont());
            UIManager.put("TextField.font", Constants.getMainFont());
            UIManager.put("PasswordField.font", Constants.getMainFont());

            // launch the login
            LoginView loginView = new LoginView();
            LoginController loginController = new LoginController(loginView);
//            SwingUtilities.invokeLater(loginView::open);

//            loginController.on("submit_success", (Void) -> {
//                SwingUtilities.invokeLater(loginView::close);
                MainWindow mainWindowView = new MainWindow();
//            });

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
