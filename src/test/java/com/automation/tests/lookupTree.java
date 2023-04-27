package com.automation.tests;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;
import static utils.helper.httpProtocol;

public class lookupTree extends Simulation {

    ChainBuilder lookupTree =
            exec(
                    http("lookup Tree")
                            .get("/api/1/rest/asset/tree/Automation1")
                            .basicAuth("rozer@snaplogic.com", "CanaryEnv16048.")
                            .check(status().is(200))
            );


    ScenarioBuilder patternRead = scenario("lookup Tree").exec(lookupTree);

    {
        setUp(
                patternRead.injectOpen(rampUsers(200).during(2))  // number of
        ).protocols(httpProtocol);
    }
}
