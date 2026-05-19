<template>
  <div>
    <!-- hero-секція з вітальним заголовком -->
    <div class="card mb-4 text-bg-primary border-0">
      <div class="card-body py-5 text-center">
        <h1 class="display-4 fw-bold">
          <i class="bi bi-airplane-engines-fill"></i>
          AviaCompany КНЕУ
        </h1>
        <p class="lead">Інформаційна система обліку рейсів та екіпажу авіакомпанії</p>
        <p>Серверна частина: <strong>Spring Boot 3.2</strong> · Клієнтська частина: <strong>Vue 3 + Vuex + Vue Router</strong></p>
      </div>
    </div>

    <!-- картки модулів -->
    <div class="row g-4">
      <div class="col-md-4">
        <div class="card h-100">
          <div class="card-body">
            <h5 class="card-title">
              <i class="bi bi-airplane text-primary"></i> Рейси
            </h5>
            <p class="card-text">Перегляд, додавання, редагування та видалення рейсів авіакомпанії з прив'язкою членів екіпажу.</p>
            <router-link to="/flights" class="btn btn-primary">
              Перейти <i class="bi bi-arrow-right"></i>
            </router-link>
          </div>
        </div>
      </div>

      <div class="col-md-4">
        <div class="card h-100">
          <div class="card-body">
            <h5 class="card-title">
              <i class="bi bi-people-fill text-primary"></i> Екіпаж
            </h5>
            <p class="card-text">Управління складом екіпажу: пілоти, бортпровідники, ліцензії та досвід роботи.</p>
            <router-link to="/crew" class="btn btn-primary">
              Перейти <i class="bi bi-arrow-right"></i>
            </router-link>
          </div>
        </div>
      </div>

      <div class="col-md-4">
        <div class="card h-100">
          <div class="card-body">
            <h5 class="card-title">
              <i class="bi bi-info-circle text-primary"></i> Про систему
            </h5>
            <p class="card-text">Інформація про застосунок, використані технології та архітектуру рішення.</p>
            <router-link to="/about" class="btn btn-primary">
              Перейти <i class="bi bi-arrow-right"></i>
            </router-link>
          </div>
        </div>
      </div>
    </div>

    <!-- блок статистики -->
    <div class="row mt-5">
      <div class="col-md-6">
        <div class="card">
          <div class="card-header">Статистика системи</div>
          <div class="card-body">
            <div v-if="loading" class="text-center">
              <div class="spinner-border text-primary"></div>
            </div>
            <ul class="list-group list-group-flush" v-else>
              <li class="list-group-item d-flex justify-content-between">
                <span><i class="bi bi-airplane"></i> Усього рейсів</span>
                <span class="badge bg-primary rounded-pill">{{ flightsCount }}</span>
              </li>
              <li class="list-group-item d-flex justify-content-between">
                <span><i class="bi bi-people"></i> Членів екіпажу</span>
                <span class="badge bg-primary rounded-pill">{{ crewCount }}</span>
              </li>
            </ul>
          </div>
        </div>
      </div>

      <div class="col-md-6">
        <div class="card">
          <div class="card-header">Поточний статус</div>
          <div class="card-body">
            <p v-if="isAuthenticated">
              <i class="bi bi-check-circle-fill text-success"></i>
              Ви авторизовані як <strong>{{ currentUser }}</strong>. Доступні всі CRUD-операції.
            </p>
            <p v-else>
              <i class="bi bi-exclamation-triangle-fill text-warning"></i>
              Для створення, редагування та видалення даних потрібна
              <router-link to="/login">авторизація</router-link>.
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import flightService from '../services/flightService'
import crewService from '../services/crewService'

export default {
  name: 'HomeView',
  data() {
    return {
      flightsCount: 0,
      crewCount: 0,
      loading: true
    }
  },
  computed: {
    ...mapGetters('auth', ['isAuthenticated', 'currentUser'])
  },
  async mounted() {
    try {
      const [flights, crew] = await Promise.all([
        flightService.getAll(),
        crewService.getAll()
      ])
      this.flightsCount = flights.data.length
      this.crewCount = crew.data.length
    } catch (err) {
      console.error('Помилка завантаження статистики', err)
    } finally {
      this.loading = false
    }
  }
}
</script>
