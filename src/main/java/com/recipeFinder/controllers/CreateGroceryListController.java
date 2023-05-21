package com.recipeFinder.controllers;

import com.recipeFinder.models.GroceryListModel;
import com.recipeFinder.views.CreateGroceryListView;

public class CreateGroceryListController {
    CreateGroceryListView view;

    public CreateGroceryListController(CreateGroceryListView view) {
        this.view = view;
        view.setController(this);
    }

    public void handleSubmit(String name, String date) {

    }
}
