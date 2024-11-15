package com.example.shortcarton.post.dto;

import com.example.shortcarton.post.entity.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

public class PostDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    public static class createReq {

        @Schema(description = "게시물 내용", example = "This is a new post text", required = true)
        private String text;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class deleteReq {

        @Schema(description = "삭제할 게시물 ID", example = "1", required = true)
        private Long postId;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class createRes {

        @Schema(description = "게시물 ID", example = "1")
        private Long postId;

        @Schema(description = "게시물 내용", example = "This is the post text.")
        private String text;

        // 게시물 목록 반환을 위한 메서드
        public static List<createRes> postList(List<Post> posts) {
            List<createRes> resList = new ArrayList<>();
            for (Post post : posts) {
                createRes res = new createRes(post.getId(), post.getText());
                resList.add(res);
            }
            return resList;
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class updateReq {

        @Schema(description = "수정할 게시물 ID", example = "1", required = true)
        private Long postId;

        @Schema(description = "수정할 게시물 내용", example = "Updated post text")
        private String text;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class showReq {

        @Schema(description = "게시물 ID", example = "1", required = true)
        private Long postId;

        @Schema(description = "게시물 내용", example = "This is a show request text")
        private String text;
    }
}
