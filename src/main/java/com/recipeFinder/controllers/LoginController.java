package com.recipeFinder.controllers;

import com.recipeFinder.views.LoginView;
import com.recipeFinder.lib.DBHandler;
import com.recipeFinder.models.UserModel;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import org.json.JSONObject;

public class LoginController {
    private final LoginView loginView;

    LoginController() {
        loginView = new LoginView();
        loginView.addActionListener(this::handleLogin);
    }

    public void initialize() {
        loginView.showLogin();
    }

    public void JSONTest() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;

        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException err) {
            err.printStackTrace();
        }

        var post = new JSONObject(response.body());
        loginView.displayMessage(String.valueOf(post), "success");
    }

    private void handleLogin(ActionEvent e) {
        DBHandler dbHandler = new DBHandler();

        String username = loginView.getUsername();
        String password = loginView.getPassword();

        try {
            dbHandler.connect();

            String query = "SELECT * FROM users WHERE username = '" + username + "'";
            ResultSet resultSet = dbHandler.executeQuery(query);

            UserModel user = new UserModel();
            if (resultSet.next()) {
                user.setUserID(resultSet.getInt("user_id"));
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
            } else {
                loginView.displayMessage("User not found", "error");
            }

            if(Objects.equals(user.getPassword(), password)) {
                loginView.displayMessage("User found!", "success");
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                dbHandler.disconnect();
            } catch (SQLException err) {
                err.printStackTrace();
            }
        }
    }
}
