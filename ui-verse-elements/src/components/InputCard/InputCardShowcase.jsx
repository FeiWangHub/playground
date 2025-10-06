import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import styled from 'styled-components'
import Card from './Card.jsx'

function InputCardShowcase() {
  const [interactionCount, setInteractionCount] = useState(0)

  const handleInteraction = (action) => {
    setInteractionCount(prev => prev + 1)
    console.log(`${action} performed! Total interactions: ${interactionCount + 1}`)
  }

  return (
    <AppContainer>
      <Header>
        <BackLink to="/">← Back to Home</BackLink>
        <Title>Input Card Components</Title>
        <Subtitle>Interactive Chat-Style Input Interface</Subtitle>
        <InteractionCounter>Total Interactions: {interactionCount}</InteractionCounter>
      </Header>

      <ComponentGrid>
        <ComponentSection>
          <SectionTitle>Basic Input Card</SectionTitle>
          <CardGrid>
            <CardWrapper onClick={() => handleInteraction('Card Interaction')}>
              <Card />
            </CardWrapper>
          </CardGrid>
          <Description>
            This is a modern chat-style input card with gradient borders, interactive buttons, 
            and AI-inspired design elements. The component features a textarea for input, 
            attachment buttons, and a submit button with smooth animations.
          </Description>
        </ComponentSection>

        <ComponentSection>
          <SectionTitle>Features</SectionTitle>
          <FeatureList>
            <FeatureItem>
              <FeatureIcon>🎨</FeatureIcon>
              <FeatureText>
                <FeatureTitle>Gradient Borders</FeatureTitle>
                <FeatureDesc>Beautiful gradient borders with subtle glow effects</FeatureDesc>
              </FeatureText>
            </FeatureItem>
            <FeatureItem>
              <FeatureIcon>⚡</FeatureIcon>
              <FeatureText>
                <FeatureTitle>Interactive Buttons</FeatureTitle>
                <FeatureDesc>Hover and click animations for all interactive elements</FeatureDesc>
              </FeatureText>
            </FeatureItem>
            <FeatureItem>
              <FeatureIcon>💬</FeatureIcon>
              <FeatureText>
                <FeatureTitle>Chat Interface</FeatureTitle>
                <FeatureDesc>Modern chat-style design with textarea and action buttons</FeatureDesc>
              </FeatureText>
            </FeatureItem>
            <FeatureItem>
              <FeatureIcon>✨</FeatureIcon>
              <FeatureText>
                <FeatureTitle>Modern Styling</FeatureTitle>
                <FeatureDesc>Clean, modern design with backdrop blur and transparency</FeatureDesc>
              </FeatureText>
            </FeatureItem>
          </FeatureList>
        </ComponentSection>

        <ComponentSection>
          <SectionTitle>Usage Examples</SectionTitle>
          <UsageGrid>
            <UsageExample>
              <UsageTitle>AI Chat Interface</UsageTitle>
              <CardWrapper onClick={() => handleInteraction('AI Chat')}>
                <Card />
              </CardWrapper>
              <UsageDesc>Perfect for AI chatbots and conversational interfaces</UsageDesc>
            </UsageExample>
            <UsageExample>
              <UsageTitle>Message Input</UsageTitle>
              <CardWrapper onClick={() => handleInteraction('Message Input')}>
                <Card />
              </CardWrapper>
              <UsageDesc>Great for messaging applications and comment systems</UsageDesc>
            </UsageExample>
          </UsageGrid>
        </ComponentSection>
      </ComponentGrid>

      <Footer>
        <FooterText>
          Interactive Input Card Component - Built with React and Styled Components
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

const InteractionCounter = styled.div`
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

const CardGrid = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 1.5rem;
`

const CardWrapper = styled.div`
  cursor: pointer;
  transition: transform 0.3s ease;
  
  &:hover {
    transform: scale(1.02);
  }
`

const Description = styled.p`
  color: white;
  opacity: 0.9;
  line-height: 1.6;
  text-align: center;
  max-width: 600px;
  margin: 0 auto;
`

const FeatureList = styled.div`
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 1.5rem;
`

const FeatureItem = styled.div`
  display: flex;
  align-items: flex-start;
  gap: 1rem;
  padding: 1rem;
  background: rgba(255, 255, 255, 0.05);
  border-radius: 0.5rem;
  border: 1px solid rgba(255, 255, 255, 0.1);
`

const FeatureIcon = styled.div`
  font-size: 1.5rem;
  flex-shrink: 0;
`

const FeatureText = styled.div`
  flex: 1;
`

const FeatureTitle = styled.h4`
  color: white;
  font-size: 1rem;
  font-weight: 600;
  margin-bottom: 0.5rem;
`

const FeatureDesc = styled.p`
  color: white;
  opacity: 0.8;
  font-size: 0.9rem;
  line-height: 1.4;
  margin: 0;
`

const UsageGrid = styled.div`
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 2rem;
`

const UsageExample = styled.div`
  text-align: center;
`

const UsageTitle = styled.h4`
  color: white;
  font-size: 1.1rem;
  font-weight: 600;
  margin-bottom: 1rem;
`

const UsageDesc = styled.p`
  color: white;
  opacity: 0.8;
  font-size: 0.9rem;
  margin-top: 1rem;
`

const Footer = styled.footer`
  margin-top: 3rem;
  text-align: center;
  color: white;
  opacity: 0.8;
`

const FooterText = styled.p`
  margin: 0.5rem 0;
`

export default InputCardShowcase