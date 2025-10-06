// UI Verse Elements - A Modern React Component Library Showcase
// Production URL: https://ui-verse-elements-3huc2vxnr-feis-projects-b8995c22.vercel.app

import React from 'react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import Home from './Home.jsx'
import Button3DShowcase from './components/Button3D/Button3DShowcase.jsx'
import LoaderShowcase from './components/Loader/LoaderShowcase.jsx'
import Navigation from './Navigation.jsx'

function App() {
  return (
    <Router>
      <Navigation />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/button3d" element={<Button3DShowcase />} />
        <Route path="/loaders" element={<LoaderShowcase />} />
      </Routes>
    </Router>
  )
}

export default App