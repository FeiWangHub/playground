import React from "react";
import { ToggleLeft, ToggleRight, ArrowRight, ChevronDown } from "lucide-react";
import { motion } from "framer-motion";

const HSBCAppsAgent = () => {
  const [enabledApps, setEnabledApps] = React.useState({
    github: true,
    copilot: false,
    bitbucket: true,
    podMaster1: false,
    podMaster2: true,
    serviceNow: false,
    confluence: true
  });
  const [loading, setLoading] = React.useState(false);

  const toggleApp = (appName) => {
    setEnabledApps(prev => ({
      ...prev,
      [appName]: !prev[appName]
    }));
  };

  const handleSend = () => {
    setLoading(true);
    // 模拟异步操作，2秒后恢复
    setTimeout(() => setLoading(false), 2000);
  };

  return (
    <div className="min-h-screen bg-[#0f172a] relative overflow-hidden">
      {/* 几何背景图案 */}
      <div className="absolute inset-0 bg-[length:40px_40px] bg-gradient-to-r from-transparent via-[rgba(99,102,241,0.1)] to-transparent opacity-30"></div>
      <div className="absolute inset-0 bg-[length:40px_40px] bg-gradient-to-l from-transparent via-[rgba(79,70,229,0.1)] to-transparent opacity-30"></div>

      <div className="container mx-auto px-4 py-8 relative z-10 max-w-6xl">
        {/* 顶部导航 */}
        <nav className="flex justify-between items-center py-5">
          <div className="text-2xl font-bold text-white">CodeCup 2025</div>
          <div className="flex items-center gap-3 text-white">
            <span>Made by </span>
            <div className="w-9 h-9 rounded-full bg-[#2563eb] flex items-center justify-center font-medium">KW</div>
            <span>Keyboard Warriors</span>
          </div>
        </nav>

        {/* 主标题 */}
        <motion.div 
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.5 }}
          className="mt-10"
        >
          <h1 className="text-3xl md:text-4xl font-bold text-white">HSBC Apps Agent</h1>
          <p className="text-[#94a3b8] mt-2">Your App Copilot to connect 20,000+ Apps</p>
        </motion.div>

        {/* 主内容区 */}
        <div className="flex flex-col lg:flex-row gap-8 mt-10">
          {/* 左侧输入区 */}
          <motion.section 
            initial={{ opacity: 0, x: -20 }}
            animate={{ opacity: 1, x: 0 }}
            transition={{ duration: 0.5, delay: 0.2 }}
            className="bg-[#1e293b] rounded-xl p-6 shadow-lg flex-1 min-w-[300px] border border-[#334155]"
          >
            <h2 className="text-2xl font-semibold text-[#38bdf8] mb-4 text-left">How can I help you?</h2>
            <div className="relative">
              <textarea
                className="w-full h-32 p-4 rounded-lg bg-[#23272f] text-[#e0e7ef] font-mono border-2 border-[#334155] focus:border-[#38bdf8] focus:ring-2 focus:ring-[#38bdf8] transition-all resize-none placeholder:text-[#64748b]"
                placeholder="Type your question or requirement here..."
              />
              <motion.button 
                whileHover={{ scale: 1.05 }}
                whileTap={{ scale: 0.95 }}
                className="absolute right-3 bottom-3 bg-gradient-to-r from-[#38bdf8] to-[#6366F1] text-white p-3 rounded-lg hover:opacity-90 transition-opacity shadow-lg flex items-center justify-center"
                onClick={handleSend}
                disabled={loading}
              >
                {loading ? (
                  <svg className="animate-spin h-5 w-5 text-white" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                    <circle className="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" strokeWidth="4"></circle>
                    <path className="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8v4a4 4 0 00-4 4H4z"></path>
                  </svg>
                ) : (
                  <ArrowRight size={20} />
                )}
              </motion.button>
            </div>
          </motion.section>

          {/* 右侧应用列表 */}
          <motion.section 
            initial={{ opacity: 0, x: 20 }}
            animate={{ opacity: 1, x: 0 }}
            transition={{ duration: 0.5, delay: 0.2 }}
            className="lg:w-[35%] w-full min-w-[300px]"
          >
            <h2 className="text-xl font-semibold text-white mb-5">Apps</h2>
            <ul className="space-y-3">
              {[
                { id: "github", name: "GitHub", color: "bg-[#2ECA68]" },
                { id: "copilot", name: "Copilot", color: "bg-[#8B5CF6]" },
                { id: "bitbucket", name: "Bitbucket", color: "bg-gradient-to-r from-[#2684FF] to-[#27C3F5]" },
                { id: "podMaster1", name: "Pod Master", color: "bg-[#3B82F6]" },
                { id: "podMaster2", name: "Pod Master", color: "bg-[#3B82F6]" },
                { id: "serviceNow", name: "Service NOW", color: "bg-gradient-to-r from-[#FF4B4B] to-[#3B82F6]" },
                { id: "confluence", name: "Confluence", color: "bg-[#10B981]" },
              ].map((app, index) => (
                <motion.li
                  key={index}
                  whileHover={{ scale: 1.03, backgroundColor: "rgba(255,255,255,0.18)" }}
                  transition={{ type: "tween", duration: 0.15 }}
                  className="flex items-center justify-between p-4 bg-white/10 backdrop-blur-md rounded-lg transition-all"
                >
                  <div className="flex items-center gap-3">
                    <div className={`w-6 h-6 rounded ${app.color}`}></div>
                    <span className="text-white">{app.name}</span>
                  </div>
                  <button 
                    onClick={() => toggleApp(app.id)}
                    className="flex items-center"
                  >
                    {enabledApps[app.id] ? (
                      <ToggleRight className="text-green-400" size={24} />
                    ) : (
                      <ToggleLeft className="text-gray-400" size={24} />
                    )}
                  </button>
                </motion.li>
              ))}
            </ul>
          </motion.section>
        </div>
      </div>

      {/* 页脚 */}
      <footer className="mt-16 py-6 text-center text-gray-400 text-sm">
        <p className="mt-1">AI generated content could be wrong.</p>
        <p className="mt-1">Please check it carefully.</p>
      </footer>
    </div>
  );
};

export default HSBCAppsAgent;