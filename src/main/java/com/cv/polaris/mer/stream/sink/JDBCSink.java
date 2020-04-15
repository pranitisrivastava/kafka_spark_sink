package com.cv.polaris.mer.stream.sink;

import com.cv.polaris.mer.stream.model.FunctionalException;
import com.cv.polaris.mer.stream.utils.Constants;
import com.cv.polaris.mer.stream.utils.DAOManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.spark.sql.ForeachWriter;
import org.apache.spark.sql.Row;

import java.io.IOException;
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
            connection = DAOManager.getOracleDBConnection();
            System.out.println("Connection  " + connection);
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
        if ( ! isJSONValid(functionalException.getValue()) )
        {
            functionalException.setCorrupt_record(functionalException.getValue());
        }
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
    public boolean isJSONValid(String jsonInString ) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(jsonInString);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
