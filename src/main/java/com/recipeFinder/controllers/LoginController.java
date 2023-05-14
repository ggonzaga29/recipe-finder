package com.recipeFinder.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.recipeFinder.models.UserModel;
import com.recipeFinder.views.LoginView;
import com.recipeFinder.views.RegistrationView;

import javax.swing.*;
import java.sql.SQLException;
import java.util.prefs.Preferences;

public class LoginController {
    private LoginView view;
    private Preferences preferences;

    private OnLoginSuccessListener loginSuccessListener;


    public LoginController(LoginView view) {
        preferences = Preferences.userNodeForPackage(getClass());

        this.view = view;
        view.setController(this);

        boolean rememberMe = preferences.getBoolean("rememberMe", false);
        if (rememberMe) {
            view.setRememberMeCheckbox(true);
            String storedUsername = preferences.get("username", null);
            view.setUsernameField(storedUsername);
            SwingUtilities.invokeLater(() -> {
                view.getPasswordField().grabFocus();
            });
        }
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

        if (view.getRememberMe()) {
            preferences.putBoolean("rememberMe", true);
            preferences.put("username", usernameTrimmed);
        } else {
            preferences.putBoolean("rememberMe", false);
            preferences.remove("username");
        }

        try {
            UserModel model = UserModel.findByUsername(usernameTrimmed);

            if (model == null) {
                // dont print "User not found" for security reasons
                JOptionPane.showMessageDialog(view, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), model.getPassword());
            if (result.verified) {
                JOptionPane.showMessageDialog(view, "Login Success!", "Login Success!", JOptionPane.INFORMATION_MESSAGE);

                if (loginSuccessListener != null) {
                    loginSuccessListener.onLoginSuccess();
                }
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    public void setOnLoginSuccessListener(OnLoginSuccessListener listener) {
        this.loginSuccessListener = listener;
    }

    public interface OnLoginSuccessListener {
        void onLoginSuccess();
    }

}
