package com.example.shortcarton.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
public class UserDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class createReq {

        @Schema(description = "사용자 이름", example = "조성준", required = true)
        private String username;
    }
}
