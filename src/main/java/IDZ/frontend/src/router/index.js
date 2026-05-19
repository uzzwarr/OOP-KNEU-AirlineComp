import { createRouter, createWebHistory } from 'vue-router'
import store from '../store'

// імпорти сторінок-views (lazy loaded для меншого бандлу)
const Home = () => import('../views/HomeView.vue')
const Login = () => import('../views/LoginView.vue')
const Flights = () => import('../views/FlightsView.vue')
const FlightForm = () => import('../views/FlightFormView.vue')
const Crew = () => import('../views/CrewView.vue')
const CrewForm = () => import('../views/CrewFormView.vue')
const About = () => import('../views/AboutView.vue')

const routes = [
  { path: '/', name: 'home', component: Home },
  { path: '/login', name: 'login', component: Login },
  { path: '/flights', name: 'flights', component: Flights },
  // create/edit за CRUD-потоком - вимагають логіну
  { path: '/flights/new', name: 'flight-new', component: FlightForm, meta: { requiresAuth: true } },
  { path: '/flights/:id/edit', name: 'flight-edit', component: FlightForm, props: true, meta: { requiresAuth: true } },
  { path: '/crew', name: 'crew', component: Crew },
  { path: '/crew/new', name: 'crew-new', component: CrewForm, meta: { requiresAuth: true } },
  { path: '/crew/:id/edit', name: 'crew-edit', component: CrewForm, props: true, meta: { requiresAuth: true } },
  { path: '/about', name: 'about', component: About },
  // catch-all 404
  { path: '/:pathMatch(.*)*', redirect: '/' }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// глобальний guard - блокує переходи на захищені сторінки
router.beforeEach((to, from, next) => {
  if (to.meta.requiresAuth && !store.getters['auth/isAuthenticated']) {
    next({ name: 'login', query: { redirect: to.fullPath } })
  } else {
    next()
  }
})

export default router
