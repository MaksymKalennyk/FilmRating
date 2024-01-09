import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "../../services/AuthService";

@Injectable({
  providedIn: 'root'
})
export class SearchService{
  private apiUrl = 'http://localhost:8080/user/movies';

  constructor(private http: HttpClient, private authService: AuthService) { }

  searchMovies(name: string) {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.authService.getToken()}`
    });
    const options = { headers: headers };
    return this.http.get(`${this.apiUrl}/search?name=${name}`, options);
  }
}
