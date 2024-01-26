import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {RouterModule, Routes} from "@angular/router";
import { RegistrationComponent } from './registration/registration.component';
import { LoginComponent } from './login/login.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { RatingComponent } from './rating/rating.component';
import { SearchMovieComponent } from './rating/search-movie/search-movie.component';
import { ReviewComponent } from './rating/review/review.component';
import { AllReviewsComponent } from './rating/all-reviews/all-reviews.component';
import { HeaderComponent } from './rating/header/header.component';
import { FriendsComponent } from './friends/friends.component';
import { FriendSearchComponent } from './friends/friend-search/friend-search.component';
import { FriendListComponent } from './friends/friend-list/friend-list.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'rating', component: RatingComponent },
  { path: 'friends', component: FriendsComponent },
];

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    LoginComponent,
    RatingComponent,
    SearchMovieComponent,
    ReviewComponent,
    AllReviewsComponent,
    HeaderComponent,
    FriendsComponent,
    FriendSearchComponent,
    FriendListComponent,
  ],
    imports: [
        BrowserModule,
        RouterModule.forRoot(routes),
        FormsModule,
        HttpClientModule,
        ReactiveFormsModule,
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
