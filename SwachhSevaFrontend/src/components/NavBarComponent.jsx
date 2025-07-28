import React from 'react';
import { Navbar, Nav, Container } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const NavbarComponent = () => {
  return (
    <Navbar bg="white" expand="lg" sticky="top" className="shadow-sm py-3">
      <Container>
        <Navbar.Brand href="#" className="fw-bold fs-4 text-primary">SwachhSeva</Navbar.Brand>
        <Navbar.Toggle aria-controls="navbar" />
        <Navbar.Collapse id="navbar">
          <Nav className="ms-auto">
            <Nav.Link href="#how" className="px-3">How it Works</Nav.Link>
            <Nav.Link href="#features" className="px-3">Features</Nav.Link>
            <Nav.Link href="#testimonials" className="px-3">Testimonials</Nav.Link>
            <Nav.Link href="#contact" className="px-3">Contact</Nav.Link>

             <div className="d-flex ms-3">
              <Link to="/login" className="btn btn-outline-primary me-2">Login</Link>
              <Link to="/register" className="btn btn-primary">Register</Link>
            </div>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
};

export default NavbarComponent;
