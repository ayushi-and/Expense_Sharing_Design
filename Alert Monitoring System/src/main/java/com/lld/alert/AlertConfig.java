package com.lld.alert;

import com.lld.alert.enums.AlertType;

public class AlertConfig {
    private AlertType type;
    private int count;
    private int windowSizeInSecs;

    public AlertConfig(AlertType type, int count, int windowSizeInSecs) {
        this.type = type;
        this.count = count;
        this.windowSizeInSecs = windowSizeInSecs;
    }

    public AlertType getType() {
        return type;
    }

    public int getCount() {
        return count;

    }

    public int getWindowSizeInSecs() {
        return windowSizeInSecs;
    }
}