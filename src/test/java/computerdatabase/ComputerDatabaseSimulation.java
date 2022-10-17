package computerdatabase;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

public class ComputerDatabaseSimulation extends Simulation {

    ChainBuilder pipeline =
            exec(
                http("List of Deleted Accounts")
                    .get("/api/1/rest/asset/list/Automation1/_rbin?asset_type=Pipeline&limit=25&offset=0&sort=metadata.d_time:-1&search=")
                        .basicAuth("rozer@snaplogic.com", "CanarySnap16048.")
                        .check(status().is(200))
            );

    ChainBuilder account =
            exec(
                    http("List of Deleted Accounts")
                            .get("/api/1/rest/asset/list/Automation1/_rbin?asset_type=Account&limit=25&offset=0&sort=metadata.d_time:-1&search=")
                            .basicAuth("rozer@snaplogic.com", "CanarySnap16048.")
                            .check(status().is(200))
            );

    ChainBuilder tasks =
            exec(
                    http("List of Deleted Accounts")
                            .get("/api/1/rest/asset/list/Automation1/_rbin?asset_type=Job&limit=25&offset=0&sort=metadata.d_time:-1&search=")
                            .basicAuth("rozer@snaplogic.com", "CanarySnap16048.")
                            .check(status().is(200))
            );

    ChainBuilder projectFolder =
            exec(
                    http("List of Deleted Accounts")
                            .get("/api/1/rest/asset/list/Automation1/_rbin?asset_type=Dir&limit=25&offset=0&sort=metadata.d_time:-1&search=")
                            .basicAuth("rozer@snaplogic.com", "CanarySnap16048.")
                            .check(status().is(200))
            );

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
                            .basicAuth("rozer@snaplogic.com", "Stage16048.")
                            .check(status().is(200))
            );

    ChainBuilder ccMetrics =
            exec(
                    http("Snaplex ccMetrics API")
                            .post("/api/1/rest/slserver/fetch_snaplex_cc_metrics?subscriber_id=automation&start_ts=1666027346512&end_ts=1666028246512")
                            .basicAuth("rozer@snaplogic.com", "Stage16048.")
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

    ScenarioBuilder users = scenario("Users").exec(ccMetrics);

    {
        setUp(
            users.injectOpen(rampUsers(10).during(2))
        ).protocols(httpProtocol);
    }
}
