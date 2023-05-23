package com.recipeFinder.features.Auth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.recipeFinder.shared.utils.Constants;

import com.recipeFinder.components.LinkLabel;

public class LoginView extends JFrame {
    private JTextField usernameField;

    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel messageLabel;
    private JCheckBox rememberMeCheckbox;
    private LoginController controller;
    private JPanel mainPanel;

    public LoginView() {
        mainPanel = createMainPanel();
        getContentPane().add(mainPanel);
    }

    public void setController(LoginController controller) {
        this.controller = controller;
    }

    protected JPanel createMainPanel() {

        setTitle("Flavor Finder Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(Constants.STANDARD_ICON);
        setSize(350, 600);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        // Icon Label
        JLabel iconLabel = new JLabel("<html><body><h2>Welcome Back!</h1></body></html>");
        Image scaledImage = Constants.STANDARD_ICON.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        iconLabel.setIcon(new ImageIcon(scaledImage));
        constraints.gridx = 1;
        constraints.gridy = 0;
//        constraints.insets = new Insets(10, 10, 10, 10);
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

        rememberMeCheckbox = new JCheckBox("Remember me");
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.LINE_START;
        panel.add(rememberMeCheckbox, constraints);

        // Login Button
        loginButton = new JButton("Login");

        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(20, 10, 0, 10);
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, constraints);

        LinkLabel linkLabel = new LinkLabel("Dont have an account?", "Dont have an account?");

        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 2;
        panel.add(linkLabel.label, constraints);

        // bind listeners to controller
        loginButton.addActionListener(e -> {
            String username = getUsername();
            String password = getPassword();
            controller.handleLogin(username, password);
        });

        linkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.redirectToRegistration();
            }
        });

        return panel;
    }

    public void setRememberMeCheckbox(boolean x) {
        rememberMeCheckbox.setSelected(x);
    }

    public boolean getRememberMe() {
        return rememberMeCheckbox.isSelected();
    }

    public void setUsernameField(String text) {
        usernameField.setText(text);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public void open() {
        this.setVisible(true);
    }

    public void close() {
        this.setVisible(false);
    }
}


