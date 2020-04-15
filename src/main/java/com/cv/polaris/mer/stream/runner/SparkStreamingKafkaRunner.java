package com.cv.polaris.mer.stream.runner;

import com.cv.polaris.mer.stream.model.FunctionalException;
import com.cv.polaris.mer.stream.sink.JDBCSink;
import com.cv.polaris.mer.stream.utils.*;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.*;
import org.apache.spark.sql.streaming.OutputMode;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.apache.spark.sql.streaming.Trigger;
import org.apache.spark.sql.types.StructType;
import org.slf4j.LoggerFactory;

public class SparkStreamingKafkaRunner {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SparkStreamingKafkaRunner.class);
    public static void main(String[] args) throws StreamingQueryException {
        System.setProperty("hadoop.home.dir", "C:\\Installations\\hadoop");
        SparkSession spark = SparkSession.builder().appName("SparkApp").config("spark.master", "local[4]").
                config("spark.serializer", "org.apache.spark.serializer.KryoSerializer").getOrCreate();
        PropertyHandler propertyHandler = new PropertyHandler(Constants.CONFIG_PROPERTIES_FILENAME);
        SchemaBuilder schemaBuilder = new SchemaBuilder();
        DataValidator dataValidator = new DataValidator();
        StructType schema = schemaBuilder.buildSchema();
        System.out.println (schema);


        Dataset<Row> datasetFromKafka = spark
                .readStream()
                .format("kafka")
                .option("kafka.bootstrap.servers", propertyHandler.getPropValue(Constants.KAFKA_BOOTSTRAP_SERVER))
                .option("subscribe", propertyHandler.getPropValue(Constants.KAFKA_TOPIC_NAME))
                .option("startingOffsets", "earliest")
                .option("failOnDataLoss", false)
                .load();

        datasetFromKafka.printSchema();
       // Dataset<Row> ds2= datasetFromKafka.selectExpr("CAST(key AS STRING)","CAST(value AS STRING)");
        datasetFromKafka = datasetFromKafka.selectExpr("CAST(key AS STRING)","CAST(value AS STRING)","CAST(offset AS STRING)");
        datasetFromKafka= datasetFromKafka.select(datasetFromKafka.col("key"),datasetFromKafka.col("value"),datasetFromKafka.col("offset"),
                functions.from_json(datasetFromKafka.col("value"),schema).alias("tmp")).select("key","value","offset","tmp.*");
        System.out.println("schema of kafka message after applying explicit schema :");
        datasetFromKafka.printSchema();
        Dataset<FunctionalException> datasetPojo = datasetFromKafka.as(Encoders.bean(FunctionalException.class));
     //   Dataset<FunctionalException> validDataset = datasetPojo.filter((FilterFunction<FunctionalException>) r -> dataValidator.isValidJson(r));
        Encoder<FunctionalException> functionalExceptionEncoder = Encoders.bean(FunctionalException.class);
        InvalidJsonHandler invalidJsonHandler = new InvalidJsonHandler();
        Dataset<FunctionalException> exceptionDataset = datasetPojo.map((MapFunction<FunctionalException, FunctionalException>) value -> invalidJsonHandler.populateCorruptRecordField(value), functionalExceptionEncoder);

        //kafka sink for testing purpose
/*
        StreamingQuery query = ds2.writeStream().format("kafka")
                .option("kafka.bootstrap.servers", "localhost:9092")
                .option("topic", "test")
                .option("checkpointLocation", "/user/root/chk")
                .start();

*/
        ForeachWriter jdbcWriter = new JDBCSink();
        StreamingQuery query = exceptionDataset.writeStream()
                .foreach(jdbcWriter)
                .option("checkpointLocation", "/user/root/chk")
                .outputMode(OutputMode.Update())
                .trigger(Trigger.ProcessingTime(1800))
                .start();

        query.awaitTermination();


    }
}
