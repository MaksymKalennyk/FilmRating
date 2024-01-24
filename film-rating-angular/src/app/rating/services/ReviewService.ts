import {Injectable} from "@angular/core";
import {BehaviorSubject, Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "../../services/AuthService";
import {Review} from "../review/Review";
import {ReviewData} from "../interfaces/ReviewData";

@Injectable({
  providedIn: 'root'
})
export class ReviewService{
  private apiUrl = 'http://localhost:8080/user';
  private movieIdSubject = new BehaviorSubject<number>(0);
  movieId$ = this.movieIdSubject.asObservable();
  private movieId: number = 0;

  constructor(private http: HttpClient, private authService: AuthService) { }

  getId(): Observable<number> {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.authService.getToken()}`
    });
    const options = { headers: headers };
    return this.http.get<number>(`${this.apiUrl}/get/id`, options);
  }

  postReview(reviewData: Review) {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.authService.getToken()}`
    });
    const options = { headers: headers };

    return this.http.post<any>(`${this.apiUrl}/reviews`, reviewData, options);
  }

  getReviewsByMovieId(movieId: number): Observable<ReviewData[]> {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.authService.getToken()}`
    });
    const options = { headers: headers };
    return this.http.get<ReviewData[]>(`${this.apiUrl}/reviews/movie/${movieId}`, options);
  }

  updateReview(id: number): Observable<ReviewData> {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.authService.getToken()}`
    });
    const options = { headers: headers };
    return this.http.post<ReviewData>(`${this.apiUrl}/update/${id}`,{}, options);
  }

  getMovieId(): number {
    return this.movieId;
  }

  setMovieId(movieId: number) {
    this.movieIdSubject.next(movieId);
  }
}
