import React from 'react';
import { Container } from 'react-bootstrap';
import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet';
import L from 'leaflet';

// Custom green check icon
const checkIcon = new L.Icon({
  iconUrl: 'https://cdn-icons-png.flaticon.com/512/190/190411.png',
  iconSize: [30, 30],
  iconAnchor: [15, 30],
  popupAnchor: [0, -30],
});

const resolvedLocations = [
  {
    position: [19.0760, 72.8777], // Mumbai
    title: 'Garbage Cleared',
    description: 'Mumbai, Maharashtra',
  },
  {
    position: [22.5726, 88.3639], // Kolkata
    title: 'Park Sanitation',
    description: 'Kolkata, West Bengal',
  },
];

const RecentlyResolved = () => {
  return (
    <Container className="py-5 " style={{ backgroundColor: '#f8f9fa' }}>
      <h2 className="text-center fw-bold mb-2">Recently Resolved Complaints</h2>
      <p className="text-center mb-4 text-muted">
        See sanitation issues that have been recently resolved in communities across the country.
      </p>
      <div style={{ height: '400px', borderRadius: '12px', overflow: 'hidden' }}>
        <MapContainer center={[21.1466, 79.0888]} zoom={5} style={{ height: '100%', width: '100%' }}>
          <TileLayer
            attribution='&copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors'
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
          />
          {resolvedLocations.map((loc, idx) => (
            <Marker key={idx} position={loc.position} icon={checkIcon}>
              <Popup>
                <strong>{loc.title}</strong>
                <br />
                {loc.description}
              </Popup>
            </Marker>
          ))}
        </MapContainer>
      </div>
    </Container>
  );
};

export default RecentlyResolved;
