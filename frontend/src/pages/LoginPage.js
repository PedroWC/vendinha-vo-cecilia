import React, { useState } from 'react';
import { useAuth } from '../hooks/useAuth';
import axios from "axios";

function LoginPage() {
    const { login } = useAuth();  // Usa o hook para acessar a função de login
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(null);

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('/api/auth/login', { email, password });
            login(response.data);  // Chama o login do contexto para salvar o token
        } catch (err) {
            setError('Credenciais inválidas');
        }
    };

    return (
        <form onSubmit={handleLogin}>
            <input
                type="email"
                placeholder="Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
            />
            <input
                type="password"
                placeholder="Senha"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
            />
            <button type="submit">Entrar</button>
            {error && <p>{error}</p>}
        </form>
    );
}

export default LoginPage;
