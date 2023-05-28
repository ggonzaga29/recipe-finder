package com.flavorfinder.features.Collections;

import com.flavorfinder.shared.utils.DBHandler;
import org.sqlite.core.DB;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CollectionModel {
    private int id;
    private String name;
    private String text;

    public CollectionModel(int id, String name, String text) {
        this.id = id;
        this.name = name;
        this.text = text;
    }

    public CollectionModel(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static ArrayList<CollectionModel> getAll() {
        try (DBHandler db = new DBHandler()) {
            db.connect();
            ArrayList<CollectionModel> collections = new ArrayList<>();
            ResultSet rs = db.executeQuery("SELECT * FROM collections");

            while (rs.next()) {
                int id = rs.getInt("collection_id");
                String name = rs.getString("collection_name");
                String text = rs.getString("collection_text");
                collections.add(new CollectionModel(id, name, text));
            }

            return collections;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void save() {
        try (DBHandler db = new DBHandler()) {
            db.connect();
            db.executeUpdate("INSERT INTO collections (collection_name, collection_text) VALUES('" + getName() + "' , '" + getText() + "' )");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
