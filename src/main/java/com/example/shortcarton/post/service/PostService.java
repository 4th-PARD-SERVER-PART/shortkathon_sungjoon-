package com.example.shortcarton.post.service;

import com.example.shortcarton.post.dto.PostDto;
import com.example.shortcarton.post.entity.Post;
import com.example.shortcarton.post.repository.PostRepository;
import com.example.shortcarton.user.entity.User;
import com.example.shortcarton.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final String uploadDir = "C:/uploads/";

    public void createPost(Long userId, PostDto.createReq req, MultipartFile file) throws IOException {
        Optional<User> users = userRepository.findById(userId);
        if (!users.isPresent()) {
            throw new IllegalArgumentException("User not found");
        }
        User user = users.get();
        String audioFilePath = null;
        if (file != null && !file.isEmpty()) {
            String fileName = UUID.randomUUID().toString() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
            Path filePath = Paths.get(uploadDir + fileName);


            Files.createDirectories(filePath.getParent());
            file.transferTo(filePath.toFile());

            audioFilePath = "/uploads/" + fileName;
        }
        Post post = Post.toEntity(req.getTitle(), audioFilePath, user);
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

        return new PostDto.createRes(postId, post.getTitle(), post.getAudioFilePath());
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
