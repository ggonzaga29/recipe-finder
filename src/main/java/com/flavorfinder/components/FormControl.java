package com.flavorfinder.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * A custom JPanel component that represents a form control with a label and text input field.
 * It provides methods for setting and getting the label text, retrieving the input value as a string, integer, or double,
 * and adding restrictions to the input field for numeric values.
 */
public class FormControl extends JPanel {

    /**
     * The label for the form control.
     */
    JLabel inputLabel;

    /**
     * The text input field.
     */
    JTextField input;

    /**
     * Constructs a new FormControl with the specified label text.
     *
     * @param labelText The text to be displayed as the label.
     */
    public FormControl(String labelText) {
        initComponents();
        inputLabel.setText(labelText);
        input.setPreferredSize(new Dimension(300, 40));
    }

    /**
     * Constructs a new FormControl with the specified label text and preferred size for the input field.
     *
     * @param labelText The text to be displayed as the label.
     * @param prefSize  The preferred size for the input field.
     */
    public FormControl(String labelText, Dimension prefSize) {
        initComponents();
        inputLabel.setText(labelText);
        input.setPreferredSize(prefSize);
    }

    /**
     * Initializes the components of the FormControl.
     * Sets the layout and adds the label and input field to the panel.
     */
    protected void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 0, 10, 0);

        inputLabel = new JLabel();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(10, 0, -2, 0);
        add(inputLabel, constraints);

        input = new JTextField();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(10, 0, 10, 0);
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

    /**
     * Hides the label of the FormControl.
     */
    public void noLabel() {
        inputLabel.setVisible(false);
    }

    /**
     * Returns the value entered in the input field as an integer.
     * If the input cannot be parsed as an integer, 0 is returned.
     *
     * @return The integer value entered in the input field, or 0 if parsing fails.
     */
    public int getInt() {
        try {
            return Integer.parseInt(input.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Returns the value entered in the input field as a double.
     * If the input cannot be parsed as a double, 0.00 is returned.
     *
     * @return The double value entered in the input field, or 0.00 if parsing fails.
     */
    public double getDouble() {
        try {
            return Double.parseDouble(input.getText());
        } catch (NumberFormatException e) {
            return 0.00;
        }
    }

    /**
     * Sets the text of the input field.
     *
     * @param text The text to be set in the input field.
     */
    public void setText(String text) {
        input.setText(text);
    }

    /**
     * Restricts the input field to only accept integer values.
     * Non-digit characters and backspace are consumed.
     */
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

    /**
     * Restricts the input field to only accept numeric (integer or decimal) values.
     * Non-digit, non-dot characters, and backspace are consumed.
     */
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

