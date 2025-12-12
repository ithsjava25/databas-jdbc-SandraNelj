package com.example.repository.jdbc;

import javax.sql.DataSource;
import com.example.repository.AccountRepository;
import java.sql.*;

public class JdbcAccountRepository implements AccountRepository {

    private final DataSource dataSource;

    public JdbcAccountRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

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

    @Override
    public void create(String firstName, String lastName,
                       String ssn, String password) {

        String trimmedFirst = firstName.trim();
        String trimmedLast = lastName.trim();
        if (trimmedFirst.isEmpty() || trimmedLast.isEmpty()) {
            throw new IllegalArgumentException("First name and last name must not be empty");
        }

        String firstPart = trimmedFirst.substring(0, 1).toUpperCase() +
                trimmedFirst.substring(1, Math.min(3, trimmedFirst.length())).toLowerCase();

        String lastPart = trimmedLast.substring(0, 1).toUpperCase() +
                trimmedLast.substring(1, Math.min(3, trimmedLast.length())).toLowerCase();

        String name = firstPart + lastPart;

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

