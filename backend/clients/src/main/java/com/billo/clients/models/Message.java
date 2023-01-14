package com.billo.clients.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {

    String username;
    String fullName;
    String toEmail;
    String toPhone;
    String message;
}
