package com.automation.tests;

import static utils.helper.httpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import io.gatling.javaapi.core.*;

public class ccMetrics extends Simulation {


    ChainBuilder ccMetrics =
            exec(
                    http("Snaplex ccMetrics API")
                            .post("/api/1/rest/slserver/fetch_snaplex_cc_metrics?subscriber_id=automation&start_ts=1666027346512&end_ts=1666028246512")
                            .basicAuth("rozer@snaplogic.com", "StageEnvironment16048.")
                            .check(status().is(200))
            );


    ScenarioBuilder metrics = scenario("patternCatalogSearch").exec(ccMetrics);

    {
        setUp(
                metrics.injectOpen(rampUsers(100).during(2))  // number of
        ).protocols(httpProtocol);
    }
}
