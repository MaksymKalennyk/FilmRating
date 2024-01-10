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

  constructor(private reviewService: ReviewService) { }

  ngOnInit(){
    this.reviewService.movieId$.subscribe((movieId) => {
      this.movieId = movieId;
      this.reviewService.getReviewsByMovieId(this.movieId).subscribe({
        next: (response) => {
          this.reviews = response;
          console.log('Reviews for movieId', this.movieId, ':', this.reviews);
        },
        error: (error) => {
          console.error('Error getting reviews:', error);
        },
      });
    });
  }
}
