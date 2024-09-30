import React from "react";
import { Link } from "react-router-dom";
import { Navbar, Container, Nav } from "react-bootstrap";

const AppNavbar = () => {
    return (
        <Navbar bg="light" expand="lg" className="shadow-sm mb-3">
            <Container>
                <Navbar.Brand href="/">
                    <i className="fas fa-store fa-2x"></i>
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="ms-auto">
                        <Link to="/addProduct" className="btn btn-primary">
                            <strong>Add Product</strong>
                        </Link>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
};

export default AppNavbar;
