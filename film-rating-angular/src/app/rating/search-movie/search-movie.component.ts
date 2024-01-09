import { Component } from '@angular/core';
import {FoundMovie} from "../interfaces/FoundMovie";
import {SearchService} from "../services/SearchService";

@Component({
  selector: 'app-search-movie',
  templateUrl: './search-movie.component.html',
  styleUrls: ['./search-movie.component.css']
})
export class SearchMovieComponent {
  searchTerm: string = '';
  movieFound: boolean = false;
  foundMovie: FoundMovie = {id: 0, name: '',source: '', genre: '', releaseDate: ''}

  constructor(private searchService: SearchService) { }

  searchMovies() {
    if (this.searchTerm.trim() !== '') {
      this.searchService.searchMovies(this.searchTerm).subscribe((result: any) => {
        if (result) {
          this.foundMovie = result;
          this.movieFound = true;
        } else {
          this.foundMovie = {id: 0, name: '',source: '', genre: '', releaseDate: ''};
          this.movieFound = false;
        }
      });
    }
  }
}
