package org.mwdziak.vaccinationbackend.controller;

import lombok.RequiredArgsConstructor;
import org.mwdziak.vaccinationbackend.dto.NotificationTokenRequest;
import org.mwdziak.vaccinationbackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/user")
public class UserController {
    private final UserService userService;
    @PostMapping("/notification-token")
    public ResponseEntity<Void> addNotificationToken(@RequestBody NotificationTokenRequest request) {
        userService.assignToken(request);
        return ResponseEntity.ok().build();
    }
}
