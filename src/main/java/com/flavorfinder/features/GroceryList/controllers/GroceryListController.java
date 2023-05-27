package com.flavorfinder.features.GroceryList.controllers;

import com.flavorfinder.features.Controller;
import com.flavorfinder.features.GroceryList.views.GroceryListView;
import com.flavorfinder.shared.enums.SQLResult;
import com.flavorfinder.features.GroceryList.models.GroceryListModel;
import com.flavorfinder.shared.exceptions.RecordAlreadyExistsException;

public class GroceryListController extends Controller {
    GroceryListView view;
    GroceryListModel model;

    public GroceryListController(GroceryListView view) {
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
