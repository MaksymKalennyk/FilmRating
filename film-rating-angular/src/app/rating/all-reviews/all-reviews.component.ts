import { Component } from '@angular/core';
import {ReviewService} from "../services/ReviewService";
import {ReviewData} from "../interfaces/ReviewData";

@Component({
  selector: 'app-all-reviews',
  templateUrl: './all-reviews.component.html',
  styleUrls: ['./all-reviews.component.css']
})
export class AllReviewsComponent {
  movieId: number = 0;
  reviews: ReviewData[] = [];
  userId: number = 0;
  errorMessage: string = "";
  likedReviews: Set<number> = new Set<number>();

  constructor(private reviewService: ReviewService) { }

  ngOnInit(){
    this.reviewService.getId().subscribe(userId => {
      this.userId = userId;
    });

    this.reviewService.movieId$.subscribe((movieId) => {
      console.log(movieId);
      this.movieId = movieId;
      this.reviewService.getReviewsByMovieId(this.movieId).subscribe({
        next: (response) => {
          this.reviews = response;
        },
        error: (error) => {
          console.error('Error getting reviews:', error);
        },
      });
    });
  }

  increaseLikeCount(review: ReviewData) {
    this.errorMessage = "";
    if (this.userId && !this.likedReviews.has(review.id)) {
      this.reviewService.updateReview(review.id).subscribe({
        next: (response) =>{
          review.likeCount++;
          this.likedReviews.add(review.id);
          console.log(response);
        },
        error:(err) => {
          this.errorMessage = "This review has already been liked.";
        },
      });
    }
  }
}
