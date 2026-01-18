package com.booker.api.client;

import com.booker.api.models.Post;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostClient {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final String POSTS = "/posts";

    public PostClient() {
        RestAssured.baseURI = BASE_URL;
    }

    public Response getPost(int id) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .get(POSTS + "/" + id);
    }

    public Response createPost(Post post) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(post)
                .post(POSTS);
    }

    public Response updatePost(int id, Post post) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(post)
                .put(POSTS + "/" + id);
    }

    public Response deletePost(int id) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .delete(POSTS + "/" + id);
    }
}
