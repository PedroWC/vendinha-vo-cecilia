import { createContext, useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

// Cria o contexto de autenticação
export const AuthContext = createContext();

// Provedor de autenticação
export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);  // Estado para armazenar o usuário autenticado
    const navigate = useNavigate();

    // Função para autenticar o usuário e salvar o token JWT
    const login = (token) => {
        localStorage.setItem('token', token);
        // Decodificar o token e obter os dados do usuário (ou fazer uma requisição)
        setUser({ token });
    };

    // Função para deslogar o usuário e limpar o estado e localStorage
    const logout = () => {
        localStorage.removeItem('token');
        setUser(null);
        navigate('/login');  // Redireciona para a página de login
    };

    // Verifica se o token está no localStorage ao carregar a aplicação
    useEffect(() => {
        const token = localStorage.getItem('token');
        if (token) {
            // Decodificar o token ou verificar com uma API de "me"
            setUser({ token });
        }
    }, []);

    return (
        <AuthContext.Provider value={{ user, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};
