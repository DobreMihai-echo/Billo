//package com.billo.notification.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class MailgunConfig {
//
//    @Value("${mailgun.api.username}")
//    String mailGunAPIUsername;
//    @Value("${mailgun.api.password}")
//    String mailGunAPIPassword;
//    @Value("${mailgun.api.base.url}")
//    String mailGunAPIBaseUrl;
//    @Value("${mailgun.api.messages.url}")
//    String mailGunAPIMessagesUrl;
//
//    @Bean
//    public String mailGunAPIUsername() {
//        return this.mailGunAPIUsername;
//    }
//
//    @Bean
//    public String mailGunAPIPassword() {
//        return this.mailGunAPIPassword;
//    }
//
//    @Bean
//    public String mailGunAPIBaseUrl() {
//        return this.mailGunAPIBaseUrl;
//    }
//
//    @Bean
//    public String mailGunAPIMessagesUrl() {
//        return this.mailGunAPIMessagesUrl;
//    }
//}
