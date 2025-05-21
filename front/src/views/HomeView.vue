<template>
  <div class="home">
    <h1>欢迎来到四叶草电影票务网站</h1>

    <div class="intro-section">
      <h2>我们的特色</h2>
      <div class="features">
        <div class="feature">
          <h3>便捷购票</h3>
          <p>一键购票，方便快捷，随时随地购买您喜爱的电影票。</p>
        </div>
        <div class="feature">
          <h3>丰富影院选择</h3>
          <p>汇聚各大品牌影院，选择更多，体验更佳。</p>
        </div>
        <div class="feature">
          <h3>最新电影资讯</h3>
          <p>第一时间获取最新电影动态，不错过任何精彩。</p>
        </div>
      </div>
    </div>

    <div v-if="!isAuthenticated" class="cta-section">
      <h2>开始您的观影之旅</h2>
      <p>立即注册或登录，享受更多会员专属优惠。</p>
      <button class="cta-button" @click="goToRegister">立即注册</button>
      <button class="cta-button" @click="goToLogin">立即登录</button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useAuthStore } from '@/stores/auth';

const router = useRouter();
const authStore = useAuthStore();

// 判断用户是否登录
const isAuthenticated = ref(authStore.isLoggedIn);

// 跳转到注册页面
const goToRegister = () => {
  router.push('/register');
};

// 跳转到登录页面
const goToLogin = () => {
  router.push('/login');
};
</script>

<style scoped>
.home {
  padding: 40px 20px;
  max-width: 1200px;
  margin: 0 auto;
}

.intro-section {
  margin-bottom: 50px;
}

.features {
  display: flex;
  flex-wrap: wrap;
  gap: 30px;
  margin-top: 30px;
}

.feature {
  flex: 1;
  min-width: 300px;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
}

.cta-section {
  text-align: center;
  margin-top: 50px;
}

.cta-button {
  padding: 12px 30px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 5px;
  font-size: 16px;
  cursor: pointer;
  margin-top: 20px;
  margin-right: 10px;
}

.cta-button:hover {
  background-color: #388E3C;
}
</style>