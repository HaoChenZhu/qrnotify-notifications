package com.nebrija.tfg.qrnotify.notifications.clients;

import com.nebrija.tfg.qrnotify.notifications.config.FeignClientUserConfiguration;
import com.nebrija.tfg.qrnotify.notifications.models.ApiUserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "qrnotify-admin", url = "${qrnotify.feign.admin}", configuration = FeignClientUserConfiguration.class)
public interface AdminClient {

    @GetMapping("/user/phone/{phone}")
    ApiUserResponseDto getUserByPhone(@PathVariable String phone);

    @GetMapping("/user")
    List<ApiUserResponseDto> getAllUsers();
}

