package com.booker.api.tests;

import com.booker.api.client.PostClient;
import com.booker.api.models.Post;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * API Tests for Posts endpoint
 * 
 * Tests cover 4 HTTP operations:
 * 1. GET - Read/Retrieve a post
 * 2. POST - Create a new post
 * 3. PUT - Update an existing post (full replacement)
 * 4. DELETE - Remove a post
 */
public class PostApiTest {

    private PostClient client;

    @BeforeClass
    public void setup() {
        client = new PostClient();
    }

    @Test(description = "GET /posts/1 - Retrieve single post")
    public void testGetPost() {
        Response response = client.getPost(1);

        assertThat("Status should be 200 OK",
                response.getStatusCode(), equalTo(200));
        assertThat("Post ID should be 1",
                response.jsonPath().getInt("id"), equalTo(1));
        assertThat("Post should have a userId",
                response.jsonPath().getInt("userId"), greaterThan(0));
        assertThat("Post should have a title",
                response.jsonPath().getString("title"), not(emptyString()));
        assertThat("Post should have a body",
                response.jsonPath().getString("body"), not(emptyString()));

        System.out.println("GET Response: " + response.jsonPath().getString("title"));
    }

    @Test(description = "POST /posts - Create new post")
    public void testCreatePost() {
        Post newPost = Post.builder()
                .userId(1)
                .title("Test Post Title")
                .body("This is the body of my test post.")
                .build();

        Response response = client.createPost(newPost);

        assertThat("Status should be 201 Created",
                response.getStatusCode(), equalTo(201));
        assertThat("New post should get ID 101",
                response.jsonPath().getInt("id"), equalTo(101));
        assertThat("Title should match",
                response.jsonPath().getString("title"), equalTo("Test Post Title"));
        assertThat("Body should match",
                response.jsonPath().getString("body"), equalTo("This is the body of my test post."));
        assertThat("UserId should match",
                response.jsonPath().getInt("userId"), equalTo(1));

        System.out.println("POST Response: Created post with ID " + response.jsonPath().getInt("id"));
    }

    @Test(description = "PUT /posts/1 - Update existing post")
    public void testUpdatePost() {
        Post updatedPost = Post.builder()
                .userId(1)
                .title("Updated Post Title")
                .body("This body has been updated completely.")
                .build();

        Response response = client.updatePost(1, updatedPost);

        assertThat("Status should be 200 OK",
                response.getStatusCode(), equalTo(200));
        assertThat("ID should still be 1",
                response.jsonPath().getInt("id"), equalTo(1));
        assertThat("Title should be updated",
                response.jsonPath().getString("title"), equalTo("Updated Post Title"));
        assertThat("Body should be updated",
                response.jsonPath().getString("body"), equalTo("This body has been updated completely."));

        System.out.println("PUT Response: Updated post title to '" + response.jsonPath().getString("title") + "'");
    }

    @Test(description = "DELETE /posts/1 - Delete a post")
    public void testDeletePost() {
        Response response = client.deletePost(1);

        assertThat("Status should be 200 OK",
                response.getStatusCode(), equalTo(200));
        assertThat("Response body should be empty object",
                response.getBody().asString(), equalTo("{}"));

        System.out.println("DELETE Response: Post deleted successfully");
    }
}
