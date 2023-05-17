package com.recipeFinder;

import com.recipeFinder.lib.Constants;
import com.recipeFinder.lib.Theme;

import com.recipeFinder.controllers.*;
import com.recipeFinder.views.*;
import com.recipeFinder.models.*;

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

            loginController.setOnLoginSuccessListener(() -> {
                SwingUtilities.invokeLater(loginView::close);


            });
            MainWindowView mainWindowView = new MainWindowView();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
