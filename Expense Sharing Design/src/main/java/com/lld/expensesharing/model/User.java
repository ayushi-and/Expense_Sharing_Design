package com.lld.expensesharing.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
public class User {

    private String userId;
    private String email;
    private String name;
    private String phoneNumber;

    public User(@NonNull String email, String name, String phoneNumber) {
        userId = UUID.randomUUID().toString();
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

}

