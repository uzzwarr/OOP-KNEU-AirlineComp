import axios from 'axios'
import router from '../router'
import store from '../store'

// створюємо preconfigured axios з базовим url для Spring Boot бекенду
const api = axios.create({
  baseURL: 'http://localhost:8080/api',
  timeout: 10000,
  headers: { 'Content-Type': 'application/json' }
})

// перехоплюємо ВСІ запити і додаємо jwt-токен якщо він є у Vuex
api.interceptors.request.use(config => {
  const token = store.state.auth.token
  if (token) {
    config.headers.Authorization = `Bearer ${token}`
  }
  return config
}, error => Promise.reject(error))

// перехоплюємо відповіді: якщо 401 - вилогінюємо користувача
api.interceptors.response.use(
  response => response,
  error => {
    if (error.response && error.response.status === 401) {
      store.dispatch('auth/logout')
      // якщо ми не на сторінці логіну - перенаправляємо
      if (router.currentRoute.value.path !== '/login') {
        router.push('/login')
      }
    }
    return Promise.reject(error)
  }
)

export default api
