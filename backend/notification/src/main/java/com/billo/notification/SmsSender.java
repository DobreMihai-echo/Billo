package com.billo.notification;

public interface SmsSender {

    void sendSms(SmsRequest smsRequest) throws IllegalAccessException;
}
