package com.recipeFinder.views;

import com.recipeFinder.components.LinkLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;
import com.recipeFinder.views.LoginView;

public class RegistrationView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JLabel messageLabel;

    public void showRegistration() {
        setVisible(true);
    }

    public void closeRegistration() {
        setVisible(false);
    }

    public RegistrationView() {
        setTitle("Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        // Username Label
        JLabel usernameLabel = new JLabel("<html><body style='width: 100px'>Username:</body></html>");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(usernameLabel, constraints);

        // Username Text Field
        usernameField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(usernameField, constraints);

        // Password Label
        JLabel passwordLabel = new JLabel("<html><body style='width: 100px'>Password:</body></html>");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(passwordLabel, constraints);

        // Password Text Field
        passwordField = new JPasswordField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(passwordField, constraints);

        // Register Button
        registerButton = new JButton("Register");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(registerButton, constraints);

        LinkLabel linkLabel = new LinkLabel("Click here to login", "Click here to login");
        linkLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                closeRegistration();
                LoginView loginView = new LoginView();
                loginView.showLogin();
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        panel.add(linkLabel.label, constraints);

        // Message Label
        messageLabel = new JLabel();
        messageLabel.setText(" ");
        messageLabel.setForeground(Color.RED);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        panel.add(messageLabel, constraints);

        add(panel);
    }

    public void displayMessage(String msg, String type) {
        messageLabel.setText("<html><body style='width: 100px'>" + msg + "</body></html>");

        if (Objects.equals(type, "success")) {
            messageLabel.setForeground(Color.BLUE);
        } else if (Objects.equals(type, "error")) {
            messageLabel.setForeground(Color.RED);
        }
    }

    public void addActionListener(ActionListener listener) {
        registerButton.addActionListener(listener);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }
}