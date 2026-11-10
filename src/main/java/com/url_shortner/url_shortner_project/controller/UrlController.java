package com.url_shortner.url_shortner_project.controller;

import com.url_shortner.url_shortner_project.entity.ShortUrl;
import com.url_shortner.url_shortner_project.repository.ShortUrlRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/urls")
public class UrlController {

    private ShortUrlRepository shortUrlRepository;

    public UrlController(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    @PostMapping
    public ResponseEntity<ShortUrl> createShortUrl(@RequestBody Map<String,String> request){

        String originalUrl=request.get("originalUrl");

        String shortCode= UUID.randomUUID().toString().substring(0,6);

        ShortUrl shortUrl = ShortUrl.builder()
                .originalUrl(originalUrl)
                .shortCode(shortCode)
                .build();

        ShortUrl urlSaved = shortUrlRepository.save(shortUrl);

        return ResponseEntity.status(HttpStatus.CREATED).body(urlSaved);
    }

}
