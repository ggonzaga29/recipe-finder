package com.flavorfinder.features.UserRecipes;

import com.flavorfinder.shared.utils.DBHandler;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

public class UserRecipeModel {

    private int custom_recipe_id;
    private String custom_recipe_label;

    private double custom_recipe_calories;
    private double custom_recipe_weight;
    private int custom_recipe_yield;
    private String custom_recipe_instructions;
    private String[] custom_recipe_ingredients;
    private int isFavorite;

    public UserRecipeModel(int custom_recipe_id, String custom_recipe_label, double custom_recipe_calories, double custom_recipe_weight, int custom_recipe_yield, String custom_recipe_instructions, String[] custom_recipe_ingredients, int isFavorite) {
        this.custom_recipe_id = custom_recipe_id;
        this.custom_recipe_label = custom_recipe_label;
        this.custom_recipe_calories = custom_recipe_calories;
        this.custom_recipe_weight = custom_recipe_weight;
        this.custom_recipe_yield = custom_recipe_yield;
        this.custom_recipe_instructions = custom_recipe_instructions;
        this.custom_recipe_ingredients = custom_recipe_ingredients;
        this.isFavorite = isFavorite;
    }

    public int getCustom_recipe_id() {
        return custom_recipe_id;
    }

    public void setCustom_recipe_id(int custom_recipe_id) {
        this.custom_recipe_id = custom_recipe_id;
    }

    public String getCustom_recipe_label() {
        return custom_recipe_label;
    }

    public void setCustom_recipe_label(String custom_recipe_label) {
        this.custom_recipe_label = custom_recipe_label;
    }

    public double getCustom_recipe_calories() {
        return custom_recipe_calories;
    }

    public void setCustom_recipe_calories(double custom_recipe_calories) {
        this.custom_recipe_calories = custom_recipe_calories;
    }

    public double getCustom_recipe_weight() {
        return custom_recipe_weight;
    }

    public void setCustom_recipe_weight(double custom_recipe_weight) {
        this.custom_recipe_weight = custom_recipe_weight;
    }

    public int getCustom_recipe_yield() {
        return custom_recipe_yield;
    }

    public void setCustom_recipe_yield(int custom_recipe_yield) {
        this.custom_recipe_yield = custom_recipe_yield;
    }

    public String getCustom_recipe_instructions() {
        return custom_recipe_instructions;
    }

    public void setCustom_recipe_instructions(String custom_recipe_instructions) {
        this.custom_recipe_instructions = custom_recipe_instructions;
    }

    public String[] getCustom_recipe_ingredients() {
        return custom_recipe_ingredients;
    }

    public void setCustom_recipe_ingredients(String[] custom_recipe_ingredients) {
        this.custom_recipe_ingredients = custom_recipe_ingredients;
    }

    public int getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(int isFavorite) {
        this.isFavorite = isFavorite;
    }

    @Override
    public String toString() {
        return "UserRecipeModel{" +
                "custom_recipe_id=" + custom_recipe_id +
                ", custom_recipe_label='" + custom_recipe_label + '\'' +
                ", custom_recipe_calories=" + custom_recipe_calories +
                ", custom_recipe_weight=" + custom_recipe_weight +
                ", custom_recipe_yield=" + custom_recipe_yield +
                ", custom_recipe_instructions='" + custom_recipe_instructions + '\'' +
                ", custom_recipe_ingredients=" + Arrays.toString(custom_recipe_ingredients) +
                ", isFavorite=" + isFavorite +
                '}';
    }

    public static ArrayList<UserRecipeModel> getAll() {
        try (DBHandler db = new DBHandler()) {
            db.connect();

            ArrayList<UserRecipeModel> userRecipes = new ArrayList<>();

            String query = "SELECT * FROM custom_recipes";
            ResultSet resultSet = db.executeQuery(query);

            while (resultSet.next()) {
                int custom_recipe_id = resultSet.getInt("custom_recipe_id");
                String custom_recipe_label = resultSet.getString("custom_recipe_label");
                double custom_recipe_calories = resultSet.getDouble("custom_recipe_calories");
                double custom_recipe_weight = resultSet.getDouble("custom_recipe_weight");
                int custom_recipe_yield = resultSet.getInt("custom_recipe_yield");
                String custom_recipe_instructions = resultSet.getString("custom_recipe_instructions");
                String[] custom_recipe_ingredients = resultSet.getString("custom_recipe_ingredients").split(",");
                int isFavorite = resultSet.getInt("isFavorite");

                UserRecipeModel userRecipe = new UserRecipeModel(custom_recipe_id, custom_recipe_label, custom_recipe_calories, custom_recipe_weight, custom_recipe_yield, custom_recipe_instructions, custom_recipe_ingredients, isFavorite);
                userRecipes.add(userRecipe);
            }

            return userRecipes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}