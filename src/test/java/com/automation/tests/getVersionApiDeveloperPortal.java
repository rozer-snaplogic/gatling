package com.automation.tests;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;
import static utils.helper.httpProtocol;

public class getVersionApiDeveloperPortal extends Simulation {

    ChainBuilder getVersionApiDeveloperPortal =
            exec(
                    http("Get Version Api Developer Portal")
                            .get("/api/2/5be4a4cded5edc0017b9aa70/rest/api_developer_portal/version")
                            .basicAuth("rozer@snaplogic.com", "CanaryEnv16048.")
                            .check(status().is(200))
            );


    ScenarioBuilder getVersionApiDevelopersPortal = scenario("Get Version Api Developer Portal").exec(getVersionApiDeveloperPortal);

    {
        setUp(
                getVersionApiDevelopersPortal.injectOpen(rampUsers(200).during(2))  // number of
        ).protocols(httpProtocol);
    }
}
