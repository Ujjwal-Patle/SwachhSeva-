import React from 'react';
import { Container, Row, Col, Card } from 'react-bootstrap';

const features = [
  {
    icon: 'fa-map-marker-alt',
    title: 'Geo-tagged Complaints',
    text: 'Automatically attach location with complaints.',
  },
  {
    icon: 'fa-camera',
    title: 'Image Upload',
    text: 'Submit visual evidence of sanitation issues.',
  },
  {
    icon: 'fa-bolt',
    title: 'Quick Tracking',
    text: 'Track status updates in real-time.',
  },
];

const Features = () => {
  return (
    <Container id="features" className="py-5">
      <h2 className="text-center mb-4">Features</h2>
      <Row className="g-4">
        {features.map((f, i) => (
          <Col key={i} md={4}>
            <Card className="h-100 shadow-sm text-center p-3">
              <i className={`fas ${f.icon} fa-3x text-primary mb-3`}></i>
              <Card.Title className="fw-bold">{f.title}</Card.Title>
              <Card.Text>{f.text}</Card.Text>
            </Card>
          </Col>
        ))}
      </Row>
    </Container>
  );
};

export default Features;
