<template>
  <div class="row justify-content-center">
    <div class="col-lg-6">
      <h2 class="page-title">
        <i class="bi" :class="isEdit ? 'bi-pencil-square' : 'bi-plus-square'"></i>
        {{ isEdit ? 'Редагування члена екіпажу' : 'Новий член екіпажу' }}
      </h2>

      <div v-if="loading" class="text-center py-5">
        <div class="spinner-border text-primary"></div>
      </div>

      <div v-else class="card">
        <div class="card-body p-4">
          <div v-if="error" class="alert alert-danger">
            <i class="bi bi-exclamation-circle"></i> {{ error }}
          </div>

          <div class="mb-3">
            <label class="form-label">ПІБ *</label>
            <input v-model="form.fullName" type="text" class="form-control" placeholder="Іваненко Іван Петрович" />
          </div>

          <div class="mb-3">
            <label class="form-label">Роль *</label>
            <select v-model="form.role" class="form-select">
              <option value="">— Оберіть роль —</option>
              <option value="Pilot">Пілот</option>
              <option value="CoPilot">Ко-пілот</option>
              <option value="Steward">Стюард</option>
              <option value="FlightAttendant">Бортпровідник</option>
            </select>
          </div>

          <div class="mb-3">
            <label class="form-label">Досвід (років)</label>
            <input v-model.number="form.experienceYears" type="number" min="0" max="60" class="form-control" />
          </div>

          <div class="mb-3">
            <label class="form-label">Номер ліцензії</label>
            <input v-model="form.licenseNumber" type="text" class="form-control" placeholder="UA-001-PL" />
          </div>

          <hr />

          <div class="d-flex gap-2">
            <button class="btn btn-primary" @click="handleSave" :disabled="saving">
              <span v-if="saving" class="spinner-border spinner-small me-2"></span>
              <i v-else class="bi bi-check-circle"></i>
              {{ isEdit ? 'Зберегти зміни' : 'Створити' }}
            </button>
            <router-link to="/crew" class="btn btn-secondary">
              <i class="bi bi-x-circle"></i> Скасувати
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import crewService from '../services/crewService'

export default {
  name: 'CrewFormView',
  props: {
    id: { type: [String, Number], default: null }
  },
  data() {
    return {
      form: {
        fullName: '',
        role: '',
        experienceYears: null,
        licenseNumber: ''
      },
      loading: false,
      saving: false,
      error: null
    }
  },
  computed: {
    isEdit() {
      return !!this.id
    }
  },
  async mounted() {
    if (this.isEdit) {
      this.loading = true
      try {
        const resp = await crewService.getById(this.id)
        this.form = {
          fullName: resp.data.fullName,
          role: resp.data.role,
          experienceYears: resp.data.experienceYears,
          licenseNumber: resp.data.licenseNumber
        }
      } catch (err) {
        this.error = 'Не вдалось завантажити дані'
      } finally {
        this.loading = false
      }
    }
  },
  methods: {
    validate() {
      if (!this.form.fullName.trim()) {
        this.error = 'Вкажіть ПІБ'
        return false
      }
      if (!this.form.role) {
        this.error = 'Оберіть роль'
        return false
      }
      return true
    },
    async handleSave() {
      this.error = null
      if (!this.validate()) return

      this.saving = true
      try {
        if (this.isEdit) {
          await crewService.update(this.id, this.form)
          this.$store.dispatch('ui/notify', { type: 'success', message: 'Зміни збережено' })
        } else {
          await crewService.create(this.form)
          this.$store.dispatch('ui/notify', { type: 'success', message: 'Члена екіпажу створено' })
        }
        this.$router.push('/crew')
      } catch (err) {
        this.error = err.response?.data?.message || 'Помилка збереження'
      } finally {
        this.saving = false
      }
    }
  }
}
</script>
