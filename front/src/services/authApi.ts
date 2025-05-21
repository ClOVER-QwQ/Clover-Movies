import apiClient from './apiClient';

export const authApi = {
    login: (credentials: { username: string; password: string }) => apiClient.post('/auth/login', credentials),
    register: (user: any) => apiClient.post('/auth/register', user),
    checkEmail: (email: string) => apiClient.get(`/auth/check-email?email=${encodeURIComponent(email)}`), // 新增邮箱检查接口
    getUserInfo: (userId: number) => apiClient.get(`/users/${userId}`),
    logout: () => apiClient.post('/auth/logout')
};

export default { authApi };