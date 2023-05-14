package com.recipeFinder.models;

import com.recipeFinder.lib.DBHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {
    private int userID;
    private String username;
    private String password;

    public UserModel(int id, String name, String password) {
        this.userID = id;
        this.username = name;
        this.password = password;
    }

    public UserModel(String name, String password) {
        this.username = name;
        this.password = password;
    }

    public UserModel() {}

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void save() throws SQLException {
        DBHandler dbHandler = new DBHandler();
        dbHandler.connect();
        String sql = String.format("INSERT INTO users (username, password) VALUES('%s', '%s')", username, password);
        dbHandler.executeUpdate(sql);
        dbHandler.disconnect();

        UserModel newUser = UserModel.findByUsername(username);
        if(newUser != null) {
            this.userID = newUser.getUserID();
            this.username = newUser.getUsername();
            this.password = newUser.getPassword();
        }
    }

    public void update() throws SQLException {
        DBHandler dbHandler = new DBHandler();
        dbHandler.connect();
        String sql = "UPDATE users SET username = '" + this.username + "', password = '" + this.password + "' WHERE user_id = " + this.userID;
        dbHandler.executeUpdate(sql);
        dbHandler.disconnect();

        UserModel newUser = UserModel.findByID(this.userID);
        if(newUser != null) {
            this.userID = newUser.getUserID();
            this.username = newUser.getUsername();
            this.password = newUser.getPassword();
        }
    }

    public static UserModel findByID(int id) throws SQLException {
        DBHandler db = new DBHandler();
        db.connect();

        String query = "SELECT * FROM users WHERE user_id=" + id;
        ResultSet resultSet = db.executeQuery(query);

        UserModel user = null;

        if(resultSet.next()) {
            int userID = resultSet.getInt("user_id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");

            user = new UserModel(userID, username, password);
        }

        resultSet.close();
        db.disconnect();
        return user;
    }

    public static UserModel findByUsername(String uname) throws SQLException {
        DBHandler db = new DBHandler();
        db.connect();

        String query = "SELECT * FROM users WHERE username='" + uname + "'";
        ResultSet resultSet = db.executeQuery(query);

        UserModel user = null;

        if(resultSet.next()) {
            int userID = resultSet.getInt("user_id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");

            user = new UserModel(userID, username, password);
        }

        resultSet.close();
        db.disconnect();
        return user;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "userID=" + userID +
                ", userName='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
