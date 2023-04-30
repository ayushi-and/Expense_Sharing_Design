package com.lld.expensesharing.service;

import com.lld.expensesharing.model.Expense;
import com.lld.expensesharing.model.User;

public interface NotificationService {
    void notifyUser(User user, Expense expense);
}