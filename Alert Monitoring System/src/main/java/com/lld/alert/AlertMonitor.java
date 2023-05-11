package com.lld.alert;

import com.lld.alert.AlertConfig;

import java.time.Instant;
import java.util.*;

public class AlertMonitor {

    private Map<String, List<Alert>> alertsByEventType;

    public AlertMonitor() {
        this.alertsByEventType = new HashMap<>();
    }

    public void addAlert(Alert alert) {
        String eventType = alert.getEventType();
        List<Alert> alerts = alertsByEventType.getOrDefault(eventType, new ArrayList<>());
        alerts.add(alert);
        alertsByEventType.put(eventType, alerts);
    }

    public void processEvent(String eventType) {
        List<Alert> alerts = alertsByEventType.get(eventType);
        if (alerts != null) {
            for (Alert alert : alerts) {
                if (alert.isAlertTriggered()) {
                    alert.setLastAlertTime(new Date());
                    alert.setEventCount(0);
                    alert.dispatchAlert();
                }
            }
        }
    }

}