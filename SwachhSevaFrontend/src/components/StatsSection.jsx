import React from 'react';
import { Container, Row, Col, Card } from 'react-bootstrap';

const stats = [
  {
    icon: 'fa-check',
    iconBg: '#059669',
    color: '#fff',
    value: '12,345+',
    label: 'Issues Resolved',
  },
  {
    icon: 'fa-users',
    iconBg: '#3b82f6',
    color: '#fff',
    value: '5,678+',
    label: 'Active Volunteers',
  },
  {
    icon: 'fa-map-marker-alt',
    iconBg: '#059669',
    color: '#fff',
    value: '120+',
    label: 'Cities Covered',
  },
];

const StatsSection = () => {
  return (
    <Container fluid className="py-5" style={{ backgroundColor: '#f8f9fa' }}>
      <Row className="g-4">
        {stats.map((stat, idx) => (
          <Col key={idx} md={4}>
            <Card className="text-center shadow-sm h-100 p-4">
              <div
                className="mx-auto mb-3 d-flex align-items-center justify-content-center"
                style={{
                  width: '50px',
                  height: '50px',
                  borderRadius: '50%',
                  backgroundColor: stat.iconBg,
                }}
              >
                <i className={`fas ${stat.icon}`} style={{ color: stat.color, fontSize: '20px' }}></i>
              </div>
              <h4 className="fw-bold">{stat.value}</h4>
              <p className="mb-0">{stat.label}</p>
            </Card>
          </Col>
        ))}
      </Row>
    </Container>
  );
};

export default StatsSection;
