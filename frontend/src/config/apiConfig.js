const API_BASE_URL = 'http://localhost:8181/api';  // Base URL da sua API

export const endpoints = {
    auth: {
        login: `${API_BASE_URL}/auth/login`,
    },
    products: {
        getAll: `${API_BASE_URL}/products`,
        getById: (id) => `${API_BASE_URL}/products/${id}`,
        create: `${API_BASE_URL}/products`,
        update: (id) => `${API_BASE_URL}/products/${id}`,
        delete: (id) => `${API_BASE_URL}/products/${id}`,
    },
    users: {
        getAll: `${API_BASE_URL}/users`,
        getById: (id) => `${API_BASE_URL}/users/${id}`,
        create: `${API_BASE_URL}/users/signup`,
        update: (id) => `${API_BASE_URL}/users/${id}`,
        delete: (id) => `${API_BASE_URL}/users/${id}`,
    },
};
