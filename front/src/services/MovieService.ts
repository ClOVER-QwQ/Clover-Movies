import apiClient from "@/services/apiClient";

export class MovieService {
    static getAllMovies() {
        return apiClient.get('/movies');
    }

    static getMovieById(id: string) {
        return apiClient.get(`/movies/${id}`);
    }

    static searchMovies(keyword: string) {
        return apiClient.get(`/movies/search?keyword=${keyword}`);
    }

    static createMovie(movie: any) {
        return apiClient.post('/movies', movie);
    }
}

export default MovieService;