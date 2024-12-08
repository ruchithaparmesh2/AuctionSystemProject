package com.example.OnlineAuctionSystem.Controller;

import com.example.OnlineAuctionSystem.Model.User;
import com.example.OnlineAuctionSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class OauthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/oauth-success")
    public Map<String, Object> oauthSuccess(@AuthenticationPrincipal OAuth2User principal) {
        Map<String, Object> attributes = principal.getAttributes();

        // Extract name and email
        String name = (String) attributes.get("name");
        String email = (String) attributes.get("email");

        // Save user to database if not already saved
        User user = userRepository.findByEmail(email)
                .orElseGet(() -> userRepository.save(new User(name, email)));

        // Return a clear JSON response
        Map<String, Object> response = new HashMap<>();
        response.put("name", user.getName());
        response.put("email", user.getEmail());
        return response;
    }

    @PostMapping("/save")
    public ResponseEntity<Map<String, String>> saveUser(@RequestBody User user) {
        // Save logic here
        Map<String, String> response = new HashMap<>();
        response.put("message", "User saved successfully");
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }


}
