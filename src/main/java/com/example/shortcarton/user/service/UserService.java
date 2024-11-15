package com.example.shortcarton.user.service;

import com.example.shortcarton.post.dto.PostDto;
import com.example.shortcarton.post.entity.Post;
import com.example.shortcarton.post.repository.PostRepository;
import com.example.shortcarton.user.dto.UserDto;
import com.example.shortcarton.user.entity.User;
import com.example.shortcarton.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public void createUser(UserDto.createReq userReq) {
        User user = new User();
        user.setUsername(userReq.getUsername());
        userRepository.save(user);
    }
    public List<PostDto.createReq> list(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        List<PostDto.createReq> posts = new ArrayList<>();
        if (user != null) {
            List<Post> userPosts = postRepository.findByUserId(userId);
            for (Post post : userPosts) {
                PostDto.createReq detail = new PostDto.createReq();
                detail.setText(post.getText());
                posts.add(detail);
            }
        }
        return posts;
    }

}
