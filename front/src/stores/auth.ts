// stores/auth.ts
import { defineStore } from 'pinia';
import { ref } from 'vue';

export const useAuthStore = defineStore('auth', () => {
    const isLoggedIn = ref(!!localStorage.getItem('isLoggedIn'));
    const userId = ref<string | null>(localStorage.getItem('userId'));
    const userAvatar = ref<string | null>(localStorage.getItem('userAvatar') || '/default-avatar.png');

    const login = (id: string, avatar: string) => {
        try {
            // 验证参数有效性
            if (!id || !avatar) {
                throw new Error('Invalid user credentials');
            }

            // 更新状态
            userId.value = id;
            userAvatar.value = avatar;
            isLoggedIn.value = true;

            // 持久化存储
            localStorage.setItem('isLoggedIn', 'true');
            localStorage.setItem('userId', id);
            localStorage.setItem('userAvatar', avatar);

        } catch (error) {
            console.error('登录状态保存失败:', error);
            logout();
            throw error;
        }
    };

    const logout = () => {
        // 清除状态
        userId.value = null;
        userAvatar.value = null;
        isLoggedIn.value = false;

        // 清除存储
        localStorage.removeItem('isLoggedIn');
        localStorage.removeItem('userId');
        localStorage.removeItem('userAvatar');

        // 推荐同时清除其他相关存储
        localStorage.removeItem('jwtToken');
    };

    return {
        isLoggedIn,
        userId,
        userAvatar,
        login,
        logout
    };
});