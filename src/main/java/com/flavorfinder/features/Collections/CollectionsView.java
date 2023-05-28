package com.flavorfinder.features.Collections;

import com.flavorfinder.components.FormControl;
import com.flavorfinder.features.View;

import javax.swing.*;
import java.awt.*;

public class CollectionsView extends View {

    CollectionController controller;

    public CollectionsView() {
        initComponents();
    }

    public void setController(CollectionController controller) {
        this.controller = controller;
    }

    public void initComponents() {
        setLayout(new FlowLayout(FlowLayout.LEADING, 15, 15));

        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new GridBagLayout());
        JLabel title = new JLabel("Collections");
        title.setHorizontalAlignment(JLabel.LEFT);
        title.setFont(title.getFont().deriveFont(24f));

        FormControl collectionTitle = new FormControl("Collection Title");
        FormControl collectionDescription = new FormControl("Collection Description");
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            controller.handleCreateCollection(collectionTitle.getText(), collectionDescription.getText());
        });

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0;
        mainContentPanel.add(title, c);

        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        mainContentPanel.add(collectionTitle, c);

        c.gridy = 2;
        mainContentPanel.add(collectionDescription, c);

        c.gridy = 3;
        mainContentPanel.add(submitButton, c);

        JPanel collectionList = new JPanel();
        collectionList.setLayout(new BoxLayout(collectionList, BoxLayout.Y_AXIS));
        for (CollectionModel collection : CollectionModel.getAll()) {
            JPanel collectionPanel = new JPanel();
            collectionPanel.setLayout((new FlowLayout(FlowLayout.LEADING, 15, 15)));
            collectionPanel.setPreferredSize(new Dimension(600, 100));
            JLabel collectionName = new JLabel("Collection Title: " + collection.getName());
            JLabel collectionDescriptionLabel = new JLabel("Collection Text: " + collection.getText());
            collectionPanel.add(collectionName);
            collectionPanel.add(collectionDescriptionLabel);
            collectionList.add(collectionPanel);
        }

        JScrollPane scrollPane = new JScrollPane(collectionList);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        c.gridy = 4;
        c.weighty = 1;
        mainContentPanel.add(scrollPane, c);

        add(mainContentPanel);
    }

}



