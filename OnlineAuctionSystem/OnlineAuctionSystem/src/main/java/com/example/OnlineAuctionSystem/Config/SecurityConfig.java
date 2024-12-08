package com.example.OnlineAuctionSystem.Config;

import com.example.OnlineAuctionSystem.Model.User;
import com.example.OnlineAuctionSystem.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/oauth-success", "/save", "/api/bids/**", "/api/auctions", "/api/auctions/add").permitAll() // Allow public access to /api/auctions
                        .requestMatchers("/api/user").authenticated()
                        .requestMatchers("/api/bids/itemName/{itemName}").permitAll()
                        .requestMatchers("/api/bids/name/{userName}").permitAll()
                        .requestMatchers("/api/highestBids").permitAll()
                        .requestMatchers("/api/highestBids/updatePayment").permitAll()
                        .requestMatchers("/api/highestBids/name/{name}").permitAll()
                        .requestMatchers("/api/auctions/name/{name}").permitAll()
                        .requestMatchers("/api/highestBids/itemName/{itemName}").permitAll()
                        .requestMatchers("/api/auctions/itemName/{itemName}").permitAll()
                        .requestMatchers("/api/payments").permitAll()
                        .requestMatchers("/api/payments/add").permitAll()
                        .requestMatchers("/api/user/get").permitAll()
                        .requestMatchers("/api/auctions/deleteItem/{itemName}").permitAll()// Add permission for /api/bids/{name}
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth -> oauth.successHandler(authenticationSuccessHandler()));

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            String email = oAuth2User.getAttribute("email");

            String redirectUrl = "http://localhost:4200/dashboard-user";
            if ("ruchithaparmeshwar2@gmail.com".equals(email)) {
                redirectUrl = "http://localhost:4200/dashboard";
            }

            response.sendRedirect(redirectUrl);
        };
    }
}
