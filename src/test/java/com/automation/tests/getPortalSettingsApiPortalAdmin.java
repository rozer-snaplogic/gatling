package com.automation.tests;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;
import static utils.helper.httpProtocol;

public class getPortalSettingsApiPortalAdmin extends Simulation {

    ChainBuilder getPortalSettingsApiPortalAdmin =
            exec(
                    http("Get Portal Settings Api Portal Admin")
                            .get("/api/2/5be4a4cded5edc0017b9aa70/rest/api_portal_admin/settings")
                            .basicAuth("rozer@snaplogic.com", "CanaryEnv16048.")
                            .check(status().is(200))
            );


    ScenarioBuilder getPortalSettingApiPortalAdmin = scenario("Get Portal Settings Api Portal Admin").exec(getPortalSettingsApiPortalAdmin);

    {
        setUp(
                getPortalSettingApiPortalAdmin.injectOpen(rampUsers(200).during(2))  // number of
        ).protocols(httpProtocol);
    }
}
