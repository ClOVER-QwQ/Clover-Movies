<!-- src/views/SearchResults.vue -->
<template>
  <div class="search-results">
    <div class="search-header">
      <h2 v-if="searchQuery">搜索关键词："{{ searchQuery }}"</h2>
      <h2 v-else>全部结果</h2>
      <div class="result-count">找到 {{ filteredMovies.length }} 个结果</div>
    </div>

    <div class="filter-controls">
      <div class="filter-item">
        <label>类型：</label>
        <select v-model="selectedType">
          <option value="">全部类型</option>
          <option v-for="type in movieTypes" :key="type">{{ type }}</option>
        </select>
      </div>
      <div class="filter-item">
        <label>排序：</label>
        <select v-model="selectedSort">
          <option value="default">默认</option>
          <option value="score_high">评分从高到低</option>
          <option value="score_low">评分从低到高</option>
        </select>
      </div>
    </div>

    <div v-if="filteredMovies.length > 0" class="movie-list">
      <div v-for="movie in sortedMovies" :key="movie.id" class="movie-card">
        <img :src="movie.image" alt="电影海报" class="poster">
        <div class="movie-info">
          <h3>{{ movie.name }}</h3>
          <div class="meta">
            <span class="score">⭐ {{ movie.score }}</span>
            <span class="genre">{{ movie.genre }}</span>
            <span class="year">{{ movie.year }}</span>
          </div>
          <p class="brief">{{ movie.brief }}</p>
          <router-link
              :to="{ name: 'MovieDetail', params: { id: movie.id }}"
              class="detail-link"
          >
            查看详情
          </router-link>
        </div>
      </div>
    </div>

    <div v-else class="no-results">
      <img src="@/assets/no-results.png" alt="无结果" class="empty-img">
      <p>没有找到相关结果，请尝试其他关键词</p>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, watchEffect } from 'vue';
import { useRoute } from 'vue-router';

interface Movie {
  id: number;
  name: string;
  image: string;
  score: number;
  genre: string;
  year: string;
  brief: string;
  type?: string;
}

// 模拟数据
const mockMovies: Movie[] = [
  {
    id: 1,
    name: '神奇女侠1984',
    image: '',
    score: 7.9,
    genre: '动作/冒险',
    year: '2020',
    brief: '戴安娜必须面对全新的强大威胁...',
    type: 'action'
  },
  // 添加更多测试数据...
];

const route = useRoute();
const searchQuery = ref(route.query.q?.toString() || '');
const selectedType = ref('');
const selectedSort = ref('default');
const movieTypes = ref(['动作', '喜剧', '爱情', '科幻']);

// 过滤逻辑
const filteredMovies = computed(() => {
  let results = mockMovies.filter(movie =>
      movie.name.toLowerCase().includes(searchQuery.value.toLowerCase())
  );

  if (selectedType.value) {
    results = results.filter(movie =>
        movie.genre.includes(selectedType.value)
    );
  }

  return results;
});

// 排序逻辑
const sortedMovies = computed(() => {
  const movies = [...filteredMovies.value];
  switch(selectedSort.value) {
    case 'score_high':
      return movies.sort((a, b) => b.score - a.score);
    case 'score_low':
      return movies.sort((a, b) => a.score - b.score);
    default:
      return movies;
  }
});

// 监听路由变化
watchEffect(() => {
  searchQuery.value = route.query.q?.toString() || '';
});
</script>

<style scoped>
.search-results {
  max-width: 1200px;
  margin: 20px auto;
  padding: 0 20px;
}

.search-header {
  display: flex;
  align-items: baseline;
  gap: 20px;
  margin-bottom: 30px;
}

.result-count {
  color: #666;
  font-size: 0.9em;
}

.filter-controls {
  display: flex;
  gap: 30px;
  margin-bottom: 25px;
}

.filter-item {
  display: flex;
  align-items: center;
  gap: 10px;
}

.filter-item select {
  padding: 6px 12px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.movie-list {
  display: grid;
  gap: 25px;
}

.movie-card {
  display: grid;
  grid-template-columns: 200px 1fr;
  gap: 25px;
  background: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.1);
}

.poster {
  width: 100%;
  height: 280px;
  object-fit: cover;
  border-radius: 4px;
}

.movie-info h3 {
  margin: 0 0 10px;
  font-size: 1.4em;
  color: #333;
}

.meta {
  display: flex;
  gap: 15px;
  margin-bottom: 12px;
  color: #666;
}

.score {
  color: #ff9f00;
  font-weight: bold;
}

.brief {
  color: #444;
  line-height: 1.6;
  margin: 15px 0;
}

.detail-link {
  display: inline-block;
  padding: 8px 20px;
  background: #4CAF50;
  color: white;
  text-decoration: none;
  border-radius: 4px;
  transition: background 0.3s;
}

.detail-link:hover {
  background: #45a049;
}

.no-results {
  text-align: center;
  padding: 50px 0;
}

.empty-img {
  width: 200px;
  margin-bottom: 20px;
  opacity: 0.8;
}
</style>