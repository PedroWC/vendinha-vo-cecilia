import api from './api';
import { endpoints } from '../config/apiConfig';

// Serviço de autenticação
export const login = async (email, password) => {
    try {
        const response = await api.post(endpoints.auth.login, { email, password });
        return response.data;  // Token JWT
    } catch (error) {
        throw error.response || error;
    }
};
