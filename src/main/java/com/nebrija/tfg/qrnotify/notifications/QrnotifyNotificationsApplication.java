package com.nebrija.tfg.qrnotify.notifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.nebrija.tfg.qrnotify.notifications"})
@EnableFeignClients
public class QrnotifyNotificationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(QrnotifyNotificationsApplication.class, args);
	}

}
