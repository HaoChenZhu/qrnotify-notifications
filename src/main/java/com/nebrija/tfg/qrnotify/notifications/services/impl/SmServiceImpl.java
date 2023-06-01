package com.nebrija.tfg.qrnotify.notifications.services.impl;

import com.nebrija.tfg.qrnotify.notifications.services.SmService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import static com.nebrija.tfg.qrnotify.notifications.constants.Constants.*;

@Service
public class SmServiceImpl implements SmService {

    @Autowired
    private Environment environment;
    @Override
    public void sendSms(String to, String message) {
        Twilio.init(environment.getProperty("twilio.account_sid"), environment.getProperty("twilio.auth_token"));
        Message.creator(
                new com.twilio.type.PhoneNumber("+34"+to),
                new com.twilio.type.PhoneNumber(environment.getProperty("twilio.phone_number")),
               TURN_MESSAGE+message).create();
    }

}
