package com.example.model;

public class Account {
    private int userId;
    private String name;
    private String password;
    private String firstName;
    private String lastName;
    private String ssn;

    /**
     * Create a new Account with the provided user identifier and personal details.
     *
     * @param userId    the numeric identifier for the user
     * @param name      the account name or username
     * @param password  the account password
     * @param firstName the user's first name
     * @param lastName  the user's last name
     * @param ssn       the user's Social Security Number
     */
    public Account(int userId, String name, String password, String firstName,
                   String lastName, String ssn) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
    }

    /**
     * Gets the user's identifier.
     *
     * @return the user identifier
     */
    public int getUserId() {
        return userId;
    }
    /**
     * Sets the account's user identifier.
     *
     * @param userId the unique identifier for this account
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    /**
     * Gets the account's name.
     *
     * @return the account name
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the account name.
     *
     * @param name the new name for this account
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Gets the account password.
     *
     * @return the account password
     */
    public String getPassword() {
        return password;
    }
    /**
     * Sets the account's password.
     *
     * @param password the new password for the account
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Retrieves the account holder's first name.
     *
     * @return the first name, or {@code null} if it has not been set.
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Sets the account's first name.
     *
     * @param firstName the new first name for the account
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * Gets the account's last name.
     *
     * @return the last name, or {@code null} if not set
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Sets the account's last name.
     *
     * @param lastName the last name to assign to the account
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * Retrieves the account's Social Security number (SSN).
     *
     * @return the Social Security number as a String
     */
    public String getSsn() {
        return ssn;
    }
    /**
     * Sets the Social Security Number for this account.
     *
     * @param ssn the Social Security Number to assign
     */
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
}