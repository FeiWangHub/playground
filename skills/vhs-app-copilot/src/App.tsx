import React from 'react';
import { Header } from './components/Header';
import { Sidebar } from './components/Sidebar';
import { ChatInterface } from './components/ChatInterface';
import { PreviewPanel } from './components/PreviewPanel';

function App() {
  return (
    <div className="flex flex-col h-screen bg-background text-text overflow-hidden selection:bg-primary/30">
      <Header />
      <main className="flex-1 flex overflow-hidden">
        <Sidebar />
        <ChatInterface />
        <PreviewPanel />
      </main>
    </div>
  );
}

export default App;
