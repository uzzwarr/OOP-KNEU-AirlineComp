<template>
  <div>
    <div class="d-flex justify-content-between align-items-center mb-3">
      <h2 class="page-title mb-0">
        <i class="bi bi-airplane"></i> Список рейсів
      </h2>
      <router-link to="/flights/new" class="btn btn-accent" v-if="isAuthenticated">
        <i class="bi bi-plus-circle"></i> Новий рейс
      </router-link>
    </div>

    <!-- пошук та фільтр -->
    <div class="card mb-3">
      <div class="card-body">
        <div class="row g-2">
          <div class="col-md-8">
            <input
              v-model="searchQuery"
              type="text"
              class="form-control"
              placeholder="Пошук за номером, містом..."
            />
          </div>
          <div class="col-md-4 text-end">
            <span class="badge bg-secondary fs-6">
              Знайдено: {{ filteredFlights.length }}
            </span>
          </div>
        </div>
      </div>
    </div>

    <!-- завантаження -->
    <div v-if="loading" class="text-center py-5">
      <div class="spinner-border text-primary"></div>
      <p class="mt-2">Завантаження рейсів...</p>
    </div>

    <!-- порожній стан -->
    <div v-else-if="filteredFlights.length === 0" class="alert alert-info">
      <i class="bi bi-info-circle"></i>
      {{ searchQuery ? 'Нічого не знайдено за вашим запитом.' : 'Рейсів немає. Додайте перший!' }}
    </div>

    <!-- таблиця рейсів -->
    <div v-else class="card">
      <div class="table-responsive">
        <table class="table table-hover mb-0">
          <thead>
            <tr>
              <th>ID</th>
              <th>Номер</th>
              <th>Маршрут</th>
              <th>Відправлення</th>
              <th>Прибуття</th>
              <th>Екіпаж</th>
              <th class="text-end">Дії</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="flight in filteredFlights" :key="flight.id">
              <td>{{ flight.id }}</td>
              <td><strong>{{ flight.flightNumber }}</strong></td>
              <td>
                {{ flight.departureCity }}
                <i class="bi bi-arrow-right text-muted mx-1"></i>
                {{ flight.arrivalCity }}
              </td>
              <td>{{ formatDateTime(flight.departureTime) }}</td>
              <td>{{ formatDateTime(flight.arrivalTime) }}</td>
              <td>
                <span v-if="flight.crew && flight.crew.length">
                  <span v-for="member in flight.crew" :key="member.id" class="crew-badge">
                    {{ member.fullName.split(' ')[0] }} ({{ roleShort(member.role) }})
                  </span>
                </span>
                <span v-else class="text-muted small">—</span>
              </td>
              <td class="text-end text-nowrap">
                <router-link
                  :to="`/flights/${flight.id}/edit`"
                  class="btn btn-sm btn-outline-primary me-1"
                  v-if="isAuthenticated"
                  title="Редагувати"
                >
                  <i class="bi bi-pencil"></i>
                </router-link>
                <button
                  class="btn btn-sm btn-outline-danger"
                  @click="confirmDelete(flight)"
                  v-if="isAuthenticated"
                  title="Видалити"
                >
                  <i class="bi bi-trash"></i>
                </button>
                <span v-if="!isAuthenticated" class="text-muted small">
                  <router-link to="/login">Увійти</router-link> для редагування
                </span>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <!-- модалка підтвердження видалення -->
    <div v-if="flightToDelete" class="modal d-block" tabindex="-1" style="background: rgba(0,0,0,0.5)">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Підтвердження видалення</h5>
            <button type="button" class="btn-close" @click="flightToDelete = null"></button>
          </div>
          <div class="modal-body">
            <p>
              Видалити рейс <strong>{{ flightToDelete.flightNumber }}</strong>
              ({{ flightToDelete.departureCity }} → {{ flightToDelete.arrivalCity }})?
              Цю дію не можна скасувати.
            </p>
          </div>
          <div class="modal-footer">
            <button class="btn btn-secondary" @click="flightToDelete = null">Скасувати</button>
            <button class="btn btn-danger" @click="deleteFlight" :disabled="deleting">
              <span v-if="deleting" class="spinner-border spinner-small me-2"></span>
              Видалити
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import flightService from '../services/flightService'

export default {
  name: 'FlightsView',
  data() {
    return {
      flights: [],
      loading: true,
      searchQuery: '',
      flightToDelete: null,
      deleting: false
    }
  },
  computed: {
    ...mapGetters('auth', ['isAuthenticated']),
    filteredFlights() {
      if (!this.searchQuery) return this.flights
      const q = this.searchQuery.toLowerCase()
      return this.flights.filter(f =>
        f.flightNumber.toLowerCase().includes(q) ||
        f.departureCity.toLowerCase().includes(q) ||
        f.arrivalCity.toLowerCase().includes(q)
      )
    }
  },
  async mounted() {
    await this.loadFlights()
  },
  methods: {
    async loadFlights() {
      this.loading = true
      try {
        const response = await flightService.getAll()
        this.flights = response.data
      } catch (err) {
        this.$store.dispatch('ui/notify', { type: 'danger', message: 'Помилка завантаження рейсів' })
      } finally {
        this.loading = false
      }
    },
    formatDateTime(iso) {
      if (!iso) return '—'
      const d = new Date(iso)
      return d.toLocaleString('uk-UA', { dateStyle: 'short', timeStyle: 'short' })
    },
    roleShort(role) {
      const map = {
        Pilot: 'Пілот',
        CoPilot: 'Ко-пілот',
        Steward: 'Стюард',
        FlightAttendant: 'Бортпров.'
      }
      return map[role] || role
    },
    confirmDelete(flight) {
      this.flightToDelete = flight
    },
    async deleteFlight() {
      if (!this.flightToDelete) return
      this.deleting = true
      try {
        await flightService.delete(this.flightToDelete.id)
        this.$store.dispatch('ui/notify', { type: 'success', message: 'Рейс видалено' })
        this.flightToDelete = null
        await this.loadFlights()
      } catch (err) {
        this.$store.dispatch('ui/notify', { type: 'danger', message: 'Не вдалось видалити рейс' })
      } finally {
        this.deleting = false
      }
    }
  }
}
</script>
