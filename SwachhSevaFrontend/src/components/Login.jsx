import React, { useState } from 'react';
import { Container, Form, Button } from 'react-bootstrap';
import { toast } from 'react-toastify';
import { Link } from 'react-router-dom';

const Login = () => {
  const [form, setForm] = useState({
    email_id: '',
    password: '',
  });

  const [errors, setErrors] = useState({});

  const validate = () => {
    const newErrors = {};

    if (!form.email_id.includes('@')) {
      newErrors.email_id = 'Enter a valid email address';
    }

    const passwordRegex = /^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{6,}$/;
    if (!passwordRegex.test(form.password)) {
      newErrors.password = 'Password must be at least 6 characters, with uppercase, lowercase, number & special char';
    }

    return newErrors;
  };

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const validationErrors = validate();
    setErrors(validationErrors);

    if (Object.keys(validationErrors).length > 0) {
      toast.error('Please fix the errors and try again');
      return;
    }

    try {
      const response = await fetch('/api/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(form),
      });

      if (!response.ok) throw new Error('Login failed');

      toast.success('Login successful!');
      setForm({ email_id: '', password: '' });
      setErrors({});
    } catch (error) {
      toast.error('Login failed. Please check your credentials.');
    }
  };




return (
  <div style={{ background: 'linear-gradient(to right, #e0f7fa, #f0fdf4)', minHeight: '100vh' }}>
    {/* SwachhSeva heading at the top */}
    <div className="text-center pt-5">
      <Link to="/" className="fw-bold fs-1 text-primary text-decoration-none">
        SwachhSeva
      </Link>
    </div>

   
    <div
      style={{
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        minHeight: 'calc(100vh - 100px)', // subtract height of heading space
        marginTop: '-60px', 
      }}
    >
      <Container
        style={{
          maxWidth: '500px',
          background: '#ffffff',
          padding: '2.5rem 2rem',
          borderRadius: '12px',
          boxShadow: '0 4px 12px rgba(0,0,0,0.1)',
        }}
      >
        <h2 className="mb-4 text-center">Login</h2>

        <Form onSubmit={handleSubmit}>
          <Form.Group className="mb-3">
            <Form.Label>Email ID</Form.Label>
            <Form.Control
              type="email"
              name="email_id"
              value={form.email_id}
              onChange={handleChange}
              isInvalid={!!errors.email_id}
              placeholder="Enter email"
            />
            <Form.Control.Feedback type="invalid">{errors.email_id}</Form.Control.Feedback>
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Password</Form.Label>
            <Form.Control
              type="password"
              name="password"
              value={form.password}
              onChange={handleChange}
              isInvalid={!!errors.password}
              placeholder="Enter password"
            />
            <Form.Control.Feedback type="invalid">{errors.password}</Form.Control.Feedback>
          </Form.Group>

          <Button type="submit" variant="primary" className="w-100">
            Login
          </Button>

          <div className="text-center mt-3">
            <span>Don't have an account? </span>
                <Link to="/register" className="text-primary fw-semibold text-decoration-none">
                    Register here
                </Link>
         </div>

        </Form>
      </Container>
    </div>
  </div>
);


};

export default Login;
