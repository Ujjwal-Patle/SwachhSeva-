import React from 'react';
import { Container, Button } from 'react-bootstrap';
import './HeroSection.css';

const HeroSection = () => {
  const heroStyle = {
    height: '90vh',
    backgroundImage: `linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url('https://images.unsplash.com/photo-1449824913935-59a10b8d2000?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8Y2l0eXxlbnwwfHwwfHx8MA%3D%3D')`,
    backgroundSize: 'cover',
    backgroundPosition: 'center',
    backgroundRepeat: 'no-repeat',
    color: 'white',
  };

  return (
    <div style={heroStyle} className="d-flex align-items-center">
      <Container fluid className="text-center p-0">
        <h1 className="display-3 fw-bold mb-3 float-heading">Public Sanitation Complaint System</h1>
        <p className="lead mb-4">
          A clean community starts with your voice. Report, track and resolve sanitation issues now.
        </p>
        <Button variant="primary" size="lg">Get Started</Button>
      </Container>
    </div>
  );
};

export default HeroSection;
