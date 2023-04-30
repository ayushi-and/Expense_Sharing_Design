package com.lld.expensesharing.model;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UserShare {

    private String userId;
    private Double share;
    private List<Contribution> contributions;

    public UserShare(String userId, Double share) {
        this.userId = userId;
        this.share = share;
        this.contributions = new ArrayList<>();
    }
}
