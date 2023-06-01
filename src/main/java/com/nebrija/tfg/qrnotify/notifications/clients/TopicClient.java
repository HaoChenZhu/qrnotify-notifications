package com.nebrija.tfg.qrnotify.notifications.clients;

import com.nebrija.tfg.qrnotify.notifications.config.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.nebrija.tfg.qrnotify.topic.model.api.ApiTopicResponseDto;

@FeignClient(name = "qrnotify-topic", url = "${qrnotify.feign.topic}", configuration = FeignClientConfiguration.class)
public interface TopicClient {

    @GetMapping("/topic/owner/{name}")
    ApiTopicResponseDto getTopicByOwner(@PathVariable String name);

    @GetMapping("/topic/{identifier}")
    ApiTopicResponseDto getTopicById(@PathVariable String identifier);

}
