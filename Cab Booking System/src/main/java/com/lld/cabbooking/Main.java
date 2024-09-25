package com.lld.alert;

import com.lld.alert.enums.AlertType;
import com.lld.alert.service.DispatchStrategy;
import com.lld.alert.service.impl.ConsoleDispatchStrategy;
import org.springframework.boot.SpringApplication;

public class Main {

    public static void main(String[] args) throws InterruptedException {

//        AlertConfig alertConfig = new AlertConfig(AlertType.SIMPLE_COUNT, 10, 2);
//        DispatchStrategy dispatchStrategy = new ConsoleDispatchStrategy("Exception in Payment");
//
//        Alert alert = new Alert("X", "PAYMENT_EXCEPTION", alertConfig);
//        alert.setDispatchStrategy(dispatchStrategy);
//
//        AlertMonitor alertMonitor = new AlertMonitor();
//        alertMonitor.addAlert(alert);
//
//        for (int i = 0; i < 20; i++) {
//            //Thread.sleep(3000);
//            alertMonitor.processEvent("PAYMENT_EXCEPTION");
//        }

    }
}
