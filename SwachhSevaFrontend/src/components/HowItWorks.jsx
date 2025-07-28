import React from 'react';
import { Container, Row, Col } from 'react-bootstrap';

const steps = [
  {
    number: 1,
    title: 'Report',
    icon: 'fa-camera',
    description: 'Upload a photo, add location, and describe the sanitation issue in your community.',
    bgColor: '#d1fae5',
    iconColor: '#047857',
  },
  {
    number: 2,
    title: 'Track',
    icon: 'fa-map-marked-alt',
    description: 'Follow real-time updates on your complaint status from submission to resolution.',
    bgColor: '#dbeafe',
    iconColor: '#1d4ed8',
  },
  {
    number: 3,
    title: 'Resolve',
    icon: 'fa-check-double',
    description: 'Get notified when fixed and rate the work done by volunteers or authorities.',
    bgColor: '#d1fae5',
    iconColor: '#047857',
  },
];

const HowItWorks = () => {
  return (
    <Container id="how" className="py-5">
      <h2 className="text-center fw-bold mb-5">How It Works</h2>
      <Row className="text-center justify-content-center">
        {steps.map((step, index) => (
          <Col key={index} md={4} className="mb-4 px-4">
            <div
              className="mx-auto mb-3 d-flex align-items-center justify-content-center"
              style={{
                width: '80px',
                height: '80px',
                borderRadius: '50%',
                backgroundColor: step.bgColor,
              }}
            >
              <i className={`fas ${step.icon}`} style={{ fontSize: '30px', color: step.iconColor }}></i>
            </div>
            <h5 className="fw-bold"> {step.number}. {step.title}</h5>
            <p className="text-muted">{step.description}</p>
          </Col>
        ))}
      </Row>
    </Container>
  );
};

export default HowItWorks;
