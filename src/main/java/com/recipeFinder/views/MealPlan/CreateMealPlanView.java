package com.recipeFinder.views.MealPlan;

import com.recipeFinder.controllers.MealPlan.CreateMealPlanController;

import javax.swing.*;

public class CreateMealPlanView extends JPanel {
    CreateMealPlanController controller;

    public CreateMealPlanView() {

    }


    public void setController(CreateMealPlanController controller) {
        this.controller = controller;
    }
}
