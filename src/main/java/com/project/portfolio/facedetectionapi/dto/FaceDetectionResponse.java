package com.project.portfolio.facedetectionapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FaceDetectionResponse {

    @JsonProperty("faces_count")
    private int facesCount;

    @JsonProperty("format")
    private String format;

    @JsonProperty("processed_image")
    private String processedImage;

    @JsonProperty("success")
    private boolean success;

    public FaceDetectionResponse() {}

    public int getFacesCount() {
        return facesCount;
    }

    public void setFacesCount(int facesCount) {
        this.facesCount = facesCount;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getProcessedImage() {
        return processedImage;
    }

    public void setProcessedImage(String processedImage) {
        this.processedImage = processedImage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
