package com.app.ecommerce.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRedis {

    private String email;
    private String password;
    private LocalDateTime time;

}
