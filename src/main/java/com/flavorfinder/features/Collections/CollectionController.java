package com.flavorfinder.features.Collections;

import com.flavorfinder.features.Controller;

public class CollectionController extends Controller {

    CollectionsView view;

    public CollectionController(CollectionsView view) {
        this.view = view;
        view.setController(this);
    }

    public void handleCreateCollection(String name, String text) {
        CollectionModel collection = new CollectionModel(name, text);
        collection.save();
        view.updateView();
    }
}
