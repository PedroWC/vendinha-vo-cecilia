import api from './api';
import { endpoints } from '../config/apiConfig';

// Cria um novo usuário
export const createUser = async (userData) => {
    try {
        const response = await api.post(endpoints.users.create, userData);
        return response.data;
    } catch (error) {
        throw error.response || error;
    }
};