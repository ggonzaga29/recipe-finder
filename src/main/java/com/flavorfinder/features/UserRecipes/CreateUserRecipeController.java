package com.flavorfinder.features.UserRecipes;

import com.flavorfinder.features.Controller;
import com.flavorfinder.models.UserRecipeModel;

public class CreateUserRecipeController extends Controller {
    private CreateUserRecipeView view;
    public CreateUserRecipeController(CreateUserRecipeView view) {
        this.view = view;
        view.setController(this);
    }

    public void handleCreateRecipe(String label, Double calories, Double weight, int yield, String instructions, String ingredients) {
        UserRecipeModel customRecipe = new UserRecipeModel(label, calories, weight, yield, instructions, ingredients);
        customRecipe.save();
    }
}
