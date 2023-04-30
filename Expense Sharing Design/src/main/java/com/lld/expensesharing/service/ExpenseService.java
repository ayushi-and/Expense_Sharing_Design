package com.lld.expensesharing.service;

import com.lld.expensesharing.exception.ExpenseDoesNotExistsException;
import com.lld.expensesharing.model.*;
import com.lld.expensesharing.repository.ExpenseRepository;
import com.lld.expensesharing.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class ExpenseService {

    @Autowired
    private NotificationService notificationService;


    //user creating an expense
    public Expense createExpense(String expenseTitle, String expenseDesc, LocalDateTime expenseDate, Double expenseAmount,
                                 String userId) {

       Expense expense = Expense.builder()
               .expenseId(UUID.randomUUID().toString())
               .userId(userId)
               .expenseAmount(expenseAmount)
               .expenseDate(expenseDate)
               .expenseDesc(expenseDesc)
               .expenseTitle(expenseTitle)
               .expenseStatus(ExpenseStatus.CREATED)
               .expenseGroup(new ExpenseGroup())
               .build();

        ExpenseRepository.expenseHashMap.putIfAbsent(expense.getExpenseId(), expense);
        return expense;
    }


    //user adding other users to share that expense
    public void addUsersToExpense(String emailId, String expenseId) throws ExpenseDoesNotExistsException {

        if(!ExpenseRepository.expenseHashMap.containsKey(expenseId)) {
            throw new ExpenseDoesNotExistsException("Expense doesn't exists for expense id : " +expenseId);
        }

        ExpenseRepository.expenseHashMap.get(expenseId).getExpenseGroup().getGroupMembers()
                .add(UserRepository.userHashMap.get(emailId));

        //notify the users
        notificationService.notifyUser(UserRepository.userHashMap.get(emailId), ExpenseRepository.expenseHashMap.get(expenseId));
    }

    //user assigning the shares to the respective users
    public void assignExpenseShare(String expenseId, String emailId, Double share) throws ExpenseDoesNotExistsException {

        if(!ExpenseRepository.expenseHashMap.containsKey(expenseId)) {
            throw new ExpenseDoesNotExistsException("Expense doesn't exists for expense id : " +expenseId);
        }

        ExpenseRepository.expenseHashMap.get(expenseId).getExpenseGroup().getUserContributions().putIfAbsent(emailId, new UserShare(emailId, share));
    }

    //to set the expense status based on the scenario
    public void setExpenseStatus(String expenseId, ExpenseStatus expenseStatus) {
        Expense expense = ExpenseRepository.expenseHashMap.get(expenseId);
        expense.setExpenseStatus(expenseStatus);
    }

    //to check if the expense is all settled up or still pending
    public Boolean isExpenseSettled(String expenseId) {
        Expense expense = ExpenseRepository.expenseHashMap.get(expenseId);
        ExpenseGroup expenseGroup = expense.getExpenseGroup();
        Map<String, UserShare> userContributions = expenseGroup.getUserContributions();

        Double totalExpenseAmount = expense.getExpenseAmount();

        for(Map.Entry<String, UserShare> entry: userContributions.entrySet()) {
            UserShare userShare = entry.getValue();
            for(Contribution contribution: userShare.getContributions()) {
                totalExpenseAmount -= contribution.getContributionValue();
            }
        }

        if(totalExpenseAmount <= 1) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;

    }


}
