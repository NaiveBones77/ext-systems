package edu.city.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DirectConnectionBuilder implements ConnectionBuilder {


    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5433/city_register",
                "postgres", "admin");
    }
}
