package com.tutrit.java.quickstart.repository;

import java.util.HashMap;

public class UserRepository {
    private static final Map<User> users = new HashMap();

    public static Map<User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "UserRepository{}"//TODO;
    }
}
