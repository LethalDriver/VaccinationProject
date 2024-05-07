package org.mwdziak.vaccinationbackend.controller;

import lombok.RequiredArgsConstructor;
import org.mwdziak.vaccinationbackend.dto.NotificationTokenRequest;
import org.mwdziak.vaccinationbackend.dto.UserDetailsDTO;
import org.mwdziak.vaccinationbackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @PostMapping("/notification-token")
    public ResponseEntity<Void> addNotificationToken(@RequestBody NotificationTokenRequest request) {
        userService.assignToken(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/details")
    public ResponseEntity<UserDetailsDTO> getUserDetails() {
        return ResponseEntity.ok(userService.getUserDetailsForCurrentUser());
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/details/{userId}")
    public ResponseEntity<UserDetailsDTO> getUserDetails(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserDetails(userId));
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/all")
    public ResponseEntity<List<UserDetailsDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUserDetails());
    }
}
