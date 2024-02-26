import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "../../services/AuthService";
import {Observable} from "rxjs";
import {User} from "../interfaces/User";


@Injectable({
  providedIn: 'root'
})
export class FriendService{
  private apiUrl = 'http://localhost:8080/user';

  constructor(private http: HttpClient, private authService: AuthService) { }

  searchUser(username: string): Observable<User> {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.authService.getToken()}`
    });
    const options = { headers: headers };
    return this.http.get<User>(`${this.apiUrl}/search/friend?username=${username}`, options);
  }

  addFriend(userId: number, friendId: number){
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.authService.getToken()}`
    });
    const options = { headers: headers };
    console.log(userId);
    console.log(friendId);
    return this.http.post(`${this.apiUrl}/friends/add`, {userId, friendId}, options);
  }
}
