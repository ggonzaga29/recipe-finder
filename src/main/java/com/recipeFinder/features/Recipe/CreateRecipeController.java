package com.recipeFinder.features.Recipe;

import com.recipeFinder.models.CustomRecipeModel;
import com.recipeFinder.features.Recipe.CreateRecipeView;

public class CreateRecipeController {
    private CreateRecipeView view;
    public CreateRecipeController(CreateRecipeView view) {
        this.view = view;
        view.setController(this);
    }

    public void handleCreateRecipe(String label, Double calories, Double weight, int yield, String instructions, String ingredients) {
        CustomRecipeModel customRecipe = new CustomRecipeModel(label, calories, weight, yield, instructions, ingredients);
        customRecipe.save();
    }
}
