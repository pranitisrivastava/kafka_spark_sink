package com.cv.polaris.mer.stream.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;
import org.json4s.jackson.Json;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SchemaBuilder {

    public StructType buildSchema() {
        HashMap<String,String> columnDetailsArray = extractColumnDetailsFromJson();
        StructType schema = new StructType();
       // columnDetailsArray.forEach((k,v) -> System.out.println("key " + k + " value " + v));
        for (Map.Entry<String, String> entry : columnDetailsArray.entrySet()) {
            schema = schema.add(entry.getKey(), getColumnDatatype(entry.getValue()));
        }
        return schema;
    }

    public DataType getColumnDatatype(String datatype) {
        DataType dataType = null;
        switch (datatype) {
            case "string" :
                dataType = DataTypes.StringType;
                break;
            case "bigint" :
                dataType = DataTypes.LongType;
                break;
            case "number" :
                dataType = DataTypes.IntegerType;
                break;
            case "timestamp" :
                dataType = DataTypes.TimestampType;
                break;
            default:
                throw new IllegalArgumentException("Invalid data type: " + datatype);
        }
        return dataType;

    }
    public  String getStringContentsFromJsonFile(String absolutePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(absolutePath));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");
        try {
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }
    public static JsonNode getObjectFromJsonString(String jsonString) throws IOException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonString);
        return jsonNode;
    }

    public  JsonNode getJsonObjectFromJsonFile(String resourceFilepath) throws IOException {
        String stringContentsFromResourceFile = getStringContentsFromJsonFile(resourceFilepath);
        JsonNode jsonObject = getObjectFromJsonString(stringContentsFromResourceFile);
        return jsonObject;
    }


    public HashMap<String, String> extractColumnDetailsFromJson()
    {
        HashMap<String, String> columnDetailsMap = new HashMap<String, String>();
        String filePath = "C:\\Users\\Praniti Srivastava\\IdeaProjects\\SparkStreamingKafka\\src\\main\\resources\\exception-functional.json";
        try {
            JsonNode exception = getJsonObjectFromJsonFile(filePath);
            JsonNode columnDetailsArray = exception.get("exceptionFunctional").get("columnDetails");
            JsonNode columnTemp;
            for (int i=0; i<columnDetailsArray.size();i++) {
                columnTemp = columnDetailsArray.get(i);
                columnDetailsMap.put(columnTemp.get("columnName").textValue(),columnTemp.get("columnDatatype").textValue());
                //System.out.print(columnDetailsMap);
            }
            //System.out.print("Final" + columnDetailsMap);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return columnDetailsMap;
    }

    //extract column details for validation

    public HashMap<String, ArrayList<String>> extractColumnDetailsForValidationFromJson(String fname)
    {
        HashMap<String, ArrayList<String>> columnDetailsMap = new HashMap<String, ArrayList<String>>();
        String filePath = "C:\\Users\\Praniti Srivastava\\IdeaProjects\\SparkStreamingKafka\\src\\main\\resources\\exception-functional.json";
        //String filePath = "C:\\Users\\Praniti Srivastava\\IdeaProjects\\SparkStreamingKafka\\src\\main\\resources\\exception-functional.json";
        try {
            JsonNode exception = getJsonObjectFromJsonFile(filePath);
            JsonNode columnDetailsArray = exception.get("exceptionFunctional").get("columnDetails");
            JsonNode columnTemp;
            for (int i=0; i<columnDetailsArray.size();i++) {
                columnTemp = columnDetailsArray.get(i);
                ArrayList<String> tempArray = new ArrayList<String>();
                tempArray.add(columnTemp.get("nullable").textValue());
                tempArray.add(columnTemp.get("length").textValue());
                columnDetailsMap.put(columnTemp.get("columnName").textValue(),tempArray);
            }
            System.out.print("Final" + columnDetailsMap);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return columnDetailsMap;
    }




}
