<template>
  <div>
    <h2 class="page-title">
      <i class="bi bi-info-circle"></i> Про систему
    </h2>

    <div class="row g-4">
      <div class="col-md-8">
        <div class="card">
          <div class="card-header">Опис проєкту</div>
          <div class="card-body">
            <p>
              <strong>AviaCompany КНЕУ</strong> — навчальна інформаційна система для обліку рейсів
              та екіпажу авіакомпанії. Створена як індивідуальне домашнє завдання (ІДЗ) з курсу
              "Об'єктно-орієнтоване програмування" в КНЕУ.
            </p>
            <p>
              Студент: <strong>Литвиненко Олексій</strong>, група <strong>ІН-204</strong>.
            </p>

            <h6 class="mt-4">Архітектура</h6>
            <p>
              Застосунок побудовано за класичною клієнт-серверною архітектурою з повним розділенням:
            </p>
            <ul>
              <li>
                <strong>Backend</strong> — REST API на Spring Boot 3.2 (Java 17), з JPA/Hibernate
                для роботи з MySQL, JWT-авторизацією та валідацією даних.
              </li>
              <li>
                <strong>Frontend</strong> — SPA на Vue 3 з Composition API, Vue Router 4 для
                навігації, Vuex 4 для глобального стану, axios для HTTP-запитів.
              </li>
            </ul>

            <h6 class="mt-4">Сутності</h6>
            <ul>
              <li><strong>Flight</strong> — рейс з номером, маршрутом, часами та екіпажем.</li>
              <li><strong>CrewMember</strong> — член екіпажу з роллю, досвідом та ліцензією.</li>
              <li><strong>AdminUser</strong> — адміністратор для входу в систему.</li>
            </ul>
            <p class="small text-muted">
              Між Flight та CrewMember реалізовано зв'язок many-to-many.
            </p>

            <h6 class="mt-4">REST API ендпоінти</h6>
            <table class="table table-sm table-bordered">
              <thead class="table-light">
                <tr><th>Метод</th><th>URL</th><th>Опис</th></tr>
              </thead>
              <tbody>
                <tr><td>POST</td><td>/api/auth/login</td><td>Логін, повертає JWT</td></tr>
                <tr><td>GET</td><td>/api/flights</td><td>Список рейсів</td></tr>
                <tr><td>GET</td><td>/api/flights/{id}</td><td>Один рейс</td></tr>
                <tr><td>POST</td><td>/api/flights</td><td>Створити (JWT)</td></tr>
                <tr><td>PUT</td><td>/api/flights/{id}</td><td>Оновити (JWT)</td></tr>
                <tr><td>DELETE</td><td>/api/flights/{id}</td><td>Видалити (JWT)</td></tr>
                <tr><td>GET</td><td>/api/crew</td><td>Список екіпажу</td></tr>
                <tr><td>POST</td><td>/api/crew</td><td>Створити (JWT)</td></tr>
                <tr><td>PUT</td><td>/api/crew/{id}</td><td>Оновити (JWT)</td></tr>
                <tr><td>DELETE</td><td>/api/crew/{id}</td><td>Видалити (JWT)</td></tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>

      <div class="col-md-4">
        <div class="card mb-3">
          <div class="card-header">Backend стек</div>
          <ul class="list-group list-group-flush">
            <li class="list-group-item"><i class="bi bi-coffee-fill text-warning"></i> Java 17</li>
            <li class="list-group-item"><i class="bi bi-leaf-fill text-success"></i> Spring Boot 3.2</li>
            <li class="list-group-item"><i class="bi bi-database-fill text-primary"></i> MySQL + JPA</li>
            <li class="list-group-item"><i class="bi bi-shield-lock-fill text-info"></i> JWT (jjwt 0.12)</li>
            <li class="list-group-item"><i class="bi bi-gear-fill text-secondary"></i> Maven</li>
          </ul>
        </div>

        <div class="card">
          <div class="card-header">Frontend стек</div>
          <ul class="list-group list-group-flush">
            <li class="list-group-item"><i class="bi bi-bootstrap-fill text-success"></i> Vue 3.4</li>
            <li class="list-group-item"><i class="bi bi-arrow-repeat text-primary"></i> Vue Router 4</li>
            <li class="list-group-item"><i class="bi bi-collection-fill text-info"></i> Vuex 4</li>
            <li class="list-group-item"><i class="bi bi-cloud-arrow-down-fill"></i> axios</li>
            <li class="list-group-item"><i class="bi bi-palette-fill text-warning"></i> Bootstrap 5</li>
            <li class="list-group-item"><i class="bi bi-lightning-fill text-warning"></i> Vite</li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AboutView'
}
</script>
