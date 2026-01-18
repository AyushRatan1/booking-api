package com.booker.api.client;

import com.booker.api.models.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * API Client for Users endpoint - 4 CRUD operations
 * 
 * Base URL: https://jsonplaceholder.typicode.com
 * Endpoint: /users
 */
public class UserClient {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final String USERS = "/users";

    public UserClient() {
        RestAssured.baseURI = BASE_URL;
    }

    // ============ 1. GET - Read a user ============
    // GET /users/{id}
    public Response getUser(int id) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get(USERS + "/" + id);
    }

    // ============ 2. POST - Create a user ============
    // POST /users
    public Response createUser(User user) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(user)
                .post(USERS);
    }

    // ============ 3. PUT - Update entire user ============
    // PUT /users/{id}
    public Response updateUser(int id, User user) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(user)
                .put(USERS + "/" + id);
    }

    // ============ 4. DELETE - Delete a user ============
    // DELETE /users/{id}
    public Response deleteUser(int id) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .delete(USERS + "/" + id);
    }
}
