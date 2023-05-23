package com.recipeFinder.features.GroceryList;

import com.recipeFinder.models.GroceryListModel;
import com.recipeFinder.features.GroceryList.AllGroceryListView;

import java.util.ArrayList;

public class AllGroceryListController {
    AllGroceryListView view;

    public AllGroceryListController(AllGroceryListView view) {
        this.view = view;
        viewGroceryListItems();
    }

    public void createGroceryList() {

    }

    public void deleteGroceryList() {

    }

    public void editGroceryList() {

    }

    public void viewGroceryList() {

    }

    public void viewGroceryListItems() {
        ArrayList<GroceryListModel> groceryListModels = GroceryListModel.getAll();
        view.updateView(groceryListModels);
    }
}
