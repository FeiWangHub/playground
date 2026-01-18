import React from 'react';
import { 
  Box, 
  Music, 
  Video, 
  Cloud, 
  Database, 
  Globe, 
  Cpu, 
  Shield, 
  Zap,
  Activity,
  Layers,
  Settings
} from 'lucide-react';
import { clsx } from 'clsx';
import { motion } from 'framer-motion';

export interface AppItem {
  id: string;
  name: string;
  icon: React.ElementType;
  enabled: boolean;
  description: string;
}

const apps: AppItem[] = [
  { id: 'bucket', name: 'Bucket', icon: Box, enabled: true, description: 'Cloud Storage' },
  { id: 'mp3', name: 'inMP3', icon: Music, enabled: true, description: 'Audio Processor' },
  { id: 'series', name: 'Series Now', icon: Video, enabled: false, description: 'Streaming' },
  { id: 'cloud', name: 'CloudSync', icon: Cloud, enabled: true, description: 'Sync Service' },
  { id: 'db', name: 'DataCore', icon: Database, enabled: true, description: 'Database' },
  { id: 'web', name: 'WebCrawler', icon: Globe, enabled: false, description: 'Scraper' },
  { id: 'ai', name: 'NeuralNet', icon: Cpu, enabled: true, description: 'AI Engine' },
  { id: 'sec', name: 'GuardDog', icon: Shield, enabled: true, description: 'Security' },
  { id: 'fast', name: 'TurboCharge', icon: Zap, enabled: false, description: 'Accelerator' },
  { id: 'health', name: 'SysHealth', icon: Activity, enabled: true, description: 'Monitoring' },
  { id: 'stack', name: 'StackOver', icon: Layers, enabled: false, description: 'Dev Tools' },
];

export const Sidebar = () => {
  const [items, setItems] = React.useState(apps);

  const toggleApp = (id: string) => {
    setItems(items.map(app => 
      app.id === id ? { ...app, enabled: !app.enabled } : app
    ));
  };

  return (
    <div className="w-80 h-full border-r border-border bg-surface flex flex-col">
      <div className="p-4 border-b border-border">
        <h2 className="text-sm font-semibold text-muted uppercase tracking-wider mb-4">Enabled Apps</h2>
        <div className="relative">
          <input 
            type="text" 
            placeholder="Search apps..." 
            className="w-full bg-background border border-border rounded-md px-3 py-2 text-sm text-text focus:outline-none focus:border-primary transition-colors"
          />
        </div>
      </div>
      
      <div className="flex-1 overflow-y-auto p-2 space-y-1">
        {items.map((app) => (
          <motion.div 
            key={app.id}
            initial={false}
            animate={{ backgroundColor: app.enabled ? 'rgba(48, 54, 61, 0.4)' : 'transparent' }}
            className={clsx(
              "flex items-center justify-between p-3 rounded-lg hover:bg-surfaceHover transition-colors group cursor-pointer",
              !app.enabled && "opacity-60 hover:opacity-100"
            )}
            onClick={() => toggleApp(app.id)}
          >
            <div className="flex items-center gap-3">
              <div className={clsx(
                "p-2 rounded-md transition-colors",
                app.enabled ? "bg-primary/20 text-primary" : "bg-border/50 text-muted"
              )}>
                <app.icon size={18} />
              </div>
              <div className="flex flex-col">
                <span className="text-sm font-medium text-text">{app.name}</span>
                <span className="text-xs text-muted">{app.description}</span>
              </div>
            </div>
            
            <div className={clsx(
              "w-10 h-5 rounded-full relative transition-colors duration-300",
              app.enabled ? "bg-primary" : "bg-border"
            )}>
              <div className={clsx(
                "absolute top-1 w-3 h-3 rounded-full bg-white transition-all duration-300",
                app.enabled ? "left-6" : "left-1"
              )} />
            </div>
          </motion.div>
        ))}
      </div>

      <div className="p-4 border-t border-border mt-auto">
        <button className="flex items-center gap-2 text-sm text-muted hover:text-text transition-colors w-full p-2 rounded-md hover:bg-surfaceHover">
          <Settings size={16} />
          <span>Manage Extensions</span>
        </button>
      </div>
    </div>
  );
};
