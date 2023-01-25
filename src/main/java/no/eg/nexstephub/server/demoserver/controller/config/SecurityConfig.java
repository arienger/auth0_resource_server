package no.eg.nexstephub.server.demoserver.controller.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
    @Value("${auth0.audience}")
    private String audience;

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuer;

    /*
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            */
/*
        This is where we configure the security required for our endpoints and setup our app to serve as
        an OAuth2 Resource Server, using JWT validation.
        *//*

        http.authorizeRequests()
                .mvcMatchers("/products").permitAll()
                .mvcMatchers("/api/private").authenticated()
                .mvcMatchers("/api/private-scoped").hasAuthority("SCOPE_read:messages")
                .and().cors()
                .and().oauth2ResourceServer().jwt();
        return http.build();
    }
*/
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*
        This is where we configure the security required for our endpoints and setup our app to serve as
        an OAuth2 Resource Server, using JWT validation.
        */

        http.authorizeHttpRequests()
                //.requestMatchers("/api/public").permitAll()
                .requestMatchers("/api/private/**").authenticated()
                .requestMatchers("/api/byggoffice/**").authenticated()
                //.requestMatchers("/api/private-scoped").hasAuthority("SCOPE_read:messages")
                .and().cors()
                .and().oauth2ResourceServer().jwt();
        return http.build();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/products/**").permitAll() // GET requests don't need auth
//                .anyRequest()
//                .authenticated()
//                .and()
//                .oauth2ResourceServer()
//                .jwt()
//                .decoder(jwtDecoder());
//    }

//    JwtDecoder jwtDecoder() {
//        OAuth2TokenValidator<Jwt> withAudience = new AudienceValidator(audience);
//        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
//        OAuth2TokenValidator<Jwt> validator = new DelegatingOAuth2TokenValidator<>(withAudience, withIssuer);
//
//        NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder) JwtDecoders.fromOidcIssuerLocation(issuer);
//        jwtDecoder.setJwtValidator(validator);
//        return jwtDecoder;
//    }

    @Bean
    JwtDecoder jwtDecoder() {
        /*
        By default, Spring Security does not validate the "aud" claim of the token, to ensure that this token is
        indeed intended for our app. Adding our own validator is easy to do:
        */

        NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder)
                JwtDecoders.fromOidcIssuerLocation(issuer);

        OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(audience);
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);

        jwtDecoder.setJwtValidator(withAudience);

        return jwtDecoder;
    }
}
