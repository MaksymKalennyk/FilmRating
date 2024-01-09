import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "../../services/AuthService";
import {Review} from "../review/Review";

@Injectable({
  providedIn: 'root'
})
export class ReviewService{
  private apiUrl = 'http://localhost:8080/user';
  private movieId: number = 0;

  constructor(private http: HttpClient, private authService: AuthService) { }

  getId(): Observable<number> {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.authService.getToken()}`
    });
    const options = { headers: headers };
    return this.http.get<number>(`${this.apiUrl}/get/id`, options);
  }

  getMovieId(): number {
    return this.movieId;
  }

  setMovieId(value: number) {
    this.movieId = value;
  }

  postReview(reviewData: Review) {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.authService.getToken()}`
    });
    const options = { headers: headers };

    return this.http.post<any>(`${this.apiUrl}/reviews`, reviewData, options);
  }
}
