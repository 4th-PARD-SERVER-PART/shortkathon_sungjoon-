package com.example.shortcarton.post.entity;

import com.example.shortcarton.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "게시물 엔티티")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "게시물 ID", example = "1", required = true)
    private Long id;

    @Setter
    @Schema(description = "게시물 제목", example = "예시 게시물.", required = true)
    private String title;

    @Setter
    @Schema(description = "음성 파일 경로", example = "/uploads/audio.mp3", required = false)
    private String audioFilePath;

    @ManyToOne
    @JoinColumn(name="user_id")
    @Schema(description = "게시물을 작성한 사용자", required = true)
    private User user;

    public static Post toEntity(String title, String audioFilePath, User user) {
        return new Post(null, title, audioFilePath, user);
    }
}
