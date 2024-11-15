package com.example.shortcarton.post.service;

import com.example.shortcarton.post.dto.PostDto;
import com.example.shortcarton.post.entity.Post;
import com.example.shortcarton.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import java.io.File;
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
    private final String uploadDir = System.getProperty("user.dir") + "/uploads/";

    public void createPost(String title, MultipartFile file) throws IOException {
        String audioFilePath = null;

        if (file != null && !file.isEmpty()) {
            String fileName = UUID.randomUUID().toString() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
            Path filePath = Paths.get(uploadDir + fileName);
            Files.createDirectories(filePath.getParent());

            try {
                file.transferTo(filePath.toFile());
                audioFilePath = "/uploads/" + fileName;
            } catch (IOException e) {
                throw new IOException("File upload failed", e);
            }
        }

        Post post = Post.toEntity(title, audioFilePath, null);
        postRepository.save(post);
    }

    public PostDto.createRes detailPost(Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (!postOptional.isPresent()) {
            throw new IllegalArgumentException("Post not found");
        }
        Post post = postOptional.get();
        return new PostDto.createRes(postId, post.getTitle(), post.getAudioFilePath());
    }


    public void deletePost(Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (!postOptional.isPresent()) {
            throw new IllegalArgumentException("Post not found");
        }
        Post post = postOptional.get();
        postRepository.delete(post);
    }
}
