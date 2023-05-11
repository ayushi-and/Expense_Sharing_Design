package com.lld.alert;

import com.lld.alert.enums.AlertType;
import com.lld.alert.service.DispatchStrategy;

import java.util.Date;

public class Alert {

    private String client;
    private String eventType;
    private AlertConfig alertConfig;
    private DispatchStrategy dispatchStrategy;
    private Date lastAlertTime;
    private int eventCount;

    public Alert(String client, String eventType, AlertConfig alertConfig) {
        this.client = client;
        this.eventType = eventType;
        this.alertConfig = alertConfig;
        this.lastAlertTime = null;
        this.eventCount = 0;
    }

    public String getClient() {
        return client;
    }

    public String getEventType() {
        return eventType;
    }

    public AlertConfig getAlertConfig() {
        return alertConfig;
    }

    public DispatchStrategy getDispatchStrategy() {
        return dispatchStrategy;
    }

    public void setDispatchStrategy(DispatchStrategy dispatchStrategy) {
        this.dispatchStrategy = dispatchStrategy;
    }

    public Date getLastAlertTime() {
        return lastAlertTime;
    }

    public void setLastAlertTime(Date lastAlertTime) {
        this.lastAlertTime = lastAlertTime;
    }

    public int getEventCount() {
        return eventCount;
    }

    public void setEventCount(int eventCount) {
        this.eventCount = eventCount;
    }

    public void incrementEventCount() {
        this.eventCount++;
    }

    public boolean isAlertTriggered() {
        if (alertConfig.getType() == AlertType.SIMPLE_COUNT) {
            incrementEventCount();
            return (eventCount >= alertConfig.getCount());
        } else if (alertConfig.getType() == AlertType.BUCKETED_WINDOW) {

            long now = System.currentTimeMillis();
            long bucketStartTime = (now / 1000 / alertConfig.getWindowSizeInSecs()) * alertConfig.getWindowSizeInSecs() * 1000;

            if (lastAlertTime == null || lastAlertTime.getTime() < bucketStartTime) {
                eventCount = 0;
            }
            incrementEventCount();
            return (eventCount >= alertConfig.getCount());
        } else if (alertConfig.getType() == AlertType.MOVING_WINDOW) {
            if (lastAlertTime == null) {
                lastAlertTime = new Date();
            } else {
                long now = System.currentTimeMillis();
                long windowStartTime = now - alertConfig.getWindowSizeInSecs() * 1000;

                while (lastAlertTime.getTime() < windowStartTime) {
                    eventCount--;
                    lastAlertTime.setTime(lastAlertTime.getTime() + alertConfig.getWindowSizeInSecs() * 1000);
                }
            }
            incrementEventCount();
            return (eventCount >= alertConfig.getCount());
        }
        return false;
    }

    public void dispatchAlert() {
        dispatchStrategy.dispatchAlert(this);
    }
}
