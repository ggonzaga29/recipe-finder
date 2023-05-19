package com.recipeFinder.controllers;

import com.recipeFinder.models.CustomRecipeModel;
import com.recipeFinder.views.CreateRecipeView;

public class CreateRecipeController {
    private CreateRecipeView view;
    public CreateRecipeController(CreateRecipeView view) {
        this.view = view;
        view.setController(this);
    }

    public void handleCreateRecipe(String label, Double calories, Double weight, int yield, String instructions) {
        CustomRecipeModel customRecipe = new CustomRecipeModel(label, calories, weight, yield, instructions);
        customRecipe.save();
    }
}
