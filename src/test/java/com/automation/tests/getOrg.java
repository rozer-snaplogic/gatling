package com.automation.tests;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;
import static utils.helper.httpProtocol;

public class getOrg extends Simulation {

    ChainBuilder getOrg =
            exec(
                    http("Get Org")
                            .get("/api/1/rest/admin/org/5be4a4cded5edc0017b9aa70")
                            .basicAuth("rozer@snaplogic.com", "CanaryEnv16048.")
                            .check(status().is(200))
            );


    ScenarioBuilder getOrganization = scenario("Get Org").exec(getOrg);

    {
        setUp(
                getOrganization.injectOpen(rampUsers(200).during(2))  // number of
        ).protocols(httpProtocol);
    }
}
