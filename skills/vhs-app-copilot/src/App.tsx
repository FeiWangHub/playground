import React, { useState } from 'react';
import { Header } from './components/Header';
import { Sidebar } from './components/Sidebar';
import { ChatInterface } from './components/ChatInterface';
import { PreviewPanel } from './components/PreviewPanel';
import { AnimatePresence, motion } from 'framer-motion';

function App() {
  const [showPreview, setShowPreview] = useState(true);

  return (
    <div className="flex flex-col h-screen bg-background text-text overflow-hidden selection:bg-primary/30">
      <Header onTogglePreview={() => setShowPreview(!showPreview)} isPreviewOpen={showPreview} />
      <main className="flex-1 flex overflow-hidden">
        <Sidebar />
        <ChatInterface />
        <AnimatePresence mode="wait">
          {showPreview && (
            <motion.div
              initial={{ width: 0, opacity: 0 }}
              animate={{ width: 400, opacity: 1 }}
              exit={{ width: 0, opacity: 0 }}
              transition={{ type: 'spring', stiffness: 300, damping: 30 }}
              className="overflow-hidden border-l border-border"
            >
              <PreviewPanel />
            </motion.div>
          )}
        </AnimatePresence>
      </main>
    </div>
  );
}

export default App;
