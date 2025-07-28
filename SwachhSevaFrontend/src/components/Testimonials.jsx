import React from 'react';
import { Container, Carousel } from 'react-bootstrap';

const Testimonials = () => {
  return (
    <Container id="testimonials" className="py-5">
      <h2 className="text-center mb-4">What People Say</h2>
      <Carousel indicators={false} interval={5000} className="w-75 mx-auto">
        <Carousel.Item>
          <blockquote className="blockquote text-center">
            <p>"Amazing app! Resolved my garbage issue in 2 days."</p>
            <footer className="blockquote-footer">Priya Desai</footer>
          </blockquote>
        </Carousel.Item>
        <Carousel.Item>
          <blockquote className="blockquote text-center">
            <p>"I love how easy it is to file a complaint and track it."</p>
            <footer className="blockquote-footer">Ramesh Kulkarni</footer>
          </blockquote>
        </Carousel.Item>
      </Carousel>
    </Container>
  );
};

export default Testimonials;
