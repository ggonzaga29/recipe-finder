package com.recipeFinder.views;

import com.recipeFinder.components.Spacer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HomeView extends JFrame {
    public HomeView() {
        setTitle("Recipe Finder Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel for the sidebar
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setBackground(Color.decode("#3F4A59"));
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));

        // Create buttons for the sidebar
        JButton button1 = createSidebarButton("RECIPES");
        JButton button2 = createSidebarButton("BUTTON 2");
        JButton button3 = createSidebarButton("Button 3");
        JButton button4 = createSidebarButton("Profile");

        int buttonPadding = 10;
        button1.setBorder(new EmptyBorder(buttonPadding, buttonPadding, buttonPadding, buttonPadding));
        button2.setBorder(new EmptyBorder(buttonPadding, buttonPadding, buttonPadding, buttonPadding));
        button3.setBorder(new EmptyBorder(buttonPadding, buttonPadding, buttonPadding, buttonPadding));
        button4.setBorder(new EmptyBorder(buttonPadding, buttonPadding, buttonPadding, buttonPadding));
        // Create a panel for the sidebarTitle with background color
        JPanel sidebarTitlePanel = new JPanel();
        sidebarTitlePanel.setBackground(Color.decode("#926DDE"));
        sidebarTitlePanel.setLayout(new BoxLayout(sidebarTitlePanel, BoxLayout.Y_AXIS));
//        sidebarTitlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Label
        JLabel sidebarTitle = new JLabel("<html><body><br><h1>&nbsp;&nbsp;RecipeFinder</h1></body></html>");
        sidebarTitle.setForeground(Color.white);
//        sidebarTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebarTitlePanel.add(sidebarTitle);

        // Set preferred height of sidebarTitlePanel based on the height of the sidebarTitle label
        Dimension titlePanelSize = sidebarTitlePanel.getPreferredSize();
        titlePanelSize.height = 75;
        sidebarTitlePanel.setPreferredSize(titlePanelSize);

        sidebarPanel.add(sidebarTitlePanel);
        sidebarPanel.add(button1);
        sidebarPanel.add(button2);
        sidebarPanel.add(button3);
        sidebarPanel.add(button4);
        sidebarPanel.add(Box.createVerticalGlue());

        // Create a content panel for the main content
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);

        // Set the layout manager for the content panel
        contentPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        contentPanel.add(new JLabel("ss"));

        // Add sidebar panel and content panel to the main frame
        add(sidebarPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        // Set the maximum size of components in the sidebar to span the entire width
        Dimension maxSidebarSize = new Dimension(Integer.MAX_VALUE, button1.getPreferredSize().height);
        button1.setMaximumSize(maxSidebarSize);
        button2.setMaximumSize(maxSidebarSize);
        button3.setMaximumSize(maxSidebarSize);
        button4.setMaximumSize(maxSidebarSize);
        sidebarTitle.setMaximumSize(maxSidebarSize);

        Dimension sidebarSize = sidebarPanel.getPreferredSize();
        sidebarSize.width = 200;
        sidebarPanel.setPreferredSize(sidebarSize);

        // Set the size and make the frame visible
        setSize(1366, 768);
        setVisible(true);
    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(300, 50));
        button.setBackground(new Color(0, 0, 0, 0));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setFocusPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                button.setBackground(new Color(255, 255, 255, 50));
            }

            public void mousePressed(MouseEvent e) {

            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(255, 255, 255, 20));
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(0, 0, 0, 0));
            }
        });

        return button;
    }
}
