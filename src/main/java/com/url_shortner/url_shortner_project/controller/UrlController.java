package com.url_shortner.url_shortner_project.controller;

import com.url_shortner.url_shortner_project.entity.Base62Converter;
import com.url_shortner.url_shortner_project.entity.ShortUrl;
import com.url_shortner.url_shortner_project.repository.ShortUrlRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/urls")
public class UrlController {

    private ShortUrlRepository shortUrlRepository;


    private Base62Converter base62Converter;

    public UrlController(ShortUrlRepository shortUrlRepository,Base62Converter base62Converter) {
        this.shortUrlRepository = shortUrlRepository;
        this.base62Converter = base62Converter;
    }

    @PostMapping
    public ResponseEntity<ShortUrl> createShortUrl(@RequestBody Map<String,String> request){

        String originalUrl=request.get("originalUrl");

       // String shortCode= UUID.randomUUID().toString().substring(0,6);

        String shortCode = base62Converter.generateRandomCode(7);
        ShortUrl shortUrl = ShortUrl.builder()
                .originalUrl(originalUrl)
                .shortCode(shortCode)
                .build();

        ShortUrl urlSaved = shortUrlRepository.save(shortUrl);

        return ResponseEntity.status(HttpStatus.CREATED).body(urlSaved);
    }

// http://localhost:9095/api/urls/l2Zjyxs
    @GetMapping("/{shortCode}")
    public ResponseEntity<?> redirectHortUrl(@PathVariable String shortCode){
//        Optional<ShortUrl> optionalShortUrl = shortUrlRepository.findByShortCode(shortCode);
//
//        if(optionalShortUrl.isPresent()){
//            ShortUrl shortUrl = optionalShortUrl.get();
//            // 302
//            return ResponseEntity.status(HttpStatus.FOUND)
//                    .location(URI.create(shortUrl.getOriginalUrl())).build();
//
//        }else{
//            // 404
//            return ResponseEntity.notFound().build();
//        }

        return shortUrlRepository.findByShortCode(shortCode)
                .map(
                   shortUrl->{
                       return ResponseEntity.
                               status(HttpStatus.FOUND)
                               .location(URI.create(shortUrl.getOriginalUrl()))
                               .build();
                   }
                ).orElseGet(()->ResponseEntity.notFound().build());
    }

}
