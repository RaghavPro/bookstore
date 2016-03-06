package com.raghavpro.bookhive.models.service;

import com.raghavpro.bookhive.models.jdbc.DatabaseConnectivity;

import java.sql.*;
import java.util.List;

/**
 * Represents a service to models.
 *
 * @author Raghav (Email: Raghav.Sharma@Outlook.in)
 */
public abstract class Service<T> {

    /**
     * Represents the <code>Connection</code> instance.
     */
    protected Connection conn = null;

    /**
     * Represents a {@code PreparedStatement}
     */
    protected PreparedStatement ps = null;

    /**
     * Constructs a new {@code Service}
     *
     * @param conn The connection object.
     */
    public Service(Connection conn) {
        this.conn = conn;
    }

    /**
     * Executes a SQL statement of all kinds.
     *
     * @param query  The SQL statement
     * @param params The parameter(s) for prepared statement.
     * @throws SQLException if any jdbc access error occurs
     */
    protected ResultSet execute(String query, Object... params) throws SQLException {
        ResultSet result = null;
        if (conn == null) //This wouldn't (shouldn't?) happen but still.
            conn = DatabaseConnectivity.getConnection();
        ps = conn.prepareStatement(query);
        if (params != null)
            for (int i = 0; i < params.length; i++) {
                if (params[i] instanceof Integer)
                    ps.setInt(i + 1, (Integer) params[i]);
                else if (params[i] instanceof String)
                    ps.setString(i + 1, (String) params[i]);
                else if (params[i] instanceof Long)
                    ps.setLong(i + 1, (Long) params[i]);
                else if (params[i] instanceof Date)
                    ps.setDate(i + 1, (Date) params[i]);
            }
        if (ps.execute())
            result = ps.getResultSet();
        return result;
    }

    /**
     * Executs a data manipulation query
     *
     * @param query The DML query
     * @param queryParams The query params required to be used in {@code PreparedStatement}
     * @return No of rows effected by insertion.
     *
     * @throws SQLException Resources gets closed in calling method
     */
    protected int executeDML(String query, Object... queryParams) throws SQLException{
        if (conn == null) //This wouldn't (shouldn't?) happen but still.
            conn = DatabaseConnectivity.getConnection();
        ps = conn.prepareStatement(query);
        if (queryParams != null)
            for (int i = 0; i < queryParams.length; i++) {
                if (queryParams[i] instanceof Integer)
                    ps.setInt(i + 1, (Integer) queryParams[i]);
                else if (queryParams[i] instanceof String)
                    ps.setString(i + 1, (String) queryParams[i]);
                else if (queryParams[i] instanceof Long)
                    ps.setLong(i + 1, (Long) queryParams[i]);
                else if (queryParams[i] instanceof Date)
                    ps.setDate(i + 1, (Date) queryParams[i]);
                else
                    System.out.println("Add more datatypes in execureInsert()");
            }
        int rowsEffected = ps.executeUpdate();
        return rowsEffected;
    }

    /**
     * Gets something from the database.
     *
     * @param args        Arguments to be used in method
     * @param query       The query to execute on database to get 'something'.
     * @param queryParams Parameters for {@code PreparedStatement}.
     * @return A {@code List} of {@code Book}s
     */
    public abstract List<T> get(Object[] args, String query, Object... queryParams);

    /**
     * Does data manipulation on the database.
     * Can be used to INSERT, DELETE, UPDATE the data.
     *
     * @param query       The query to dataManipulation that 'something'
     * @param queryParams Parameters for {@code PreparedStatement}
     * @return {@code true} if the insertion was successful else {@code false}
     */
    public abstract boolean dataManipulation(String query, Object... queryParams);

    /**
     * Closes the opened connection. Which then goes back to the pool instad of really getting closed.
     */
    public void close() {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
