package com.automation.tests;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;
import static utils.helper.httpProtocol;
import java.time.LocalDate;
import java.time.LocalTime;

public class createProject extends Simulation {
    ChainBuilder createProject =
            exec(
                    http("Create Project")
                            .post("/api/1/rest/public/apim/create_from_project").body(StringBody("{\n" +
                                    "    \"project_path\": \"Automation2/apim/SwaggerPetstore11/1.0.0\",\n" +
                                    "   \"asset_types\" : [\"Job\",\"Pipeline\",\"Policy\",\"Account\",\"File\"],   \n" +
                                    "   \"api\": \""+"ApiName"+ LocalTime.now()+"\",\n" +
                                    "   \"version\": \"1.0\",\n" +
                                    "   \"tags\": [\"new\"],\n" +
                                    "   \"description\": \"Api Description for Testing\",\n" +
                                    "    \"plex_path\": \"Automation2/shared/PlexApim\"}")).header("Content-Type","application/json")
                            .basicAuth("autoregplatform+admn11@gmail.com", "Snaplogic@12345")
                            .check(status().is(200))
            );


    ScenarioBuilder enable = scenario("Create Project").exec(createProject);

    {
        setUp(
                enable.injectOpen(rampUsers(3).during(20) )// number of
        ).protocols(httpProtocol).assertions(global().responseTime().max().lt(20000));
    }

}
