package com.example.repository.jdbc;

import javax.sql.DataSource;
import com.example.repository.AccountRepository;
import java.sql.*;

public class JdbcAccountRepository implements AccountRepository {

    private final DataSource dataSource;

    /**
     * Creates a JdbcAccountRepository backed by the given DataSource.
     *
     * @param dataSource the JDBC DataSource used to obtain connections
     */
    public JdbcAccountRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Checks whether an account exists with the provided name and password.
     *
     * @param name the account name to validate
     * @param password the password to validate for the account
     * @return `true` if an account with the given credentials exists, `false` otherwise
     * @throws RuntimeException if a database access error occurs
     */
    @Override
    public boolean validateLogin(String name, String password) {
        String sql = "Select 1 from account where name = ? and password = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, name);
                statement.setString(2, password);

                 ResultSet resultSet = statement.executeQuery();
                 return resultSet.next();

        } catch (SQLException e) {
             throw new RuntimeException(e);
        }
    }

    /**
     * Creates a new account record in the database and derives the account name from the provided first and last names.
     *
     * The derived account name is formed from the first three characters of `firstName` (first letter uppercase, next two lowercase)
     * followed by the first three characters of `lastName` (first letter uppercase, next two lowercase).
     *
     * @param firstName the account holder's first name (used for name derivation and stored as `first_name`)
     * @param lastName  the account holder's last name (used for name derivation and stored as `last_name`)
     * @param ssn       the account holder's social security number to store in the record
     * @param password  the account password to store
     * @throws RuntimeException if a database access error occurs while inserting the account
     */
    @Override
    public void create(String firstName, String lastName,
                       String ssn, String password) {

        String name =
                firstName.substring(0, 3).substring(0, 1).toUpperCase() +
                        firstName.substring(1, 3).toLowerCase() +
                        lastName.substring(0, 1).toUpperCase() +
                        lastName.substring(1, 3).toLowerCase();

        String sql = "Insert into account (name, password, first_name, last_name, ssn) values (?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setString(2, password);
            statement.setString(3, firstName);
            statement.setString(4, lastName);
            statement.setString(5, ssn);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Update the account password for the specified user.
     *
     * @param userId      the user's identifier (corresponds to the account.user_id column)
     * @param newPassword the new password to set for the account
     * @throws RuntimeException if a database error prevents the update
     */
    @Override
    public void updatePassword(int userId, String newPassword) {
        String sql = "Update account set password = ? where user_id = ?";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, newPassword);
            statement.setInt(2, userId);

            statement.executeUpdate();

        } catch (SQLException e) {
        throw new RuntimeException(e);
        }
    }

    /**
     * Delete the account with the given user identifier.
     *
     * @param userId the identifier of the user whose account will be removed
     * @throws RuntimeException if a database error occurs while performing the deletion
     */
    @Override
    public void delete(int userId){
        String sql = "Delete from account where user_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.executeUpdate();

        } catch (SQLException e) {
        throw new RuntimeException(e);}
    }
}
