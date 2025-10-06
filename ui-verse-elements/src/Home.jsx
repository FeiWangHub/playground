import React from 'react'
import { Link } from 'react-router-dom'
import styled from 'styled-components'
import Button3D from './components/Button3D/Button3D.jsx'

function Home() {
  return (
    <HomeContainer>
      <Hero>
        <Title>UI Verse Elements</Title>
        <Subtitle>A Modern React Component Library Showcase</Subtitle>
        <Description>
          Explore beautiful, interactive UI components built with React and Styled Components.
          Each component is crafted with attention to detail and modern design principles.
        </Description>
      </Hero>

      <ComponentShowcase>
        <ShowcaseTitle>Component Categories</ShowcaseTitle>
        <ComponentGrid>
          <ComponentCard to="/button3d">
            <CardIcon>🎯</CardIcon>
            <CardTitle>3D Buttons</CardTitle>
            <CardDescription>
              Interactive 3D button components with advanced CSS styling, 
              hover effects, and multiple variants for different use cases.
            </CardDescription>
            <CardFeatures>
              <Feature>• Multiple variants (Primary, Success, Warning, Danger)</Feature>
              <Feature>• Smooth 3D animations</Feature>
              <Feature>• Click interactions</Feature>
              <Feature>• Customizable text</Feature>
            </CardFeatures>
            <ButtonPreview>
              <Button3D text="Default Button" onClick={() => alert('Button clicked!')} />
            </ButtonPreview>
            <ViewButton>View Components →</ViewButton>
          </ComponentCard>

          <ComponentCard disabled>
            <CardIcon>📝</CardIcon>
            <CardTitle>Form Elements</CardTitle>
            <CardDescription>
              Beautiful form inputs, textareas, and form controls with 
              modern styling and validation states.
            </CardDescription>
            <CardFeatures>
              <Feature>• Input fields</Feature>
              <Feature>• Textareas</Feature>
              <Feature>• Validation states</Feature>
              <Feature>• Custom styling</Feature>
            </CardFeatures>
            <ComingSoon>Coming Soon</ComingSoon>
          </ComponentCard>

          <ComponentCard disabled>
            <CardIcon>🎨</CardIcon>
            <CardTitle>Cards & Layouts</CardTitle>
            <CardDescription>
              Flexible card components and layout systems for organizing 
              content in beautiful, responsive designs.
            </CardDescription>
            <CardFeatures>
              <Feature>• Card components</Feature>
              <Feature>• Grid layouts</Feature>
              <Feature>• Responsive design</Feature>
              <Feature>• Shadow effects</Feature>
            </CardFeatures>
            <ComingSoon>Coming Soon</ComingSoon>
          </ComponentCard>

          <ComponentCard disabled>
            <CardIcon>🚀</CardIcon>
            <CardTitle>Animations</CardTitle>
            <CardDescription>
              Smooth animations and transitions to bring your UI to life 
              with modern motion design principles.
            </CardDescription>
            <CardFeatures>
              <Feature>• CSS animations</Feature>
              <Feature>• Transition effects</Feature>
              <Feature>• Loading states</Feature>
              <Feature>• Micro-interactions</Feature>
            </CardFeatures>
            <ComingSoon>Coming Soon</ComingSoon>
          </ComponentCard>
        </ComponentGrid>
      </ComponentShowcase>

      <Footer>
        <FooterText>
          Built with React, Styled Components, and React Router
        </FooterText>
        <FooterText>
          Components inspired by <a href="https://uiverse.io" target="_blank" rel="noopener noreferrer">Uiverse.io</a>
        </FooterText>
      </Footer>
    </HomeContainer>
  )
}

const HomeContainer = styled.div`
  min-height: 100vh;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  padding: 2rem;
`

const Hero = styled.section`
  text-align: center;
  color: white;
  max-width: 800px;
  margin: 0 auto 4rem;
  padding: 2rem 0;
`

const Title = styled.h1`
  font-size: 4rem;
  font-weight: 700;
  margin-bottom: 1rem;
  text-shadow: 0 2px 4px rgba(0, 0, 0, 0.3);
  
  @media (max-width: 768px) {
    font-size: 2.5rem;
  }
`

const Subtitle = styled.h2`
  font-size: 1.5rem;
  font-weight: 400;
  opacity: 0.9;
  margin-bottom: 1.5rem;
  
  @media (max-width: 768px) {
    font-size: 1.2rem;
  }
`

const Description = styled.p`
  font-size: 1.1rem;
  line-height: 1.6;
  opacity: 0.8;
  max-width: 600px;
  margin: 0 auto;
`

const ComponentShowcase = styled.section`
  max-width: 1200px;
  margin: 0 auto;
`

const ShowcaseTitle = styled.h2`
  color: white;
  font-size: 2.5rem;
  font-weight: 600;
  text-align: center;
  margin-bottom: 3rem;
  text-shadow: 0 2px 4px rgba(0, 0, 0, 0.3);
`

const ComponentGrid = styled.div`
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 2rem;
  margin-bottom: 4rem;
`

const ComponentCard = styled(Link)`
  background: rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(10px);
  border: 1px solid rgba(255, 255, 255, 0.2);
  border-radius: 1rem;
  padding: 2rem;
  text-decoration: none;
  color: white;
  transition: all 0.3s ease;
  display: block;
  position: relative;
  overflow: hidden;
  
  ${props => props.disabled && `
    pointer-events: none;
    opacity: 0.7;
  `}
  
  &:hover {
    transform: translateY(-5px);
    box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
    border-color: rgba(255, 255, 255, 0.4);
  }
  
  &::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    height: 3px;
    background: linear-gradient(90deg, #fbbf24, #f59e0b);
    opacity: 0;
    transition: opacity 0.3s ease;
  }
  
  &:hover::before {
    opacity: 1;
  }
`

const CardIcon = styled.div`
  font-size: 3rem;
  margin-bottom: 1rem;
`

const CardTitle = styled.h3`
  font-size: 1.5rem;
  font-weight: 600;
  margin-bottom: 1rem;
`

const CardDescription = styled.p`
  font-size: 1rem;
  line-height: 1.5;
  opacity: 0.9;
  margin-bottom: 1.5rem;
`

const CardFeatures = styled.ul`
  list-style: none;
  padding: 0;
  margin-bottom: 2rem;
`

const Feature = styled.li`
  font-size: 0.9rem;
  opacity: 0.8;
  margin-bottom: 0.5rem;
`

const ViewButton = styled.div`
  background: linear-gradient(135deg, #fbbf24, #f59e0b);
  color: #1f2937;
  padding: 0.75rem 1.5rem;
  border-radius: 0.5rem;
  font-weight: 600;
  text-align: center;
  transition: all 0.3s ease;
  
  &:hover {
    transform: scale(1.05);
    box-shadow: 0 5px 15px rgba(251, 191, 36, 0.4);
  }
`

const ComingSoon = styled.div`
  background: rgba(255, 255, 255, 0.2);
  color: white;
  padding: 0.75rem 1.5rem;
  border-radius: 0.5rem;
  font-weight: 600;
  text-align: center;
  opacity: 0.7;
`

const Footer = styled.footer`
  text-align: center;
  color: white;
  opacity: 0.8;
  margin-top: 4rem;
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

const ButtonPreview = styled.div`
  display: flex;
  justify-content: center;
  margin: 1.5rem 0;
  padding: 1rem;
  background: rgba(255, 255, 255, 0.05);
  border-radius: 0.5rem;
  border: 1px solid rgba(255, 255, 255, 0.1);
`

export default Home