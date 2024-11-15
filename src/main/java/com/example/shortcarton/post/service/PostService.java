package com.example.shortcarton.post.service;

import com.example.shortcarton.post.dto.PostDto;
import com.example.shortcarton.post.entity.Post;
import com.example.shortcarton.post.repository.PostRepository;
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
    private final String uploadDir = "C:/uploads/";  // 파일 업로드 디렉토리 설정

    public void createPost(PostDto.createReq req, MultipartFile file) throws IOException {
        String audioFilePath = null;

        if (file != null && !file.isEmpty()) {
            String fileName = UUID.randomUUID().toString() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
            Path filePath = Paths.get(uploadDir + fileName);
            Files.createDirectories(filePath.getParent());
            file.transferTo(filePath.toFile());
            audioFilePath = "/uploads/" + fileName;
        }
        Post post = Post.toEntity(req.getTitle(), audioFilePath, null);  // user 없이 생성
        postRepository.save(post);
    }

    // 게시물 상세 조회 (userId 없이)
    public PostDto.createRes detailPost(Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (!postOptional.isPresent()) {
            throw new IllegalArgumentException("Post not found");
        }
        Post post = postOptional.get();
        return new PostDto.createRes(postId, post.getTitle(), post.getAudioFilePath());
    }

    // 게시물 삭제 (userId 없이)
    public void deletePost(Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (!postOptional.isPresent()) {
            throw new IllegalArgumentException("Post not found");
        }
        Post post = postOptional.get();
        postRepository.delete(post);
    }
}
