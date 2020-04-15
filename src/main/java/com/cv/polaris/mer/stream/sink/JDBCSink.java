package com.cv.polaris.mer.stream.sink;

import com.cv.polaris.mer.stream.model.FunctionalException;
import com.cv.polaris.mer.stream.utils.Constants;
import org.apache.spark.sql.ForeachWriter;
import org.apache.spark.sql.Row;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.LoggerFactory;

public class JDBCSink extends ForeachWriter<FunctionalException> implements Serializable {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(JDBCSink.class);
    Connection connection;
    PreparedStatement preparedStatement;
    PreparedStatement statementWithParams;
    @Override
    public boolean open(long partitionId, long version) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.132:1521/orcl"
           ,"system","oracle");

            logger.info("JDBC Connection " + connection);
            System.out.println("Connection  " + connection);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Connection not established " + connection);
            System.exit(-1);
        }
        if(connection !=null)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    @Override
    public void process(FunctionalException functionalException) {
    System.out.println("Corrupt Record " + functionalException.getCorrupt_record() );
        String query = Constants.INSERT_INTO_EXCEPTION_FUN_TABLE;
        logger.info("Insert Query " + query);
        System.out.println("Query " + query);
        try {
            preparedStatement = connection.prepareStatement(query);
            statementWithParams = JDBCSinkOps.addParamsToInsertQuery(preparedStatement,functionalException);
            statementWithParams.execute();
            statementWithParams.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close(Throwable throwable) {
        try {
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
