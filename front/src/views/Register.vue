<template>
  <div class="register-container">
    <div class="register-form">
      <h2>用户注册</h2>
      <form @submit.prevent="handleRegister">
        <div class="form-group">
          <label for="username">用户名:</label>
          <input type="text" id="username" v-model="username" required>
        </div>
        <div class="form-group">
          <label for="email">邮箱:</label>
          <input type="email" id="email" v-model="email" required>
        </div>
        <div class="form-group">
          <label for="password">密码:</label>
          <input type="password" id="password" v-model="password" required>
        </div>
        <div class="form-group">
          <label for="confirmPassword">确认密码:</label>
          <input type="password" id="confirmPassword" v-model="confirmPassword" required>
        </div>
        <button type="submit" class="register-btn">注册</button>
        <div class="login-link">
          <router-link to="/login">已有账号？立即登录</router-link>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { authApi } from '@/services/authApi';
import apiClient from "@/services/apiClient";

const router = useRouter();
const username = ref('');
const email = ref('');
const password = ref('');
const confirmPassword = ref('');
const loading = ref(false);

const handleRegister = async () => {
  if (password.value !== confirmPassword.value) {
    alert('密码不一致');
    return;
  }

  loading.value = true;
  try {
    const payload = {
      username: username.value.trim(),
      email: email.value.trim(),
      password: password.value
    };

    // 检查邮箱是否已被注册
    const isEmailExists = await checkEmailExists(payload.email);
    if (isEmailExists) {
      alert('该邮箱已被注册，请使用其他邮箱或登录');
      loading.value = false;
      return;
    }

    const response = await authApi.register(payload);
    if (response.status === 201) {
      alert('注册成功，请登录');
      await router.push('/login');
    }

  } catch (error) {
    console.error('注册失败:', error);
    const errorMessage = error.response?.data?.message || '注册失败，请检查输入';
    alert(errorMessage);
  } finally {
    loading.value = false;
  }
};

// 新增邮箱检查函数
const checkEmailExists = async (email) => {
  try {
    const response = await apiClient.get(`/auth/check-email?email=${encodeURIComponent(email)}`);
    return response.data.exists;
  } catch (error) {
    console.error('检查邮箱失败:', error);
    return false;
  }
};
</script>

<style scoped>
.register-container {
  display: flex;
  justify-content: center;
  align-items: center;
}
.register-form {
  background-color: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  width: 400px;
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
  box-sizing: border-box;
}
.register-btn {
  width: 100%;
  padding: 10px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
.register-btn:hover {
  background-color: #45a049;
}
.login-link {
  margin-top: 15px;
  text-align: center;
}
</style>