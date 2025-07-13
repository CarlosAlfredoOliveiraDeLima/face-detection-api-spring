package com.project.portfolio.facedetectionapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FaceDetectionApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FaceDetectionApiApplication.class, args);
    }

}
