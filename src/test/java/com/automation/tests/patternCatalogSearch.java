package com.automation.tests;

import static utils.helper.httpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import io.gatling.javaapi.core.*;

public class patternCatalogSearch extends Simulation {

    ChainBuilder patternCatalogSearch =
            exec(
                    http("Search pattern API")
                            .post("/api/2/5806521b242e3f794b90c16c/rest/pattern/search")
                            .formParam("text","")
                            .basicAuth("rozer@snaplogic.com", "StageEnvironment16048.")
                            .check(status().is(200))
            );


    ScenarioBuilder patternSearch = scenario("patternCatalogSearch").exec(patternCatalogSearch);

    {
        setUp(
                patternSearch.injectOpen(rampUsers(100).during(2))  // number of
        ).protocols(httpProtocol);
    }
}
