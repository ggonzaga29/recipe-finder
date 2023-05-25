package com.recipeFinder.features.MealPlan.views;

import com.recipeFinder.features.MealPlan.controllers.CreateMealPlanController;

import javax.swing.*;

public class CreateMealPlanView extends JPanel {
    CreateMealPlanController controller;

    public CreateMealPlanView() {

    }


    public void setController(CreateMealPlanController controller) {
        this.controller = controller;
    }
}
