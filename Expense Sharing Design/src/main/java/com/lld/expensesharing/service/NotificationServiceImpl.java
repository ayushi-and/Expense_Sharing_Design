package com.lld.expensesharing.service;

import com.lld.expensesharing.model.Expense;
import com.lld.expensesharing.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Override
    public void notifyUser(User user, Expense expense) {
        System.out.println("Notified");
    }
}

