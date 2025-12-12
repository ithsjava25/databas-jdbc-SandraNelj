package com.example.datasource;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Logger;

public class SimpleDataSource implements DataSource {
        private final String url;
        private final String user;
        private final String pass;

/**
 * Create a SimpleDataSource configured with the given JDBC URL and credentials.
 *
 * @param url  the JDBC connection URL
 * @param user the username to use for connections
 * @param pass the password to use for connections
 */

        public SimpleDataSource(String url, String user, String pass) {
            this.url = url;
            this.user = user;
            this.pass = pass;
        }

/**
 * Obtain a new JDBC Connection using the data source's configured URL and credentials.
 *
 * @return a new JDBC {@link Connection} connected to the configured URL using the stored username and password
 * @throws SQLException if a database access error occurs or a connection cannot be established
 */

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

    /**
     * Create a new JDBC Connection to the configured URL using the supplied credentials.
     *
     * @param username the username to authenticate the connection
     * @param password the password to authenticate the connection
     * @return a new {@link Connection} authenticated with the provided credentials
     * @throws SQLException if a database access error occurs or the URL is invalid
     */

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }
    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {}

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {}

    @Override
    public int getLoginTimeout() throws SQLException {return 0;}

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}
