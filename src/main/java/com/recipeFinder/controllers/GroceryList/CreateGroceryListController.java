package com.recipeFinder.controllers;

import com.recipeFinder.enums.SQLResult;
import com.recipeFinder.models.GroceryListModel;
import com.recipeFinder.views.GroceryList.CreateGroceryListView;

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
