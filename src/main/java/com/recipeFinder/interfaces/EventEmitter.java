package com.recipeFinder.interfaces;

public interface EventEmitter {
    void on(String eventName, EventListener listener);
    void emit(String eventName);
}
