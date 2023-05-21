package com.recipeFinder.views;

import com.recipeFinder.controllers.CreateMealPlanController;
import com.recipeFinder.interfaces.IController;
import com.recipeFinder.interfaces.IView;

import javax.swing.*;

public class CreateMealPlanView extends JPanel {
    CreateMealPlanController controller;

    public CreateMealPlanView() {

    }


    public void setController(CreateMealPlanController controller) {
        this.controller = controller;
    }
}
