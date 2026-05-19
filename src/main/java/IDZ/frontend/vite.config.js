import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// налаштування Vite - запуск на 5173, без проксі (CORS вирішено на бекенді)
export default defineConfig({
  plugins: [vue()],
  server: {
    port: 5173,
    open: true
  }
})
