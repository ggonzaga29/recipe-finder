package com.recipeFinder.features.GroceryList.controllers;

import com.recipeFinder.features.Controller;
import com.recipeFinder.features.GroceryList.views.CreateGroceryListView;
import com.recipeFinder.shared.enums.SQLResult;
import com.recipeFinder.features.GroceryList.models.GroceryListModel;
import com.recipeFinder.shared.exceptions.RecordAlreadyExistsException;

public class CreateGroceryListController extends Controller {
    CreateGroceryListView view;
    GroceryListModel model;

    public CreateGroceryListController(CreateGroceryListView view) {
        super();

        this.view = view;
        view.setController(this);
    }

    public void handleSubmit(String name, String date) {
        if(name.isEmpty() || date.isEmpty()) {
            view.showMessage("Please enter a name for your grocery list.", "error");
            return;
        }

        model = new GroceryListModel(name, date);
        try {
            SQLResult result = model.save();
            if(result == SQLResult.SUCCESS) {
                view.clearInputs();
                view.updateView();
            }
        } catch (RecordAlreadyExistsException e) {
            view.showMessage("A grocery list with that name already exists.", "error");
        }
    }

    public void handleDelete(int _id) {
        if(_id == -1) {
            view.showMessage("Please select a grocery list before deleting");
            return;
        }

        GroceryListModel groceryListModel = GroceryListModel.findByID(_id);
        SQLResult result = groceryListModel.delete();

        if(result == SQLResult.SUCCESS) {
            view.setActiveID(-1);
            view.updateView();
        }
    }

    public void handleEdit(int _id, String name, String date) {
        if(_id == -1) {
            view.showMessage("Please select a grocery list before editing");
            return;
        }

        GroceryListModel groceryListModel = GroceryListModel.findByID(_id);
        groceryListModel.setName(name);
        groceryListModel.setDate(date);
        SQLResult result = groceryListModel.update();

        if(result == SQLResult.SUCCESS) {
            view.setActiveID(-1);
            view.updateView();
        }
    }

    public void handleGetGroceryList(int id) {
        GroceryListModel groceryListModel = GroceryListModel.findByID(id);
        if(groceryListModel != null) {
            view.setGroceryListModel(groceryListModel);
        }
    }
}
