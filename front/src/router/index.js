import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/Login.vue';
import CinemaView from '../views/Cinema.vue';
import MovieDetailView from '../views/MovieDetail.vue';
import BookingView from '../views/Booking.vue';
import SearchResultsView from '../views/SearchResults.vue';
import MoviesView from '../views/Movies.vue';
import ProfileView from '../views/Profile.vue';
import Register from "@/views/Register.vue";

const router = createRouter({
    history: createWebHistory(),
    routes: [
        { path: '/', name: 'Home', component: HomeView },
        { path: '/login', name: 'Login', component: LoginView },
        { path: '/cinemas', name: 'Cinemas', component: CinemaView },
        { path: '/movies', name: 'Movies', component: MoviesView },
        { path: '/profile', name: 'Profile', component: ProfileView },
        { path: '/movie/:id', name: 'MovieDetail', component: MovieDetailView },
        { path: '/booking', name: 'Booking', component: BookingView },
        { path: '/search', name: 'Search', component: SearchResultsView },
        { path: '/register', name: 'Register', component: Register },
    ]
});

export default router;