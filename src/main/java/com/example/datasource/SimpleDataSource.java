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
    /**
     * Gets the PrintWriter used for logging by this DataSource.
     *
     * @return the log writer for this data source, or `null` if no log writer is set
     */
    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }
    /**
     * Does nothing; this DataSource does not support setting a log writer.
     *
     * @param out the PrintWriter to set (ignored)
     */
    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {}

    /**
     * Ignores the requested login timeout; this data source does not track or enforce a login timeout.
     *
     * @param seconds the requested timeout in seconds; ignored by this implementation
     */
    @Override
    public void setLoginTimeout(int seconds) throws SQLException {}

    /**
     * Retrieve the maximum time in seconds this DataSource will wait when attempting to obtain a connection.
     *
     * @return 0 to indicate no login timeout is configured
     */
    @Override
    public int getLoginTimeout() throws SQLException {return 0;}

    /**
     * Retrieves the parent Logger for this data source.
     *
     * @return the parent {@link Logger}, or {@code null} if no parent logger is available
     * @throws SQLFeatureNotSupportedException if obtaining a parent logger is not supported
     */
    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    /**
     * Indicates that this DataSource does not support unwrapping to the requested interface.
     *
     * @param <T>   the target interface type
     * @param iface the interface class to unwrap to
     * @return an instance implementing `iface`, or `null` if unwrapping is not supported
     */
    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    /**
     * Indicates whether this data source is a wrapper for the specified interface; this implementation always returns false.
     *
     * @param iface the interface to check
     * @return {@code true} if this data source is a wrapper for the specified interface, {@code false} otherwise
     */
    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}