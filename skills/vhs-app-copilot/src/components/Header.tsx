import React from 'react';
import { Search, Bell } from 'lucide-react';

export const Header = () => {
  return (
    <header className="h-16 border-b border-border bg-surface/80 backdrop-blur-md flex items-center justify-between px-6 sticky top-0 z-50">
      <div className="flex items-center gap-4">
        <div className="flex items-center gap-2">
          <div className="w-8 h-8 bg-gradient-to-br from-primary to-purple-600 rounded-lg flex items-center justify-center text-white font-bold text-lg shadow-lg shadow-primary/20">
            V
          </div>
          <div>
            <h1 className="font-bold text-text leading-tight">VHS App Copilot</h1>
            <p className="text-[10px] text-muted font-medium tracking-wide">
              YOUR APPS COPILOT · CONNECT 20+ SAAS
            </p>
          </div>
        </div>
      </div>

      <div className="flex items-center gap-4">
        <div className="relative hidden md:block group">
          <Search className="absolute left-3 top-1/2 -translate-y-1/2 text-muted group-focus-within:text-primary transition-colors" size={16} />
          <input
            type="text"
            placeholder="Search..."
            className="bg-background border border-border rounded-full py-1.5 pl-9 pr-4 text-sm text-text focus:outline-none focus:border-primary w-64 transition-all"
          />
          <div className="absolute right-3 top-1/2 -translate-y-1/2 flex items-center gap-1">
            <kbd className="hidden sm:inline-block border border-border rounded px-1.5 text-[10px] font-mono text-muted bg-surface">⌘ K</kbd>
          </div>
        </div>

        <div className="h-6 w-px bg-border mx-2"></div>

        <button className="relative p-2 text-muted hover:text-text hover:bg-background rounded-full transition-colors">
          <Bell size={20} />
          <span className="absolute top-1.5 right-1.5 w-2 h-2 bg-danger rounded-full border-2 border-surface"></span>
        </button>

        <button className="flex items-center gap-2 hover:bg-background pl-1 pr-3 py-1 rounded-full transition-colors border border-transparent hover:border-border">
          <div className="w-8 h-8 rounded-full bg-gradient-to-r from-blue-400 to-emerald-400 flex items-center justify-center text-white font-medium text-sm">
            F
          </div>
          <span className="text-sm font-medium text-text hidden sm:block">Fei</span>
        </button>
      </div>
    </header>
  );
};
