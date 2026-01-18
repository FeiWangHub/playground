# Skills Playground

这是一个存放各种 Skill 项目和实验性功能的目录。

## 📂 项目列表

### 1. [VHS App Copilot](./vhs-app-copilot)

一个基于 AI 的 Chatbot 风格 Web 应用，演示了现代化的 UI/UX 设计（GitHub/Apple 风格）。

**功能亮点：**
- **Sidebar**: App 开关控制，带平滑动画。
- **Chat Interface**: 模拟 AI 对话，支持代码块高亮。
- **Preview Panel**: 右侧实时预览 App 运行结果（Mock）。
- **Design**: 玻璃拟态 (Glassmorphism)，暗色模式。

#### 🚀 快速启动

进入项目目录：

```bash
cd vhs-app-copilot
```

安装依赖：

```bash
npm install
```

启动开发服务器：

```bash
npm run dev
```

打开浏览器访问：http://localhost:5173

#### 🛠️ 技术栈

- React 19
- TypeScript
- Vite
- Tailwind CSS v4
- Framer Motion
- Lucide React

---

## 📦 如何添加/安装新 Skill

如果你想在这个目录下添加新的 Skill 项目：

1. **创建项目目录**：
   ```bash
   mkdir my-new-skill
   cd my-new-skill
   ```

2. **初始化项目** (以 Vite React 为例)：
   ```bash
   npm create vite@latest . -- --template react-ts
   ```

3. **安装通用依赖** (推荐使用本项目约定的 UI 库)：
   ```bash
   npm install lucide-react framer-motion clsx tailwind-merge
   ```

4. **配置 Tailwind CSS**：
   参考 `vhs-app-copilot` 中的 `tailwind.config.js` 和 `src/index.css` 以保持风格一致。
