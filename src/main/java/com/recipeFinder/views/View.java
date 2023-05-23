package com.recipeFinder.views;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;

/**
 * The View class represents the user interface component for the flavor finder application.
 * It extends JPanel and provides methods for displaying messages and clearing input fields.
 */
public class View extends JPanel {

    /**
     * Constructs a new View instance.
     */
    public View() {}

    /**
     * Displays a message dialog with the specified message and message type.
     *
     * @param message      the message to display
     * @param messageType the type of the message (error, information, warning, question)
     */
    public void showMessage(String message, String messageType) {
        int messageTypeValue = JOptionPane.PLAIN_MESSAGE; // Default message type

        if ("error".equalsIgnoreCase(messageType)) {
            messageTypeValue = JOptionPane.ERROR_MESSAGE;
        } else if ("information".equalsIgnoreCase(messageType)) {
            messageTypeValue = JOptionPane.INFORMATION_MESSAGE;
        } else if ("warning".equalsIgnoreCase(messageType)) {
            messageTypeValue = JOptionPane.WARNING_MESSAGE;
        } else if ("question".equalsIgnoreCase(messageType)) {
            messageTypeValue = JOptionPane.QUESTION_MESSAGE;
        }

        JOptionPane.showMessageDialog(this, message, messageType.toUpperCase(), messageTypeValue);
    }

    /**
     * Displays a message dialog with the specified message.
     *
     * @param message the message to display
     */
    public void showMessage(String message) {
        showMessage(message, "default");
    }

    /**
     * Call this most of the time to clear the input fields within the view.
     */
    public void clearInputs() {
        clearInputs(this);
    }

    /**
     * Recursively clears the input fields within the specified container.
     * It iterates through each component in the container and performs the appropriate action
     * to clear the input field based on its type. If a nested container is encountered, the method
     * is recursively called to clear the input fields within that container as well.
     *
     * @param container the container containing the input fields to clear (usually the view JPanel)
     *
     */
    private void clearInputs(Container container) {
        // Iterate through each component in the container
        for (Component component : container.getComponents()) {
            // Check the type of the component
            if (component instanceof JTextField textField) {
                // If it is a JTextField, set the text to an empty string to clear the input
                textField.setText("");
            } else if (component instanceof JTextArea textArea) {
                // If it is a JTextArea, set the text to an empty string to clear the input
                textArea.setText("");
            } else if (component instanceof JComboBox<?> comboBox) {
                // If it is a JComboBox, set the selected index to 0 to reset the selection
                comboBox.setSelectedIndex(0);
            } else if (component instanceof JCheckBox checkBox) {
                // If it is a JCheckBox, set the selected state to false to clear the input
                checkBox.setSelected(false);
            } else if (component instanceof DatePicker datePicker) {
                // If it is a DatePicker (from LGoodDatePicker library),
                // invoke the clear() method to reset the date selection
                datePicker.clear();
            } else if (component instanceof Container) {
                // If it is a nested container, recursively call the clearInputs method
                // to clear the input fields within that container as well
                clearInputs((Container) component);
            }
        }
    }

}
