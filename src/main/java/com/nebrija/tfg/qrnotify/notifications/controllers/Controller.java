package com.nebrija.tfg.qrnotify.notifications.controllers;

import com.nebrija.tfg.qrnotify.notifications.model.api.ApiGeneralResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${chen.base_path}")
public class Controller implements TestApi {

    @Override
    public ResponseEntity<ApiGeneralResponse> test() {
        ApiGeneralResponse generalResponse = new ApiGeneralResponse();
        generalResponse.setMessage("Test OK");
        return ResponseEntity.ok(generalResponse);
    }

}
