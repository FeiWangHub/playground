import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import styled from 'styled-components'
import Button3D from './Button3D.jsx'

function Button3DShowcase() {
  const [clickCount, setClickCount] = useState(0)

  const handleButtonClick = (buttonName) => {
    setClickCount(prev => prev + 1)
    console.log(`${buttonName} clicked! Total clicks: ${clickCount + 1}`)
  }

  return (
    <AppContainer>
      <Header>
        <BackLink to="/">← Back to Home</BackLink>
        <Title>3D Button Components</Title>
        <Subtitle>Interactive 3D Button Showcase</Subtitle>
        <ClickCounter>Total Clicks: {clickCount}</ClickCounter>
      </Header>

      <ComponentGrid>
        <ComponentSection>
          <SectionTitle>Basic Styles</SectionTitle>
          <ButtonGrid>
            <Button3D 
              text="Default Button" 
              onClick={() => handleButtonClick('Default Button')}
            />
            <Button3D 
              text="Primary Button" 
              variant="primary"
              onClick={() => handleButtonClick('Primary Button')}
            />
          </ButtonGrid>
        </ComponentSection>

        <ComponentSection>
          <SectionTitle>State Styles</SectionTitle>
          <ButtonGrid>
            <Button3D 
              text="Success Button" 
              variant="success"
              onClick={() => handleButtonClick('Success Button')}
            />
            <Button3D 
              text="Warning Button" 
              variant="warning"
              onClick={() => handleButtonClick('Warning Button')}
            />
            <Button3D 
              text="Danger Button" 
              variant="danger"
              onClick={() => handleButtonClick('Danger Button')}
            />
          </ButtonGrid>
        </ComponentSection>

        <ComponentSection>
          <SectionTitle>Action Buttons</SectionTitle>
          <ButtonGrid>
            <Button3D 
              text="Login" 
              variant="primary"
              onClick={() => handleButtonClick('Login')}
            />
            <Button3D 
              text="Register" 
              variant="success"
              onClick={() => handleButtonClick('Register')}
            />
            <Button3D 
              text="Reset" 
              variant="warning"
              onClick={() => handleButtonClick('Reset')}
            />
            <Button3D 
              text="Delete" 
              variant="danger"
              onClick={() => handleButtonClick('Delete')}
            />
          </ButtonGrid>
        </ComponentSection>

        <ComponentSection>
          <SectionTitle>Custom Text</SectionTitle>
          <ButtonGrid>
            <Button3D 
              text="🚀 Launch" 
              variant="primary"
              onClick={() => handleButtonClick('Launch')}
            />
            <Button3D 
              text="💾 Save" 
              variant="success"
              onClick={() => handleButtonClick('Save')}
            />
            <Button3D 
              text="⚙️ Settings" 
              onClick={() => handleButtonClick('Settings')}
            />
            <Button3D 
              text="❤️ Like" 
              variant="danger"
              onClick={() => handleButtonClick('Like')}
            />
          </ButtonGrid>
        </ComponentSection>
      </ComponentGrid>

      <Footer>
        <FooterText>
          3D Button Components based on <a href="https://uiverse.io" target="_blank" rel="noopener noreferrer">Uiverse.io</a>
        </FooterText>
        <FooterText>
          Built with React + Styled Components
        </FooterText>
      </Footer>
    </AppContainer>
  )
}

const AppContainer = styled.div`
  min-height: 100vh;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  padding: 2rem;
  display: flex;
  flex-direction: column;
  align-items: center;
`

const Header = styled.header`
  text-align: center;
  margin-bottom: 3rem;
  color: white;
  position: relative;
`

const BackLink = styled(Link)`
  position: absolute;
  top: 0;
  left: 0;
  color: white;
  text-decoration: none;
  font-weight: 500;
  padding: 0.5rem 1rem;
  border-radius: 0.5rem;
  background: rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(10px);
  border: 1px solid rgba(255, 255, 255, 0.2);
  transition: all 0.3s ease;
  
  &:hover {
    background: rgba(255, 255, 255, 0.2);
    transform: translateX(-5px);
  }
`

const Title = styled.h1`
  font-size: 3rem;
  font-weight: 700;
  margin-bottom: 0.5rem;
  text-shadow: 0 2px 4px rgba(0, 0, 0, 0.3);
`

const Subtitle = styled.p`
  font-size: 1.2rem;
  opacity: 0.9;
  margin-bottom: 1rem;
`

const ClickCounter = styled.div`
  background: rgba(255, 255, 255, 0.2);
  padding: 0.5rem 1rem;
  border-radius: 2rem;
  font-weight: 500;
  backdrop-filter: blur(10px);
  border: 1px solid rgba(255, 255, 255, 0.3);
`

const ComponentGrid = styled.div`
  max-width: 1200px;
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 3rem;
`

const ComponentSection = styled.section`
  background: rgba(255, 255, 255, 0.1);
  padding: 2rem;
  border-radius: 1rem;
  backdrop-filter: blur(10px);
  border: 1px solid rgba(255, 255, 255, 0.2);
`

const SectionTitle = styled.h2`
  color: white;
  font-size: 1.5rem;
  font-weight: 600;
  margin-bottom: 1.5rem;
  text-align: center;
`

const ButtonGrid = styled.div`
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 2rem;
  justify-items: center;
  align-items: center;
`

const Footer = styled.footer`
  margin-top: 3rem;
  text-align: center;
  color: white;
  opacity: 0.8;
`

const FooterText = styled.p`
  margin: 0.5rem 0;
  
  a {
    color: #fbbf24;
    text-decoration: none;
    font-weight: 500;
    
    &:hover {
      text-decoration: underline;
    }
  }
`

export default Button3DShowcase