package com.automation.tests;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import com.redis.RedisClient;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

public class Runner extends Simulation {

    ChainBuilder patternCatalogRead =
            exec(
                    http("Read pattern API")
                            .get("/api/2/5963cdb3242e3f0a03a86478/rest/pattern/628fbffe04a5702991f2bf0e")
                            .basicAuth("rozer@snaplogic.com", "Stage16048.")
                            .check(status().is(200))
    );

    ChainBuilder patternCatalogSearch =
            exec(
                    http("Search pattern API")
                            .post("/api/2/5806521b242e3f794b90c16c/rest/pattern/search")
                            .formParam("text","")
                            .basicAuth("rozer@snaplogic.com", "SnapStage16048.")
                            .check(status().is(200))
    );

    ChainBuilder ccMetrics =
            exec(
                    http("Snaplex ccMetrics API")
                            .post("/api/1/rest/slserver/fetch_snaplex_cc_metrics?subscriber_id=automation&start_ts=1666027346512&end_ts=1666028246512")
                            .basicAuth("rozer@snaplogic.com", "Stage16048.")
                            .check(status().is(200))
    );

    ChainBuilder triggeredTask =
            exec(
                    http("Triggered Task")
                            .post("/api/1/rest/slsched/job?duplicate_check=true")
                            .basicAuth("rozer@snaplogic.com", "StageEnv16048.")
                            .check(status().is(200))
    );

    ChainBuilder userLogin =
            exec(
                    http("Triggered Task")
                            .post("/api/1/rest/asset/session?caller=rozer%40snaplogic.com")
                            .basicAuth("rozer@snaplogic.com", "CanarySnap16048.")
                            .check(status().is(200))
    );


    HttpProtocolBuilder httpProtocol =
        http.baseUrl("https://cdn.stage.elastic.snaplogicdev.com")
            .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .acceptLanguageHeader("en-US,en;q=0.5")
            .acceptEncodingHeader("gzip, deflate")
            .userAgentHeader(
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0"
            );


    ScenarioBuilder users = scenario("patternCatalogSearch").exec(patternCatalogSearch);

    {
        setUp(
            users.injectOpen(rampUsers(100).during(2))  // number of
        ).protocols(httpProtocol);
    }
}
