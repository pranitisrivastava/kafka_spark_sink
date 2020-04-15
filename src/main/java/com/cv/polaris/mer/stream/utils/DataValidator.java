package com.cv.polaris.mer.stream.utils;

import com.cv.polaris.mer.stream.model.FunctionalException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.Row;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataValidator implements Serializable {

    public boolean isValidJson(FunctionalException func)
    {
        boolean valid=true;
        if(!isJSONValid(func.getValue()))
        {
            valid = false;
            System.out.println("The json at offset " + func.getOffset() + " is invalid.");
            System.out.println("Invalid json : " + func.getValue());

        }
        return valid;
    }
    public boolean isValidJson(Row r, Broadcast broadcastColumnValidationDetails)
    {
            boolean valid=true;
            String fieldName;
            String nullable;
            Integer length;
            //validate json

            if(!isJSONValid(r.getAs("value")))
            {
                valid = false;
                System.out.println("The json at offset " + r.getAs("offset") + " is invalid.");
                System.out.println("Invalid json : " + r.getAs("value"));

            }
            if(valid) {
                HashMap<String, ArrayList<String>> broadVar = (HashMap<String, ArrayList<String>>) broadcastColumnValidationDetails.getValue();
                for (Map.Entry<String, ArrayList<String>> entry : broadVar.entrySet()) {
                    fieldName = entry.getKey();
                    nullable = entry.getValue().get(0);
                    length = Integer.parseInt(entry.getValue().get(1));
                    if(nullable.equals("false") && r.getAs(fieldName) == null)
                    {
                        valid=false;
                        System.out.println(fieldName + " cannot be null.");
                        System.out.println("Invalid json : " + r.getAs("value"));
                        System.out.println("Invalid json offset : " + r.getAs("offset"));
                        break;
                    }
                    else if ((nullable.equals("false") && r.getAs(fieldName) != null) && (r.getAs(fieldName).toString().length() > length)) {
                        valid = false;
                        System.out.println("Length of " + fieldName + " is incorrect.");
                        System.out.println("Maximum Length of " + fieldName + " can be " + length);
                        System.out.println("Actual Length of " + fieldName + " is " + r.getAs(fieldName).toString().length() );
                        System.out.println("Invalid json : " + r.getAs("value"));
                        System.out.println("Invalid json offset : " + r.getAs("offset"));
                        break;
                    }
                    else if ((nullable.equals("true") && r.getAs(fieldName) != null) && (r.getAs(fieldName).toString().length() > length)) {
                        valid = false;
                        System.out.println("Length of " + fieldName + " is incorrect.");
                        System.out.println("Maximum Length of " + fieldName + " can be " + length);
                        System.out.println("Actual Length of " + fieldName + " is " + r.getAs(fieldName).toString().length() );
                        System.out.println("Invalid json : " + r.getAs("value"));
                        System.out.println("Invalid json offset : " + r.getAs("offset"));
                        break;
                    }
                }
            }
            return valid;
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
