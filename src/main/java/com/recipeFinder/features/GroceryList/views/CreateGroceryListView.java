package com.recipeFinder.features.GroceryList.views;

import com.github.lgooddatepicker.components.DatePicker;
import com.recipeFinder.components.FormControl;
import com.recipeFinder.features.GroceryList.controllers.CreateGroceryListController;
import com.recipeFinder.features.GroceryList.models.GroceryListModel;
import com.recipeFinder.features.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class CreateGroceryListView extends View {
    private CreateGroceryListController controller;
    FormControl groceryListTitle;
    DatePicker datePicker;
    private int activeID;

    public CreateGroceryListView() {
        initComponents();
    }

    public void initComponents() {
        activeID = -1;

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
        groceryListTitle.setBorder(new EmptyBorder(0, 0, 0, 0));
        constraints.gridy = 1;
        createGroceryListPanel.add(groceryListTitle, constraints);


        datePicker = new DatePicker();
        datePicker.setDateToToday(); // Set the initial date to today
        datePicker.getComponentToggleCalendarButton().setText("Select Date"); // Set the button text
        constraints.gridy = 2;
        createGroceryListPanel.add(datePicker, constraints);

        Dimension buttonSize = new Dimension(150, 40);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEADING));

        JButton submitButton = new JButton("Save Grocery List");
        submitButton.setPreferredSize(buttonSize);
        submitButton.setBackground(Color.decode("#2ecc71"));
        submitButton.setForeground(Color.decode("#2c3e50"));
        submitButton.setBorder(new EmptyBorder(0, 0, 0, 0));

        JButton deleteButton = new JButton("Delete Grocery List");
        deleteButton.setPreferredSize(buttonSize);
        deleteButton.setBackground(Color.decode("#e74c3c"));
        deleteButton.setForeground(Color.decode("#FFFFFF"));
        deleteButton.setBorder(new EmptyBorder(0, 0, 0, 0));

        constraints.gridy = 3;
        buttonPanel.add(submitButton);
        buttonPanel.add(deleteButton);
        createGroceryListPanel.add(buttonPanel, constraints);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("List Name");
        model.addColumn("Date");


        ArrayList<GroceryListModel> groceryListModels = GroceryListModel.getAll();
        Collections.reverse(groceryListModels);

        for (GroceryListModel groceryListModel : groceryListModels) {
            Object[] row = { groceryListModel.getId(), groceryListModel.getName(), groceryListModel.getDate() };
            model.addRow(row);
        }

        JTable table = new JTable(model);
        table.getColumnModel().getColumn(0).setWidth(0);
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);

        table.setEnabled(false);
        // hover effects for table
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(false);
        table.setGridColor(Color.decode("#bdc3c7"));
        table.setRowHeight(30);
        table.setRowSelectionAllowed(false);
        table.setCellSelectionEnabled(false);
        table.setFillsViewportHeight(true);
        table.setBackground(Color.decode("#ecf0f1"));
        table.setForeground(Color.decode("#2c3e50"));
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        table.getTableHeader().setBackground(Color.decode("#2ecc71"));
        table.getTableHeader().setForeground(Color.decode("#2c3e50"));
        table.getTableHeader().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                activeID = (int) table.getValueAt(row, 0);
                controller.handleGetGroceryList((int) table.getValueAt(row, 0));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor());
            }
        });


        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 200));
        constraints.gridx = 0;
        constraints.gridy = 4;
        createGroceryListPanel.add(scrollPane, constraints);

        add(createGroceryListPanel);

        submitButton.addActionListener(e -> {
            if(activeID == -1) {
                controller.handleSubmit(groceryListTitle.getText(), String.valueOf(datePicker.getDate()));
                return;
            }

            if(activeID != -1){
                controller.handleEdit(activeID, groceryListTitle.getText(), String.valueOf(datePicker.getDate()));
            }
        });

        deleteButton.addActionListener(e -> controller.handleDelete(activeID));
    }

    public void setGroceryListModel(GroceryListModel groceryListModel) {
        this.groceryListTitle.setText(groceryListModel.getName());
        this.datePicker.setDate(LocalDate.parse(groceryListModel.getDate()));
    }

    public void setController(CreateGroceryListController controller) {
        this.controller = controller;
    }

    public void setActiveID(int activeID) {
        this.activeID = activeID;
    }
}
