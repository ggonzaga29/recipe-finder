package com.flavorfinder.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.flavorfinder.shared.utils.DBHandler;
import org.sqlite.core.DB;

public class RecipeModel {
    private int recipe_id;
    private String label;
    private double calories;
    private int total_time;
    private double total_weight;
    private int yield;
    private String image_url;
    private String local_image_url;
    private String source;
    private String source_url;
    private String recipe_url;
    private int isFavorite;
    private ArrayList<String> ingredientLines;

    public RecipeModel(int recipe_id, String label, double calories, int total_time, double total_weight, int yield,
                       String image_url, String local_image_url, String source, String source_url, String recipe_url, int isFavorite) {
        this.recipe_id = recipe_id;
        this.label = label;
        this.calories = calories;
        this.total_time = total_time;
        this.total_weight = total_weight;
        this.yield = yield;
        this.image_url = image_url;
        this.local_image_url = local_image_url;
        this.source = source;
        this.source_url = source_url;
        this.recipe_url = recipe_url;
        this.isFavorite = isFavorite;
        this.ingredientLines = new ArrayList<>();
    }

    public int getRecipeId() {
        return recipe_id;
    }

    public String getLabel() {
        return label;
    }

    public double getCalories() {
        return calories;
    }

    public int getTotalTime() {
        return total_time;
    }

    public double getTotalWeight() {
        return total_weight;
    }

    public int getYield() {
        return yield;
    }

    public String getImageUrl() {
        return image_url;
    }

    public String getLocalImageUrl() {
        return local_image_url;
    }

    public String getSource() {
        return source;
    }

    public String getSourceUrl() {
        return source_url;
    }

    public String getRecipeUrl() {
        return recipe_url;
    }

    public int getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(int isFavorite) {
        this.isFavorite = isFavorite;
    }

    public ArrayList<String> getIngredientLines() {
        return ingredientLines;
    }

    public void addIngredientLine(String ingredientLine) {
        this.ingredientLines.add(ingredientLine);
    }

    public void setIngredientLines(ArrayList<String> ingredientLines) {
        this.ingredientLines = ingredientLines;
    }

    public static ArrayList<RecipeModel> paginatedSearch(String query, int limit, int offset) {

        try (DBHandler db = new DBHandler()) {
            db.connect();

            String sql = "SELECT * FROM recipes WHERE label LIKE '%" + query + "%' LIMIT " + limit + " OFFSET " + offset;

            ResultSet resultSet = db.executeQuery(sql);

            ArrayList<RecipeModel> recipes = new ArrayList<RecipeModel>();

            while (resultSet.next()) {
                int recipeId = resultSet.getInt("recipe_id");
                String label = resultSet.getString("label");
                double calories = resultSet.getDouble("calories");
                int totalTime = resultSet.getInt("total_time");
                double totalWeight = resultSet.getDouble("total_weight");
                int yield = resultSet.getInt("yield");
                String imageUrl = resultSet.getString("image_url");
                String localImageUrl = resultSet.getString("local_image_url");
                String source = resultSet.getString("source");
                String sourceUrl = resultSet.getString("source_url");
                String recipeUrl = resultSet.getString("recipe_url");
                int isFavorite = resultSet.getInt("is_favorite");

                RecipeModel recipe = new RecipeModel(recipeId, label, calories, totalTime, totalWeight, yield, imageUrl, localImageUrl, source, sourceUrl, recipeUrl, isFavorite);

                String sql2 = "SELECT * FROM ingredientLines WHERE recipe_id=" + recipeId + " ORDER BY ingredient_line_order";
                ResultSet resultSet2 = db.executeQuery(sql2);

                while (resultSet2.next()) {
                    recipe.addIngredientLine(resultSet2.getString("ingredient_line_text"));
                }

                recipes.add(recipe);
            }

            return recipes;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<RecipeModel> getAll(int limit, int offset) {
        try {
            DBHandler db = new DBHandler();
            db.connect();

            String query = "SELECT * FROM recipes LIMIT " + limit + " OFFSET " + offset;
            ResultSet resultSet = db.executeQuery(query);

            ArrayList<RecipeModel> recipes = new ArrayList<RecipeModel>();

            while (resultSet.next()) {
                int recipeId = resultSet.getInt("recipe_id");
                String label = resultSet.getString("label");
                double calories = resultSet.getDouble("calories");
                int totalTime = resultSet.getInt("total_time");
                double totalWeight = resultSet.getDouble("total_weight");
                int yield = resultSet.getInt("yield");
                String imageUrl = resultSet.getString("image_url");
                String localImageUrl = resultSet.getString("local_image_url");
                String source = resultSet.getString("source");
                String sourceUrl = resultSet.getString("source_url");
                String recipeUrl = resultSet.getString("recipe_url");
                int isFavorite = resultSet.getInt("is_favorite");

                RecipeModel recipe = new RecipeModel(recipeId, label, calories, totalTime, totalWeight, yield, imageUrl, localImageUrl, source, sourceUrl, recipeUrl, isFavorite);

                String query2 = "SELECT * FROM ingredientLines WHERE recipe_id=" + recipeId + " ORDER BY ingredient_line_order";
                ResultSet resultSet2 = db.executeQuery(query2);

                while (resultSet2.next()) {
                    recipe.addIngredientLine(resultSet2.getString("ingredient_line_text"));
                }

                recipes.add(recipe);
            }

            return recipes;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<RecipeModel> getAllFavorites() {
        ArrayList<RecipeModel> recipes = getAll(100, 0);
        ArrayList<RecipeModel> favorites = new ArrayList<>();

        if (recipes != null) {
            for (RecipeModel recipe : recipes) {
                if (recipe.getIsFavorite() == 1) {
                    favorites.add(recipe);
                }
            }
        }

        return favorites;
    }

    public static ArrayList<RecipeModel> getAll() {
        return getAll(20, 0);
    }

    public static ArrayList<RecipeModel> getAll(int limit) {
        return getAll(limit, 0);
    }

    @Override
    public String toString() {
        return "RecipeModel{" +
                "recipe_id=" + recipe_id +
                ", label='" + label + '\'' +
                ", calories=" + calories +
                ", total_time=" + total_time +
                ", total_weight=" + total_weight +
                ", yield=" + yield +
                ", image_url='" + image_url + '\'' +
                ", local_image_url='" + local_image_url + '\'' +
                ", source='" + source + '\'' +
                ", source_url='" + source_url + '\'' +
                ", recipe_url='" + recipe_url + '\'' +
                ", ingredientLines=" + ingredientLines +
                '}';
    }

    public static void main(String[] args) {
        ArrayList<RecipeModel> recipes = RecipeModel.getAll(40);

        for (RecipeModel recipe : recipes) {
            System.out.println(recipe);
        }
    }
}