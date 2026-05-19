<template>
  <div class="row justify-content-center">
    <div class="col-md-6 col-lg-5">
      <div class="card mt-5">
        <div class="card-header text-center">
          <i class="bi bi-shield-lock-fill"></i> Авторизація адміністратора
        </div>
        <div class="card-body p-4">
          <div v-if="error" class="alert alert-danger" role="alert">
            <i class="bi bi-exclamation-circle"></i> {{ error }}
          </div>

          <div class="mb-3">
            <label class="form-label">Логін</label>
            <input
              v-model="form.username"
              type="text"
              class="form-control"
              placeholder="admin"
              @keyup.enter="handleLogin"
              :disabled="loading"
            />
          </div>

          <div class="mb-3">
            <label class="form-label">Пароль</label>
            <input
              v-model="form.password"
              type="password"
              class="form-control"
              placeholder="••••"
              @keyup.enter="handleLogin"
              :disabled="loading"
            />
          </div>

          <button
            class="btn btn-primary w-100"
            @click="handleLogin"
            :disabled="loading || !form.username || !form.password"
          >
            <span v-if="loading" class="spinner-border spinner-small me-2"></span>
            <i v-else class="bi bi-box-arrow-in-right"></i>
            Увійти
          </button>

          <div class="alert alert-info mt-3 mb-0 small">
            <i class="bi bi-info-circle"></i>
            Тестові облікові дані: <strong>admin</strong> / <strong>1234</strong>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'LoginView',
  data() {
    return {
      form: { username: '', password: '' },
      loading: false,
      error: null
    }
  },
  methods: {
    async handleLogin() {
      if (!this.form.username || !this.form.password) return
      this.loading = true
      this.error = null
      try {
        await this.$store.dispatch('auth/login', this.form)
        this.$store.dispatch('ui/notify', { type: 'success', message: 'Вхід виконано успішно' })
        // якщо була redirect-ціль - йдемо туди, інакше на /flights
        const redirect = this.$route.query.redirect || '/flights'
        this.$router.push(redirect)
      } catch (err) {
        this.error = err.response?.data?.message || err.response?.data?.error || 'Невірний логін або пароль'
      } finally {
        this.loading = false
      }
    }
  }
}
</script>
