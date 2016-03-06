package com.raghavpro.bookhive.models.jdbc;

import com.raghavpro.bookhive.Constants;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Hashtable;

/**
 * Gets the {@code Connection} object from the JNDI Data Source.
 *
 * @author Raghav
 */
public class DatabaseConnectivity {

    public static Connection getConnection() {
        Connection conn = null;
        /*
        String datasource_name = "jdbc/bookhive_db";
        try {
            //A JNDI Initial context to be able to lookup the DataSource
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/" + datasource_name);
            if (ds == null)
                throw new NamingException("Unknown DataSource '" + datasource_name + "'");
            conn = ds.getConnection();
        } catch (NamingException | SQLException ex) {
            ex.printStackTrace();
        }*/


        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:" + Constants.DB_URL + "/" + Constants.DATABASE, Constants.USER, Constants.PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}