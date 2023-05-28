package com.flavorfinder.features.Reviews;

import com.flavorfinder.features.View;

import javax.swing.*;
import java.awt.*;

public class ViewRecipeReviewsWindow extends JFrame {
    public ViewRecipeReviewsWindow() {
        super("View Recipe Reviews");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 600);
        setBackground(Color.decode("#DDDDDD"));
        setLocationRelativeTo(null);
        setVisible(true);

        View viewPanel = new View(new BorderLayout());
        viewPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        JLabel title = new JLabel("View Recipe Reviews");
        title.setFont(title.getFont().deriveFont(24.0f));
        headerPanel.add(title, BorderLayout.NORTH);

        JPanel reviewPanel = new JPanel();
        reviewPanel.setLayout(new BoxLayout(reviewPanel, BoxLayout.Y_AXIS));
        reviewPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        for(int i = 0; i < 10; i++) {
            JPanel review = new JPanel(new BorderLayout());
            review.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
            JLabel reviewText = new JLabel("This is a review");
            review.add(reviewText, BorderLayout.NORTH);
            JLabel reviewRating = new JLabel("Rating: 5");
            review.add(reviewRating, BorderLayout.SOUTH);
            reviewPanel.add(review);
        }

        add(viewPanel);
    }

    public static void main(String[] args) {
    }
}
