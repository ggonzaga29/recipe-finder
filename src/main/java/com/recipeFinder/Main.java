package com.recipeFinder;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.recipeFinder.controllers.*;
import com.recipeFinder.lib.Constants;
import com.recipeFinder.models.UserModel;
import com.recipeFinder.views.LoginView;
import com.recipeFinder.views.RegistrationView;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {

        try {
            try {
                FlatDarkLaf.setup();
                UIManager.put( "Component.focusWidth", 1 );
                UIManager.put("Button[border].enable", false);
                UIManager.put("Button[border].toAll", false);

                UIManager.put("Label.font", Constants.getMainFont());
                UIManager.put("Button.font", Constants.getMainFont());
                UIManager.put("TextField.font", Constants.getMainFont());
                UIManager.put("PasswordField.font", Constants.getMainFont());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // look for a remembered user

            // if there is a remembered user, laun                             ch the main window
//            MainView homeView = new MainView();
//            MainView mainView = new                     ew();
//            mainView.setVisible(true);

            // if there isn't any, launch the login
//            LoginView loginView = new LoginView();
//            LoginController loginController = new LoginController(loginView);
//            SwingUtilities.invokeLater(loginView::open);

            RegistrationView registrationView = new RegistrationView();
            RegistrationController registrationController = new RegistrationController(registrationView);
            SwingUtilities.invokeLater(registrationView::open);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
