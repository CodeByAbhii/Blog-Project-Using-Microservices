package com.microservices.post.Post.controller;

import com.microservices.post.Post.entity.Post;
import com.microservices.post.Post.payload.PostDto;
import com.microservices.post.Post.services.PostService;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //http://localhost:8081/api/post

    @PostMapping
    public ResponseEntity<Post>createPost(@RequestBody Post post){
        Post newpPost = postService.cretePost(post);
        return  new ResponseEntity<>(newpPost , HttpStatus.CREATED);
    }

    //http://localhost:8081/api/post

    @GetMapping
    public  ResponseEntity<List<Post>>getAllPost(){
        List<Post> getAllPost = postService.getAllPost();
        return new ResponseEntity<>(getAllPost , HttpStatus.OK);
    }


    // find The postId using get Mapping --- singleId Find

    //http://localhost:8081/api/post/{postId}
    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostByPostId(@PathVariable String postId){
       Post post =  postService.getPostByPostId(postId);
       return  new ResponseEntity<>(post , HttpStatus.OK);
    }

    //http://localhost:8081/api/post/{postId}/comments
    @GetMapping("/{postId}/comments")
    public ResponseEntity<PostDto>getPostWithAllComments(String postId){
        PostDto postWithAllComments = postService.getPostWithAllComments(postId);
        return new ResponseEntity<>(postWithAllComments , HttpStatus.OK);

    }


}
