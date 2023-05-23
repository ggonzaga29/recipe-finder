package com.recipeFinder.models;

import com.recipeFinder.shared.enums.SQLResult;
import com.recipeFinder.shared.exceptions.RecordAlreadyExistsException;
import com.recipeFinder.shared.utils.DBHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

    @Override
    public String toString() {
        return "GroceryListModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public static ArrayList<GroceryListModel> getAll() {
        try (DBHandler db = new DBHandler()) {
            db.connect();

            ArrayList<GroceryListModel> groceryListModels = new ArrayList<GroceryListModel>();

            String sql = "SELECT * FROM grocery_lists";
            try (ResultSet resultSet = db.executeQuery(sql)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("grocery_list_id");
                    String name = resultSet.getString("grocery_list_name");
                    String date = resultSet.getString("grocery_list_date");

                    GroceryListModel groceryListModel = new GroceryListModel(id, name, date);
                    groceryListModels.add(groceryListModel);
                }
            }

            return groceryListModels;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SQLResult save() {
        try (DBHandler db = new DBHandler()) {
            db.connect();

            String sql = "SELECT COUNT(*) FROM grocery_lists WHERE grocery_list_name = '" + name + "'";
            try (ResultSet resultSet = db.executeQuery(sql)) {
                resultSet.next();
                int count = resultSet.getInt(1);

                if (count > 0) {
                    throw new RecordAlreadyExistsException("Record already exists. Cannot insert.");
                }
            }

            sql = "INSERT INTO grocery_lists (grocery_list_name, grocery_list_date) VALUES ('" + getName() + "', '" + getDate() + "')";
            try (Statement statement = db.getConnection().createStatement()) {
                int rowsAffected = statement.executeUpdate(sql);

                if (rowsAffected > 0) {
                    return SQLResult.SUCCESS;
                } else {
                    return SQLResult.FAILURE;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static GroceryListModel findByName(String _name) {
        DBHandler db = new DBHandler();

        try {
            db.connect();
            String sql = String.format("SELECT * FROM grocery_lists WHERE grocery_list_name='%s'", _name);
            ResultSet resultSet = db.executeQuery(sql);

            GroceryListModel groceryListModel = null;

            if (resultSet.next()) {
                int id = resultSet.getInt("grocery_list_id");
                String name = resultSet.getString("grocery_list_name");
                String date = resultSet.getString("grocery_list_date");

                groceryListModel = new GroceryListModel(id, name, date);
            }

            resultSet.close();
            return groceryListModel;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            db.close();
        }
    }

    public static void main(String[] args) {
        GroceryListModel list = new GroceryListModel("asdfdf", "05/08/2002"); // should throw exception
        list.save();
    }
}
