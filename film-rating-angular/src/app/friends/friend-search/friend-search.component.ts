import { Component } from '@angular/core';
import {User} from "../interfaces/User";
import {FriendService} from "../services/FriendService";
import {ReviewService} from "../../rating/services/ReviewService";

@Component({
  selector: 'app-friend-search',
  templateUrl: './friend-search.component.html',
  styleUrls: ['./friend-search.component.css']
})
export class FriendSearchComponent {
  searchTerm: string = '';
  userFound: boolean = false;
  userId: number = 0;
  user: User = {id: 0, username: "", email: ""}

  constructor(private friendService: FriendService, private reviewService: ReviewService) { }

  ngOnInit() {
    this.reviewService.getId().subscribe(userId => {
      this.userId = userId;
      console.log('Received userId:', userId);
    });
  }

  searchUser() {
    if (this.searchTerm.trim() !== '') {
      this.friendService.searchUser(this.searchTerm).subscribe((result: any) => {
        if (result) {
          this.user = result;
          this.userFound = true;
        } else {
          this.user = {id: 0, username: "", email: ""};
          this.userFound = false;
        }
      });
    }
  }

  subscribe() {
    this.friendService.addFriend(this.userId, this.user.id).subscribe({
      next: (response) => {
        console.log("Successful " + response);
      },
      error: (error) => {
        console.error('Error:', error);
      },
    });
  }
}
