import axios from 'axios';

// Cria uma instância do axios sem baseURL
const api = axios.create({
    headers: {
        'Content-Type': 'application/json',
    },
});

// Interceptor para adicionar o token JWT nas requisições
api.interceptors.request.use((config) => {
    const token = localStorage.getItem('token');
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
}, (error) => {
    return Promise.reject(error);
});

export default api;
