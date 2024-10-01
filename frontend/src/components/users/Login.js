import React, { useState } from 'react';
import { useAuth } from '../../hooks/useAuth';
import { login as authLogin } from '../../services/authService';
import { useNavigate } from 'react-router-dom';
import '../../styles/login.css';

function Login() {
    const { login } = useAuth();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const token = await authLogin(email, password);
            login(token);
            navigate("/home");
        } catch (err) {
            setError('Credenciais inválidas');
        }
    };

    const handleSignup = () => {
        navigate("/signup");
    };

    return (
        <div className="login-container">
            <div className="login-card">
                <h1 className="login-title">Login</h1>
                <form onSubmit={handleLogin}>
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
                            <button className="button button-login is-primary is-fullwidth" type="submit">Login</button>
                        </div>
                    </div>
                </form>

                <div className="field">
                    <div className="control">
                        <button className="button button-login is-link is-fullwidth" onClick={handleSignup}>
                            Sign Up
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Login;
