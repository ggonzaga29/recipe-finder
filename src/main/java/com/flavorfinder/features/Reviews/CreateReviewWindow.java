package com.flavorfinder.features.Reviews;

import com.flavorfinder.components.FormControl;
import com.flavorfinder.features.View;
import com.flavorfinder.shared.CurrentUser;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class CreateReviewWindow extends JFrame {
    public CreateReviewWindow(String type, int id) {
        super("Create Review");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        View viewPanel = new View();
        viewPanel.setLayout(new GridBagLayout());
        viewPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.NORTHWEST;

        JLabel title = new JLabel("Create Review");
        title.setFont(title.getFont().deriveFont(24.0f));
        constraints.insets = new Insets(0, 0, 20, 0);
        viewPanel.add(title, constraints);

        constraints.insets = new Insets(0, 0, 10, 0);
        JTextArea review = new JTextArea();
        review.setLineWrap(true);
        review.setWrapStyleWord(true);
        review.setBorder(BorderFactory.createTitledBorder("Review"));
        constraints.gridy = 1;
        viewPanel.add(review, constraints);

        FormControl recipeRating = new FormControl("Recipe Rating");
        recipeRating.lockInt();
        constraints.gridy = 2;
        viewPanel.add(recipeRating, constraints);

        JButton submitButton = new JButton("Submit");
        constraints.gridy = 3;
        viewPanel.add(submitButton, constraints);

        submitButton.addActionListener(e -> {
            ReviewModel reviewModel = new ReviewModel();
            reviewModel.setReview_text(review.getText());
            reviewModel.setReview_rating(recipeRating.getInt());
            reviewModel.setReview_type(type);

            CurrentUser currentUser = CurrentUser.getInstance();
            reviewModel.setUser_id(currentUser.getId());

            if(Objects.equals(type, "community")) {
                reviewModel.setCommunity_recipe_id(id);
            } else if(Objects.equals(type, "user")) {
                reviewModel.setUser_recipe_id(id);
            }

            reviewModel.save();
            dispose();
        });

        add(viewPanel);
        setVisible(true);
    }
}
