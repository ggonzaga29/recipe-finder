package com.recipeFinder.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import com.recipeFinder.lib.Constants;

import com.recipeFinder.components.LinkLabel;
import com.recipeFinder.controllers.RegistrationController;

public class RegistrationView extends JFrame {
    private JTextField usernameField;

    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton registerButton;

    private RegistrationController controller;
    private JPanel mainPanel;

    public RegistrationView() {
        mainPanel = createMainPanel();
        getContentPane().add(mainPanel);
    }

    public void setController(RegistrationController controller) {
        this.controller = controller;
    }

    protected JPanel createMainPanel() {
        setTitle("Flavor Finder Account Creation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(Constants.STANDARD_ICON);
        setSize(350, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultLookAndFeelDecorated(true);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        // Icon Label
        JLabel iconLabel = new JLabel("<html><body><h2>Create an Account</h1></body></html>");
        Image scaledImage = Constants.STANDARD_ICON.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        iconLabel.setIcon(new ImageIcon(scaledImage));
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(iconLabel, constraints);

        // Username Label
        JLabel usernameLabel = new JLabel("<html><body style='width: 100px'>Username</body></html>");
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(20, 10, -2, 10);
        panel.add(usernameLabel, constraints);

        // Username Text Field
        usernameField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.insets = new Insets(10, 10, 10, 10);
        panel.add(usernameField, constraints);

        // Password Label
        JLabel passwordLabel = new JLabel("<html><body style='width: 100px'>Password</body></html>");
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.insets = new Insets(10, 10, -2, 10);
        panel.add(passwordLabel, constraints);

        // Password Text Field
        passwordField = new JPasswordField(20);
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.insets = new Insets(10, 10, 10, 10);
        panel.add(passwordField, constraints);

        // Confirm Password Label
        JLabel confirmPasswordLabel = new JLabel("<html><body style='width: 100px'>Confirm Password</body></html>");
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.insets = new Insets(10, 10, -2, 10);
        panel.add(confirmPasswordLabel, constraints);

        // Confirm Password Field
        confirmPasswordField = new JPasswordField(20);
        constraints.gridx = 1;
        constraints.gridy = 6;
        constraints.insets = new Insets(10, 10, 10, 10);
        panel.add(confirmPasswordField, constraints);

        // Login Button
        registerButton = new JButton("Register");
        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(30, 10, 10, 10);
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(registerButton, constraints);


        LinkLabel linkLabel = new LinkLabel("Click here to register", "Click here to register");
        linkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.redirectToLogin();
            }
        });

        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 10, 0, 10);
        panel.add(linkLabel.label, constraints);


        registerButton.addActionListener(e -> {
            String username = getUsername();
            String password = getPassword();
            String confirmPassword = getConfirmPassword();

            controller.handleRegistration(username, password, confirmPassword);
        });

        return panel;
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public String getConfirmPassword() {
        return new String(confirmPasswordField.getPassword());
    }

    public void open() {
        this.setVisible(true);
    }

    public void close() {
        this.setVisible(false);
    }
}


