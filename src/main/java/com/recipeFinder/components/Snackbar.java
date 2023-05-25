package com.recipeFinder.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Snackbar extends JPanel {
    private JLabel messageLabel;
    private static final int SNACKBAR_HEIGHT = 40;
    private static final int ANIMATION_DURATION = 2000; // Snackbar display duration in milliseconds

    public Snackbar() {
        setLayout(new BorderLayout());
        setOpaque(true);
        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(getWidth(), SNACKBAR_HEIGHT));
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        messageLabel = new JLabel();
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setFont(messageLabel.getFont().deriveFont(Font.BOLD));

        add(messageLabel, BorderLayout.CENTER);
    }

    public void showSnackbar(String message) {
        messageLabel.setText(message);

        JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (mainFrame != null) {
            mainFrame.add(this, BorderLayout.PAGE_START);
            mainFrame.revalidate();
            mainFrame.repaint();

            Timer timer = new Timer(ANIMATION_DURATION, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.remove(Snackbar.this);
                    mainFrame.revalidate();
                    mainFrame.repaint();
                }
            });

            timer.setRepeats(false);
            timer.start();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snackbar Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton showSnackbarButton = new JButton("Show Snackbar");
        showSnackbarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Snackbar snackbar = new Snackbar();
                snackbar.showSnackbar("Snackbar message");
            }
        });

        mainPanel.add(showSnackbarButton);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}