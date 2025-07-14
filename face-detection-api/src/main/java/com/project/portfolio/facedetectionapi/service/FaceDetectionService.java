package com.project.portfolio.facedetectionapi.service;


import com.project.portfolio.facedetectionapi.client.FaceRecognitionApiClient;
import com.project.portfolio.facedetectionapi.dto.FaceDetectionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class FaceDetectionService {

    @Autowired
    private FaceRecognitionApiClient faceRecognitionApiClient;

    public FaceDetectionResponse detectFaces(MultipartFile imageFile) throws IOException {
        if(imageFile == null || imageFile.isEmpty()){
            throw new IllegalArgumentException("Arquivo de imagem não pode ser vazio ou nulo");
        }

        String contentType = imageFile.getContentType();
        if(contentType == null || !contentType.startsWith("image/")){
            throw new IllegalArgumentException("Arquivo deve ser uma imagem");
        }

        String imageBase64 = convertToBase64(imageFile);

        Map<String, String> request = new HashMap<>();
        request.put("image", imageBase64);

        FaceDetectionResponse response;
        response = faceRecognitionApiClient.detectFaces(request);

        if (!response.isSuccess() || response.getProcessedImage() == null){
            throw new RuntimeException("Erro no processamento da imagem pelo Flask");
        }

        return response;
    }

    private String convertToBase64(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }

    public byte[] convertBase64ToBytes(String base64){
        return Base64.getDecoder().decode(base64);
    }
}
