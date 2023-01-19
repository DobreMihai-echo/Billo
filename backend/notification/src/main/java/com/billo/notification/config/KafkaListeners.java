package com.billo.notification.config;

import com.billo.clients.models.Message;
import com.billo.notification.EmailService;
import com.billo.notification.SmsRequest;
import com.billo.notification.TwilioSmsSender;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;

@Component
@Slf4j
public class KafkaListeners {

    @Autowired
    private TwilioSmsSender twilioSmsSender;

    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "${spring.kafka.topic.name}")
    void listener(Message data) throws IllegalAccessException, IOException, MessagingException, TemplateException {
        log.info("RECEIVED PPP");
        System.out.println("ReceivedP:" + data);
        if (data.getToEmail()!=null) {
            emailService.sendEmail(data);
        }
        if (data.getToPhone()!=null){
            twilioSmsSender.sendSms(new SmsRequest("+40766849043","Please use this code to activate your account:0000"));
        }
    }

    @KafkaListener(topics = "provider-approval")
    void listener2(Message data) throws IllegalAccessException, IOException, MessagingException, TemplateException {
        log.info("RECEIVED PPP");
        System.out.println("ReceivedP2affvbvsdsdAFASSFGAFGAGFGAGG:" + data);
        if (data.getToEmail()!=null) {
            emailService.sendEmail2(data);
        }
    }

}
