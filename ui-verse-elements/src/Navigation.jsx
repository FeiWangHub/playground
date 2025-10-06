import React from 'react'
import { Link, useLocation } from 'react-router-dom'
import styled from 'styled-components'

function Navigation() {
  const location = useLocation()

  return (
    <NavContainer>
      <NavContent>
        <Logo to="/">
          <LogoIcon>🎨</LogoIcon>
          <LogoText>UI Verse</LogoText>
        </Logo>
        
        <NavLinks>
          <NavLink to="/" $active={location.pathname === '/'}>
            Home
          </NavLink>
          <NavLink to="/button3d" $active={location.pathname === '/button3d'}>
            3D Buttons
          </NavLink>
          <NavLink to="/loaders" $active={location.pathname === '/loaders'}>
            Loaders
          </NavLink>
        </NavLinks>
      </NavContent>
    </NavContainer>
  )
}

const NavContainer = styled.nav`
  background: rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(10px);
  border-bottom: 1px solid rgba(255, 255, 255, 0.2);
  position: sticky;
  top: 0;
  z-index: 100;
`

const NavContent = styled.div`
  max-width: 1200px;
  margin: 0 auto;
  padding: 1rem 2rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
`

const Logo = styled(Link)`
  display: flex;
  align-items: center;
  text-decoration: none;
  color: white;
  font-weight: 700;
  font-size: 1.5rem;
  transition: all 0.3s ease;
  
  &:hover {
    transform: scale(1.05);
  }
`

const LogoIcon = styled.span`
  font-size: 2rem;
  margin-right: 0.5rem;
`

const LogoText = styled.span`
  text-shadow: 0 2px 4px rgba(0, 0, 0, 0.3);
`

const NavLinks = styled.div`
  display: flex;
  gap: 2rem;
  align-items: center;
`

const NavLink = styled(Link)`
  color: white;
  text-decoration: none;
  font-weight: 500;
  padding: 0.5rem 1rem;
  border-radius: 0.5rem;
  transition: all 0.3s ease;
  position: relative;
  
  ${props => props.$active && `
    background: rgba(255, 255, 255, 0.2);
    color: #fbbf24;
  `}
  
  &:hover {
    background: rgba(255, 255, 255, 0.1);
    transform: translateY(-2px);
  }
  
  &::after {
    content: '';
    position: absolute;
    bottom: -2px;
    left: 50%;
    width: 0;
    height: 2px;
    background: #fbbf24;
    transition: all 0.3s ease;
    transform: translateX(-50%);
  }
  
  ${props => props.$active && `
    &::after {
      width: 80%;
    }
  `}
  
  &:hover::after {
    width: 80%;
  }
`

export default Navigation