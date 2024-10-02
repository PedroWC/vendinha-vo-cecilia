import { useContext } from 'react';
import { AuthContext } from '../context/AuthContext';

// Hook para usar o contexto de autenticação
export const useAuth = () => {
    return useContext(AuthContext);
};
