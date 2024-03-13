package com.microservices.comment.Services.impl;

import com.microservices.comment.Services.CommentService;
import com.microservices.comment.config.RestTemplateConfig;
import com.microservices.comment.entity.Comment;
import com.microservices.comment.payload.Post;
import com.microservices.comment.repository.CommentRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;


@Service
public class CommentServiceImpl implements CommentService {


    private CommentRepository commentRepository;

    private RestTemplateConfig restTemplate;

    public CommentServiceImpl(CommentRepository commentRepository , RestTemplateConfig restTemplate) {
        this.commentRepository = commentRepository;
        this.restTemplate = restTemplate;
    }



    @Override
    public Comment saveComment(Comment comment) {


        // http://localhost:8082/api/comments/{postId}
        // microservices part with API
        Post post = restTemplate.getRestTemplate().getForObject("http://localhost:8081/api/post/"+comment.getPostId(),Post.class);

       if(post!=null){
           String commentId = UUID.randomUUID().toString();
           comment.setCommentId(commentId);
           Comment savedComment = commentRepository.save(comment);
           return savedComment;

       }else {
           return null;
       }

    }

    @Override
    public List<Comment> findAllCommentsByPostId(String postId) {
        List<Comment> byPostId = commentRepository.findByPostId(postId);
        return byPostId;
    }

    @Override
    public Comment findCommentByPostId(String postId) {
        Comment commentByPostId = commentRepository.findCommentByPostId(postId);
        return commentByPostId;
    }


}
