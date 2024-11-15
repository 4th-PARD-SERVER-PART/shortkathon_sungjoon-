package com.example.shortcarton.post.entity;


import com.example.shortcarton.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String text;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    public static Post toEntity(String text,User user) {
        return new Post(null,text,user);
    }
}
