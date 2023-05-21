package com.recipeFinder.controllers;

import com.recipeFinder.enums.SQLResult;
import com.recipeFinder.models.GroceryListModel;
import com.recipeFinder.views.CreateGroceryListView;

public class CreateGroceryListController {
    CreateGroceryListView view;

    public CreateGroceryListController(CreateGroceryListView view) {
        this.view = view;
        view.setController(this);
    }

    public void handleSubmit(String name, String date) {
        GroceryListModel groceryList = new GroceryListModel(name, date);
        SQLResult result = groceryList.save();
        if(result == SQLResult.SUCCESS) {
            // update view
        }
    }
}
