<template>
  <div class="login-container">
    <div class="login-form">
      <h2>用户登录</h2>
      <form @submit.prevent="handleSubmit">
        <div class="form-group">
          <label for="username">用户名:</label>
          <input type="text" id="username" v-model="username" required>
        </div>
        <div class="form-group">
          <label for="password">密码:</label>
          <input type="password" id="password" v-model="password" required>
        </div>
        <button type="submit" class="login-btn">登录</button>
      </form>
      <div class="register-link">
        <a href="#" @click.prevent="goToRegister">还没有账号？前往注册</a>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { authApi } from '@/services/authApi';
import { useAuthStore } from '@/stores/auth';

const router = useRouter();
const authStore = useAuthStore();
const username = ref('');
const password = ref('');

const handleSubmit = async () => {
  try {
    const credentials = {
      username: username.value,
      password: password.value
    };

    console.log('发送的登录凭据:', credentials);

    const response = await authApi.login(credentials);
    console.log('登录成功:', response.data);

    const userData = response.data;

    const avatar = userData.avatar || '/default-avatar.png';

    // 存储用户ID和头像
    authStore.login(userData.id.toString(), avatar);

    // 跳转前验证登录状态
    if (authStore.isLoggedIn) {
      await router.push('/profile');
    } else {
      console.error('登录状态更新失败');
    }
  } catch (error) {
    console.error('登录失败:', error);
    // 清除无效的登录状态
    authStore.logout();
    alert('登录失败，请检查用户名和密码');
  }
};

// 跳转到注册页面
const goToRegister = () => {
  router.push('/register');
};
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
}

.login-form {
  background-color: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  width: 300px;
}

h2 {
  text-align: center;
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  margin-bottom: 5px;
}

input {
  width: 100%;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.login-btn {
  width: 100%;
  padding: 10px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.login-btn:hover {
  background-color: #45a049;
}

.register-link {
  margin-top: 15px;
  text-align: center;
}

.register-link a {
  text-decoration: none;
  color: #2196F3;
  font-size: 14px;
}

.register-link a:hover {
  text-decoration: underline;
}
</style>