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
    private final String uploadDir = System.getProperty("user.dir") + "/uploads/";  // 현재 작업 디렉토리를 기준으로 설정

    public void createPost(String title, MultipartFile file) throws IOException {
        String audioFilePath = null;

        // 음성 파일 처리
        if (file != null && !file.isEmpty()) {
            String fileName = UUID.randomUUID().toString() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
            Path filePath = Paths.get(uploadDir + fileName);
            Files.createDirectories(filePath.getParent());  // 부모 디렉토리가 없으면 생성

            try {
                file.transferTo(filePath.toFile());  // 파일을 지정된 경로에 저장
                audioFilePath = "/uploads/" + fileName;  // 웹에서 접근 가능한 경로로 설정
            } catch (IOException e) {
                throw new IOException("File upload failed", e);  // 파일 업로드 실패 시 예외 처리
            }
        }

        // 게시물 생성
        Post post = Post.toEntity(title, audioFilePath, null);  // user 없이 생성
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
