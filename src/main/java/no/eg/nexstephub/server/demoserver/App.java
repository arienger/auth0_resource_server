package no.eg.nexstephub.server.demoserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

//    @Value("${auth0.audience}")
//    private String audience;
//
//    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
//    private String issuer;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
