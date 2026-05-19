# Як інтегрувати ІДЗ у твій існуючий OOP-KNEU-AirlineComp

## Що у цій папці

```
idz-only/
├── backend-add/src/main/java/
│   ├── Lab8/              ← нова серверна частина ІДЗ (REST API + JWT + CORS)
│   └── com/kneu/          ← новий головний клас Spring Boot
├── frontend/              ← окремий Vue 3 проект
└── AviaCompany-IDZ-Postman-collection.json
```

---

## ⚠️ Важливо: конфлікт з Lab7

У твоєму існуючому проекті Lab7 теж має `@SpringBootApplication` (`Lab7/AviaCompanyApplication.java`) і свої сутності `Flight`, `CrewMembers`, `AdminUser`. У Spring Boot може бути **тільки один** `@SpringBootApplication`. Тому обери один зі сценаріїв нижче.

---

## Сценарій A (рекомендую): тимчасово закоментувати Lab7 на час захисту ІДЗ

Найшвидший і найчистіший шлях.

### Крок 1. Скопіювати Lab8 та головний клас

Скопіюй вміст `backend-add/src/main/java/` у свій `src/main/java/` — додадуться папки:
- `Lab8/` (всі підпапки)
- `com/kneu/AviaCompanyIdzApplication.java`

### Крок 2. Тимчасово вимкнути Lab7

Відкрий `src/main/java/Lab7/AviaCompanyApplication.java` і **закоментуй анотацію** `@SpringBootApplication`:

```java
package Lab7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication  ← закоментовано на час ІДЗ
public class AviaCompanyApplication {
    public static void main(String[] args) {
        SpringApplication.run(AviaCompanyApplication.class, args);
    }
}
```

### Крок 3. Тимчасово вимкнути Lab7 контролери та DataInitializer

В цих файлах закоментуй анотації класів:
- `Lab7/controller/FlightController.java` → закоментуй `@Controller`
- `Lab7/controller/AuthController.java` → закоментуй `@Controller`
- `Lab7/DataInitializer.java` → закоментуй `@Component`

Це потрібно щоб Spring не намагався створити дві сутності `Flight` (з Lab7 і Lab8) — без активних компонентів Lab7 буде просто "мертвим" Java-кодом.

### Крок 4. Оновити `pom.xml`

Додай **JWT-залежності** у свій `pom.xml` (всередину `<dependencies>`):

```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.12.5</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.12.5</version>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.12.5</version>
    <scope>runtime</scope>
</dependency>
```

### Крок 5. Оновити `application.properties`

Додай у кінець свого `src/main/resources/application.properties`:

```properties
# JWT секретний ключ для ІДЗ
jwt.secret=Q2xhdWRlSURaQXZpYUNvbXBhbnlTZWNyZXRLZXlGb3JKV1RUb2tlbnNNdXN0QmVMb25nRW5vdWdoMjU2Yml0cyE=
jwt.expiration-ms=86400000

# відключаємо FreeMarker (бо REST API не потребує)
spring.freemarker.enabled=false
```

### Крок 6. Запуск

Запускай через **`AviaCompanyIdzApplication`** (правий клік → Run у IntelliJ), а не через `Lab7/AviaCompanyApplication`.

---

## Сценарій Б: повернутися до Lab7 після захисту

Коли потрібно знову показати Lab7 (MVC) — просто розкоментуй усі `@SpringBootApplication`, `@Controller`, `@Component` у Lab7, і навпаки закоментуй `@SpringBootApplication` у `com/kneu/AviaCompanyIdzApplication.java`. Lab8 при цьому просто стане неактивним кодом.

---

## Сценарій В: тримати обидва паралельно (продвинутий)

Можна перейменувати таблиці Lab7 (`@Table(name = "flight_lab7")` на сутностях) та залишити обидва набори активними. Але потрібно ще усунути дублікати імен бінів — це довше. Якщо не потрібно прямо зараз — пропусти.

---

## Frontend

Папка `frontend/` — окремий Vue-проект. **Її не треба інтегрувати у Java-проект**, тримай поруч (наприклад, скопіюй на робочий стіл або поряд з `OOP-KNEU-AirlineComp`).

### Запуск
```bash
cd frontend
npm install
npm run dev
```
Відкриється `http://localhost:5173`. Логін: `admin / 1234`.

---

## Послідовність перевірки

1. Закомпілюй та запусти бекенд через `AviaCompanyIdzApplication`.
2. Відкрий MySQL Workbench → переконайся що з'явились таблиці `flights`, `crew_members`, `flight_crew`, `admin_users`.
3. Імпортуй у Postman колекцію `AviaCompany-IDZ-Postman-collection.json`.
4. Виконай `POST /auth/login` → отримай токен (зберігається автоматично).
5. Виконай `GET /flights`, `POST /flights`, `PUT /flights/1`, `DELETE /flights/N` — зроби скріни.
6. Те саме для `/api/crew`.
7. Запусти Vue: `npm run dev` → залогінся → пройди CRUD-операції на обох сторінках → зроби скріни.

---

## Ендпоінти REST (для звіту)

| Метод | URL | Авторизація |
|-------|-----|-------------|
| POST | `/api/auth/login` | — |
| GET | `/api/flights` | — |
| GET | `/api/flights/{id}` | — |
| POST | `/api/flights` | JWT |
| PUT | `/api/flights/{id}` | JWT |
| DELETE | `/api/flights/{id}` | JWT |
| GET | `/api/crew` | — |
| GET | `/api/crew/{id}` | — |
| POST | `/api/crew` | JWT |
| PUT | `/api/crew/{id}` | JWT |
| DELETE | `/api/crew/{id}` | JWT |
