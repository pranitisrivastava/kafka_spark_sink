package com.cv.polaris.mer.stream.utils;

public class Constants {

    public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static final String ORACLE_URL = "oracle.url";
    public static final String ORACLE_USERNAME = "oracle.username";
    public static final String ORACLE_PASSWORD = "oracle.password";
    public static final String KAFKA_BOOTSTRAP_SERVER = "kafka.bootstrap.servers";
    public static final String KAFKA_TOPIC_NAME = "kafka.topic.name";
    public static final String CONFIG_PROPERTIES_FILENAME = "config.properties";
    public static final String INSERT_INTO_EXCEPTION_FUN_TABLE = "INSERT INTO exception_functional"
            + "(exception_key,job_id,job_sequence,job_scheduler,exception_id,tenant,business_category,"
            + "business_subcategory,client_name,origin,component,sub_component,exception_type,exception_subtype,"
            + "status,severity,owned_by,exception_message,exception_message_detail,exception_data,retry_count,"
            + "exception_created_time,remediation_status,resolved_by,resolved_time,created_by,created_time,"
            + "updated_by,updated_time,corrupt_record)"
            + "VALUES(EXCEPTION_KEY_SEQ.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP,?,CURRENT_TIMESTAMP,?)";

    public static final String INSERT_INTO_EXCEPTION_FUN_PARTIAL_TABLE = "INSERT INTO exception_fun"
            + "(exception_key,job_id,job_sequence,job_scheduler,exception_id,tenant)"
            + "VALUES(?,?,?,?,?,?)";

    public static final String INSERT_INTO_EXCEPTION_PARTIAL_2_FUN_TABLE = "INSERT INTO exception_fun"
            + "(exception_key,job_id,job_sequence,job_scheduler,exception_id,tenant,business_category,"
            + "business_subcategory,client_name,origin,component,sub_component,exception_type,exception_subtype,"
            + "status,severity,owned_by,exception_message,exception_message_detail,exception_data,retry_count)"
            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public static final String TEMP = "INSERT INTO exception_fun"
            + "(exception_key,exception_created_time)"
            + "VALUES(?,?)";
}
