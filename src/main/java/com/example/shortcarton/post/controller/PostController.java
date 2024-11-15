package com.example.shortcarton.post.controller;
import com.example.shortcarton.post.dto.PostDto;
import com.example.shortcarton.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "게시물 관리", description = "게시물 생성, 수정, 조회, 삭제 등 게시물 관리 API")
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @Operation(
            summary = "게시물 생성",
            description = """
                    새로운 게시물을 생성합니다.
                    @param userId - 게시물을 작성할 유저의 ID
                    @param request - 게시물 제목, 내용 등의 정보가 담긴 요청 본문
                    @return 201 Created 상태의 ResponseEntity
                    @exception 유저 ID가 존재하지 않을 경우 404 Not Found 반환
                    """
    )
    @PostMapping("create/{userId}")
    public ResponseEntity<Void> createPost(@PathVariable Long userId, @RequestBody PostDto.createReq request) {
        postService.createPost(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @Operation(
            summary = "게시물 상세 조회",
            description = """
                    게시물 ID를 기준으로 게시물의 상세 정보를 조회합니다.
                    @param postId - 조회할 게시물의 ID
                    @return 게시물의 상세 정보와 200 OK 상태 반환
                    @exception 게시물 ID가 존재하지 않을 경우 404 Not Found 반환
                    """
    )

    @GetMapping("/detail/{userId}/{postId}")
    public ResponseEntity<PostDto.createRes> detailPost(@PathVariable Long userId, @PathVariable Long postId) {
        PostDto.createRes post = postService.detailPost(userId, postId);
        return ResponseEntity.ok(post);
    }
    @Operation(
            summary = "게시물 삭제",
            description = """
                    게시물 ID를 기준으로 게시물을 삭제합니다.
                    @param postId - 삭제할 게시물의 ID
                    @return 성공 시 204 No Content 상태 반환
                    @exception 게시물 ID가 존재하지 않을 경우 404 Not Found 반환
                    """
    )
    @DeleteMapping("/delete/{userId}/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long userId, @PathVariable Long postId) {
        postService.deletePost(userId, postId);
        return ResponseEntity.noContent().build();  // 204 No Content 응답
    }
}
