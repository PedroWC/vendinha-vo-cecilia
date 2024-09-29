import api from './api';
import { endpoints } from '../config/apiConfig';

// Obtém todos os produtos
export const getAllProducts = async () => {
    try {
        const response = await api.get(endpoints.products.getAll);
        return response.data;
    } catch (error) {
        throw error.response || error;
    }
};

// Obtém um produto por ID
export const getProductById = async (id) => {
    try {
        const response = await api.get(endpoints.products.getById(id));
        return response.data;
    } catch (error) {
        throw error.response || error;
    }
};

// Cria um novo produto
export const createProduct = async (productData) => {
    try {
        const response = await api.post(endpoints.products.create, productData);
        return response.data;
    } catch (error) {
        throw error.response || error;
    }
};

// Atualiza um produto existente
export const updateProduct = async (id, productData) => {
    try {
        const response = await api.put(endpoints.products.update(id), productData);
        return response.data;
    } catch (error) {
        throw error.response || error;
    }
};

// Exclui um produto por ID
export const deleteProduct = async (id) => {
    try {
        await api.delete(endpoints.products.delete(id));
    } catch (error) {
        throw error.response || error;
    }
};
