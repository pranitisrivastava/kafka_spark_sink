package com.cv.polaris.mer.stream.runner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test {

    public static void main(String args[]) throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.122:1521/orcl"
                ,"system","oracle");

        System.out.println("Connection  " + connection);
    }
}
