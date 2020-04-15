package com.cv.polaris.mer.stream.utils;

import com.cv.polaris.mer.stream.model.FunctionalException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Row;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InvalidJsonHandler implements Serializable {

    public FunctionalException populateCorruptRecordField(FunctionalException func)
    {
        if ( ! isJSONValid(func.getValue()) )
        {
            func.setCorrupt_record(func.getValue());
        }
        return func;
    }
    public static boolean isJSONValid(String jsonInString ) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(jsonInString);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
