import React from "react";
import { BrowserRouter as Router } from 'react-router-dom';
import RoutesConfig from './routes';
import { AuthProvider } from './context/AuthContext';  // Contexto de autenticação
import './styles/App.css'

function App() {
    return (
        <Router>
            <AuthProvider>
                <RoutesConfig />
            </AuthProvider>
        </Router>
    );
}

export default App;
