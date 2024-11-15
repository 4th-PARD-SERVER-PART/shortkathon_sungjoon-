package com.example.shortcarton.post.repository;


import com.example.shortcarton.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post>findByUserId(Long userid);
}
