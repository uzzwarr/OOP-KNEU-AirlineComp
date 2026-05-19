import api from './api'

// сервіс для роботи з REST API рейсів
export default {
  getAll() {
    return api.get('/flights')
  },
  getById(id) {
    return api.get(`/flights/${id}`)
  },
  create(flight) {
    return api.post('/flights', flight)
  },
  update(id, flight) {
    return api.put(`/flights/${id}`, flight)
  },
  delete(id) {
    return api.delete(`/flights/${id}`)
  }
}
