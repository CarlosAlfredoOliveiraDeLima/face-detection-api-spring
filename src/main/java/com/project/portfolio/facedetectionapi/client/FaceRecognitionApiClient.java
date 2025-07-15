package com.project.portfolio.facedetectionapi.client;


import com.project.portfolio.facedetectionapi.dto.FaceDetectionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(
    name = "face-recognition-api",
    url = "http://localhost:5000"
)

public interface FaceRecognitionApiClient {
    @PostMapping("/process-image")
    FaceDetectionResponse detectFaces(@RequestBody Map<String, String> request);
}
