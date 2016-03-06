package com.raghavpro.bookhive.models.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Raghav (Email: Raghav.Sharma@Outlook.in)
 */
public class CountService extends Service<Integer> {
    /**
     * Constructs a new {@code Service}
     *
     * @param conn The connection object.
     */
    public CountService(Connection conn) {
        super(conn);
    }

    /**
     * Gets the count of a column
     *
     *
     * @param args
     * @param query The query to execute
     * @param queryParams PreparedStatement's params
     * @return A list with one object in it
     */
    @Override
    public List<Integer> get(Object[] args, String query, Object... queryParams) {
        List<Integer> count = null;
        ResultSet rs = null;
        try {
            rs = execute(query, queryParams);
            if (rs == null)
                return null;
            rs.next();
            count = new ArrayList<>(1);
            count.add(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    @Override
    public boolean dataManipulation(String query, Object... queryParams) {
        return false;
    }
}
