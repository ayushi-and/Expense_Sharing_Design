package com.lld.alert.service.impl;

import com.lld.alert.Alert;
import com.lld.alert.service.DispatchStrategy;

import java.io.FileWriter;
import java.io.IOException;

public class FileDispatchStrategy implements DispatchStrategy {
    private final String message;

    public FileDispatchStrategy(String message) {
        this.message = message;
    }

    @Override
    public void dispatchAlert(Alert alert) {
        String message = String.format("Alert for client %s and event type %s: %s",
                alert.getClient(), alert.getEventType(), this.message + "\n");

        String file ="/Users/Ayushi.Sharma/Downloads/alert.txt";

        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(message);
            writer.close();
            System.out.println("Data written to file successfully.");
        }
        catch (IOException ex) {
            System.out.println("An error occurred while writing to file: " + ex.getMessage());
        }
    }
}