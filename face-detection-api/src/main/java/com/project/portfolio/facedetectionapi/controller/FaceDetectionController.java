package com.project.portfolio.facedetectionapi.controller;


import com.project.portfolio.facedetectionapi.dto.FaceDetectionResponse;
import com.project.portfolio.facedetectionapi.service.FaceDetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/faces")
@CrossOrigin(origins = "*")
public class FaceDetectionController {

    @Autowired
    private FaceDetectionService faceDetectionService;

    @PostMapping("/detect")
    public ResponseEntity<byte[]> detectFaces(@RequestParam("image") MultipartFile image){
        try{
            if (image == null || image.isEmpty()){
                return ResponseEntity.badRequest().build();
            }

            //Obter resposta completa do Flask
            FaceDetectionResponse response = faceDetectionService.detectFaces(image);

            // ConverterBase64 para bytes
            byte[] processedImageBytes = faceDetectionService.convertBase64ToBytes(response.getProcessedImage());

            //Configurar headers para retornar imagem
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentLength(processedImageBytes.length);
            headers.set("Content-Disposition", "inline; filename=\"processedImage.jpg\"");

            // Adicionar metadados do Flask nos headers customizados
            headers.set("X-Faces-Count", String.valueOf(response.getFacesCount()));
            headers.set("X-Success", String.valueOf(response.isSuccess()));
            headers.set("X-Format", response.getFormat());


            return ResponseEntity.ok()
                    .headers(headers)
                    .body(processedImageBytes);

        } catch (IllegalArgumentException e){
            // Erro de validação (400 - Bad Request)
            return ResponseEntity.badRequest().build();
        } catch (IOException e){
            // Erro interno (500 - Internal Server Error)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e){
            // Qualquer outro erro
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
