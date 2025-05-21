<template>
  <div class="profile-container">
    <div v-if="isLoggedIn">
      <div v-if="user">
        <img :src="user.avatar || 'no-img.png'" alt="用户头像" class="avatar">
        <p><strong>用户名：</strong>{{ user.username }}</p>
        <p><strong>邮箱：</strong>{{ maskedEmail(user.email) }}</p>
        <button @click="logout" class="logout-button">退出登录</button>
      </div>
      <div v-else>
        <p>加载用户信息中...</p>
      </div>
    </div>

    <div v-else class="not-logged">
      <p>请登录查看个人信息</p>
      <button @click="goLogin" class="login-button">立即登录</button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { useAuthStore } from '@/stores/auth';
import { authApi } from '@/services/authApi';

const authStore = useAuthStore();
const { userId, isLoggedIn } = authStore;
const user = ref<{ id: string; username: string; email: string; avatar: string } | null>(null);
const loading = ref(true);

// 邮箱脱敏处理
const maskedEmail = (email?: string) => {
  if (!email) return '';
  const [name, domain] = email.split('@');
  return `${name?.[0]}****${name?.slice(-1)}@${domain}`;
};

// 头像加载失败处理
const handleAvatarError = (e: Event) => {
  const img = e.target as HTMLImageElement;
  img.src = '../assets/no-img.png';
};

// 自动获取用户信息
onMounted(async () => {
  if (isLoggedIn && userId) {
    loading.value = true;
    try {
      const response = await authApi.getUserInfo(parseInt(userId));
      user.value = {
        id: response.data.id,
        username: response.data.username,
        email: response.data.email,
        avatar: response.data.avatar
      };
    } catch (error) {
      console.error('获取用户信息失败:', error);
      user.value = null; // 或者设置默认值
    } finally {
      loading.value = false;
    }
  } else {
    loading.value = false;
  }
});

// 退出登录
const logout = async () => {
  try {
    await authStore.logout();
  } catch (error) {
    console.error('退出登录失败:', error);
  }

  goLogin()
};

// 前往登录页面
const goLogin = () => {
  window.location.href = '/login';
};
</script>

<style scoped>
.profile-container {
  max-width: 800px;
  margin: 2rem auto;
  padding: 2rem;
  background: #fff;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.avatar {
  width: 120px;
  height: 120px;
  border-radius: 50%;
  object-fit: cover;
  border: 2px solid #eee;
  margin-bottom: 1.5rem;
}

.not-logged {
  text-align: center;
  padding: 2rem;
}

.login-button,
.logout-button {
  display: inline-flex;
  align-items: center;
  padding: 0.75rem 1.5rem;
  border-radius: 8px;
  font-weight: 500;
  transition: all 0.2s ease;
}

.logout-button {
  background: #ff4757;
  color: white;
}

.logout-button:hover {
  background: #ff6b81;
}

.login-button {
  background: #2ed573;
  color: white;
}

.login-button:hover {
  background: #7bed9f;
}

p {
  margin: 0.8rem 0;
  font-size: 1.1rem;
  color: #333;
}
</style>