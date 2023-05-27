package com.flavorfinder.features.Auth.models;

import com.flavorfinder.shared.utils.DBHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrentUser {
    private static int id;
    private static String name;
    public static int getId() {
        int id = -1;

        try {
            DBHandler db = new DBHandler();
            db.connect();

            String $query = "SELECT * FROM current_user";
            ResultSet resultSet = db.executeQuery($query);

            if(resultSet.next()) {
                id = resultSet.getInt("current_user_id");
            }
        } catch (SQLException e) {

        }

        return id;
    }

    public static int update(int oldId, int newId, String name) {
        int id = -1;

        try {
            DBHandler db = new DBHandler();
            db.connect();

            String $query = "UPDATE current_user SET current_user_id=" + newId + ", current_user_username='" + name + "' WHERE current_user_id=" + oldId + "";
            ResultSet resultSet = db.executeQuery($query);

            if(resultSet.next()) {
                id = resultSet.getInt("current_user_id");
            }
        } catch (SQLException e) {

        }

        return id;
    }

    public static void main(String[] args) {
        int id = CurrentUser.getId();
        CurrentUser.update(id, 2, "Gian22Gonzaga");
    }
}
