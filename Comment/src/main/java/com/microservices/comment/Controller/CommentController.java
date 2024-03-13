package com.microservices.comment.Controller;


import com.microservices.comment.Services.CommentService;
import com.microservices.comment.entity.Comment;
import com.microservices.comment.payload.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {


    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    // http://localhost:8082/api/comments

    @PostMapping
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment){
        Comment saveComment= commentService.saveComment(comment);
        return new ResponseEntity<>(saveComment , HttpStatus.OK);
    }

    // http://localhost:8082/api/comments/{postId}
    @GetMapping("/{postId}")
    public ResponseEntity<List<Comment>>getAllComment(@PathVariable String postId){
        List<Comment> allComments = commentService.findAllCommentsByPostId(postId);
        return new ResponseEntity<>(allComments , HttpStatus.OK);
    }

   // http://localhost:8082/api/comments/{postId}/comments
    @GetMapping("/{postId}/comments")
    public ResponseEntity<Comment>getCommentsByPostId(String postId){
        Comment commentByPostId = commentService.findCommentByPostId(postId);
        return  new ResponseEntity<>(commentByPostId , HttpStatus.OK);
    }

}
