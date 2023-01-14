package com.billo.notification;

import com.billo.notification.config.TwilioConfig;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TwilioSmsSender implements SmsSender{

    @Autowired
    private TwilioConfig twilioConfig;

    @Override
    public void sendSms(SmsRequest smsRequest) throws IllegalAccessException {
       if (isPhoneValid(smsRequest.getPhoneNumber())) {
           PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
           PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());
           String message = smsRequest.getMessage();
           MessageCreator creator = Message.creator(
                   to,
                   from,
                   message
           );
           creator.create();
           log.info("Message sent");
       } else {
           throw new IllegalAccessException("Phone number provided is not valid");
       }
    }

    private boolean isPhoneValid(String phoneNumber) {
        return true;

        //TODO: Validate phone numbers
    }
}
