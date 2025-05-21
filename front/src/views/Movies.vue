<template>
  <div class="movies">
    <h1>电影列表</h1>

    <div class="movie-filters">
      <div class="filter-group">
        <h3>分类</h3>
        <div class="filter-options">
          <span class="filter-option">全部</span>
          <span class="filter-option">动作</span>
          <span class="filter-option">喜剧</span>
          <span class="filter-option">科幻</span>
          <span class="filter-option">恐怖</span>
        </div>
      </div>

      <div class="filter-group">
        <h3>评分</h3>
        <div class="filter-options">
          <span class="filter-option">全部</span>
          <span class="filter-option">9分以上</span>
          <span class="filter-option">8-9分</span>
          <span class="filter-option">7-8分</span>
          <span class="filter-option">6-7分</span>
        </div>
      </div>
    </div>

    <div class="movie-list">
      <div class="movie-card" v-for="movie in movies" :key="movie.id">
        <img :src="movie.posterUrl" :alt="movie.title">
        <h3>{{ movie.title }}</h3>
        <p>评分: {{ movie.rating }}</p>
        <router-link :to="{ name: 'MovieDetail', params: { id: movie.id }}">查看详情</router-link>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts" name="Movies">
import { ref, onMounted } from 'vue';
import { MovieService } from '@/services/MovieService';

interface Movie {
  id: number;
  title: string;
  posterUrl: string;
  rating: number;
}

const movies = ref<Movie[]>([]);

onMounted(async () => {
  try {
    const response = await MovieService.getAllMovies();
    movies.value = response.data.map((m: any) => ({
      id: m.movieId,
      title: m.title,
      posterUrl: m.posterUrl,
      rating: m.rating
    }));
  } catch (error) {
    console.error('获取电影失败:', error);
  }
});
</script>

<style scoped>
.movies {
  padding: 20px;
}

.movie-filters {
  background-color: #fff;
  border-radius: 8px;
  padding: 20px;
  margin-bottom: 30px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.filter-group {
  margin-bottom: 15px;
}

.filter-group h3 {
  margin-top: 0;
  margin-bottom: 10px;
}

.filter-options {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}

.filter-option {
  padding: 5px 10px;
  background-color: #f1f1f1;
  border-radius: 15px;
  font-size: 14px;
  cursor: pointer;
}

.filter-option:hover {
  background-color: #4CAF50;
  color: white;
}

.movie-list {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
}

.movie-card {
  width: 200px;
  border: 1px solid #ddd;
  border-radius: 8px;
  overflow: hidden;
  transition: transform 0.3s;
}

.movie-card:hover {
  transform: translateY(-5px);
}

.movie-card img {
  width: 100%;
  height: 250px;
  object-fit: cover;
}

.movie-card h3 {
  margin: 10px;
  font-size: 18px;
}

.movie-card p {
  margin: 10px;
  color: #666;
}

.movie-card a {
  display: block;
  padding: 10px;
  text-align: center;
  background-color: #4CAF50;
  color: white;
  text-decoration: none;
  border-radius: 0 0 8px 8px;
}
</style>