package com.url_shortner.url_shortner_project.repository;

import com.url_shortner.url_shortner_project.entity.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
}
