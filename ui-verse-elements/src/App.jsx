import React from 'react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import Home from './Home.jsx'
import Button3DShowcase from './components/Button3D/Button3DShowcase.jsx'
import Navigation from './Navigation.jsx'

function App() {
  return (
    <Router>
      <Navigation />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/button3d" element={<Button3DShowcase />} />
      </Routes>
    </Router>
  )
}

export default App