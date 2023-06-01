package com.nebrija.tfg.qrnotify.notifications.clients.impl;

import com.nebrija.tfg.qrnotify.notifications.clients.AdminClient;
import com.nebrija.tfg.qrnotify.notifications.models.ApiUserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminClientImpl {

    @Autowired
    AdminClient adminClient;

    public ApiUserResponseDto getUserByPhone(String phone) {
        try {
            ApiUserResponseDto apiUserResponseDto = adminClient.getUserByPhone(phone);
            if (apiUserResponseDto == null) {
                System.out.println("Error getting user by phone");
            }
            return apiUserResponseDto;
        } catch (Exception e) {
            System.out.println("Error getting user by phone");
            throw e;
        }
    }

    public List<ApiUserResponseDto> getAllUsers() {
        try {
            List<ApiUserResponseDto> apiUserResponseDto = adminClient.getAllUsers();
            if (apiUserResponseDto == null) {
                System.out.println("Error getting all users");
            }
            return apiUserResponseDto;
        } catch (Exception e) {
            System.out.println("Error getting all users");
            throw e;
        }
    }

}
