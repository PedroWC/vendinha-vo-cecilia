import {Routes, Route, Navigate} from 'react-router-dom';
import Users from "./components/users/Users";
import Login from "./components/users/Login";
import Home from "./components/pages/Home";
import AddProduct from "./components/products/AddProduct";
import EditProduct from "./components/products/EditProduct";
import Signup from "./components/users/Signup";

function RoutesConfig() {
    return (
        <Routes>
            <Route path="/" element={<Navigate to="/login" />} />
            <Route path="/login" element={<Login />} />
            <Route exact path="/home" component={Home} />
            <Route exact path="/addProduct" component={AddProduct} />
            <Route exact path="/editProduct/:id" component={EditProduct} />
            <Route path="/users" element={<Users />} />
            <Route path="/signup" element={<Signup />} />
        </Routes>
    );
}

export default RoutesConfig;
