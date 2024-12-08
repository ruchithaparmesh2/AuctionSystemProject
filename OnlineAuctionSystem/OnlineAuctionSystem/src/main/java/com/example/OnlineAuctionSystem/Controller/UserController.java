package com.example.OnlineAuctionSystem.Controller;

import com.example.OnlineAuctionSystem.Model.User;
import com.example.OnlineAuctionSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api/user",produces = "application/json")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<String> getLoggedInUser(@AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            String email = (String) principal.getAttribute("email");
            Optional<User> user = userRepository.findByEmail(email);

            if (user.isPresent()) {
                return ResponseEntity.ok(user.get().getName());
            } else {
                return ResponseEntity.status(404).body("User not found");
            }
        }
        return ResponseEntity.status(401).body("Unauthorized");
    }
    @GetMapping("/get")
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

}
