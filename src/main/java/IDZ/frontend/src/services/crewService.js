import api from './api'

// сервіс для роботи з REST API членів екіпажу
export default {
  getAll() {
    return api.get('/crew')
  },
  getById(id) {
    return api.get(`/crew/${id}`)
  },
  create(crewMember) {
    return api.post('/crew', crewMember)
  },
  update(id, crewMember) {
    return api.put(`/crew/${id}`, crewMember)
  },
  delete(id) {
    return api.delete(`/crew/${id}`)
  }
}
