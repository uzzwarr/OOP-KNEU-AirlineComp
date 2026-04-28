package Lab7.controller;

import Lab7.AdminUser;
import Lab7.repository.AdminUserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final AdminUserRepository userRepository;

    public AuthController(AdminUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // Обробляємо дані
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        // Шукаємо користувача в базі за логіном
        AdminUser user = userRepository.findByUsername(username);

        // Перевіряємо, чи користувач існує і чи збігається пароль
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("logged_in_user", user.getUsername());
            return "redirect:/flights";
        }

        model.addAttribute("error", "Невірний логін або пароль!");
        return "login";
    }

    // Вихід з системи
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Повністю знищуємо сесію
        return "redirect:/login";
    }
}