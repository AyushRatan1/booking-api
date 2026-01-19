package core;

import config.ConfigManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecFactory {

    private static RequestSpecification requestSpec;

    private RequestSpecFactory() {}

    public static RequestSpecification getRequestSpec() {

        if (requestSpec == null) {
            requestSpec = new RequestSpecBuilder()
                    .setBaseUri(ConfigManager.get("base.url"))
                    .addHeader("api_key", ConfigManager.get("api.key")) //Petstore User auth
                    .setContentType(ContentType.JSON)
                    .build();
        }
        return requestSpec;
    }
}
