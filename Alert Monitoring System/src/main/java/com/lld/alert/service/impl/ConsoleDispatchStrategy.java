package com.lld.alert.service.impl;

import com.lld.alert.Alert;
import com.lld.alert.service.DispatchStrategy;

public class ConsoleDispatchStrategy implements DispatchStrategy {
    private final String message;

    public ConsoleDispatchStrategy(String message) {
        this.message = message;
    }

    @Override
    public void dispatchAlert(Alert alert) {
        String message = String.format("Alert for client %s and event type %s: %s",
                alert.getClient(), alert.getEventType(), this.message);
        System.out.println(message);
    }
}