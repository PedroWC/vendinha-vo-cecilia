import React, {useContext} from "react";
import { Link } from "react-router-dom";
import {Navbar, Container, Nav, Button} from "react-bootstrap";
import {AuthContext} from "../../context/AuthContext";

const AppNavbar = () => {

    // Obtém a função de logout do AuthContext
    const { logout } = useContext(AuthContext);

    return (
        <Navbar bg="light" expand="lg" className="shadow-sm mb-3">
            <Container>
                <Navbar.Brand href="/">
                    <i className="fas fa-store fa-2x"></i>
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="ms-auto">
                        <Link to="/addProduct" className="btn btn-outline-success m-3">
                            <strong>Add Product</strong>
                        </Link>
                        <Button variant="outline-danger" className="m-3" onClick={logout}>
                            Sign Out
                        </Button>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
};

export default AppNavbar;
