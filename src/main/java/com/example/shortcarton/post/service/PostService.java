package com.example.shortcarton.post.service;

import com.example.shortcarton.post.dto.PostDto;
import com.example.shortcarton.post.entity.Post;
import com.example.shortcarton.post.repository.PostRepository;
import com.example.shortcarton.user.entity.User;
import com.example.shortcarton.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public void createPost(Long userId, PostDto.createReq req){
        Optional<User> users=userRepository.findById(userId);
        User user = users.get();
        Post post=Post.toEntity(req.getText(),user);
        postRepository.save(post);
    }
    public PostDto.createRes detailPost(Long userId, Long postId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new IllegalArgumentException("User not found");
        }
        User user = userOptional.get();
        Optional<Post> postOptional = postRepository.findById(postId);
        if (!postOptional.isPresent()) {
            throw new IllegalArgumentException("Post not found");
        }
        Post post = postOptional.get();
        if (!post.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("Post does not belong to the user");
        }

        return new PostDto.createRes(postId, post.getText());
    }

    public void deletePost(Long userId, Long postId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new IllegalArgumentException("User not found");
        }
        User user = userOptional.get();
        Optional<Post> postOptional = postRepository.findById(postId);
        if (!postOptional.isPresent()) {
            throw new IllegalArgumentException("Post not found");
        }
        Post post = postOptional.get();
        if (post.getUser().getId().equals(user.getId())) {
            postRepository.delete(post);
        } else {
            throw new IllegalArgumentException("Post does not belong to the user");
        }
    }







}
