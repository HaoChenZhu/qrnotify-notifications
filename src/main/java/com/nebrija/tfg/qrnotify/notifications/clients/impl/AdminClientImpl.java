package com.nebrija.tfg.qrnotify.notifications.clients.impl;

import com.nebrija.tfg.qrnotify.notifications.clients.AdminClient;
import com.nebrija.tfg.qrnotify.notifications.exceptions.ApiResourceNotFoundException;
import com.nebrija.tfg.qrnotify.notifications.models.ApiUserResponseDto;
import feign.FeignException;
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
                throw new ApiResourceNotFoundException("User not found");
            }
            return apiUserResponseDto;
        } catch (FeignException e) {
            System.out.println("Error getting user by phone");
            throw e;
        } catch (Exception e) {
            System.out.println("Error getting user by phone");
            throw e;
        }
    }

    public List<ApiUserResponseDto> getAllUsers() {
        try {
            List<ApiUserResponseDto> apiUserResponseDto = adminClient.getAllUsers();
            if (apiUserResponseDto == null) {
                throw new ApiResourceNotFoundException("Users not found");
            }
            return apiUserResponseDto;
        } catch (FeignException e) {
            System.out.println("Error getting all users");
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    public ApiUserResponseDto getUserById(String identifier) {
        try {
            ApiUserResponseDto apiUserResponseDto = adminClient.getUserById(identifier);
            if (apiUserResponseDto == null) {
                throw new ApiResourceNotFoundException("User not found");
            }
            return apiUserResponseDto;
        } catch (FeignException e) {
            System.out.println("Error getting user by id");
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }
}
