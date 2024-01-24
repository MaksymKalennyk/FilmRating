package com.example.filmrating.repo;

import com.example.filmrating.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<Friend> findAllByUsersId(Long userId);
    Optional<Friend> findByUsersIdAndFriendsId(Long userId, Long friendId);
    boolean existsByUsersIdAndFriendsId(Long userId, Long friendId);
    List<Friend> findAllByUsersIdOrFriendsId(Long userId, Long friendId);
}
