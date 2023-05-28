package com.flavorfinder.features.MealPlan.views;

import com.flavorfinder.features.MealPlan.controllers.CreateMealPlanController;
import com.flavorfinder.features.View;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CreateMealPlanView extends View {
    private CreateMealPlanController controller;
    private JTextField breakfastField;
    private JTextField lunchField;
    private JTextField dinnerField;
    private JTable mealPlanTable;
    private ArrayList<MealPlanEntry> mealPlanEntries;

    public CreateMealPlanView() {
        initComponents();
    }

    public void initComponents() {
        // Create title label
        JLabel titleLabel = new JLabel("Create a Meal Plan");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Create labels
        JLabel breakfastLabel = new JLabel("Breakfast:");
        JLabel lunchLabel = new JLabel("Lunch:");
        JLabel dinnerLabel = new JLabel("Dinner:");
        JLabel weekdayLabel = new JLabel("Select Whole Week:");

        // Create text fields
        breakfastField = new JTextField();
        lunchField = new JTextField();
        dinnerField = new JTextField();

        // Set preferred width for text fields
        int textFieldWidth = 30;
        breakfastField.setPreferredSize(new Dimension(textFieldWidth, breakfastField.getPreferredSize().height));
        lunchField.setPreferredSize(new Dimension(textFieldWidth, lunchField.getPreferredSize().height));
        dinnerField.setPreferredSize(new Dimension(textFieldWidth, dinnerField.getPreferredSize().height));

        // Create weekday picker
        String[] weekdays = {
                "Sunday",
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday"
        };
        JComboBox<String> weekdayPicker = new JComboBox<>(weekdays);

        // Create buttons
        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        JButton updateButton = new JButton("Update");

        // Create table model
        mealPlanEntries = new ArrayList<>();

        // Create table using the custom table model
        mealPlanTable = new JTable(new MealPlanTableModel(mealPlanEntries));

        // Create scroll pane for the table
        JScrollPane tableScrollPane = new JScrollPane(mealPlanTable);

        // Create panels
        JPanel formPanel = new JPanel(new BorderLayout()); // Main panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel tablePanel = new JPanel(new BorderLayout());

        // Add components to the input panel using GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(breakfastLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        inputPanel.add(breakfastField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        inputPanel.add(lunchLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        inputPanel.add(lunchField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        inputPanel.add(dinnerLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        inputPanel.add(dinnerField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        inputPanel.add(weekdayLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        inputPanel.add(weekdayPicker, gbc);

        // Add buttons to the button panel
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);

        // Add table to the table panel
        tablePanel.add(new JScrollPane(mealPlanTable), BorderLayout.CENTER);

        // Add input panel and button panel to the form panel
        formPanel.add(inputPanel, BorderLayout.CENTER);
        formPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add form panel and table panel to the main panel using BorderLayout
        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(tablePanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String breakfast = breakfastField.getText();
                String lunch = lunchField.getText();
                String dinner = dinnerField.getText();
                String weekday = (String) weekdayPicker.getSelectedItem();

                // Create a new MealPlanEntry
                MealPlanEntry entry = new MealPlanEntry(weekday, breakfast, lunch, dinner);

                // Add the entry to the list
                mealPlanEntries.add(entry);

                // Refresh the table
                mealPlanTable.revalidate();
                mealPlanTable.repaint();

                // Clear the text fields
                breakfastField.setText("");
                lunchField.setText("");
                dinnerField.setText("");
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected row index from the table
                int selectedRowIndex = mealPlanTable.getSelectedRow();

                // Check if a row is selected
                if (selectedRowIndex != -1) {
                    // Remove the selected entry from the list
                    mealPlanEntries.remove(selectedRowIndex);

                    // Refresh the table
                    mealPlanTable.revalidate();
                    mealPlanTable.repaint();
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected row index from the table
                int selectedRowIndex = mealPlanTable.getSelectedRow();

                // Check if a row is selected
                if (selectedRowIndex != -1) {
                    // Get the updated values from the text fields
                    String breakfast = breakfastField.getText();
                    String lunch = lunchField.getText();
                    String dinner = dinnerField.getText();
                    String weekday = (String) weekdayPicker.getSelectedItem();

                    // Update the selected entry with the new values
                    MealPlanEntry entry = mealPlanEntries.get(selectedRowIndex);
                    entry.setWeekday(weekday);
                    entry.setBreakfast(breakfast);
                    entry.setLunch(lunch);
                    entry.setDinner(dinner);

                    // Refresh the table
                    mealPlanTable.revalidate();
                    mealPlanTable.repaint();
                }
            }
        });

        setPreferredSize(new Dimension(500, 400));
    }

    public void setController(CreateMealPlanController controller) {
        this.controller = controller;
    }

    private static class MealPlanTableModel extends AbstractTableModel {
        private final ArrayList<MealPlanEntry> entries;
        private final String[] columnNames = {
                "Weekday",
                "Breakfast",
                "Lunch",
                "Dinner"
        };

        public MealPlanTableModel(ArrayList<MealPlanEntry> entries) {
            this.entries = entries;
        }

        @Override
        public int getRowCount() {
            return entries.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            MealPlanEntry entry = entries.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return entry.getWeekday();
                case 1:
                    return entry.getBreakfast();
                case 2:
                    return entry.getLunch();
                case 3:
                    return entry.getDinner();
                default:
                    return null;
            }
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }
    }

    private static class MealPlanEntry {
        private String weekday;
        private String breakfast;
        private String lunch;
        private String dinner;

        public MealPlanEntry(String weekday, String breakfast, String lunch, String dinner) {
            this.weekday = weekday;
            this.breakfast = breakfast;
            this.lunch = lunch;
            this.dinner = dinner;
        }

        public String getWeekday() {
            return weekday;
        }

        public void setWeekday(String weekday) {
            this.weekday = weekday;
        }

        public String getBreakfast() {
            return breakfast;
        }

        public void setBreakfast(String breakfast) {
            this.breakfast = breakfast;
        }

        public String getLunch() {
            return lunch;
        }

        public void setLunch(String lunch) {
            this.lunch = lunch;
        }

        public String getDinner() {
            return dinner;
        }

        public void setDinner(String dinner) {
            this.dinner = dinner;
        }
    }
}