package com.recipeFinder.views.GroceryList;

import com.github.lgooddatepicker.components.DatePicker;
import com.recipeFinder.components.FormControl;
import com.recipeFinder.controllers.GroceryList.CreateGroceryListController;
import com.recipeFinder.views.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CreateGroceryListView extends View {
    private CreateGroceryListController controller;
    FormControl groceryListTitle;

    public CreateGroceryListView() {
        JPanel createGroceryListPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 10));
        createGroceryListPanel.setPreferredSize(new Dimension(400, 700));
        JLabel title = new JLabel("<html><h1>Create a Grocery List</h1></html>");
        title.setPreferredSize(new Dimension(300, 70));
        createGroceryListPanel.add(title);

        groceryListTitle = new FormControl("Grocery List Name *");
        groceryListTitle.setBorder(new EmptyBorder(0,0,0,0));

        GridBagConstraints constraints;
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        DatePicker datePicker = new DatePicker();
        datePicker.setDateToToday(); // Set the initial date to today
        datePicker.getComponentToggleCalendarButton().setText("Select Date"); // Set the button text
        datePicker.setPreferredSize(new Dimension(200, 25));

        JButton submitButton = new JButton("Create Grocery List");
        submitButton.setPreferredSize(new Dimension(300, 40));
        submitButton.setBackground(Color.decode("#2ecc71"));
        submitButton.setForeground(Color.decode("#2c3e50"));
        submitButton.setBorder(new EmptyBorder(0, 0, 0, 0));

        createGroceryListPanel.add(groceryListTitle);
        createGroceryListPanel.add(datePicker);
        JPanel spacerPanel = new JPanel();
        spacerPanel.setPreferredSize(new Dimension(300, 30));
        createGroceryListPanel.add(spacerPanel);
        createGroceryListPanel.add(submitButton);
        add(createGroceryListPanel);

        submitButton.addActionListener(e -> controller.handleSubmit(groceryListTitle.getText(), datePicker.getText()));
    }

    public void setController(CreateGroceryListController controller) {
        this.controller = controller;
    }
}
