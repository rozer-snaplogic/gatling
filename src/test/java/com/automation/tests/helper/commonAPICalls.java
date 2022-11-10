package com.automation.tests.helper;

import io.gatling.javaapi.core.ChainBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;


import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class commonAPICalls {

    public ChainBuilder getRequest(String name, String endpoint, Integer status){
        return exec(
                http(name)
                        .get(endpoint)
                        .basicAuth("rozer@snaplogic.com", "CanarySnap16048.")
                        .check(status().is(status))
        );
    }

    public ChainBuilder postRequest(String name, String endpoint, Integer status){
        return exec(
                http(name)
                        .post(endpoint)
                        .basicAuth("rozer@snaplogic.com", "CanarySnap16048.")
                        .check(status().is(status))
        );
    }

    public ChainBuilder postRequest(String name, String endpoint, Integer status,String fileName) throws IOException {

        String json = null;

        try {
            JSONParser parser = new JSONParser();
            JSONObject data = (JSONObject) parser.parse(
                    new FileReader("src/test/java/com/automation/tests/testData/"+fileName+".json"));//path to the JSON file.
            json = data.toJSONString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return exec(
                http(name)
                        .post(endpoint)
                        .body()
                        .basicAuth("rozer@snaplogic.com", "CanarySnap16048.")
                        .check(status().is(status))
        );
    }


}
