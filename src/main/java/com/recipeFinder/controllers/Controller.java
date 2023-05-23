package com.recipeFinder.controllers;

import com.recipeFinder.interfaces.EventEmitter;
import com.recipeFinder.interfaces.EventListener;

import java.util.*;

public class _BaseController implements EventEmitter {
    private final Map<String, List<EventListener>> eventListeners;

    public _BaseController() {
        eventListeners = new HashMap<>();
    }

    @Override
    public void on(String eventName, EventListener listener) {
        List<EventListener> listeners = eventListeners.get(eventName);

        if (listeners == null) {
            listeners = new ArrayList<>();
            eventListeners.put(eventName, listeners);
        }

        listeners.add(listener);
    }

    @Override
    public void emit(String eventName) {
        List<EventListener> listeners = eventListeners.get(eventName);

        if (listeners != null) {
            for (EventListener listener : listeners) {
                listener.onEvent(eventName);
            }
        }
    }

    // testing
    public static void main(String[] args) {
        _BaseController controller = new _BaseController();

        controller.on("submit", e -> {
            System.out.println("Sdf");
        });

        controller.emit("submit");
    }
}
