package com.example.repository;

public interface AccountRepository {
    /**
 * Checks whether the supplied credentials correspond to an existing account.
 *
 * @param name     the account's username
 * @param password the account's password
 * @return         `true` if the credentials match an existing account, `false` otherwise
 */
boolean validateLogin(String name, String password);
    /**
 * Creates a new account using the provided personal details.
 *
 * @param firstName the user's given name
 * @param lastName  the user's family name
 * @param ssn       the user's Social Security Number (used to identify the account)
 * @param password  the account password as provided by the caller
 */
void create(String firstName, String lastName, String ssn, String password);
    /**
 * Updates the password for the account identified by the given userId.
 *
 * @param userId the identifier of the user whose password will be changed
 * @param newPassword the new password to assign to the user
 */
void updatePassword(int userId, String newPassword);
    /**
 * Deletes the account identified by the given user ID.
 *
 * @param userId the unique identifier of the account to delete
 */
void delete(int userId);

}