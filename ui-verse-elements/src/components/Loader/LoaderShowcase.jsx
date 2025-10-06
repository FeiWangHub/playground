import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import styled from 'styled-components'
import Loader from './Loader.jsx'

function LoaderShowcase() {
  const [isLoading, setIsLoading] = useState(true)

  const toggleLoading = () => {
    setIsLoading(!isLoading)
  }

  return (
    <AppContainer>
      <Header>
        <BackLink to="/">← 返回首页</BackLink>
        <Title>Matrix 加载器组件</Title>
        <Subtitle>Matrix 风格的加载动画展示</Subtitle>
        <ToggleButton onClick={toggleLoading}>
          {isLoading ? '停止加载' : '开始加载'}
        </ToggleButton>
      </Header>

      <ComponentGrid>
        <ComponentSection>
          <SectionTitle>基础 Matrix 加载器</SectionTitle>
          <LoaderContainer>
            {isLoading && <Loader />}
            {!isLoading && <PlaceholderText>点击上方按钮开始加载动画</PlaceholderText>}
          </LoaderContainer>
        </ComponentSection>

        <ComponentSection>
          <SectionTitle>多个加载器展示</SectionTitle>
          <MultiLoaderGrid>
            <LoaderItem>
              <LoaderLabel>加载器 1</LoaderLabel>
              <Loader />
            </LoaderItem>
            <LoaderItem>
              <LoaderLabel>加载器 2</LoaderLabel>
              <Loader />
            </LoaderItem>
            <LoaderItem>
              <LoaderLabel>加载器 3</LoaderLabel>
              <Loader />
            </LoaderItem>
          </MultiLoaderGrid>
        </ComponentSection>

        <ComponentSection>
          <SectionTitle>使用场景示例</SectionTitle>
          <UseCaseGrid>
            <UseCase>
              <UseCaseTitle>数据加载</UseCaseTitle>
              <UseCaseDescription>
                在获取 API 数据时显示 Matrix 风格的加载动画
              </UseCaseDescription>
              <Loader />
            </UseCase>
            <UseCase>
              <UseCaseTitle>文件处理</UseCaseTitle>
              <UseCaseDescription>
                在处理文件上传或下载时的加载状态
              </UseCaseDescription>
              <Loader />
            </UseCase>
            <UseCase>
              <UseCaseTitle>AI 处理</UseCaseTitle>
              <UseCaseDescription>
                在 AI 模型处理数据时的科技感加载效果
              </UseCaseDescription>
              <Loader />
            </UseCase>
          </UseCaseGrid>
        </ComponentSection>
      </ComponentGrid>

      <Footer>
        <FooterText>
          Matrix 风格加载器 - 科技感十足的用户界面组件
        </FooterText>
      </Footer>
    </AppContainer>
  )
}

const AppContainer = styled.div`
  min-height: 100vh;
  background: linear-gradient(135deg, #0a0a0a 0%, #1a1a2e 50%, #16213e 100%);
  padding: 2rem;
  display: flex;
  flex-direction: column;
  align-items: center;
`

const Header = styled.header`
  text-align: center;
  margin-bottom: 3rem;
  color: #00ff88;
  position: relative;
`

const BackLink = styled(Link)`
  position: absolute;
  top: 0;
  left: 0;
  color: #00ff88;
  text-decoration: none;
  font-weight: 500;
  padding: 0.5rem 1rem;
  border-radius: 0.5rem;
  background: rgba(0, 255, 136, 0.1);
  backdrop-filter: blur(10px);
  border: 1px solid rgba(0, 255, 136, 0.3);
  transition: all 0.3s ease;

  &:hover {
    background: rgba(0, 255, 136, 0.2);
    transform: translateX(-5px);
    box-shadow: 0 0 10px rgba(0, 255, 136, 0.3);
  }
`

const Title = styled.h1`
  font-size: 3rem;
  font-weight: 700;
  margin-bottom: 0.5rem;
  text-shadow: 0 0 10px rgba(0, 255, 136, 0.5);
  font-family: monospace;
`

const Subtitle = styled.p`
  font-size: 1.2rem;
  opacity: 0.9;
  margin-bottom: 1rem;
  font-family: monospace;
`

const ToggleButton = styled.button`
  background: rgba(0, 255, 136, 0.2);
  color: #00ff88;
  border: 1px solid rgba(0, 255, 136, 0.5);
  padding: 0.75rem 1.5rem;
  border-radius: 0.5rem;
  font-weight: 500;
  font-family: monospace;
  cursor: pointer;
  transition: all 0.3s ease;
  backdrop-filter: blur(10px);

  &:hover {
    background: rgba(0, 255, 136, 0.3);
    box-shadow: 0 0 15px rgba(0, 255, 136, 0.4);
    transform: translateY(-2px);
  }
`

const ComponentGrid = styled.div`
  max-width: 1200px;
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 3rem;
`

const ComponentSection = styled.section`
  background: rgba(0, 255, 136, 0.05);
  padding: 2rem;
  border-radius: 1rem;
  backdrop-filter: blur(10px);
  border: 1px solid rgba(0, 255, 136, 0.2);
`

const SectionTitle = styled.h2`
  color: #00ff88;
  font-size: 1.5rem;
  font-weight: 600;
  margin-bottom: 1.5rem;
  text-align: center;
  font-family: monospace;
  text-shadow: 0 0 5px rgba(0, 255, 136, 0.5);
`

const LoaderContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 200px;
`

const PlaceholderText = styled.div`
  color: #00ff88;
  font-family: monospace;
  opacity: 0.7;
  text-align: center;
`

const MultiLoaderGrid = styled.div`
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 2rem;
  justify-items: center;
`

const LoaderItem = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 1rem;
`

const LoaderLabel = styled.div`
  color: #00ff88;
  font-family: monospace;
  font-weight: 500;
  text-shadow: 0 0 5px rgba(0, 255, 136, 0.5);
`

const UseCaseGrid = styled.div`
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 2rem;
`

const UseCase = styled.div`
  background: rgba(0, 255, 136, 0.05);
  padding: 1.5rem;
  border-radius: 0.5rem;
  border: 1px solid rgba(0, 255, 136, 0.2);
  text-align: center;
`

const UseCaseTitle = styled.h3`
  color: #00ff88;
  font-family: monospace;
  font-size: 1.2rem;
  margin-bottom: 0.5rem;
  text-shadow: 0 0 5px rgba(0, 255, 136, 0.5);
`

const UseCaseDescription = styled.p`
  color: #00ff88;
  font-family: monospace;
  opacity: 0.8;
  margin-bottom: 1rem;
  font-size: 0.9rem;
  line-height: 1.4;
`

const Footer = styled.footer`
  margin-top: 3rem;
  text-align: center;
  color: #00ff88;
  opacity: 0.8;
`

const FooterText = styled.p`
  margin: 0.5rem 0;
  font-family: monospace;
  text-shadow: 0 0 5px rgba(0, 255, 136, 0.3);
`

export default LoaderShowcase