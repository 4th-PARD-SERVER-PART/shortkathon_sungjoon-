package com.example.shortcarton.user.dto;

import lombok.*;
import org.springframework.stereotype.Component;


@Component
public class UserDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class createReq{
        private String username;
    }








}
