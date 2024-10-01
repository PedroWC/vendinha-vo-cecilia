import React from 'react';
import { Navigate } from 'react-router-dom';

const PrivateRoute = ({ children }) => {
    const token = localStorage.getItem('token');

    // Se o token não existir, redireciona para a página de login
    if (!token) {
        return <Navigate to="/login" />;
    }

    // Caso o token exista, renderiza o componente filho
    return children;
};

export default PrivateRoute;
