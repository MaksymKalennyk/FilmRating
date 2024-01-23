package com.example.filmrating.repo;

import com.example.filmrating.model.ReviewUserLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewUserLikeRepository extends JpaRepository<ReviewUserLike, Long> {
    boolean existsByUsers_IdAndReviews_Id(Long userId, Long reviewId);
}
