package com.tutrit.java.quickstart.repository;

import com.tutrit.java.quickstart.bean.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private static final Map<String, User> users = new HashMap();

    public static Map<String, User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "UserRepository{}";//TODO;
    }
}
