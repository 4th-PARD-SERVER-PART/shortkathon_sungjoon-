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
    @Schema(description = "게시물 내용", example = "이것은 예시 게시물입니다.", required = true)
    private String text;

    @ManyToOne
    @JoinColumn(name="user_id")
    @Schema(description = "게시물을 작성한 사용자", required = true)
    private User user;

    public static Post toEntity(String text, User user) {
        return new Post(null, text, user);
    }
}
