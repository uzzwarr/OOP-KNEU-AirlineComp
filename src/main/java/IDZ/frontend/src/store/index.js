import { createStore } from 'vuex'
import authService from '../services/authService'

// модуль авторизації - зберігає jwt токен у localStorage
const auth = {
  namespaced: true,
  state: () => ({
    token: localStorage.getItem('jwt_token') || null,
    username: localStorage.getItem('username') || null
  }),
  getters: {
    isAuthenticated: state => !!state.token,
    currentUser: state => state.username
  },
  mutations: {
    SET_AUTH(state, { token, username }) {
      state.token = token
      state.username = username
      localStorage.setItem('jwt_token', token)
      localStorage.setItem('username', username)
    },
    CLEAR_AUTH(state) {
      state.token = null
      state.username = null
      localStorage.removeItem('jwt_token')
      localStorage.removeItem('username')
    }
  },
  actions: {
    async login({ commit }, credentials) {
      const response = await authService.login(credentials)
      commit('SET_AUTH', { token: response.data.token, username: response.data.username })
      return response.data
    },
    logout({ commit }) {
      commit('CLEAR_AUTH')
    }
  }
}

// загальний модуль для повідомлень (toast'и)
const ui = {
  namespaced: true,
  state: () => ({
    notification: null
  }),
  mutations: {
    SET_NOTIFICATION(state, payload) {
      state.notification = payload
    },
    CLEAR_NOTIFICATION(state) {
      state.notification = null
    }
  },
  actions: {
    notify({ commit }, { type, message }) {
      commit('SET_NOTIFICATION', { type, message })
      // авто-зникнення через 3 сек
      setTimeout(() => commit('CLEAR_NOTIFICATION'), 3000)
    }
  }
}

const store = createStore({
  modules: { auth, ui }
})

export default store
