package com.recipeFinder.models;

import com.recipeFinder.utils.DBHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroceryListModel {
    private int id;
    private String name;
    private String date;

    public GroceryListModel(int id, String name, String date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public GroceryListModel(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void save() {
        try {
            DBHandler db = new DBHandler();
            db.connect();
            String sql = String.format("INSERT INTO grocery_lists (username, password) VALUES('%s', '%s')", getName(), getDate());
            db.executeQuery(sql);
            db.disconnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static GroceryListModel findByName(String _name) {
        try {
            DBHandler db = new DBHandler();
            db.connect();
            String sql = String.format("SELECT * FROM grocery_lists WHERE grocery_list_name='%s'", _name);
            ResultSet resultSet = db.executeQuery(sql);

            GroceryListModel groceryListModel = null;

            if(resultSet.next()) {
                int id = resultSet.getInt("grocery_list_id");
                String name = resultSet.getString("grocery_list_name");
                String date = resultSet.getString("grocery_list_date");

                groceryListModel = new GroceryListModel(id, name, date);
            }

            resultSet.close();
            db.disconnect();
            return groceryListModel;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        GroceryListModel.findByName("Weekend Shopping");
    }
}
