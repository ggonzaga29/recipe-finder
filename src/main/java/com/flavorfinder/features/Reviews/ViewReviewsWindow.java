package com.flavorfinder.features.Reviews;

import com.flavorfinder.features.View;
import com.flavorfinder.models.RecipeModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewReviewsWindow extends JFrame {
    private String type;
    private int id;

    public ViewReviewsWindow(String type, int id) {
        super("View Recipe Reviews");
        this.type = type;
        this.id = id;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null);
        setVisible(true);

        initComponents();
    }

    public void initComponents() {
        View viewPanel = new View(new BorderLayout());
        viewPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        JLabel title = new JLabel("View Recipe Reviews");
        title.setFont(title.getFont().deriveFont(24.0f));
        headerPanel.add(title, BorderLayout.NORTH);

        JPanel reviewPanel = new JPanel();
        reviewPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        reviewPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        ArrayList<ReviewModel> reviews = ReviewModel.getAllByTypeAndId(type, id);
        System.out.println(reviews);

        JScrollPane scrollPane = new JScrollPane(reviewPanel);
        scrollPane.setPreferredSize(new Dimension(getWidth(), 500));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        for(ReviewModel review : reviews) {
            JPanel reviewItem = new JPanel(new BorderLayout());
            reviewItem.setPreferredSize(new Dimension(400, 70));
            reviewItem.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

            JLabel reviewText = new JLabel(review.getReview_text());
            reviewText.setFont(reviewText.getFont().deriveFont(16.0f));
            reviewItem.add(reviewText, BorderLayout.NORTH);

            JPanel reviewInfo = new JPanel(new BorderLayout());
            reviewInfo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

            JLabel reviewRating = new JLabel("Rating: " + review.getReview_rating());
            reviewRating.setFont(reviewRating.getFont().deriveFont(16.0f));
            reviewInfo.add(reviewRating, BorderLayout.WEST);

//            JLabel reviewUser = new JLabel("User: " + review.getUser_name());
//            reviewUser.setFont(reviewUser.getFont().deriveFont(16.0f));
//            reviewInfo.add(reviewUser, BorderLayout.EAST);

            reviewItem.add(reviewInfo, BorderLayout.SOUTH);

            reviewPanel.add(reviewItem);
        }

        viewPanel.add(headerPanel, BorderLayout.NORTH);
        viewPanel.add(reviewPanel, BorderLayout.CENTER);

        add(viewPanel);
    }

}
