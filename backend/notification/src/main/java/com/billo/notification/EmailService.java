package com.billo.notification;

import com.billo.clients.models.Message;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    @Autowired
    final Configuration configuration;
    @Autowired
    final JavaMailSender javaMailSender;

//    @Autowired
//    private SpringTemplateEngine templateEngine;

    public EmailService(Configuration configuration, JavaMailSender javaMailSender) {
        this.configuration = configuration;
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(Message user) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Welcome To SpringHow.com");
        helper.setTo(user.getToEmail());
        String emailContent = getEmailContent(user);
        helper.setText(emailContent, true);
        javaMailSender.send(mimeMessage);
    }

//    public void sendMail(AbstractEmailContext email) throws MessagingException {
//        MimeMessage message = javaMailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,
//                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
//                StandardCharsets.UTF_8.name());
//        Context context = new Context();
//        context.setVariables(email.getContext());
//        String emailContent = templateEngine.process(email.getTemplateLocation(), context);
//
//        mimeMessageHelper.setTo(email.getTo());
//        mimeMessageHelper.setSubject(email.getSubject());
//        mimeMessageHelper.setFrom(email.getFrom());
//        mimeMessageHelper.setText(emailContent, true);
//        javaMailSender.send(message);
//    }

    String getEmailContent(Message user) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("user", user);
        configuration.getTemplate("registrationEmail.ftlh").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }
}
