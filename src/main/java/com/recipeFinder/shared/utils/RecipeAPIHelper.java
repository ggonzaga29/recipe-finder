package com.recipeFinder.shared.utils;

import okhttp3.*;
import org.json.*;

import java.io.IOException;
import java.util.ArrayList;

public class RecipeAPIHelper {

    /**
     * Obviously in a production environment, this would be stored somewhere else
     */
    public static final String RECIPE_URL = "https://api.edamam.com/api/recipes/v2?type=public&app_id=8c9b3254&app_key=49dde40fd0e2230db8b0f3d9133f7465&cuisineType=Asian&imageSize=THUMBNAIL&random=true";

    public static final MediaType JSON = MediaType.get("application/json");

    /**
     * Retrieves recipes from the Edamam Recipe API.
     *
     * @param limit The maximum number of recipes to retrieve.
     * @return An ArrayList of Recipe objects representing the retrieved recipes.
     * @throws RuntimeException If an IO error occurs while making the API request.
     * @see Recipe
     */
    public static ArrayList<Recipe> getRecipes(int limit) {
        OkHttpClient client = new OkHttpClient();

        // Create a request object with the specified recipe API URL
        Request request = new Request.Builder()
                .url(RECIPE_URL)
                .build();

        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();

        try {
            // Send the HTTP request and obtain the response
            Response response = client.newCall(request).execute();

            if (response.body() != null) {
                // Parse the response body as JSON
                JSONObject responseJson = new JSONObject(response.body().string());
                JSONArray hits = new JSONArray(responseJson.getJSONArray("hits"));
                JSONArray recipes = new JSONArray();

                // Extract the recipe objects from the response
                for (int i = 0; i < hits.length(); i++) {
                    JSONObject hit = hits.getJSONObject(i);
                    recipes.put(hit.getJSONObject("recipe"));
                }

                /**
                 * Convert the recipe JSON objects into Recipe objects
                 * @see Recipe
                 */
                for (int i = 0; i < recipes.length(); i++) {
                    JSONObject recipe = recipes.getJSONObject(i);

                    // Extract the relevant fields from the recipe JSON object
                    String uri = recipe.getString("uri");
                    String label = recipe.getString("label");
                    String image = recipe.getString("image");
                    String imageThumbUrl = recipe.getString("image");
                    int calories = recipe.getInt("calories");
                    int totalWeight = recipe.getInt("totalWeight");
                    float totalTime = recipe.getFloat("totalTime");

                    // Create a new Recipe object and add it to the list
                    recipeList.add(new Recipe(uri, label, image, imageThumbUrl, calories, totalWeight, totalTime));
                }
            }
        } catch (IOException e) {
            // Throw a RuntimeException if an IO error occurs
            throw new RuntimeException(e);
        }

        // Return the list of retrieved recipes
        return recipeList;
    }

    public static Recipe getRecipe() {
        return null;
    }

    public static void main(String[] args) {
        ArrayList<Recipe> recipes = RecipeAPIHelper.getRecipes(20);

        for (Recipe recipe : recipes) {
            System.out.println(recipe);
        }
    }
}
