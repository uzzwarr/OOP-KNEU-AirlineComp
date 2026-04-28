package Lab7.controller;

import Lab7.Flight;
import Lab7.services.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/flights")
    public String showFlightsList(Model model) {
        List<Flight> allFlights = flightService.findAll();

        model.addAttribute("flights", allFlights);

        return "flights-list";
    }

    @GetMapping("/flights/new")
    public String showCreateForm(Model model) {

        Flight flight = new Flight();
        model.addAttribute("flight", flight);

        return "create-flight";
    }

    @PostMapping
    public String saveFlight(@ModelAttribute Flight flight) {
        // віддаємо заповнений об'єкт сервісу для збереження в базу
        flightService.save(flight);

        // Перенаправлення на головну сторінку з таблицею
        return "redirect:/flights";
    }


    @GetMapping("/flights/delete/{id}")
    public String deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);

        // повертаємо користувача назад до загальної таблиці
        return "redirect:/flights";
    }

    @GetMapping("/flights/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Flight flight = flightService.getFlightById(id);
        model.addAttribute("flight", flight);

        return "create-flight";
    }
}
