<template>
  <div class="row justify-content-center">
    <div class="col-lg-8">
      <h2 class="page-title">
        <i class="bi" :class="isEdit ? 'bi-pencil-square' : 'bi-plus-square'"></i>
        {{ isEdit ? 'Редагування рейсу' : 'Новий рейс' }}
      </h2>

      <div v-if="loading" class="text-center py-5">
        <div class="spinner-border text-primary"></div>
      </div>

      <div v-else class="card">
        <div class="card-body p-4">
          <div v-if="error" class="alert alert-danger">
            <i class="bi bi-exclamation-circle"></i> {{ error }}
          </div>

          <div class="row g-3">
            <div class="col-md-6">
              <label class="form-label">Номер рейсу *</label>
              <input v-model="form.flightNumber" type="text" class="form-control" placeholder="KNEU-204" />
            </div>

            <div class="col-md-3">
              <label class="form-label">Місто відправлення *</label>
              <input v-model="form.departureCity" type="text" class="form-control" placeholder="Київ" />
            </div>

            <div class="col-md-3">
              <label class="form-label">Місто прибуття *</label>
              <input v-model="form.arrivalCity" type="text" class="form-control" placeholder="Львів" />
            </div>

            <div class="col-md-6">
              <label class="form-label">Час відправлення *</label>
              <input v-model="form.departureTime" type="datetime-local" class="form-control" />
            </div>

            <div class="col-md-6">
              <label class="form-label">Час прибуття *</label>
              <input v-model="form.arrivalTime" type="datetime-local" class="form-control" />
            </div>

            <div class="col-12">
              <label class="form-label">Екіпаж</label>
              <div class="border rounded p-3" style="max-height: 250px; overflow-y: auto;">
                <div v-if="!availableCrew.length" class="text-muted small">
                  Спочатку додайте членів екіпажу на сторінці "Екіпаж".
                </div>
                <div v-for="member in availableCrew" :key="member.id" class="form-check">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    :id="`crew-${member.id}`"
                    :value="member.id"
                    v-model="form.crewIds"
                  />
                  <label class="form-check-label" :for="`crew-${member.id}`">
                    <strong>{{ member.fullName }}</strong>
                    <span class="badge bg-secondary ms-2">{{ member.role }}</span>
                    <span v-if="member.experienceYears" class="text-muted small ms-2">
                      ({{ member.experienceYears }} р. досвіду)
                    </span>
                  </label>
                </div>
              </div>
              <small class="text-muted">Обрано: {{ form.crewIds.length }} осіб</small>
            </div>
          </div>

          <hr class="my-4" />

          <div class="d-flex gap-2">
            <button class="btn btn-primary" @click="handleSave" :disabled="saving">
              <span v-if="saving" class="spinner-border spinner-small me-2"></span>
              <i v-else class="bi bi-check-circle"></i>
              {{ isEdit ? 'Зберегти зміни' : 'Створити рейс' }}
            </button>
            <router-link to="/flights" class="btn btn-secondary">
              <i class="bi bi-x-circle"></i> Скасувати
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import flightService from '../services/flightService'
import crewService from '../services/crewService'

export default {
  name: 'FlightFormView',
  props: {
    id: { type: [String, Number], default: null }
  },
  data() {
    return {
      form: {
        flightNumber: '',
        departureCity: '',
        arrivalCity: '',
        departureTime: '',
        arrivalTime: '',
        crewIds: []
      },
      availableCrew: [],
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
    this.loading = true
    try {
      // завантажуємо список екіпажу для чекбоксів
      const crewResp = await crewService.getAll()
      this.availableCrew = crewResp.data

      // якщо це редагування - підтягуємо існуючий рейс
      if (this.isEdit) {
        const flightResp = await flightService.getById(this.id)
        const f = flightResp.data
        this.form.flightNumber = f.flightNumber
        this.form.departureCity = f.departureCity
        this.form.arrivalCity = f.arrivalCity
        this.form.departureTime = this.toInputFormat(f.departureTime)
        this.form.arrivalTime = this.toInputFormat(f.arrivalTime)
        this.form.crewIds = f.crewIds || []
      }
    } catch (err) {
      this.error = 'Не вдалось завантажити дані'
    } finally {
      this.loading = false
    }
  },
  methods: {
    // конвертація ISO -> формат datetime-local
    toInputFormat(iso) {
      if (!iso) return ''
      // обрізаємо до 16 символів - 'yyyy-MM-ddTHH:mm'
      return iso.substring(0, 16)
    },
    validate() {
      if (!this.form.flightNumber || !this.form.departureCity || !this.form.arrivalCity) {
        this.error = 'Заповніть усі обов\'язкові поля'
        return false
      }
      if (!this.form.departureTime || !this.form.arrivalTime) {
        this.error = 'Вкажіть час відправлення та прибуття'
        return false
      }
      if (new Date(this.form.arrivalTime) <= new Date(this.form.departureTime)) {
        this.error = 'Час прибуття має бути пізніше часу відправлення'
        return false
      }
      return true
    },
    async handleSave() {
      this.error = null
      if (!this.validate()) return

      this.saving = true
      try {
        const payload = {
          flightNumber: this.form.flightNumber,
          departureCity: this.form.departureCity,
          arrivalCity: this.form.arrivalCity,
          // додаємо :00 щоб був повний LocalDateTime
          departureTime: this.form.departureTime.length === 16
            ? this.form.departureTime + ':00'
            : this.form.departureTime,
          arrivalTime: this.form.arrivalTime.length === 16
            ? this.form.arrivalTime + ':00'
            : this.form.arrivalTime,
          crewIds: this.form.crewIds
        }

        if (this.isEdit) {
          await flightService.update(this.id, payload)
          this.$store.dispatch('ui/notify', { type: 'success', message: 'Рейс оновлено' })
        } else {
          await flightService.create(payload)
          this.$store.dispatch('ui/notify', { type: 'success', message: 'Рейс створено' })
        }
        this.$router.push('/flights')
      } catch (err) {
        this.error = err.response?.data?.message
                   || err.response?.data?.error
                   || 'Помилка при збереженні. Перевірте дані.'
      } finally {
        this.saving = false
      }
    }
  }
}
</script>
