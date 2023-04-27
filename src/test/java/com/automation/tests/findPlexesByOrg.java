package com.automation.tests;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;
import static utils.helper.httpProtocol;

public class findPlexesByOrg extends Simulation {

    ChainBuilder findPlexesByOrg =
            exec(
                    http("find Plexes By Org")
                            .get("/api/1/rest/plex/org/Automation1")
                            .basicAuth("rozer@snaplogic.com", "CanaryEnv16048.")
                            .check(status().is(200))
            );


    ScenarioBuilder findPlexesByOrganization = scenario("find Plexes By Org").exec(findPlexesByOrg);

    {
        setUp(
                findPlexesByOrganization.injectOpen(rampUsers(200).during(2))  // number of
        ).protocols(httpProtocol);
    }
}
