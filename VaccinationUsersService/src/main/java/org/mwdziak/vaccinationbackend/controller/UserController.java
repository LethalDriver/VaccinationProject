package org.mwdziak.vaccinationbackend.controller;

import lombok.RequiredArgsConstructor;
import org.mwdziak.vaccinationbackend.dto.NotificationTokenRequest;
import org.mwdziak.vaccinationbackend.dto.UserDetailsDTO;
import org.mwdziak.vaccinationbackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
