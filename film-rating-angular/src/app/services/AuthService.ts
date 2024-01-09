import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserLogin} from "../login/UserLogin";
import {UserRegistration} from "../registration/UserRegistration";
import {Injectable} from "@angular/core";
import {JwtAuthenticationResponse} from "./JwtAuthenticationResponse";

@Injectable({
  providedIn: 'root'
})
export class AuthService{
  private apiUrl = 'http://localhost:8080/auth';
  private token: string | null = null;

  constructor(private http: HttpClient) { }

  signUp(request: UserRegistration): Observable<JwtAuthenticationResponse> {
    return this.http.post<JwtAuthenticationResponse>(`${this.apiUrl}/sign-up`, request);
  }

  signIn(request: UserLogin): Observable<JwtAuthenticationResponse> {
    return this.http.post<JwtAuthenticationResponse>(`${this.apiUrl}/sign-in`, request);
  }

  setToken(token: string) {
    this.token = token;
  }

  getToken(): string | null {
    return this.token;
  }
}
