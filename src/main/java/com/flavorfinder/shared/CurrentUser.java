package com.flavorfinder.shared;

public class CurrentUser {
    private static CurrentUser instance;
    private static final Object lock = new Object();

    private String username = "";

    private int id = -1;
    private CurrentUser() {
        // Private constructor to prevent direct instantiation
    }

    public static CurrentUser getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new CurrentUser();
                }
            }
        }
        return instance;
    }

    public synchronized String getUsername() {
        return username;
    }

    public synchronized void setUsername(String username) {
        this.username = username;
    }

    public synchronized int getId() {
        return id;
    }

    public synchronized void setId(int id) {
        this.id = id;
    }

    public synchronized void reset() {
        this.username = "";
        this.id = -1;
    }
}
