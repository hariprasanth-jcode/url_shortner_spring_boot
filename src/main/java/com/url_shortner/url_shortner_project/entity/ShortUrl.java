package com.url_shortner.url_shortner_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name="url_shortner_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShortUrl {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length =2050)
    private String originalUrl;

    @Column(nullable =false,unique=true)
    private String shortCode;

    @Builder.Default
    @Column(nullable =false)
    private Instant createdAt=Instant.now();




}
