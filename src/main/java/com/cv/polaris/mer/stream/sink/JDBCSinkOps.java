package com.cv.polaris.mer.stream.sink;

import com.cv.polaris.mer.stream.model.FunctionalException;
import org.apache.spark.sql.Row;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCSinkOps {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(JDBCSinkOps.class);
    public static PreparedStatement addParamsToInsertQuery(PreparedStatement statement, FunctionalException value) throws SQLException {

        //logger.info("Add params");
        //logger.info("exception key " + value.getAs("exception_key") );
        //statement.setLong(1, Long.parseLong(value.getAs("exception_key")));
        statement.setString(1, value.getJob_id());
        statement.setInt(2, value.getJob_sequence() == null ? 0 : value.getJob_sequence());
        statement.setString(3, value.getJob_scheduler());
        statement.setString(4, value.getException_id());
        statement.setString(5, value.getTenant());
        statement.setString(6, value.getBusiness_category());
        statement.setString(7, value.getBusiness_subcategory());
        statement.setString(8, value.getClient_name());
        statement.setString(9,value.getOrigin());
        statement.setString(10, value.getComponent());
        statement.setString(11, value.getSub_component());
        statement.setString(12, value.getException_type());
        statement.setString(13, value.getException_subtype());
        statement.setString(14, value.getStatus());
        statement.setString(15, value.getSeverity());
        statement.setString(16, value.getOwned_by());
        statement.setString(17, value.getException_message());
        statement.setString(18, value.getException_message_detail());
        statement.setString(19, value.getException_data());
        statement.setInt(20, value.getRetry_count() == null ? 0 : value.getRetry_count());
        statement.setString(21, value.getException_created_time());
        statement.setString(22, value.getRemediation_status());
        statement.setString(23, value.getResolved_by());
        statement.setString(24, value.getResolved_time());
        statement.setString(25, "MER".toString());
        statement.setString(26, "MER".toString());
        statement.setString(27, value.getCorrupt_record());
        return statement;
    }
}
