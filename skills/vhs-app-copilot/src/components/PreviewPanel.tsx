import React from 'react';
import { RotateCw, Terminal, Maximize2 } from 'lucide-react';

export const PreviewPanel = () => {
  return (
    <div className="w-[400px] h-full border-l border-border bg-background flex flex-col">
      <div className="h-12 border-b border-border flex items-center justify-between px-4 bg-surface">
        <div className="flex items-center gap-2">
          <span className="w-3 h-3 rounded-full bg-danger"></span>
          <span className="w-3 h-3 rounded-full bg-yellow-500"></span>
          <span className="w-3 h-3 rounded-full bg-success"></span>
        </div>
        <div className="text-xs text-muted font-mono">localhost:3000/preview</div>
        <div className="flex gap-2 text-muted">
          <RotateCw size={14} className="cursor-pointer hover:text-text" />
          <Maximize2 size={14} className="cursor-pointer hover:text-text" />
        </div>
      </div>

      <div className="flex-1 bg-surface/50 p-4 overflow-y-auto">
        {/* Streamlit-like mock UI */}
        <div className="bg-surface border border-border rounded-lg shadow-sm overflow-hidden mb-4">
          <div className="p-4 border-b border-border bg-surface flex justify-between items-center">
            <h3 className="font-semibold text-text">Audio Processing Pipeline</h3>
            <span className="px-2 py-0.5 rounded-full bg-success/20 text-success text-xs border border-success/30">Running</span>
          </div>

          <div className="p-4 space-y-4">
            <div className="bg-background border border-border rounded p-3">
              <label className="text-xs text-muted uppercase font-bold block mb-2">Input Source</label>
              <div className="flex items-center gap-2 text-sm text-text">
                <div className="w-2 h-2 rounded-full bg-primary animate-pulse"></div>
                Listening to Bucket/uploads...
              </div>
            </div>

            <div className="space-y-2">
              <label className="text-xs text-muted uppercase font-bold block">Recent Activity</label>
              {[1, 2, 3].map((i) => (
                <div key={i} className="flex items-center justify-between text-sm p-2 hover:bg-background rounded cursor-default">
                  <span className="text-muted">recording_{i}.mp3</span>
                  <span className="text-success text-xs">Processed</span>
                </div>
              ))}
            </div>

            <div className="mt-4">
              <button className="w-full bg-primary/10 hover:bg-primary/20 text-primary border border-primary/30 rounded py-2 text-sm font-medium transition-colors">
                View Logs
              </button>
            </div>
          </div>
        </div>

        {/* Console Output Mock */}
        <div className="bg-black/40 border border-border rounded-lg p-3 font-mono text-xs text-muted">
          <div className="flex items-center gap-2 text-text mb-2 border-b border-border/30 pb-2">
            <Terminal size={12} />
            <span>Output Console</span>
          </div>
          <div className="space-y-1">
            <p><span className="text-success">➜</span> vhs-copilot initialized</p>
            <p><span className="text-blue-400">ℹ</span> Connected to NeuralNet API</p>
            <p><span className="text-blue-400">ℹ</span> Connected to DataCore</p>
            <p><span className="text-muted">&gt; Waiting for events...</span></p>
          </div>
        </div>
      </div>
    </div>
  );
};
