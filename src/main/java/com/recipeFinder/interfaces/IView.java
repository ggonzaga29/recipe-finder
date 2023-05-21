package com.recipeFinder.interfaces;

import com.recipeFinder.controllers.CreateMealPlanController;

public interface IView {
    public void setController();

    void setController(CreateMealPlanController controller);
}
