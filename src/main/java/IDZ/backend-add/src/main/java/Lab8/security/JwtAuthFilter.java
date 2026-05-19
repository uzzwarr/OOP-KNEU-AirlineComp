package Lab8.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// фільтр перевіряє jwt токен у заголовку Authorization для захищених запитів
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();
        String method = request.getMethod();

        // публічні ендпоінти - пропускаємо без перевірки
        if (isPublic(path, method)) {
            filterChain.doFilter(request, response);
            return;
        }

        // витягуємо токен з заголовка
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"error\":\"Відсутній токен авторизації\"}");
            return;
        }

        String token = authHeader.substring(7);
        if (!jwtUtil.validateToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"error\":\"Невалідний або прострочений токен\"}");
            return;
        }

        // зберігаємо ім'я користувача для контролерів (якщо знадобиться)
        request.setAttribute("username", jwtUtil.extractUsername(token));
        filterChain.doFilter(request, response);
    }

    // публічними є логін, опції-преліт CORS, та GET-запити на читання даних
    private boolean isPublic(String path, String method) {
        // OPTIONS завжди дозволяємо для CORS
        if ("OPTIONS".equalsIgnoreCase(method)) return true;
        // логін - відкритий
        if (path.startsWith("/api/auth/")) return true;
        // дозволяємо GET-запити на сутності щоб список можна було дивитись без логіну
        if ("GET".equalsIgnoreCase(method) &&
                (path.startsWith("/api/flights") || path.startsWith("/api/crew"))) {
            return true;
        }
        // інші шляхи поза /api/ не потребують захисту (старі MVC)
        if (!path.startsWith("/api/")) return true;
        return false;
    }
}
