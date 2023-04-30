package com.lld.expensesharing.repository;

import com.lld.expensesharing.model.Expense;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ExpenseRepository {

    public static Map<String, Expense> expenseHashMap = new HashMap<>();
}
