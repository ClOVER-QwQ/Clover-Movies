<template>
  <div class="header-container">
    <div class="top-bar">
      <h1 class="main-title">🍀四叶草电影票务</h1>
      <div class="search-box">
        <input type="text" placeholder="搜索电影、影院、演员" v-model="searchQuery">
        <button @click="search">搜索</button>
      </div>
      <div class="user-section">
        <div v-if="authStore.isLoggedIn" class="user-info">
          <img :src="authStore.userAvatar || 'no-img.png'" alt="用户头像" class="avatar">
        </div>
        <button v-else class="login-btn" @click="goLogin">登录/注册</button>
      </div>
    </div>

    <nav class="main-nav">
      <router-link to="/">首页</router-link>
      <router-link to="/movies">电影</router-link>
      <router-link to="/cinemas">影院</router-link>
      <router-link to="/profile">个人中心</router-link>
    </nav>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { useAuthStore } from '@/stores/auth';

const router = useRouter();
const authStore = useAuthStore();
const searchQuery = ref('');

onMounted(() => {
});

const search = () => {
  router.push({ path: '/search', query: { q: searchQuery.value } });
};

const goLogin = () => {
  router.push('/login');
};
</script>

<style scoped>
.header-container {
  position: fixed;
  top: 0;
  width: 100%;
  background: rgba(255, 255, 255, 0.95);
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  z-index: 1000;
}

.top-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 50px;
  border-bottom: 1px solid #eee;
}

.main-title {
  color: #4CAF50;
  font-size: 32px;
  margin: 0;
}

.search-box {
  flex: 0 1 500px;
  display: flex;
  gap: 10px;
}

.search-box input {
  flex: 1;
  padding: 8px 15px;
  border: 2px solid #4CAF50;
  border-radius: 25px;
}

.user-section {
  display: flex;
  align-items: center;
  gap: 15px;
}

.main-nav {
  display: flex;
  justify-content: center;
  gap: 40px;
  padding: 15px;
  background: #f8f9fa;
}

.main-nav a {
  color: #333;
  text-decoration: none;
  font-weight: 500;
  padding: 5px 10px;
  transition: all 0.3s;
}

.main-nav a:hover {
  color: #4CAF50;
  border-bottom: 2px solid #4CAF50;
}

.login-btn {
  padding: 8px 25px;
  background: #4CAF50;
  color: white;
  border: none;
  border-radius: 20px;
  cursor: pointer;
}

.avatar {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  object-fit: cover;
  border: 2px solid #eee;
  margin-bottom: 1.5rem;
}
</style>