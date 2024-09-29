import {Routes, Route, Navigate} from 'react-router-dom';
import UsersPage from "./pages/UsersPage";
import ProductsPage from "./pages/ProductsPage";
import LoginPage from "./pages/LoginPage";

function RoutesConfig() {
    return (
        <Routes>
            <Route path="/" element={<Navigate to="/login" />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/products" element={<ProductsPage />} />
            <Route path="/users" element={<UsersPage />} />
        </Routes>
    );
}

export default RoutesConfig;
