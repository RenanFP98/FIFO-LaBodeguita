package com.fifo.app_bodeguita.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;

import jakarta.annotation.PostConstruct;

@Configuration
public class FirebaseConfig {
    private static final Logger logger = LoggerFactory.getLogger(FirebaseConfig.class);

    @PostConstruct
    public void init() {
        logger.info("Iniciando configuracion de Firebase...");
        try (InputStream serviceAccount = new ClassPathResource("serviceAccountKey.json").getInputStream()) {

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                logger.info("Conexion exitosa con Firebase.");
            } else {
                logger.info("Firebase ya estaba inicializado, se reutiliza la instancia existente.");
            }
        } catch (Exception e) {
            logger.error("Error al conectar con Firebase", e);
        }
    }
}