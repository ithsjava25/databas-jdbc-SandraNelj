package com.example.repository.jdbc;

import com.example.model.MoonMission;
import com.example.repository.MoonMissionRepository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcMoonMissionRepository implements MoonMissionRepository {
    private final DataSource dataSource;

    public JdbcMoonMissionRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<String> listSpacecraftNames() {
        List<String> list = new ArrayList<>();

        String sql = "SELECT spacecraft from moon_mission order by mission_id";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                list.add(resultSet.getString("spacecraft"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    @Override
    public MoonMission getById(int missionId) {
        String sql = "select * from moon_mission where mission_id = ?";

        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, missionId);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) return null;

            return new MoonMission(
            resultSet.getInt("mission_id"),
            resultSet.getString("spacecraft"),
            resultSet.getDate("launch_date"),
            resultSet.getString("carrier_rocket"),
            resultSet.getString("operator"),
            resultSet.getString("mission_type"),
            resultSet.getString("outcome")
            );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByYear(int year) {

        String sql = "select COUNT(*) from moon_mission where year (launch_date) = ?";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, year);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            return resultSet.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
