package com.microservices.post.Post.services;

import com.microservices.post.Post.entity.Post;
import com.microservices.post.Post.payload.PostDto;

import java.util.List;

public interface PostService {
    Post cretePost(Post post);
    
    List<Post>getAllPost();

    Post getPostByPostId(String postId);

    PostDto getPostWithAllComments(String postId);



}
