import React, { useState } from 'react';
import { Container, Form, Button } from 'react-bootstrap';
import { toast } from 'react-toastify';
import { Link } from 'react-router-dom';


const Register = () => {
  const [form, setForm] = useState({
    name: '',
    email: '',
    password: '',
    role: '',
    age: '',
    aadhaarNumber: '',
    mobileNumber: '',
  });

  const [errors, setErrors] = useState({});

  const validate = () => {
    const newErrors = {};

    if (!form.name.trim()) newErrors.name = 'Name is required';
    if (!form.email.includes('@')) newErrors.email = 'Enter a valid email';
    if (!/^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{6,})/.test(form.password)) {
    newErrors.password = 'Password must be at least 6 characters and include a number & special character';
    }

    if (!form.role) newErrors.role = 'Select a role';
    if (!form.age || form.age < 18) newErrors.age = 'Age must be 18 or above';
    if (!/^\d{12}$/.test(form.aadhaarNumber)) newErrors.aadhaarNumber = 'Aadhaar must be exactly 12 digits';
    if (!/^\d{10}$/.test(form.mobileNumber)) newErrors.mobileNumber = 'Mobile number must be 10 digits';

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
      const response = await fetch('/api/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(form),
      });

      if (!response.ok) throw new Error('Registration failed');

      toast.success('Registered successfully!');
      setForm({ name: '', email: '', password: '', role: '', age: '', aadhaarNumber: '', mobileNumber: '' });
      setErrors({});
    } catch (error) {
      toast.error('Registration failed. Please try again.');
    }
  };


return (
  <div style={{ background: 'linear-gradient(to right, #e0f7fa, #f0fdf4)', minHeight: '100vh' }}>
   
    <div className="text-center pt-5">
      <Link to="/" className="fw-bold fs-1 text-primary text-decoration-none">
        SwachhSeva
      </Link>
    </div>

    {/* Register card */}
    <div
      style={{
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        minHeight: 'calc(100vh - 100px)',
        //marginTop: '-60px',
      }}
    >
      <Container
        style={{
          maxWidth: '600px',
          background: '#ffffff',
          padding: '2.5rem 2rem',
          borderRadius: '12px',
          boxShadow: '0 4px 12px rgba(0,0,0,0.1)',
        }}
      >
        <h2 className="mb-4 text-center">Register</h2>
        <Form onSubmit={handleSubmit}>
            {['name', 'email', 'password', 'age', 'aadhaarNumber', 'mobileNumber'].map((field) => (
            <Form.Group className="mb-3" key={field}>
              <Form.Label>
                {field === 'aadhaarNumber' ? 'Aadhaar Number' : field.charAt(0).toUpperCase() + field.slice(1)}
              </Form.Label>
              <Form.Control
                type={
                  field === 'password'
                    ? 'password'
                    : field === 'email'
                    ? 'email'
                    : field === 'age' || field === 'mobileNumber' || field === 'aadhaarNumber'
                    ? 'number'
                    : 'text'
                }
                name={field}
                value={form[field]}
                onChange={handleChange}
                isInvalid={!!errors[field]}
                placeholder={`Enter ${field}`}
              />
              <Form.Control.Feedback type="invalid">{errors[field]}</Form.Control.Feedback>
            </Form.Group>
          ))}

          <Form.Group className="mb-3">
            <Form.Label>Role</Form.Label>
            <Form.Select
              name="role"
              value={form.role}
              onChange={handleChange}
              isInvalid={!!errors.role}
            >
              <option value="">Select role</option>
              <option>Reporter</option>
              <option>Volunteer</option>
              <option>Manager</option>
            </Form.Select>
            <Form.Control.Feedback type="invalid">{errors.role}</Form.Control.Feedback>
          </Form.Group>

          <Button type="submit" variant="primary" className="w-100">
            Register
          </Button>

          {/* Already registered message */}
          <div className="text-center mt-3">
            <span>Already registered? </span>
            <Link to="/login" className="text-primary fw-semibold text-decoration-none">
              Login here
            </Link>
          </div>
        </Form>
      </Container>
    </div>
  </div>
);

};

export default Register;
