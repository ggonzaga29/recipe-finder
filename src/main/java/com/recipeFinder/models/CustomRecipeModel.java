package com.recipeFinder.models;

import com.recipeFinder.utils.DBHandler;

import java.sql.SQLException;
import java.util.Arrays;

public class CustomRecipeModel {
    private String label;
    private Double calories;
    private Double weight;
    private int yield;
    private String instructions;
    private String ingredients;


    public CustomRecipeModel(String label, Double calories, Double weight, int yield, String instructions, String ingredients) {
        this.label = label;
        this.calories = calories;
        this.weight = weight;
        this.yield = yield;
        this.instructions = instructions;
        String[] ingredientsArray = ingredients.trim().split("[\n,]+");
        this.ingredients = Arrays.toString(ingredientsArray);
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public int getYield() {
        return yield;
    }

    public void setYield(int yield) {
        this.yield = yield;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "CustomRecipeModel{" +
                "label='" + label + '\'' +
                ", calories=" + calories +
                ", weight=" + weight +
                ", yield=" + yield +
                ", instructions='" + instructions + '\'' +
                '}';
    }

    public void save() {
        try {
            DBHandler dbHandler = new DBHandler();
            dbHandler.connect();
            String sql = String.format("INSERT INTO custom_recipes (custom_recipe_label, custom_recipe_calories, custom_recipe_weight, custom_recipe_yield, custom_recipe_instructions, custom_recipe_ingredients) VALUES('%s', %.2f, %.2f, %d, '%s', '%s')", getLabel(), getCalories(), getWeight(), getYield(), getInstructions(), getIngredients());
            dbHandler.executeUpdate(sql);
            dbHandler.disconnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
