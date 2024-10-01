import { Routes, Route, Navigate } from 'react-router-dom';
import Login from "./components/users/Login";
import Home from "./components/pages/Home";
import AddProduct from "./components/products/AddProduct";
import EditProduct from "./components/products/EditProduct";
import Signup from "./components/users/Signup";
import PrivateRoute from "./context/PrivateRoute";

function RoutesConfig() {
    return (
        <Routes>
            <Route path="/" element={<Navigate to="/home" />} />
            <Route path="/login" element={<Login />} />
            <Route path="/home" element={<PrivateRoute><Home /></PrivateRoute>} />
            <Route path="/addProduct" element={<PrivateRoute><AddProduct /></PrivateRoute>} />
            <Route path="/editProduct/:id" element={<PrivateRoute><EditProduct /></PrivateRoute>} />
            <Route path="/signup" element={<Signup />} />
        </Routes>
    );
}

export default RoutesConfig;
