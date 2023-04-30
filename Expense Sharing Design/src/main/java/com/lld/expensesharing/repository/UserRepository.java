package com.lld.expensesharing.repository;

import com.lld.expensesharing.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class UserRepository {

    public static Map<String, User> userHashMap = new HashMap<>();
}
