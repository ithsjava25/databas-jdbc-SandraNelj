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

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getSsn() {
        return ssn;
    }
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
}
