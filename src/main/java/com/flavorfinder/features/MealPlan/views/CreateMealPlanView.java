package com.flavorfinder.features.MealPlan.views;

import com.flavorfinder.features.MealPlan.controllers.CreateMealPlanController;
import com.flavorfinder.features.View;

import javax.swing.*;

public class CreateMealPlanView extends View {
    CreateMealPlanController controller;

    public CreateMealPlanView() {

    }


    public void setController(CreateMealPlanController controller) {
        this.controller = controller;
    }
}
