package com.nebrija.tfg.qrnotify.notifications.clients.impl;

import com.nebrija.tfg.qrnotify.notifications.clients.TopicClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.nebrija.tfg.qrnotify.topic.model.api.ApiTopicResponseDto;

@Component
@Slf4j
public class TopicClientImpl {

    @Autowired
    TopicClient topicClient;

    public ApiTopicResponseDto getTopicByOwner(String name) {
        try {
            ApiTopicResponseDto apiTopicResponseDto = topicClient.getTopicByOwner(name);
            if (apiTopicResponseDto == null) {
                log.error("Error getting topic by owner");
            }
            return apiTopicResponseDto;
        } catch (Exception e) {
            throw e;
        }
    }

    public ApiTopicResponseDto getTopicById(String identifier) {
        try {
            ApiTopicResponseDto apiTopicResponseDto = topicClient.getTopicById(identifier);
            if (apiTopicResponseDto == null) {
                log.error("Error getting topic by id");
            }
            return apiTopicResponseDto;
        } catch (Exception e) {
            throw e;
        }
    }
}
