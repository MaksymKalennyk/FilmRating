package com.example.filmrating.model.dto;

import lombok.Data;

@Data
public class FriendRequest {
    private Long userId;
    private Long friendId;
}
