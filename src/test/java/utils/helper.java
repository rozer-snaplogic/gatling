package utils;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.HttpProtocolBuilder;

public class helper {

    public static ChainBuilder post(String Name, String url) {
        return exec(
                http("Search pattern API")
                        .post(url)
                        .formParam("text","")
                        .basicAuth("autoregplatform+admn11@gmail.com", "StageEnvironment16048.")
                        .check(status().is(200))
        );
    }

    public static HttpProtocolBuilder httpProtocol =
            http.baseUrl("https://cdn.stage.elastic.snaplogicdev.com")
                    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                    .acceptLanguageHeader("en-US,en;q=0.5")
                    .acceptEncodingHeader("gzip, deflate")
                    .userAgentHeader(
                            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0"
                    );
}
