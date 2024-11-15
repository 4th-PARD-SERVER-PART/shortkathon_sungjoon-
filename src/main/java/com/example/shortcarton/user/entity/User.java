package com.example.shortcarton.user.entity;

import com.example.shortcarton.post.entity.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Schema(description = "사용자 엔티티")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "사용자 ID", example = "1", required = true)
    private Long id;

    @Setter
    @Column(nullable = false)
    @Schema(description = "사용자 이름", example = "조성준", required = true)
    private String username;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "사용자가 작성한 게시물 목록")
    private List<Post> post;
}
