package com.billo.user.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class JwtResponse {

    private final String token;
    private String type = "Bearer ";
    private final Long id;
    private final String username;
    private final String email;
    private final List<String> roles;

}
