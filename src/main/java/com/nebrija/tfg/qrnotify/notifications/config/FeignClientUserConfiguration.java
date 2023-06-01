package com.nebrija.tfg.qrnotify.notifications.config;

import com.nebrija.tfg.qrnotify.notifications.services.AuthUserService;
import com.nebrija.tfg.qrnotify.notifications.utils.JwtUtil;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FeignClientUserConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            // Aquí deberías recuperar el token de la forma que corresponda en tu caso.
            requestTemplate.header("Authorization", "Bearer " + JwtUtil.generateToken());
        };
    }
}
