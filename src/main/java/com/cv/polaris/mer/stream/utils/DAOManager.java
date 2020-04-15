package com.cv.polaris.mer.stream.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOManager {

    public static Connection getOracleDBConnection()
    {
        Connection connection = null;
        try {
            PropertyHandler prop = new PropertyHandler();
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(prop.getPropValue(Constants.ORACLE_URL)
                    ,prop.getPropValue(Constants.ORACLE_USERNAME),prop.getPropValue(Constants.ORACLE_PASSWORD));
        } catch (SQLException e) {
            System.out.println("SQL Exception " + e.getMessage());
            System.exit(1);
        }
        catch (ClassNotFoundException e) {
            System.out.println("Oracle Driver not found. " + e.getMessage() );
            System.exit(1);
        }
        return connection;
    }
}
