import React from 'react';
import { Container, Form, Button } from 'react-bootstrap';

const Contact = () => {
  return (
    <Container fluid id="contact" className="py-5 bg-light">
      <h2 className="text-center mb-4">Contact Us</h2>
      <Form className="mx-auto" style={{ maxWidth: '600px' }}>
        <Form.Group className="mb-3" controlId="formName">
          <Form.Control type="text" placeholder="Your Name" required />
        </Form.Group>
        <Form.Group className="mb-3" controlId="formEmail">
          <Form.Control type="email" placeholder="Your Email" required />
        </Form.Group>
        <Form.Group className="mb-3" controlId="formMessage">
          <Form.Control as="textarea" rows={4} placeholder="Your Message" required />
        </Form.Group>
        <div className="text-center">
          <Button variant="primary" type="submit">Send</Button>
        </div>
      </Form>
    </Container>
  );
};

export default Contact;
