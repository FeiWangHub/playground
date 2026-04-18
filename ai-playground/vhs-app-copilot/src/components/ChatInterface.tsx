import React, { useRef, useEffect } from 'react';
import { Send, Image as ImageIcon, Mic, Paperclip, Bot, User, PlusCircle } from 'lucide-react';
import { motion } from 'framer-motion';
import ReactMarkdown from 'react-markdown';
import { Prism as SyntaxHighlighter } from 'react-syntax-highlighter';
import { vscDarkPlus } from 'react-syntax-highlighter/dist/esm/styles/prism';

interface Message {
  id: string;
  role: 'user' | 'assistant';
  content: string;
  type: string;
}

export const ChatInterface = () => {
  const [input, setInput] = React.useState('');
  const [messages, setMessages] = React.useState<Message[]>([
    { id: '1', role: 'assistant', content: "Hello! I'm your VHS App Copilot. I can help you connect your apps and automate workflows. What would you like to build today?", type: 'markdown' }
  ]);
  const [isLoading, setIsLoading] = React.useState(false);
  const messagesEndRef = useRef<HTMLDivElement>(null);

  const scrollToBottom = () => {
    messagesEndRef.current?.scrollIntoView({ behavior: "smooth" });
  };

  useEffect(scrollToBottom, [messages]);

  const handleNewChat = () => {
    if (messages.length > 1) {
      if (confirm("Are you sure you want to start a new chat? This will clear current conversation.")) {
        setMessages([
          { id: '1', role: 'assistant', content: "Hello! I'm your VHS App Copilot. I can help you connect your apps and automate workflows. What would you like to build today?", type: 'markdown' }
        ]);
        setInput('');
      }
    }
  };

  const handleSend = async () => {
    if (!input.trim() || isLoading) return;

    const userMessage: Message = {
      id: Date.now().toString(),
      role: 'user',
      content: input,
      type: 'markdown'
    };

    setMessages(prev => [...prev, userMessage]);
    setInput('');
    setIsLoading(true);

    try {
      const response = await fetch('http://localhost:8000/api/chat', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          messages: [...messages, userMessage].map(m => ({
            role: m.role,
            content: m.content
          })),
          model: "stepfun/step-3.5-flash:free"
        }),
      });

      if (!response.ok) {
        throw new Error('Failed to fetch from backend');
      }

      const data = await response.json();

      const assistantMessage: Message = {
        id: data.id || Date.now().toString(),
        role: 'assistant',
        content: data.content,
        type: 'markdown'
      };

      setMessages(prev => [...prev, assistantMessage]);
    } catch (error) {
      console.error('Error sending message:', error);
      const errorMessage: Message = {
        id: Date.now().toString(),
        role: 'assistant',
        content: "Sorry, I encountered an error. Please make sure the backend is running and configured correctly.",
        type: 'markdown'
      };
      setMessages(prev => [...prev, errorMessage]);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="flex-1 flex flex-col h-full bg-background relative overflow-hidden">
      {/* Chat Header */}
      <div className="h-14 border-b border-border flex items-center justify-between px-6 bg-surface/30 backdrop-blur-sm">
        <div className="flex items-center gap-2">
          <Bot size={18} className="text-primary" />
          <span className="text-sm font-medium text-text">What app to build today?</span>
        </div>
        <button
          onClick={handleNewChat}
          className="flex items-center gap-1.5 text-xs font-medium text-muted hover:text-danger hover:bg-danger/10 px-2.5 py-1.5 rounded-lg transition-all"
          title="Start New Chat"
        >
          <PlusCircle size={14} />
          <span>New Chat</span>
        </button>
      </div>

      {/* Messages Area */}
      <div className="flex-1 overflow-y-auto p-6 space-y-6">
        {messages.map((msg) => (
          <motion.div
            key={msg.id}
            initial={{ opacity: 0, y: 10 }}
            animate={{ opacity: 1, y: 0 }}
            className={`flex gap-4 ${msg.role === 'user' ? 'flex-row-reverse' : ''}`}
          >
            <div className={`w-8 h-8 rounded-full flex items-center justify-center shrink-0 ${msg.role === 'assistant' ? 'bg-primary/20 text-primary' : 'bg-muted/20 text-text'}`}>
              {msg.role === 'assistant' ? <Bot size={18} /> : <User size={18} />}
            </div>

            <div className={`flex flex-col max-w-[85%] ${msg.role === 'user' ? 'items-end' : 'items-start'}`}>
              <div className={`px-4 py-3 rounded-2xl ${msg.role === 'user'
                ? 'bg-primary text-white rounded-tr-sm'
                : 'bg-surface border border-border text-text rounded-tl-sm'
                }`}>
                <div className="prose prose-sm prose-invert max-w-none">
                  <ReactMarkdown
                    components={{
                      code({ node, inline, className, children, ...props }: any) {
                        const match = /language-(\w+)/.exec(className || '');
                        return !inline && match ? (
                          <div className="rounded-lg overflow-hidden my-2 border border-border/50">
                            <div className="bg-surface px-3 py-1 border-b border-border/50 text-xs font-mono text-muted flex justify-between items-center">
                              <span>{match[1]}</span>
                            </div>
                            <SyntaxHighlighter
                              style={vscDarkPlus}
                              language={match[1]}
                              PreTag="div"
                              customStyle={{
                                margin: 0,
                                padding: '1rem',
                                backgroundColor: 'rgba(0,0,0,0.3)',
                                fontSize: '0.875rem'
                              }}
                              {...props}
                            >
                              {String(children).replace(/\n$/, '')}
                            </SyntaxHighlighter>
                          </div>
                        ) : (
                          <code className={`${className} bg-black/30 px-1 rounded text-primary-light font-mono`} {...props}>
                            {children}
                          </code>
                        );
                      },
                      p({ children }) {
                        return <p className="mb-2 last:mb-0 leading-relaxed text-sm">{children}</p>;
                      },
                      ul({ children }) {
                        return <ul className="list-disc ml-4 mb-2 space-y-1">{children}</ul>;
                      },
                      ol({ children }) {
                        return <ol className="list-decimal ml-4 mb-2 space-y-1">{children}</ol>;
                      },
                      h1: ({ children }) => <h1 className="text-xl font-bold mb-2 mt-4 first:mt-0">{children}</h1>,
                      h2: ({ children }) => <h2 className="text-lg font-bold mb-2 mt-3">{children}</h2>,
                      h3: ({ children }) => <h3 className="text-md font-bold mb-1 mt-2">{children}</h3>,
                      blockquote: ({ children }) => <blockquote className="border-l-4 border-primary/50 pl-4 italic my-2 text-muted">{children}</blockquote>
                    }}
                  >
                    {msg.content}
                  </ReactMarkdown>
                </div>
              </div>
            </div>
          </motion.div>
        ))}
        {isLoading && (
          <motion.div
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            className="flex gap-4"
          >
            <div className="w-8 h-8 rounded-full flex items-center justify-center bg-primary/20 text-primary">
              <Bot size={18} className="animate-pulse" />
            </div>
            <div className="bg-surface border border-border text-text rounded-2xl rounded-tl-sm px-4 py-3">
              <div className="flex gap-1">
                <span className="w-1.5 h-1.5 bg-muted rounded-full animate-bounce" style={{ animationDelay: '0ms' }}></span>
                <span className="w-1.5 h-1.5 bg-muted rounded-full animate-bounce" style={{ animationDelay: '150ms' }}></span>
                <span className="w-1.5 h-1.5 bg-muted rounded-full animate-bounce" style={{ animationDelay: '300ms' }}></span>
              </div>
            </div>
          </motion.div>
        )}
        <div ref={messagesEndRef} />
      </div>

      {/* Input Area */}
      <div className="p-4 border-t border-border bg-background/50 backdrop-blur-sm z-10">
        <div className="max-w-3xl mx-auto relative">
          <div className="bg-surface border border-border rounded-xl shadow-lg flex flex-col transition-colors focus-within:border-primary/50">
            <textarea
              value={input}
              onChange={(e) => setInput(e.target.value)}
              onKeyDown={(e) => {
                if (e.key === 'Enter' && !e.shiftKey) {
                  e.preventDefault();
                  handleSend();
                }
              }}
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

              <button
                onClick={handleSend}
                disabled={isLoading || !input.trim()}
                className="bg-primary hover:bg-primary/90 disabled:opacity-50 disabled:cursor-not-allowed text-white p-2 rounded-lg transition-colors flex items-center gap-2 px-4 font-medium text-sm"
              >
                <span>{isLoading ? 'Thinking...' : 'Send'}</span>
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
