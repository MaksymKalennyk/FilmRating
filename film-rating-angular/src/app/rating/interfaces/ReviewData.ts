export interface ReviewData {
  id: number;
  users: {
    id: number;
    username: string;
  };
  movies: {
    id: number;
    name: string;
    source: string;
    genre: string;
    releaseDate: Date;
  };
  rating: number;
  reviewText: string;
  reviewDate: Date;
  likeCount: number;
}
