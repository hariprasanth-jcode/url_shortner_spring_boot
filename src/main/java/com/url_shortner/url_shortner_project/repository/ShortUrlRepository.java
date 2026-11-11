package com.url_shortner.url_shortner_project.repository;

import com.url_shortner.url_shortner_project.entity.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {


    public Optional<ShortUrl> findByShortCode(String shortCode);


}
