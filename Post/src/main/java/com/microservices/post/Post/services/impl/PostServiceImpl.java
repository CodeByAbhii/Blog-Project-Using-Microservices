package com.microservices.post.Post.services.impl;

import com.microservices.post.Post.config.RestTemplateConfig;
import com.microservices.post.Post.entity.Post;
import com.microservices.post.Post.payload.PostDto;
import com.microservices.post.Post.repository.PostRepository;
import com.microservices.post.Post.services.PostService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {


    private PostRepository postRepository;

    private RestTemplateConfig restTemplateConfig;

    public PostServiceImpl(PostRepository postRepository , RestTemplateConfig restTemplateConfig ) {
        this.postRepository = postRepository;
        this.restTemplateConfig = restTemplateConfig;
    }

    @Override
    public Post cretePost(Post post) {
        // create PostId Automaitacally by random UUId() methods
        String postId = UUID.randomUUID().toString();
        post.setId(postId);
        Post savedPost = postRepository.save(post);
        return savedPost;
    }

    @Override
    public List<Post> getAllPost() {
        List<Post> getAllPost = postRepository.findAll();
        return getAllPost;
    }

    @Override
    public Post getPostByPostId(String postId) {
        Post post = postRepository.findById(postId).get();
        return post;
    }

    @Override
    public PostDto getPostWithAllComments(String postId) {
        Post post = postRepository.findById(postId).get();
        ArrayList getAllComment = restTemplateConfig.getRestTemplate().getForObject("http://localhost:8083/api/comments/" + postId, ArrayList.class);

       PostDto dto = new PostDto();
       dto.setId(post.getId());
       dto.setTitle(post.getTitle());
       dto.setDescription(post.getDescription());
       dto.setContent(post.getContent());
       dto.setComments(getAllComment);
        return dto;
    }
}
