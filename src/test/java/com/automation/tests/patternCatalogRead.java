package com.automation.tests;

import static utils.helper.httpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import io.gatling.javaapi.core.*;

public class patternCatalogRead extends Simulation {

    ChainBuilder patternCatalogRead =
            exec(
                    http("Read pattern API")
                            .get("/api/2/5963cdb3242e3f0a03a86478/rest/pattern/628fbffe04a5702991f2bf0e")
                            .basicAuth("rozer@snaplogic.com", "StageEnvironment16048.")
                            .check(status().is(200))
            );


    ScenarioBuilder patternRead = scenario("patternCatalogRead").exec(patternCatalogRead);

    {
        setUp(
                patternRead.injectOpen(rampUsers(100).during(2))  // number of
        ).protocols(httpProtocol);
    }
}

