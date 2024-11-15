package com.example.shortcarton.post.dto;

import com.example.shortcarton.post.entity.Post;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
public class PostDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    public static class createReq{
        private String text;
    }
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class deleteReq{
        private Long postId;

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class createRes{
        private Long PostId;
        private String text;

        public static List<createRes>postList(List<Post>posts){
            List<createRes> resList = new ArrayList<>();
            for(Post post : posts){
                createRes res = new createRes(post.getId(),post.getText());
                resList.add(res);
            }
            return resList;
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class updateReq{
        private Long postId;
        private String text;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class showReq{
        private Long postId;
        private String text;
    }
}
