import { createApp } from 'vue'
import App from './App.vue'
import './style.css'
import {
  createRouter,
  createWebHistory,
  setupDataFetchingGuard
} from 'vue-router/auto'

const router = createRouter({
  history: createWebHistory()
})

setupDataFetchingGuard(router)

const app = createApp(App)

app.use(router)

app.mount('#app')
