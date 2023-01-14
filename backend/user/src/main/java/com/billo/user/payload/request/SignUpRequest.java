package com.billo.user.payload.request;

import lombok.Data;

import java.util.Set;

@Data
public class SignUpRequest {

    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Set<String> role;
    private String password;
}
