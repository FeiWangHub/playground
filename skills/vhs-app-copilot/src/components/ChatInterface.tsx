import React, { useRef, useEffect } from 'react';
import { Send, Image as ImageIcon, Mic, Paperclip, Bot, User } from 'lucide-react';
import { motion } from 'framer-motion';

interface Message {
  id: string;
  role: 'user' | 'assistant';
  content: string;
  type: 'text' | 'code';
}

const mockMessages: Message[] = [
  { id: '1', role: 'assistant', content: "Hello! I'm your VHS App Copilot. I can help you connect your apps and automate workflows. What would you like to build today?", type: 'text' },
  { id: '2', role: 'user', content: "I want to create a workflow that takes new MP3 files from Bucket, transcribes them using NeuralNet, and saves the text to DataCore.", type: 'text' },
  { id: '3', role: 'assistant', content: "I can help with that. Here is a Python script that uses the enabled apps to perform this workflow. I've also set up a preview of the dashboard.", type: 'text' },
  {
    id: '4', role: 'assistant', content: `import vhs_sdk
from apps import Bucket, NeuralNet, DataCore

def process_audio_pipeline(event):
    # 1. Trigger on new file
    file = Bucket.get_file(event.file_id)
    
    # 2. Transcribe
    transcript = NeuralNet.transcribe(
        audio=file.stream(),
        language='en-US'
    )
    
    # 3. Save to DB
    DataCore.insert(
        table='transcripts',
        data={
            'filename': file.name,
            'text': transcript.text,
            'timestamp': event.timestamp
        }
    )
    
    return "Processing Complete"`, type: 'code'
  },
];

export const ChatInterface = () => {
  const [input, setInput] = React.useState('');
  const messagesEndRef = useRef<HTMLDivElement>(null);

  const scrollToBottom = () => {
    messagesEndRef.current?.scrollIntoView({ behavior: "smooth" });
  };

  useEffect(scrollToBottom, []);

  return (
    <div className="flex-1 flex flex-col h-full bg-background relative overflow-hidden">
      {/* Messages Area */}
      <div className="flex-1 overflow-y-auto p-6 space-y-6">
        {mockMessages.map((msg) => (
          <motion.div
            key={msg.id}
            initial={{ opacity: 0, y: 10 }}
            animate={{ opacity: 1, y: 0 }}
            className={`flex gap-4 ${msg.role === 'user' ? 'flex-row-reverse' : ''}`}
          >
            <div className={`w-8 h-8 rounded-full flex items-center justify-center shrink-0 ${msg.role === 'assistant' ? 'bg-primary/20 text-primary' : 'bg-muted/20 text-text'}`}>
              {msg.role === 'assistant' ? <Bot size={18} /> : <User size={18} />}
            </div>

            <div className={`flex flex-col max-w-[80%] ${msg.role === 'user' ? 'items-end' : 'items-start'}`}>
              <div className={`px-4 py-3 rounded-2xl ${msg.role === 'user'
                  ? 'bg-primary text-white rounded-tr-sm'
                  : 'bg-surface border border-border text-text rounded-tl-sm'
                }`}>
                {msg.type === 'code' ? (
                  <pre className="font-mono text-sm overflow-x-auto bg-black/30 p-3 rounded-lg my-1 border border-border/50">
                    <code>{msg.content}</code>
                  </pre>
                ) : (
                  <p className="leading-relaxed text-sm">{msg.content}</p>
                )}
              </div>
            </div>
          </motion.div>
        ))}
        <div ref={messagesEndRef} />
      </div>

      {/* Input Area */}
      <div className="p-4 border-t border-border bg-background/50 backdrop-blur-sm z-10">
        <div className="max-w-3xl mx-auto relative">
          <div className="bg-surface border border-border rounded-xl shadow-lg flex flex-col transition-colors focus-within:border-primary/50">
            <textarea
              value={input}
              onChange={(e) => setInput(e.target.value)}
              placeholder="Ask Copilot to build something..."
              className="w-full bg-transparent text-text p-4 min-h-[60px] max-h-[200px] outline-none resize-none placeholder:text-muted/50"
              rows={2}
            />

            <div className="flex items-center justify-between p-2 pl-4">
              <div className="flex items-center gap-2">
                <button className="p-2 text-muted hover:text-primary hover:bg-primary/10 rounded-lg transition-colors" title="Upload Image">
                  <ImageIcon size={18} />
                </button>
                <button className="p-2 text-muted hover:text-primary hover:bg-primary/10 rounded-lg transition-colors" title="Voice Input">
                  <Mic size={18} />
                </button>
                <button className="p-2 text-muted hover:text-primary hover:bg-primary/10 rounded-lg transition-colors" title="Attach File">
                  <Paperclip size={18} />
                </button>
              </div>

              <button className="bg-primary hover:bg-primary/90 text-white p-2 rounded-lg transition-colors flex items-center gap-2 px-4 font-medium text-sm">
                <span>Send</span>
                <Send size={16} />
              </button>
            </div>
          </div>
          <p className="text-center text-xs text-muted mt-2">
            AI can make mistakes. Please review generated code.
          </p>
        </div>
      </div>
    </div>
  );
};
