export interface Movie {
    movieId: number;
    title: string;
    director: string;
    actors: string;
    rating: number;
    posterUrl: string | null;
    description: string;
}