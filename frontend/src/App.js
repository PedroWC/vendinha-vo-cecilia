import { BrowserRouter as Router } from 'react-router-dom';
import Navbar from './components/Navbar';
import RoutesConfig from './routes';
import { AuthProvider } from './context/AuthContext';  // Contexto de autenticação

function App() {
    return (
        <Router>  {/* O Router precisa envolver todo o resto */}
            <AuthProvider>  {/* Agora o AuthProvider está dentro do Router */}
                <Navbar />
                <RoutesConfig />
            </AuthProvider>
        </Router>
    );
}

export default App;
