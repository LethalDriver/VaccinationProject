package org.mwdziak.notificationservice.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfiguration {
    @Bean
    FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp) {
        return FirebaseMessaging.getInstance(firebaseApp);
    }

    @Bean
    FirebaseApp firebaseApp(GoogleCredentials credentials) {
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(credentials)
                .build();

        return FirebaseApp.initializeApp(options);
    }

    @Bean
    GoogleCredentials googleCredentials() throws IOException {
        return GoogleCredentials.fromStream(new ByteArrayInputStream(
                String.format(
                        "{ \"type\": \"service_account\", \"project_id\": \"%s\", \"private_key_id\": \"%s\", \"private_key\": \"%s\", \"client_email\": \"%s\", \"client_id\": \"%s\", \"auth_uri\": \"%s\", \"token_uri\": \"%s\", \"auth_provider_x509_cert_url\": \"%s\", \"client_x509_cert_url\": \"%s\" }",
                        System.getenv("FIREBASE_PROJECT_ID"),
                        System.getenv("FIREBASE_PRIVATE_KEY_ID"),
                        System.getenv("FIREBASE_PRIVATE_KEY").replace("\\n", "\n"),
                        System.getenv("FIREBASE_CLIENT_EMAIL"),
                        System.getenv("FIREBASE_CLIENT_ID"),
                        System.getenv("FIREBASE_AUTH_URI"),
                        System.getenv("FIREBASE_TOKEN_URI"),
                        System.getenv("FIREBASE_AUTH_PROVIDER_X509_CERT_URL"),
                        System.getenv("FIREBASE_CLIENT_X509_CERT_URL")
                ).getBytes())
        );
    }

}
