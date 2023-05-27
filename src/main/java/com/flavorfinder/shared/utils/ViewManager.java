package com.flavorfinder.shared.utils;

import com.flavorfinder.features.View;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ViewManager {
    private final Map<String, View> views;
    private String activeView = "";
    CardLayout cardLayout;
    Container container;

    public ViewManager(Container container) {
        this.views = new HashMap<>();
        this.cardLayout = (CardLayout) container.getLayout();
        this.container = container;
    }

    public void addView(String name, View view) {
        views.put(name, view);
    }

    public void closeViewsExcept(String selectedView) {
        for (Map.Entry<String, View> entry : views.entrySet()) {
            String viewName = entry.getKey();
            View view = entry.getValue();

            if (!viewName.equals(selectedView)) {
                view.dispose();
            }
        }
    }

    public void showView(String name) {
        if (activeView.equals(name)) {
            return;
        }

        closeViewsExcept(name);
        cardLayout.show(container, name);
        View view = views.get(name);
        view.initComponents();
        activeView = name;
    }
}
