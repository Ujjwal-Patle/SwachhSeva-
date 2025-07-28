
import React from 'react';
import NavbarComponent from './NavBarComponent';
import HeroSection from './TopSection';
import StatsSection from './StatsSection';
import HowItWorks from './HowItWorks';
import RecentlyResolved from './RecentlyResolved';
import Features from './Features';
import Testimonials from './Testimonials';
import Contact from './Contacts';

function App() {
  return (
    <>
      <NavbarComponent />
      <HeroSection />
      <StatsSection />
      <HowItWorks />
      <RecentlyResolved />
      <Features />
      <Testimonials />
      <Contact />
    </>
  );
}

export default App;
