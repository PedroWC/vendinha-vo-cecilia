import axios from 'axios';

// Cria uma instância do axios
const api = axios.create({
    headers: {
        'Content-Type': 'application/json',
    },
});

// Interceptor para adicionar o token JWT nas requisições, exceto para login e criação de usuário
api.interceptors.request.use((config) => {
    const token = localStorage.getItem('token');

    // Verifica se a URL não é de login ou criação de usuário
    if (!config.url.includes('/auth/login') && !(config.url === '/api/users' && config.method === 'post')) {
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
    }

    return config;
}, (error) => {
    return Promise.reject(error);
});

export default api;
