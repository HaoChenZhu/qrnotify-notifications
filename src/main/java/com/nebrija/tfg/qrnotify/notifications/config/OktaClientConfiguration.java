package com.nebrija.tfg.qrnotify.notifications.config;

import com.okta.sdk.authc.credentials.TokenClientCredentials;
import com.okta.sdk.client.Client;
import com.okta.sdk.client.Clients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OktaClientConfiguration {

    @Autowired
    private OktaProperties oktaProperties;

    @Bean
    public Client oktaClient() {
        return Clients.builder()
                .setOrgUrl(oktaProperties.getOrgUrl())
                .setClientCredentials(new TokenClientCredentials(oktaProperties.getToken()))
                .build();
    }
}