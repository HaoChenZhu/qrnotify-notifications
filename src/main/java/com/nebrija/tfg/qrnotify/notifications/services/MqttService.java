package com.nebrija.tfg.qrnotify.notifications.services;

public interface MqttService {
    boolean publish(String topic, String message, int qos, long timeout);
    void disconnect();
}
