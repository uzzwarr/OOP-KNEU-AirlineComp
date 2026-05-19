import api from './api'

// сервіс авторизації
export default {
  login(credentials) {
    return api.post('/auth/login', credentials)
  },
  me() {
    return api.get('/auth/me')
  }
}
