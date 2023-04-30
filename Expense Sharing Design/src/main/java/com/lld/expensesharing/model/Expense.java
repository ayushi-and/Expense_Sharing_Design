package com.lld.expensesharing.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Expense {

    private String expenseId;
    private String userId;
    private Double expenseAmount;
    private LocalDateTime expenseDate;
    private String expenseTitle;
    private String expenseDesc;
    private ExpenseStatus expenseStatus;
    private ExpenseGroup expenseGroup;
}
