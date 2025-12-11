package com.example.service;

import com.example.repository.AccountRepository;

public class AccountService {

    private final AccountRepository repo;

    /**
     * Creates an AccountService backed by the given AccountRepository.
     *
     * @param repo the repository used for account persistence and lookup operations
     */
    public AccountService(AccountRepository repo) {
        this.repo = repo;
    }

    /**
     * Determines whether the supplied username and password authenticate an existing account.
     *
     * @param name     the username or account identifier to authenticate
     * @param password the plaintext password to verify for the given username
     * @return `true` if the credentials are valid for an account, `false` otherwise
     */
    public boolean login(String name, String password) {
        return repo.validateLogin(name, password);
    }

    /**
     * Creates a new user account using the provided personal details and password.
     *
     * @param firstName the user's given name
     * @param lastName  the user's family name
     * @param ssn       the user's Social Security Number
     * @param password  the account password
     */
    public void create (String firstName, String lastName, String ssn, String password) {
        repo.create(firstName, lastName, ssn, password);
    }

    /**
     * Update the password for the user with the given id.
     *
     * @param userId      the identifier of the user whose password will be changed
     * @param newPassword the new password to set for the user
     */
    public void updatePassword(int userId, String newPassword) {
        repo.updatePassword(userId, newPassword);
    }

    /**
     * Deletes the user account identified by the given user ID.
     *
     * @param userId the identifier of the user account to remove
     */
    public void delete(int userId) {
        repo.delete(userId);
    }
}