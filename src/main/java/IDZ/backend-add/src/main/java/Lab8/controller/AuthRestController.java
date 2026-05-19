package Lab8.controller;

import Lab8.dto.LoginRequest;
import Lab8.dto.LoginResponse;
import Lab8.entity.AdminUser;
import Lab8.repository.AdminUserRepository;
import Lab8.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

// REST-контролер авторизації - повертає jwt токен
@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    private final AdminUserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthRestController(AdminUserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    // POST /api/auth/login - вхід та видача токена
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        Optional<AdminUser> userOpt = userRepository.findByUsername(request.getUsername());

        if (userOpt.isEmpty() || !userOpt.get().getPassword().equals(request.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Невірний логін або пароль");
        }

        String token = jwtUtil.generateToken(userOpt.get().getUsername());
        return ResponseEntity.ok(new LoginResponse(token, userOpt.get().getUsername()));
    }

    // GET /api/auth/me - перевірка поточного користувача (для фронту)
    @GetMapping("/me")
    public ResponseEntity<LoginResponse> me(@RequestAttribute(value = "username", required = false) String username) {
        if (username == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        // повертаємо лише ім'я (без нового токена)
        LoginResponse response = new LoginResponse(null, username);
        return ResponseEntity.ok(response);
    }
}
