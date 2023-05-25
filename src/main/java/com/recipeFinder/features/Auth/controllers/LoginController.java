package com.recipeFinder.features.Auth.controllers;

import javax.swing.*;
import java.sql.SQLException;
import java.util.prefs.Preferences;

import at.favre.lib.crypto.bcrypt.BCrypt;

import com.recipeFinder.features.Auth.models.User;
import com.recipeFinder.features.Auth.views.LoginView;
import com.recipeFinder.features.Auth.views.RegistrationView;
import com.recipeFinder.features.Controller;

public class LoginController extends Controller {
    private final LoginView view;
    private final Preferences preferences;

    private OnLoginSuccessListener loginSuccessListener;

    public LoginController(LoginView view) {
        preferences = Preferences.userNodeForPackage(getClass());
        this.view = view;
        view.setController(this);
    }

    public void redirectToRegistration() {
        view.close();
        RegistrationView registrationView = new RegistrationView();
        RegistrationController registrationController = new RegistrationController(registrationView);
        SwingUtilities.invokeLater(registrationView::open);
    }

    public void handleLogin(String username, String password) {
        if (username.length() == 0 || password.length() == 0) {
            JOptionPane.showMessageDialog(view, "Username and password fields should not be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String usernameTrimmed = username.trim();

        try {
            User model = User.findByUsername(usernameTrimmed);

            if (model == null) {
                // dont print "User not found" for security reasons
                JOptionPane.showMessageDialog(view, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), model.getPassword());
            if (result.verified) {
                if (view.getRememberMe()) {
                    preferences.putBoolean("rememberMe", true);
                    preferences.put("username", usernameTrimmed);
                } else {
                    preferences.putBoolean("rememberMe", false);
                    preferences.remove("username");
                }

                emit("submit_success");
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    public void dispose() {

    }

    public interface OnLoginSuccessListener {
        void onLoginSuccess();
    }

}
