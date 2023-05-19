package com.recipeFinder.utils;

import okhttp3.*;
import org.json.*;

import java.io.IOException;
import java.util.ArrayList;

public class RecipeAPIHelper {
    public static final String RECIPE_URL = "https://api.edamam.com/api/recipes/v2?type=public&app_id=8c9b3254&app_key=49dde40fd0e2230db8b0f3d9133f7465&cuisineType=Asian&imageSize=THUMBNAIL&random=true";

    public static final MediaType JSON = MediaType.get("application/json");

    public static ArrayList<Recipe> getRecipes(int limit) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(RECIPE_URL)
                .build();

        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();

        try {
            Response response = client.newCall(request).execute();

            if(response.body() != null) {
                JSONObject responseJson = new JSONObject(response.body().string());
                JSONArray hits = new JSONArray(responseJson.getJSONArray("hits"));
                JSONArray recipes = new JSONArray();

                for (int i = 0; i < hits.length(); i++) {
                    JSONObject hit = hits.getJSONObject(i);
                    recipes.put(hit.getJSONObject("recipe"));
                }

                for (int i = 0; i < recipes.length(); i++) {
                    JSONObject recipe = recipes.getJSONObject(i);

                    String uri = recipe.getString("uri");
                    String label = recipe.getString("label");
                    String image = recipe.getString("image");
                    String imageThumbUrl = recipe.getString("image");
                    int calories = recipe.getInt("calories");
                    int totalWeight = recipe.getInt("totalWeight");
                    float totalTime = recipe.getFloat("totalTime");

                    recipeList.add(new Recipe(uri, label, image, imageThumbUrl, calories, totalWeight, totalTime));
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return recipeList;
    }

    public static Recipe getRecipe() {
        return null;
    }

    public static void main(String[] args) {
        ArrayList<Recipe> recipes = RecipeAPIHelper.getRecipes(20);

        for(Recipe recipe : recipes) {
            System.out.println(recipe);
        }
    }
}
