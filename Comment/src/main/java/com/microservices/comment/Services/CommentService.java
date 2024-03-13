package com.microservices.comment.Services;

import com.microservices.comment.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment saveComment(Comment comment);

    List<Comment> findAllCommentsByPostId(String  postId);

    Comment findCommentByPostId(String postId);
}
