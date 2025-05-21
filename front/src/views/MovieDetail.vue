<template>
  <div class="movie-detail">
    <div v-if="loading" class="loading">加载中...</div>
    <div v-else-if="error" class="error">{{ error }}</div>
    <div v-else class="detail-content">
      <div class="poster-section">
        <img src="../../public/no-img.png" alt="电影海报" class="mp">
      </div>
      <div class="info-section">
        <h1>{{ movie.title }}</h1>
        <p class="description">{{ movie.description }}</p>
        <div class="movie-info">
          <p>导演：{{ movie.director }}</p>
          <p>主演：{{ movie.actors }}</p>
          <p>评分：{{ movie.rating }}</p>
        </div>
        <button @click="purchaseTicket" class="purchase-button">特惠购票</button>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import { MovieService } from '@/services/MovieService';
import {Movie} from "@/types/Movie";

const route = useRoute();
const loading = ref(true);
const error = ref<string | null>(null);



const movie = ref<Movie>({
  movieId: 0,
  title: '',
  director: '',
  actors: '',
  rating: 0,
  posterUrl: 'no-img.png',
  description: ''
});

// 将默认海报图片路径更改为相对路径
const defaultPoster = "../../public/no-img.png";

const purchaseTicket = () => {
  alert('购票功能即将上线，敬请期待！');
};

onMounted(async () => {
  try {
    const movieId = parseInt(route.params.id as string, 10);
    if (isNaN(movieId)) {
      throw new Error('无效的电影 ID');
    }

    const response = await MovieService.getMovieById(movieId.toString());

    if (!response.data.posterUrl) {
      response.data.posterUrl = defaultPoster;
    }

    Object.assign(movie.value, response.data);

  } catch (err) {
    error.value = '无法加载电影信息，请稍后再试';
    console.error('获取电影详情失败:', err);
  } finally {
    loading.value = false;
  }
});
</script>

<style scoped>
.mp{
  width: 120px;
  height: 300px;
  object-fit: cover;
  border: 2px solid #eee;
  margin-right:20px;
}


.loading,
.error {
  text-align: center;
  padding: 40px;
  font-size: 18px;
}

.error {
  color: #ff4444;
}

.movie-detail {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.detail-content {
  display: flex;
  gap: 30px;
}

.poster-section {
  flex: 0 0 200px;
}

.poster-section img {
  width: 100%;
  border-radius: 8px;
}

.info-section {
  flex: 1;
}

h1 {
  font-size: 24px;
  margin-bottom: 15px;
}

.description {
  line-height: 1.6;
  margin-bottom: 20px;
}

.movie-info {
  margin-bottom: 20px;
}

.purchase-button {
  padding: 10px 20px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
}

.purchase-button:hover {
  background-color: #45a049;
}

@media (max-width: 768px) {
  .detail-content {
    flex-direction: column;
  }

  .poster-section {
    flex: 0 0 auto;
    width: 100%;
    max-width: 300px;
  }
}
</style>