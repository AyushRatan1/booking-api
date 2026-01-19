package utils;

import core.RequestSpecFactory;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestUtils {

    private RestUtils() {}

    public static Response post(String endpoint, Object body) {
        return given()
                .spec(RequestSpecFactory.getRequestSpec())
                .log().all()                 //REQUEST LOG
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .log().all()                 //RESPONSE LOG
                .extract()
                .response();
    }

    public static Response get(String endpoint, Map<String, String> pathParams) {
        return given()
                .spec(RequestSpecFactory.getRequestSpec())
                .log().all()
                .pathParams(pathParams)
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }


    public static Response getWithQueryParams(String endpoint, Map<String, String> queryParams) {
        return given()
                .spec(RequestSpecFactory.getRequestSpec())
                .log().all()
                .queryParams(queryParams)
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public static Response put(String endpoint, Map<String, String> pathParams, Object body) {
        return given()
                .spec(RequestSpecFactory.getRequestSpec())
                .log().all()
                .pathParams(pathParams)
                .body(body)
                .when()
                .put(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public static Response delete(String endpoint, Map<String, String> pathParams) {
        return given()
                .spec(RequestSpecFactory.getRequestSpec())
                .log().all()
                .pathParams(pathParams)
                .when()
                .delete(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }
}
