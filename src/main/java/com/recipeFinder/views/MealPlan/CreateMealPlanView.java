package com.recipeFinder.views;

import com.recipeFinder.controllers.CreateMealPlanController;

import javax.swing.*;

public class CreateMealPlanView extends JPanel {
    CreateMealPlanController controller;

    public CreateMealPlanView() {

    }


    public void setController(CreateMealPlanController controller) {
        this.controller = controller;
    }
}
