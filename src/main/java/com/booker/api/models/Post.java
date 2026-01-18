package com.booker.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {
    
    @JsonProperty("id")
    private Integer id;
    
    @JsonProperty("userId")
    private Integer userId;
    
    @JsonProperty("title")
    private String title;
    
    @JsonProperty("body")
    private String body;
    
    public Post() {}
    
    public static PostBuilder builder() {
        return new PostBuilder();
    }
    
    public static class PostBuilder {
        private Integer id;
        private Integer userId;
        private String title;
        private String body;
        
        public PostBuilder id(Integer id) { this.id = id; return this; }
        public PostBuilder userId(Integer userId) { this.userId = userId; return this; }
        public PostBuilder title(String title) { this.title = title; return this; }
        public PostBuilder body(String body) { this.body = body; return this; }
        
        public Post build() {
            Post post = new Post();
            post.id = this.id;
            post.userId = this.userId;
            post.title = this.title;
            post.body = this.body;
            return post;
        }
    }
    
    public Integer getId() { return id; }
    public Integer getUserId() { return userId; }
    public String getTitle() { return title; }
    public String getBody() { return body; }
    
    public void setId(Integer id) { this.id = id; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public void setTitle(String title) { this.title = title; }
    public void setBody(String body) { this.body = body; }
}
