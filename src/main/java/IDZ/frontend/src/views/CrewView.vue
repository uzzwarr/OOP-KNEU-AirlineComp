<template>
  <div>
    <div class="d-flex justify-content-between align-items-center mb-3">
      <h2 class="page-title mb-0">
        <i class="bi bi-people-fill"></i> Члени екіпажу
      </h2>
      <router-link to="/crew/new" class="btn btn-accent" v-if="isAuthenticated">
        <i class="bi bi-plus-circle"></i> Додати члена екіпажу
      </router-link>
    </div>

    <!-- фільтр за роллю -->
    <div class="card mb-3">
      <div class="card-body">
        <div class="row g-2">
          <div class="col-md-6">
            <input
              v-model="searchQuery"
              type="text"
              class="form-control"
              placeholder="Пошук за ПІБ або ліцензією..."
            />
          </div>
          <div class="col-md-4">
            <select v-model="roleFilter" class="form-select">
              <option value="">Усі ролі</option>
              <option value="Pilot">Пілот</option>
              <option value="CoPilot">Ко-пілот</option>
              <option value="Steward">Стюард</option>
              <option value="FlightAttendant">Бортпровідник</option>
            </select>
          </div>
          <div class="col-md-2 text-end">
            <span class="badge bg-secondary fs-6">{{ filteredCrew.length }}</span>
          </div>
        </div>
      </div>
    </div>

    <div v-if="loading" class="text-center py-5">
      <div class="spinner-border text-primary"></div>
    </div>

    <div v-else-if="filteredCrew.length === 0" class="alert alert-info">
      <i class="bi bi-info-circle"></i>
      {{ searchQuery || roleFilter ? 'Нічого не знайдено.' : 'Поки немає членів екіпажу.' }}
    </div>

    <div v-else class="row g-3">
      <div class="col-md-6 col-lg-4" v-for="member in filteredCrew" :key="member.id">
        <div class="card h-100">
          <div class="card-body">
            <div class="d-flex justify-content-between align-items-start mb-2">
              <h5 class="card-title mb-0">
                <i class="bi bi-person-circle text-primary"></i>
                {{ member.fullName }}
              </h5>
              <span class="badge bg-primary">{{ roleLabel(member.role) }}</span>
            </div>
            <p class="card-text mb-2">
              <small class="text-muted">
                <i class="bi bi-hash"></i> ID: {{ member.id }}
              </small>
            </p>
            <ul class="list-unstyled small mb-3">
              <li v-if="member.experienceYears != null">
                <i class="bi bi-clock-history"></i>
                Досвід: <strong>{{ member.experienceYears }} р.</strong>
              </li>
              <li v-if="member.licenseNumber">
                <i class="bi bi-credit-card-2-front"></i>
                Ліцензія: <strong>{{ member.licenseNumber }}</strong>
              </li>
            </ul>
            <div class="d-flex gap-1" v-if="isAuthenticated">
              <router-link
                :to="`/crew/${member.id}/edit`"
                class="btn btn-sm btn-outline-primary flex-grow-1"
              >
                <i class="bi bi-pencil"></i> Редагувати
              </router-link>
              <button class="btn btn-sm btn-outline-danger" @click="confirmDelete(member)">
                <i class="bi bi-trash"></i>
              </button>
            </div>
            <div v-else class="text-muted small">
              <router-link to="/login">Увійти</router-link> для редагування
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- модалка видалення -->
    <div v-if="memberToDelete" class="modal d-block" tabindex="-1" style="background: rgba(0,0,0,0.5)">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Підтвердження видалення</h5>
            <button type="button" class="btn-close" @click="memberToDelete = null"></button>
          </div>
          <div class="modal-body">
            <p>Видалити члена екіпажу <strong>{{ memberToDelete.fullName }}</strong>?</p>
          </div>
          <div class="modal-footer">
            <button class="btn btn-secondary" @click="memberToDelete = null">Скасувати</button>
            <button class="btn btn-danger" @click="deleteMember" :disabled="deleting">
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
import crewService from '../services/crewService'

export default {
  name: 'CrewView',
  data() {
    return {
      crew: [],
      loading: true,
      searchQuery: '',
      roleFilter: '',
      memberToDelete: null,
      deleting: false
    }
  },
  computed: {
    ...mapGetters('auth', ['isAuthenticated']),
    filteredCrew() {
      let list = this.crew
      if (this.roleFilter) {
        list = list.filter(m => m.role === this.roleFilter)
      }
      if (this.searchQuery) {
        const q = this.searchQuery.toLowerCase()
        list = list.filter(m =>
          m.fullName.toLowerCase().includes(q) ||
          (m.licenseNumber && m.licenseNumber.toLowerCase().includes(q))
        )
      }
      return list
    }
  },
  async mounted() {
    await this.loadCrew()
  },
  methods: {
    async loadCrew() {
      this.loading = true
      try {
        const resp = await crewService.getAll()
        this.crew = resp.data
      } catch (err) {
        this.$store.dispatch('ui/notify', { type: 'danger', message: 'Помилка завантаження екіпажу' })
      } finally {
        this.loading = false
      }
    },
    roleLabel(role) {
      const map = {
        Pilot: 'Пілот',
        CoPilot: 'Ко-пілот',
        Steward: 'Стюард',
        FlightAttendant: 'Бортпровідник'
      }
      return map[role] || role
    },
    confirmDelete(member) {
      this.memberToDelete = member
    },
    async deleteMember() {
      if (!this.memberToDelete) return
      this.deleting = true
      try {
        await crewService.delete(this.memberToDelete.id)
        this.$store.dispatch('ui/notify', { type: 'success', message: 'Члена екіпажу видалено' })
        this.memberToDelete = null
        await this.loadCrew()
      } catch (err) {
        this.$store.dispatch('ui/notify', { type: 'danger', message: 'Не вдалось видалити' })
      } finally {
        this.deleting = false
      }
    }
  }
}
</script>
