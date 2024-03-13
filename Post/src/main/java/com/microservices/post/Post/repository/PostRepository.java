package com.microservices.post.Post.repository;

import com.microservices.post.Post.entity.Post;
import com.microservices.post.Post.payload.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post , String > {
}
