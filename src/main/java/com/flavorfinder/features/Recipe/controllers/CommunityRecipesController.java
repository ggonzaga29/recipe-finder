package com.flavorfinder.features.Recipe.controllers;

import com.flavorfinder.features.Controller;
import com.flavorfinder.features.Recipe.views.CommunityRecipesView;
import com.flavorfinder.models.RecipeModel;

import java.util.ArrayList;

public class CommunityRecipesController extends Controller {

    private final CommunityRecipesView view;

    public CommunityRecipesController(CommunityRecipesView view) {
        this.view = view;
        view.setController(this);
        view.updateView(RecipeModel.paginatedSearch("", 10, 0));
    }

    public void handleSearch(String query) {
        view.updateView(RecipeModel.paginatedSearch(query, 20, 0));
    }

    public ArrayList<RecipeModel> getRecipes() {
        return RecipeModel.paginatedSearch("", 10, 0);
    }

    public void handlePagination(int page) {
        System.out.println("Page: " + page);
    }
}
