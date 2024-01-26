package com.example.filmrating.service;

import com.example.filmrating.model.Friend;
import com.example.filmrating.model.Users;
import com.example.filmrating.repo.FriendRepository;
import com.example.filmrating.repo.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    @Autowired
    public FriendService(FriendRepository friendRepository, UserRepository userRepository) {
        this.friendRepository = friendRepository;
        this.userRepository = userRepository;
    }

    public void addFriend(Long userId, Long friendId) {
        if (friendRepository.existsByUsersIdAndFriendsId(userId, friendId)) {
            throw new IllegalStateException("Users are already friends");
        }

        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Users friend = userRepository.findById(friendId)
                .orElseThrow(() -> new EntityNotFoundException("Friend not found"));

        Friend newFriend = new Friend();
        newFriend.setUsers(user);
        newFriend.setFriends(friend);
        friendRepository.save(newFriend);
    }

    public void removeFriend(Long userId, Long friendId) {
        Friend friend = friendRepository.findByUsersIdAndFriendsId(userId, friendId)
                .orElseThrow(() -> new EntityNotFoundException("Friendship not found"));
        friendRepository.delete(friend);
    }

    public List<Friend> getFriendsList(Long userId) {
        return friendRepository.findAllByUsersIdOrFriendsId(userId, userId);
    }
}
