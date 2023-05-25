package com.recipeFinder.features.GroceryList.controllers;

import com.recipeFinder.features.Controller;
import com.recipeFinder.features.GroceryList.views.CreateGroceryListView;
import com.recipeFinder.shared.enums.SQLResult;
import com.recipeFinder.models.GroceryListModel;

public class CreateGroceryListController extends Controller {
    CreateGroceryListView view;
    GroceryListModel model;

    public CreateGroceryListController(CreateGroceryListView view) {
        super();

        this.view = view;
        view.setController(this);
    }

    public void handleSubmit(String name, String date) {
        model = new GroceryListModel(name, date);
        SQLResult result = model.save();
        if(result == SQLResult.SUCCESS) {
            emit("submit");
        }
    }
}
