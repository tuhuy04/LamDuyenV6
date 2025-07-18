package com.example.WebAoDai.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class PixelcutUtil {

    @Value("${pixelcut.api.key}")
    private String apiKey;

    private RestTemplate restTemplate;

    public PixelcutUtil() {
        this.restTemplate = new RestTemplate();
    }

    public String tryOnClothes(String selfieUrl, String productImageUrl) {
        String apiUrl = "https://api.developer.pixelcut.ai/v1/try-on";

        String requestBody = "{\n" +
                "  \"person_image_url\": \"" + selfieUrl + "\",\n" +
                "  \"garment_image_url\": \"" + productImageUrl + "\"\n" +
                "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-KEY", apiKey);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        return restTemplate.postForObject(apiUrl, entity, String.class);
    }
}
