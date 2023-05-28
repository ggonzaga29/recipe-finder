package com.flavorfinder.features.UserRecipes;

import com.flavorfinder.features.Recipe.views.SingleRecipeViewWindow;

public class UserRecipesController {
    UserRecipesView view;

    public UserRecipesController(UserRecipesView view) {
        this.view = view;
        view.setController(this);
    }


    public void showRecipeDetails(UserRecipeModel recipe) {
        SingleUserRecipeViewWindow window = new SingleUserRecipeViewWindow(recipe);
        SingleUserRecipeController controller = new SingleUserRecipeController(window);
    }
}
