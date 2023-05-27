package com.flavorfinder.features.Auth.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.flavorfinder.features.Auth.models.User;
import com.flavorfinder.features.Auth.views.LoginView;
import com.flavorfinder.features.Auth.views.RegistrationView;
import com.flavorfinder.MainWindow;
import com.flavorfinder.features.Controller;

import javax.swing.*;
import java.sql.SQLException;

public class RegistrationController extends Controller {
    private final RegistrationView view;

    public RegistrationController(RegistrationView view) {
        this.view = view;
        view.setController(this);
    }

    public void redirectToLogin() {
        view.close();
        LoginView loginView = new LoginView();
        LoginController loginController = new LoginController(loginView);
        SwingUtilities.invokeLater(loginView::open);

        loginController.on("submit_success", (Void) -> {
            SwingUtilities.invokeLater(loginView::close);
            MainWindow mainWindowView = new MainWindow();
        });
    }

    public void handleRegistration(String username, String password, String confirmPassword) {
        if(username.length() == 0 || password.length() == 0 || confirmPassword.length() == 0) {
            JOptionPane.showMessageDialog(view, "Username and password fields should not be empty.", "Register Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(view, "Passwords do not match.", "Confirm password error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String usernameTrimmed = username.trim();
            User model = User.findByUsername(usernameTrimmed);

            if(model != null) {
                JOptionPane.showMessageDialog(view, "User already exists.", "Registration", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());
            model = new User(usernameTrimmed, hashedPassword);
            model.save();

            JOptionPane.showMessageDialog(view, "User created.", "Registration", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

}
