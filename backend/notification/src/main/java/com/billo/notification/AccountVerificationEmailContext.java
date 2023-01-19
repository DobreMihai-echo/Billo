//package com.billo.notification;
//
//import com.billo.clients.models.Message;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import java.util.Map;
//
//public class AccountVerificationEmailContext extends AbstractEmailContext{
//
//    private String token;
//
//
//    public void init(Map<String, Object> context){
//        Message message = (Message) context; // we pass the customer informati
//        put("firstName", message.getFullName());
//        setTemplateLocation("emails/email-verification");
//        setSubject("Complete your registration");
//        setFrom("no-reply@javadevjournal.com");
//        setTo(message.getToPhone());
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//        put("token", token);
//    }
//
//    public void buildVerificationUrl(final String baseURL, final String token){
//        final String url= UriComponentsBuilder.fromHttpUrl(baseURL)
//                .path("/register/verify").queryParam("token", token).toUriString();
//        put("verificationURL", url);
//    }
//}
