import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Review} from "./Review";
import {ReviewService} from "../services/ReviewService";
import {catchError, tap, throwError} from "rxjs";
import {ReviewData} from "../interfaces/ReviewData";

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent {
  reviewForm: FormGroup;
  currentDate: Date = new Date();
  savedReviewData: ReviewData | undefined;
  userId: number = 0;
  stars: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

  constructor(private fb: FormBuilder, private reviewService: ReviewService) {
    this.reviewForm = this.fb.group({
      rating: [0, [Validators.required, Validators.min(1), Validators.max(10)]],
      reviewText: ['', Validators.required],
    });
  }

  ngOnInit() {
    this.reviewService.getId().subscribe(userId => {
      this.userId = userId;
      console.log('Received userId:', userId);
    });
  }

  submitForm() {
    const ratingControl = this.reviewForm.get('rating');
    const reviewTextControl = this.reviewForm.get('reviewText');
    if (this.reviewForm.valid) {
      this.reviewService.movieId$.subscribe((movieId) => {
      if (ratingControl && reviewTextControl && ratingControl.valid && reviewTextControl.valid) {
        const reviewData: Review = {
          userId: this.userId,
          movieId: movieId,
          rating: ratingControl.value,
          reviewText: reviewTextControl.value,
          reviewDate: this.currentDate,
        };
        this.reviewService.postReview(reviewData).pipe(
          tap((response : ReviewData) => {
            console.log('Review submitted successfully:', response);
            this.savedReviewData = response;
            console.log(this.savedReviewData);
          }),
          catchError((error) => {
            console.error('Error submitting review:', error);
            return throwError(error);
          })
        ).subscribe();
      }
      });
    }
  }

  rate(star: number) {
    this.reviewForm.get('rating')?.setValue(star);
  }
}
