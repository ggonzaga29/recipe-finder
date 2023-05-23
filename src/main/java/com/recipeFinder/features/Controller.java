package com.recipeFinder.features;

import com.recipeFinder.shared.interfaces.EventEmitter;
import com.recipeFinder.shared.interfaces.EventListener;

import java.util.*;

/**
 * The Controller class handles events and event listeners for the recipe finder application.
 * It implements the EventEmitter interface, allowing event registration and emission.
 * Event emission is implemented because some components (like other views) aside from the controller's view need to
 * be subscribed to events in the controller. This allows the controller to emit events to those components.
 * @see EventListener
 */
public class Controller implements EventEmitter {
    private final Map<String, List<EventListener>> eventListeners;

    /**
     * Constructs a new Controller instance.
     * Initializes the eventListeners map for event emitting
     */
    public Controller() {
        eventListeners = new HashMap<>();
    }

    /**
     * Registers an event listener for the specified event name.
     *
     * @param eventName the name of the event to listen for
     * @param listener  the event listener to register
     */
    @Override
    public void on(String eventName, EventListener listener) {
        List<EventListener> listeners = eventListeners.get(eventName);

        if (listeners == null) {
            listeners = new ArrayList<>();
            eventListeners.put(eventName, listeners);
        }

        listeners.add(listener);
    }

    /**
     * Emits an event with the specified event name, triggering all associated event listeners.
     * When an event is emitted, all registered event listeners for that event are invoked with the event name.
     * @param eventName the name of the event to emit
     */
    @Override
    public void emit(String eventName) {
        List<EventListener> listeners = eventListeners.get(eventName);

        if (listeners != null) {
            // Invoke each listener's onEvent method with the event name
            for (EventListener listener : listeners) {
                listener.onEvent(eventName);
            }
        }
    }

    // testing
    public static void main(String[] args) {
        Controller controller = new Controller();

        controller.on("submit", e -> System.out.println("Sdf"));

        controller.emit("submit");
    }
}
