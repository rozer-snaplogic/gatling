package com.automation.tests;

import static utils.helper.httpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import io.gatling.javaapi.core.*;

public class enableTask extends Simulation {

    ChainBuilder enableTask =
            exec(
                    http("Enable Task")
                            .post("/api/1/rest/public/task/enable")
                            .formParam("task_path","/automation/Baris/TriggeredTasksAPI/verify_triggeredTask_Timeout_C8882_Task")
                            .basicAuth("rozer@snaplogic.com", "StageEnvironment16048.")
                            .check(status().is(200))
            );


    ScenarioBuilder enable = scenario("EnableTask").exec(enableTask);

    {
        setUp(
                enable.injectOpen(rampUsers(500).during(2))  // number of
        ).protocols(httpProtocol);
    }
}
