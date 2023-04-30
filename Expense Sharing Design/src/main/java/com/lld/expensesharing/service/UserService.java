package com.lld.expensesharing.service;

import com.lld.expensesharing.exception.ContributionExceededException;
import com.lld.expensesharing.exception.ExpenseSettledException;
import com.lld.expensesharing.exception.InvalidExpenseStateException;
import com.lld.expensesharing.model.*;
import com.lld.expensesharing.repository.ExpenseRepository;
import com.lld.expensesharing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    //user registration
    public User createUser(String email, String name, String phoneNumber) {
        User user = new User(email, name, phoneNumber);
        UserRepository.userHashMap.putIfAbsent(email, user);
        return user;
    }

    //users start contributing to the expenss
    public void contributeToExpense(String expenseId, String emailId, Contribution contribution) throws InvalidExpenseStateException, ExpenseSettledException, ContributionExceededException {
        Expense expense = ExpenseRepository.expenseHashMap.get(expenseId);
        ExpenseGroup expenseGroup = expense.getExpenseGroup();

        if(ExpenseStatus.CREATED.equals(expense.getExpenseStatus())) {
            throw new InvalidExpenseStateException("Invalid Expense State");
        }

        if(ExpenseStatus.SETTLED.equals(expense.getExpenseStatus())) {
            throw new ExpenseSettledException("Expense is already settled");
        }

        UserShare userShare = expenseGroup.getUserContributions().get(emailId);

        if(contribution.getContributionValue() > userShare.getShare()) {
            throw new ContributionExceededException("User's contribution exceeded the share");
        }

        userShare.getContributions().add(contribution);
    }
}
