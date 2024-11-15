package com.example.shortcarton.user.entity;

import com.example.shortcarton.post.entity.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String username;

    @OneToMany(mappedBy="user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Post> post;

}
