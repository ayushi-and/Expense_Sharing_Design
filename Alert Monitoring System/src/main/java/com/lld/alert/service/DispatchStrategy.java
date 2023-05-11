package com.lld.alert.service;

import com.lld.alert.Alert;

public interface DispatchStrategy {
    void dispatchAlert(Alert alert);
}