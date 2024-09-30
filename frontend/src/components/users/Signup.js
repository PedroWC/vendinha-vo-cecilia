import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { createUser } from '../../services/userService';  // Serviço de criação de usuário
import '../../styles/login.css';

function Signup() {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    // Função para lidar com o cadastro
    const handleSignup = async (e) => {
        e.preventDefault();
        try {
            await createUser({ username, email, password });  // Envia os dados de criação
            navigate("/login");  // Redireciona para a página de login após cadastro bem-sucedido
        } catch (err) {
            setError('Erro ao cadastrar usuário. Verifique os campos e tente novamente.');
        }
    };

    return (
        <div className="login-container">
            <div className="login-card">
                <h1 className="login-title">Sign Up</h1>
                <form onSubmit={handleSignup}>
                    <div className="field">
                        <label className="label">Username</label>
                        <div className="control">
                            <input
                                className="input"
                                type="text"
                                placeholder="Enter your username"
                                value={username}
                                onChange={(e) => setUsername(e.target.value)}
                                required
                            />
                        </div>
                    </div>

                    <div className="field">
                        <label className="label">Email</label>
                        <div className="control">
                            <input
                                className="input"
                                type="email"
                                placeholder="Enter your email"
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                                required
                            />
                        </div>
                    </div>

                    <div className="field">
                        <label className="label">Password</label>
                        <div className="control">
                            <input
                                className="input"
                                type="password"
                                placeholder="Enter your password"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                required
                            />
                        </div>
                    </div>

                    {error && <p className="help is-danger">{error}</p>}

                    <div className="field">
                        <div className="control">
                            <button className="button is-primary is-fullwidth" type="submit">Sign Up</button>
                        </div>
                    </div>
                </form>

                <div className="field">
                    <div className="control">
                        <button className="button is-link is-fullwidth" onClick={() => navigate("/login")}>
                            Back to Login
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Signup;
