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

        @Schema(description = "게시물 제목", example = "This is a new post text", required = true)
        private String title;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class createRes {

        @Schema(description = "게시물 ID", example = "1")
        private Long postId;

        @Schema(description = "게시물 제목", example = "This is the post text.")
        private String title;

        @Schema(description = "음성 파일 경로", example = "/uploads/audio.mp3")
        private String audioFilePath;

        public static List<createRes> postList(List<Post> posts) {
            List<createRes> resList = new ArrayList<>();
            for (Post post : posts) {
                createRes res = new createRes(post.getId(), post.getTitle(), post.getAudioFilePath());
                resList.add(res);
            }
            return resList;
        }
    }
}
