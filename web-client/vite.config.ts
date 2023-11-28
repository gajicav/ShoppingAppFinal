import { defineConfig } from 'vite'
import Vue from '@vitejs/plugin-vue'
import VueRouter from 'unplugin-vue-router/vite'
import AutoImport from 'unplugin-auto-import/vite'
import { VueRouterAutoImports } from 'unplugin-vue-router'

// https://vitejs.dev/config/
export default defineConfig({
  clearScreen: false,
  build: {
    sourcemap: true,
    outDir: '../src/main/resources/static'
  },
  plugins: [
    VueRouter({
      dataFetching: true,
    }),
    Vue(),
    AutoImport({
      imports: [VueRouterAutoImports]
    })
  ],
  server: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080'
      }
    }
  }
})
