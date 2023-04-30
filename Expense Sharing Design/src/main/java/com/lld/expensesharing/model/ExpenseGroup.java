package com.lld.expensesharing.model;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
public class ExpenseGroup {

    private String expenseGroupId;
    private Set<User> groupMembers;

    @Setter
    private Map<String, UserShare> userContributions;

    public ExpenseGroup() {
        this.expenseGroupId = UUID.randomUUID().toString();
        this.groupMembers = new HashSet<>();
        this.userContributions = new HashMap<>();
    }


}
