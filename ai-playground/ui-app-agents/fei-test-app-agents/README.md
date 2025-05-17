# React + Vite + Tailwind CSS Quick Start Guide

This project is based on Vite + React and integrates Tailwind CSS for rapid modern frontend development.

---

## 1. Clone the Project

```bash
# If you haven't cloned the code yet
# git clone <your-repo-url>
cd ui-app-agents/my-react-app
```

## 2. Install Dependencies

```bash
npm install
```

## 3. Install and Configure Tailwind CSS

```bash
npm install -D tailwindcss postcss autoprefixer
npx tailwindcss init -p
```

- Edit `tailwind.config.js` and make sure the content field includes:
  ```js
  export default {
    content: [
      "./index.html",
      "./src/**/*.{js,ts,jsx,tsx}",
    ],
    theme: {
      extend: {},
    },
    plugins: [],
  }
  ```
- In `src/index.css` (or `src/main.css`), add:
  ```css
  @tailwind base;
  @tailwind components;
  @tailwind utilities;
  ```
- Make sure your entry file (e.g., `main.jsx`) imports `index.css`:
  ```js
  import './index.css';
  ```

## 4. Start the Development Server

```bash
npm run dev
```

Open your browser and visit the local address shown in the terminal (e.g., http://localhost:5173/) to view the app.

---

## 5. Additional Notes
- To install extra dependencies (such as `lucide-react`, `framer-motion`), run:
  ```bash
  npm install lucide-react framer-motion
  ```
- If you encounter node/npm version issues, it is recommended to use [nvm](https://github.com/nvm-sh/nvm) to manage Node.js versions.

---

## References
- [Vite Documentation](https://vitejs.dev/)
- [React Documentation](https://react.dev/)
- [Tailwind CSS Documentation](https://tailwindcss.com/)
