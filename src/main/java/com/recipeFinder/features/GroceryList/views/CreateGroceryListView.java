package com.recipeFinder.features.GroceryList.views;

import com.github.lgooddatepicker.components.DatePicker;
import com.recipeFinder.components.FormControl;
import com.recipeFinder.features.GroceryList.controllers.CreateGroceryListController;
import com.recipeFinder.features.View;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CreateGroceryListView extends View {
    private CreateGroceryListController controller;
    FormControl groceryListTitle;

    public CreateGroceryListView() {

        Object[][] data = {
                {
                    "John Smith",
                    12
                },

                {
                        "John Smith",
                        12
                },

                {
                        "John Smith",
                        12
                },

                {
                        "John Smith",
                        12
                },
        };


        setLayout(new FlowLayout(FlowLayout.LEADING));
        JPanel createGroceryListPanel = new JPanel();
        createGroceryListPanel.setLayout(new GridBagLayout());
        JLabel title = new JLabel("<html><h1>Create a Grocery List</h1></html>");

        GridBagConstraints constraints;
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.weightx = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        createGroceryListPanel.add(title, constraints);

        groceryListTitle = new FormControl("Grocery List Name *");
        groceryListTitle.setBorder(new EmptyBorder(0,0,0,0));
        constraints.gridy = 1;
        createGroceryListPanel.add(groceryListTitle, constraints);


        DatePicker datePicker = new DatePicker();
        datePicker.setDateToToday(); // Set the initial date to today
        datePicker.getComponentToggleCalendarButton().setText("Select Date"); // Set the button text
        constraints.gridy = 2;
        createGroceryListPanel.add(datePicker, constraints);

        JButton submitButton = new JButton("Create Grocery List");
        submitButton.setPreferredSize(new Dimension(300, 40));
        submitButton.setBackground(Color.decode("#2ecc71"));
        submitButton.setForeground(Color.decode("#2c3e50"));
        submitButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        constraints.gridy = 3;
        createGroceryListPanel.add(submitButton, constraints);

        add(createGroceryListPanel);
        submitButton.addActionListener(e -> controller.handleSubmit(groceryListTitle.getText(), datePicker.getText()));
    }

    public void setController(CreateGroceryListController controller) {
        this.controller = controller;
    }
}
