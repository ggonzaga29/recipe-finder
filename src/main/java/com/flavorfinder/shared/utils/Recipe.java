package com.flavorfinder.shared.utils;

import java.util.ArrayList;

public class Recipe {
    public String uri;
    public String label;
    public String imageUrl;
    public String imageThumbUrl;
    public int calories;
    public int totalWeight;
    public float totalTime;

    public ArrayList<String> ingredients;

    public Recipe(String uri, String label, String imageUrl, String imageThumbUrl, int calories, int totalWeight, float totalTime) {
        this.uri = uri;
        this.label = label;
        this.imageUrl = imageUrl;
        this.imageThumbUrl = imageThumbUrl;
        this.calories = calories;
        this.totalWeight = totalWeight;
        this.totalTime = totalTime;
//        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "uri='" + uri + '\'' +
                ", label='" + label + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", imageThumbUrl='" + imageThumbUrl + '\'' +
                ", calories=" + calories +
                ", totalWeight=" + totalWeight +
                ", totalTime=" + totalTime +
                '}';
    }
}
