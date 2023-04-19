package com.automation.tests;

import static utils.helper.httpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import io.gatling.javaapi.core.*;

public class triggeredTask extends Simulation {

    ChainBuilder triggeredTask =
            exec(
                    http("Triggered Task")
                            .post("/api/1/rest/slsched/job?duplicate_check=true")
                            .basicAuth("rozer@snaplogic.com", "StageEnvironment16048.")
                            .check(status().is(200))
            );


    ScenarioBuilder task = scenario("triggeredTask").exec(triggeredTask);

    {
        setUp(
                task.injectOpen(rampUsers(100).during(2))  // number of
        ).protocols(httpProtocol);
    }
}

