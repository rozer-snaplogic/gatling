package com.automation.tests.scenarios;

import io.gatling.javaapi.core.ChainBuilder;
import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class listOfDeleted {

    public static ChainBuilder pipeline(){
        ChainBuilder pipeline =
                exec(
                        http("List of Deleted Accounts")
                                .get("/api/1/rest/asset/list/Automation1/_rbin?asset_type=Pipeline&limit=25&offset=0&sort=metadata.d_time:-1&search=")
                                .basicAuth("rozer@snaplogic.com", "CanarySnap16048.")
                                .check(status().is(200))
        );

        return pipeline;
    }


    public static ChainBuilder account(){
        ChainBuilder account =
                exec(
                        http("List of Deleted Accounts")
                                .get("/api/1/rest/asset/list/Automation1/_rbin?asset_type=Account&limit=25&offset=0&sort=metadata.d_time:-1&search=")
                                .basicAuth("rozer@snaplogic.com", "CanarySnap16048.")
                                .check(status().is(200))
        );

        return account;
    }

    public static ChainBuilder tasks(){
        ChainBuilder tasks =
                exec(
                        http("List of Deleted Accounts")
                                .get("/api/1/rest/asset/list/Automation1/_rbin?asset_type=Job&limit=25&offset=0&sort=metadata.d_time:-1&search=")
                                .basicAuth("rozer@snaplogic.com", "CanarySnap16048.")
                                .check(status().is(200))
        );

        return tasks;
    }

    public static ChainBuilder projectFolder(){
        ChainBuilder projectFolder =
                exec(
                        http("List of Deleted Accounts")
                                .get("/api/1/rest/asset/list/Automation1/_rbin?asset_type=Dir&limit=25&offset=0&sort=metadata.d_time:-1&search=")
                                .basicAuth("rozer@snaplogic.com", "CanarySnap16048.")
                                .check(status().is(200))
        );

        return projectFolder;
    }



}
