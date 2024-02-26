package com.example.filmrating.controller;

import com.example.filmrating.model.Users;
import com.example.filmrating.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get/id")
    public Long getId(@RequestHeader("Authorization") String authorizationHeader){
        String token = extractTokenFromHeader(authorizationHeader);

        return userService.getUserIdFromToken(token);
    }

    @GetMapping("/search/friend")
    public Users getUserByUsername(@RequestParam("username") String username){
        return userService.getByUsername(username);
    }

    private String extractTokenFromHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}
