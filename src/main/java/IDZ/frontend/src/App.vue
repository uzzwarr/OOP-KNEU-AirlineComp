<template>
  <div id="app-wrapper">
    <!-- верхнє меню навігації -->
    <nav class="navbar navbar-expand-lg brand-navbar shadow-sm">
      <div class="container">
        <router-link class="navbar-brand" to="/">
          <i class="bi bi-airplane-engines-fill me-2"></i>
          AviaCompany КНЕУ
        </router-link>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mainNav">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="mainNav">
          <ul class="navbar-nav me-auto">
            <li class="nav-item">
              <router-link class="nav-link" to="/">
                <i class="bi bi-house-door"></i> Головна
              </router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link" to="/flights">
                <i class="bi bi-airplane"></i> Рейси
              </router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link" to="/crew">
                <i class="bi bi-people-fill"></i> Екіпаж
              </router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link" to="/about">
                <i class="bi bi-info-circle"></i> Про систему
              </router-link>
            </li>
          </ul>

          <ul class="navbar-nav">
            <li class="nav-item" v-if="!isAuthenticated">
              <router-link class="nav-link" to="/login">
                <i class="bi bi-box-arrow-in-right"></i> Увійти
              </router-link>
            </li>
            <li class="nav-item dropdown" v-else>
              <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">
                <i class="bi bi-person-circle"></i> {{ currentUser }}
              </a>
              <ul class="dropdown-menu dropdown-menu-end">
                <li>
                  <a class="dropdown-item" href="#" @click.prevent="handleLogout">
                    <i class="bi bi-box-arrow-right"></i> Вийти
                  </a>
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <!-- toast-повідомлення -->
    <div v-if="notification" class="toast-container position-fixed top-0 end-0 p-3" style="z-index: 1100">
      <div class="toast show" :class="`text-bg-${notification.type}`" role="alert">
        <div class="toast-body">
          {{ notification.message }}
        </div>
      </div>
    </div>

    <!-- основний контент -->
    <main class="container page-container">
      <router-view />
    </main>

    <!-- футер -->
    <footer class="footer">
      <div class="container">
        © 2026 AviaCompany КНЕУ |
        ІДЗ з ООП | Литвиненко Олексій, група ІН-204
      </div>
    </footer>
  </div>
</template>

<script>
import { mapGetters, mapState } from 'vuex'

export default {
  name: 'App',
  computed: {
    ...mapGetters('auth', ['isAuthenticated', 'currentUser']),
    ...mapState('ui', ['notification'])
  },
  methods: {
    handleLogout() {
      this.$store.dispatch('auth/logout')
      this.$store.dispatch('ui/notify', { type: 'info', message: 'Ви вийшли з системи' })
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>
#app-wrapper {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}
main {
  flex: 1;
}
</style>
