package com.example.shortcarton.post.controller;

import com.example.shortcarton.post.dto.PostDto;
import com.example.shortcarton.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "게시물 관리", description = "게시물 생성, 수정, 조회, 삭제 등 게시물 관리 API")
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @Operation(
            summary = "게시물 생성",
            description = """
                    새로운 게시물을 생성.
                    @param title - 게시물 제목
                    @param file - 업로드할 음성 파일
                    @return 201 Created 상태의 ResponseEntity
                    """
    )
    @PostMapping("/create")
    public ResponseEntity<Void> createPost(@RequestParam String title,
                                           @RequestParam(required = false) MultipartFile file) throws IOException {
        postService.createPost(title, file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "게시물 상세 조회",
            description = """
                    게시물 ID를 기준으로 게시물의 상세 정보를 조회.
                    @param postId - 조회할 게시물의 ID
                    @return 게시물의 상세 정보와 200 OK 상태 반환
                    """
    )
    @GetMapping("/detail/{postId}")
    public ResponseEntity<PostDto.createRes> detailPost(@PathVariable Long postId) {
        PostDto.createRes post = postService.detailPost(postId);
        return ResponseEntity.ok(post);
    }

    @Operation(
            summary = "게시물 삭제",
            description = """
                    게시물 ID를 기준으로 게시물을 삭제.
                    @param postId - 삭제할 게시물의 ID
                    @return 성공 시 204 No Content 상태 반환
                    """
    )
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }
}
