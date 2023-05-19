package com.recipeFinder.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FormControl extends JPanel {
    JLabel inputLabel;
    JTextField input;

    public FormControl(String labelText) {
        initComponents();
        inputLabel.setText(labelText);
        input.setPreferredSize(new Dimension(300, 40));
    }

    public FormControl(String labelText, Dimension prefSize) {
        initComponents();
        inputLabel.setText(labelText);
        input.setPreferredSize(prefSize);
    }

    protected void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.insets = new Insets(10, 10, 10, 10);

        inputLabel = new JLabel();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(10, 10, -2, 10);
        add(inputLabel, constraints);

        input = new JTextField();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(10, 10,   10, 10);
        add(input, constraints);
    }

    public String getLabelText() {
        return inputLabel.getText();
    }

    public void setLabelText(String text) {
        inputLabel.setText(text);
    }

    public String getText() {
        return input.getText();
    }

    public int getInt() {
        try {
            return Integer.parseInt(input.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public double getDouble() {
        try {
            return Double.parseDouble(input.getText());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return 0.00;
        }
    }

    public void setText(String text) {
        input.setText(text);
    }

    public void lockInt() {
        input.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char character = e.getKeyChar();
                if (!Character.isDigit(character) && character != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }
        });
    }

    public void lockDouble() {
        input.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char character = e.getKeyChar();
                if (!Character.isDigit(character) && character != '.' && character != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }
        });
    }

}
