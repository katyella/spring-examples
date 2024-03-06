package dev.katyella.auth0.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        /*
//        This is where we configure the security required for our endpoints and setup our app to serve as
//        an OAuth2 Resource Server, using JWT validation.
//        */
//        return http
//                .authorizeHttpRequests((authorize) -> authorize
//                        .requestMatchers(HttpMethod.POST, "/api/v1/signup", "/api/v1/signin").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/api/v1/test/**").permitAll()
//                        .requestMatchers("/api/v1/???").hasAuthority("SCOPE_read:messages") // apply role-based permissions here?
//                        .anyRequest().authenticated()
//                )
//                .cors(withDefaults())
//                .oauth2ResourceServer(oauth2 -> oauth2
//                        .jwt(withDefaults())
//                )
//                .build();
//    }
//}

/**
 * TEMPORARY Configures our application with Spring Security to restrict access to our API endpoints.
 */
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*
        This is where we configure the security required for our endpoints and setup our app to serve as
        an OAuth2 Resource Server, using JWT validation.
        */
        return http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/api/public").permitAll()
                        .requestMatchers("/api/private").authenticated()
                        .requestMatchers("/api/private-scoped").hasAuthority("SCOPE_read:messages")
                )
                .cors(withDefaults())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(withDefaults())
                )
                .build();
    }
}