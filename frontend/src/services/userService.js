import api from './api';
import { endpoints } from '../config/apiConfig';

// Obtém todos os usuários
export const getAllUsers = async () => {
    try {
        const response = await api.get(endpoints.users.getAll);
        return response.data;
    } catch (error) {
        throw error.response || error;
    }
};

// Obtém um usuário por ID
export const getUserById = async (id) => {
    try {
        const response = await api.get(endpoints.users.getById(id));
        return response.data;
    } catch (error) {
        throw error.response || error;
    }
};

// Cria um novo usuário
export const createUser = async (userData) => {
    try {
        const response = await api.post(endpoints.users.create, userData);
        return response.data;
    } catch (error) {
        throw error.response || error;
    }
};

// Atualiza um usuário existente
export const updateUser = async (id, userData) => {
    try {
        const response = await api.put(endpoints.users.update(id), userData);
        return response.data;
    } catch (error) {
        throw error.response || error;
    }
};

// Exclui um usuário por ID
export const deleteUser = async (id) => {
    try {
        await api.delete(endpoints.users.delete(id));
    } catch (error) {
        throw error.response || error;
    }
};
