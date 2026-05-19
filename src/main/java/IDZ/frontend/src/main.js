import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

// підключаємо Bootstrap і його іконки
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-icons/font/bootstrap-icons.css'
import 'bootstrap/dist/js/bootstrap.bundle.min.js'

// власні стилі
import './assets/main.css'

// створюємо застосунок та реєструємо плагіни
const app = createApp(App)
app.use(router)
app.use(store)
app.mount('#app')
