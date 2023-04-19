package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static environment.Environment.API_URL;

public class RequestSpecUtil {
    public static RequestSpecification NonAuthRequestSpec() {
        RequestSpecification spec = new RequestSpecBuilder()
                .setBaseUri(API_URL)
                .setContentType("application/json")
                .build();
        return spec;
    }

    public static RequestSpecification NonAuthRequestSpecWithBody(String body) {
        RequestSpecification spec = NonAuthRequestSpec()
                .body(body);
        return spec;
    }

    public static RequestSpecification AuthRequestSpec(String token) {
        RequestSpecification spec = NonAuthRequestSpec()
                .header("Authorization", "Bearer " + token);
        return spec;
    }

    public static RequestSpecification AuthRequestSpecWithBody(String token, String body) {
        RequestSpecification spec = AuthRequestSpec(token)
                .body(body);
        return spec;
    }

}
