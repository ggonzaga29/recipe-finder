package com.recipeFinder.views;

import com.recipeFinder.controllers.GroceryListController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GroceryListView extends JPanel {
    GroceryListController controller;

    public GroceryListView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(0, 40, 0, 40));
        JPanel groceryListsPanel = new JPanel();
        groceryListsPanel.setLayout(new BoxLayout(groceryListsPanel, BoxLayout.Y_AXIS));

        add(new JLabel("<html><h1>Grocery Lists</h1></html>"));

        groceryListsPanel.add(createGroceryListPanel("Dinner", "05/08/2002"));
        groceryListsPanel.add(Box.createVerticalStrut(10));
        groceryListsPanel.add(createGroceryListPanel("Dinner", "05/08/2002"));
        groceryListsPanel.add(Box.createVerticalStrut(10));
        groceryListsPanel.add(createGroceryListPanel("Dinner", "05/08/2002"));
        add(groceryListsPanel);

        JButton createGroceryListButton = new JButton("Create Grocery List");
        createGroceryListButton.setAlignmentX(Component.LEFT_ALIGNMENT); // Set alignment to the left
//        add(createGroceryListButton);
    }

    protected JPanel createGroceryListPanel(String label, String date) {
        JPanel groceryList = new JPanel();
        JLabel groceryListLabel = new JLabel(label);
        JLabel groceryListDate = new JLabel(date);
        groceryList.setLayout(new BoxLayout(groceryList, BoxLayout.X_AXIS));
        groceryList.setPreferredSize(new Dimension(600, 60));
        groceryList.setBorder(new EmptyBorder(20, 10, 20, 10));
        groceryList.setBackground(Color.decode("#505254"));
        groceryList.add(groceryListLabel);
        groceryList.add(Box.createHorizontalGlue());
        groceryList.add(groceryListDate);

        groceryList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                groceryList.setBackground(Color.decode("#636567"));
                groceryList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                groceryList.setBackground(Color.decode("#505254"));
                groceryList.setCursor(Cursor.getDefaultCursor());
            }
        });

        return groceryList;
    }
}

