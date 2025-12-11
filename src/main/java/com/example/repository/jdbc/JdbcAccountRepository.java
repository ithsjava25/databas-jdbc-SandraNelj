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

