# VHS App Copilot

## 🚀 项目简介
VHS App Copilot 是一个基于 AI 的现代化应用程序集成平台。它将多个独立的应用功能（Skills）整合到一个统一的 Chat 界面中，通过 AI 驱动的交互实现高效的任务处理与结果预览。

## 🛠️ 前端技术栈 (Frontend Tech Stack)

本项目采用了高度定制化的现代化前端架构，旨在提供卓越的 UI/UX 体验：

- **核心框架**: [React 19](https://react.dev/) + [TypeScript](https://www.typescriptlang.org/)
- **构建工具**: [Vite 7](https://vite.dev/) (极速热更新与构建)
- **样式引擎**: [Tailwind CSS v4](https://tailwindcss.com/) (原子化 CSS，零运行时开销)
- **动画效果**: [Framer Motion](https://www.framer.com/motion/) (实现 Apple 级别的丝滑交互动效)
- **图标系统**: [Lucide React](https://lucide.dev/) (简洁美观的矢量图标)
- **AI 内容展示**:
  - \`react-markdown\`: 高性能 Markdown 渲染
  - \`react-syntax-highlighter\`: 优雅的代码语法高亮
- **代码规范**: ESLint 9 + TypeScript ESLint

### 设计风格
项目放弃了传统的组件库（如 MUI 或 Material Design），转而采用 **Tailwind CSS + Framer Motion** 的组合，以实现：
- **极简主义设计**: 仿 Apple 的高级感与玻璃拟态效果。
- **现代化 UI**: 借鉴 GitHub 官网的深色模式与动画质感。
- **高度灵活性**: 每一像素均可控，无第三方组件库的视觉束缚。

## 🐍 后端技术栈 (Backend Tech Stack)

- **框架**: [FastAPI](https://fastapi.tiangolo.com/) (Python)
- **AI 引擎**: [OpenRouter](https://openrouter.ai/) (集成多种大模型能力)

## 📦 快速启动

1. **环境准备**:
   - Node.js (建议 v18+)
   - Python 3.12+

2. **启动项目**:
   \`\`\`bash
   bash start.sh
   \`\`\`
   *该脚本会自动启动前端 (http://localhost:5173) 与后端 (http://localhost:8000)*

---

# Prompt of this WEB
帮我在SKills这个目录里起一个新的网站项目，他是一个基于AI的chat boot风格的网站，整个布局的来讲左侧是一系列的可以启用的App，比如说给他比如说bucket，比如说in MP3说series now左边就是一系列的软件，并且每个软件都带有他的一个小logo和名字以及一个开关，可以让用户来打开和禁用它，然后中间是一个经典的，可以跟用户沟通对话的一个UI框用户可以输入他想要的提示词，然后跟系统交互系统交互的结果里面一般会带有代码，最好这个交互中还支持图片输入语音输入后整个网站的右侧显示的是一个用户交互之后输出的App运行环境，它是用户通过互之后运行出来的一个运行结果类似于一个stream let风格的小网页，然后整个这个App就叫做VHS App口plot名字可以写在左上方副标题可以写上you apps Copland can next twenty to sent a PS in one place，然后右上方会有用户登录和配置的小按钮，希望整个以外的设计风格很现代化，像苹果一样有高级感，像github的官网一样有现代化，带上一些合适的动画效果来彰显质感

# React + TypeScript + Vite

This template provides a minimal setup to get React working in Vite with HMR and some ESLint rules.

Currently, two official plugins are available:

- [@vitejs/plugin-react](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react) uses [Babel](https://babeljs.io/) (or [oxc](https://oxc.rs) when used in [rolldown-vite](https://vite.dev/guide/rolldown)) for Fast Refresh
- [@vitejs/plugin-react-swc](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react-swc) uses [SWC](https://swc.rs/) for Fast Refresh

## React Compiler

The React Compiler is not enabled on this template because of its impact on dev & build performances. To add it, see [this documentation](https://react.dev/learn/react-compiler/installation).

## Expanding the ESLint configuration

If you are developing a production application, we recommend updating the configuration to enable type-aware lint rules:

\`\`\`js
export default defineConfig([
  globalIgnores(['dist']),
  {
    files: ['**/*.{ts,tsx}'],
    extends: [
      // Other configs...

      // Remove tseslint.configs.recommended and replace with this
      tseslint.configs.recommendedTypeChecked,
      // Alternatively, use this for stricter rules
      tseslint.configs.strictTypeChecked,
      // Optionally, add this for stylistic rules
      tseslint.configs.stylisticTypeChecked,

      // Other configs...
    ],
    languageOptions: {
      parserOptions: {
        project: ['./tsconfig.node.json', './tsconfig.app.json'],
        tsconfigRootDir: import.meta.dirname,
      },
      // other options...
    },
  },
])
\`\`\`

You can also install [eslint-plugin-react-x](https://github.com/Rel1cx/eslint-react/tree/main/packages/plugins/eslint-plugin-react-x) and [eslint-plugin-react-dom](https://github.com/Rel1cx/eslint-react/tree/main/packages/plugins/eslint-plugin-react-dom) for React-specific lint rules:

\`\`\`js
// eslint.config.js
import reactX from 'eslint-plugin-react-x'
import reactDom from 'eslint-plugin-react-dom'

export default defineConfig([
  globalIgnores(['dist']),
  {
    files: ['**/*.{ts,tsx}'],
    extends: [
      // Other configs...
      // Enable lint rules for React
      reactX.configs['recommended-typescript'],
      // Enable lint rules for React DOM
      reactDom.configs.recommended,
    ],
    languageOptions: {
      parserOptions: {
        project: ['./tsconfig.node.json', './tsconfig.app.json'],
        tsconfigRootDir: import.meta.dirname,
      },
      // other options...
    },
  },
])
\`\`\`
