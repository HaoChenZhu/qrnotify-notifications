package com.nebrija.tfg.qrnotify.notifications.services.impl;

import com.nebrija.tfg.qrnotify.notifications.config.MqttBeans;
import com.nebrija.tfg.qrnotify.notifications.services.MqttService;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqttServiceImpl implements MqttService {

    @Autowired
    private MqttBeans client;
    @Override
    public boolean publish(String topic, String message, int qos, long timeout) {
        try {
            client.publish(topic, message, qos, timeout);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());

        }
    }

    @Override
    public void disconnect() {
        client.disconnect();
    }
}
