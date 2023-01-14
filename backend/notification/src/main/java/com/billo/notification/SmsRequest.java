package com.billo.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Data
@ToString
public class SmsRequest {

    private final String phoneNumber;
    private final String message;


}
