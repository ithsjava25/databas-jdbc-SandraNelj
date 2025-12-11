package com.example.service;

import com.example.repository.AccountRepository;

public class AccountService {

    private final AccountRepository repo;

    public AccountService(AccountRepository repo) {
        this.repo = repo;
    }

    public boolean login(String name, String password) {
        return repo.validateLogin(name, password);
    }

    public void create (String firstName, String lastName, String ssn, String password) {
        repo.create(firstName, lastName, ssn, password);
    }

    public void updatePassword(int userId, String newPassword) {
        repo.updatePassword(userId, newPassword);
    }

    public void delete(int userId) {
        repo.delete(userId);
    }
}
