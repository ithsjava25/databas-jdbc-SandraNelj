package com.example.repository;

public interface AccountRepository {
    boolean validateLogin(String name, String password);
    void create(String firstName, String lastName, String ssn, String password);
    void updatePassword(int userId, String newPassword);
    void delete(int userId);

}
