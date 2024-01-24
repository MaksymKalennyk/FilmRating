package com.example.filmrating.controller;

import com.example.filmrating.model.Friend;
import com.example.filmrating.model.dto.FriendRequest;
import com.example.filmrating.service.FriendService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/friends")
public class FriendController {

    private final FriendService friendService;

    @Autowired
    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addFriend(@RequestBody FriendRequest friendRequest) {
        try {
            friendService.addFriend(friendRequest.getUserId(), friendRequest.getFriendId());
            return ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> removeFriend(@RequestBody FriendRequest friendRequest) {
        try {
            friendService.removeFriend(friendRequest.getUserId(), friendRequest.getFriendId());
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Friend>> getFriendsList(@RequestBody FriendRequest friendRequest) {
        return ResponseEntity.ok(friendService.getFriendsList(friendRequest.getUserId()));
    }
}
