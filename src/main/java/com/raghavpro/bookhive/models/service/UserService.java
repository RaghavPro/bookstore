package com.raghavpro.bookhive.models.service;

import com.raghavpro.bookhive.models.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Raghav (Email: Raghav.Sharma@Outlook.in)
 */
public class UserService extends Service<User> {

    /**
     * Constructs a new {@code Service}
     *
     * @param conn The connection object.
     */
    public UserService(Connection conn) {
        super(conn);
    }

    @Override
    public List<User> get(Object[] args, String query, Object... queryParams) {
        List<User> users = null;
        ResultSet rs = null;
        try {
            /* Executes the query */
            rs = execute(query, queryParams);
            if (rs == null)
                return null; // idk?
            users = new ArrayList<>();
            while (rs.next()) {
                int userId = rs.getInt("userId");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String gender = rs.getString("gender");
                Date sqlDate = rs.getDate("date");

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
                String date = sdf.format(sqlDate);

                User user = new User();
                user.setUserId(userId);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setUserName(username);
                user.setPassword(password);
                user.setEmail(email);
                user.setAddress(address);
                user.setGender(gender);
                user.setDate(date);

                users.add(user);
            }
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
        return users;
    }

    @Override
    public boolean dataManipulation(String query, Object... queryParams) {
        int rowsEffected = 0;
        try {
            rowsEffected = executeDML(query, queryParams);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rowsEffected > 0;
    }
}
